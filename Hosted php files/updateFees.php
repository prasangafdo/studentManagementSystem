<?php
require 'conn.php';
$student_ID = $_POST['studentID'];
$next_Date = $_POST['next_Date'];
$current_Date = $_POST['current_Date'];
$fee = $_POST['fee'];

$next_due_date = date('Y-m-d', strtotime($next_Date)); //Converting current date to mysql date time 
$paid_date = date('Y-m-d', strtotime($current_Date));


 $update_fees = "UPDATE term_fees
 SET is_paid = 1, 
 paid_date = '$paid_date', 
 next_due_date ='$next_due_date',
 fee = '$fee' 
 WHERE student_ID = '$student_ID'"; 

if(mysqli_query($conn, $update_fees)){
	echo "Updated";
}
else
echo "Update failed";

/*
  echo $student_ID."<br/>";
  echo $next_due_date."<br/>";
  echo $paid_date."<br/>";
  echo $fee."<br/>";
  echo "<br/> This is update fees";
*/
//File is working. Need to add the update query


?>