package com.fdo.prasanga.studentmanagementsystem;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Fees2Activity extends AppCompatActivity {
    Button btn_completePayment, btn_generateInvoice;
    TextView tv_CurrentDate, tv_grade, tv_Fee, tv_studentID;
    DatePicker dtp_calendar;
    String StudentID;

    private static final String TAG = "PdfCreatorActivity";
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees2);

        btn_completePayment = (Button) findViewById(R.id.btn_completePaytment);
        btn_generateInvoice =(Button) findViewById(R.id.btn_generateInvoice);
        tv_CurrentDate = (TextView) findViewById(R.id.tv_CurrentDate);

        tv_grade = (TextView) findViewById(R.id.tv_grade);
        tv_Fee = (TextView) findViewById(R.id.tv_Fee);
        tv_studentID = (TextView) findViewById(R.id.tv_studentID);
        dtp_calendar = (DatePicker) findViewById(R.id.dtp_Calendar);
        btn_generateInvoice.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            createPdfWrapper();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
});




        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        tv_CurrentDate.setText(date);//Setting the current date


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Fees2Activity.this);//Retrieving all saved data.
        String student_ID = preferences.getString("Student_ID", null);
        String grade = preferences.getString("grade", "def");//def is the default value

        tv_grade.setText(grade);
        tv_studentID.setText(student_ID);

        if (Integer.parseInt(grade)>5){//Calculating fees
            tv_Fee.setText("4000.00");

        }
        else
        {
            tv_Fee.setText("3000.00");
        }

      btn_completePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CalendarDate date = new CalendarDate();//Generating date from the subclass
                date.setDate(Integer.toString(dtp_calendar.getYear()),Integer.toString(dtp_calendar.getMonth()+1), Integer.toString(dtp_calendar.getDayOfMonth()));

                String type = "updateFees";
                String studentID = tv_studentID.getText().toString();
                String next_Date = date.getDate();
                String current_Date = tv_CurrentDate.getText().toString();
                String fee = tv_Fee.getText().toString();
                //Calculate fees and send

                Toast.makeText(getApplicationContext(),"St: "+studentID+" N Date: "+next_Date+ "C date: "+current_Date, Toast.LENGTH_LONG).show();

                BackgroundWorker backgroundWorker = new BackgroundWorker(Fees2Activity.this);
                backgroundWorker.execute(type,studentID, next_Date, current_Date, fee);//Put strings to execute
            }
        });

    }

    private void createPdfWrapper() throws FileNotFoundException, DocumentException {

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        } else {
            createPdf();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }

        pdfFile = new File(docsFolder.getAbsolutePath(), "HelloWorld.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        document.add(new Paragraph("Student ID: "));//Here we include the data we want to put into the pdf file
        document.add(new Paragraph("Grade: "));
        document.add(new Paragraph("Paid Date: "));
        document.add(new Paragraph("Paid Amount: "));
        document.add(new Paragraph("Next Payment Date: "));

        document.close();
        previewPdf();

    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        } else {
            Toast.makeText(this, "Download a PDF Viewer to see the generated PDF", Toast.LENGTH_SHORT).show();
        }
    }
}
