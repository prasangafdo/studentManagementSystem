<?php
//require 'setUnpaidStudentID.php';
require 'conn.php';
$SID = $_POST['student_ID'];

//$query = "SELECT student.student_ID, monthly_fees.next_due_date from student INNER JOIN monthly_fees on student.student_ID = monthly_fees.student_ID WHERE student.student_ID =$SID";
//Copied from studentID... need to add current date

/*
$query = "SELECT user_details.first_Name,
user_details.last_Name, 
user_details.user_Address, 
student.grade, 
student.parent_Contact_Num,   
term_fees.next_due_date
FROM user_details
INNER JOIN student 
ON user_details.student_ID=student.student_ID 
INNER JOIN term_fees
ON term_fees.student_ID = student.student_ID 
WHERE term_fees.next_due_date = CURDATE() AND ";//Contains two join queries
*/

/*$query = "SELECT user_details.first_Name,
user_details.last_Name, 
user_details.user_Address, 
student.grade, 
student.parent_Contact_Num,   
term_fees.next_due_date
FROM user_details
INNER JOIN student 
ON user_details.student_ID=student.student_ID 
INNER JOIN term_fees
ON term_fees.student_ID = student.student_ID 
WHERE term_fees.next_due_date = CURDATE()";// AND student.student_ID =$SID";
*/

$query = "SELECT user_details.first_Name,
user_details.last_Name, 
user_details.user_Address, 
student.grade, 
student.parent_Contact_Num,   
term_fees.next_due_date
FROM user_details
INNER JOIN student 
ON user_details.user_ID=student.user_ID 
INNER JOIN term_fees
ON term_fees.student_ID = student.student_ID 
WHERE term_fees.next_due_date = CURDATE() AND student.student_ID =$SID";

//This is the second query that need to execute

$result = mysqli_query($conn, $query);


if ($result) {
	while ($row=mysqli_fetch_array($result)) {
		$aa[] = $row;
	}
	print(json_encode($aa));


//Correct Query
	/*SELECT student.student_ID, monthly_fees.next_due_date from student INNER JOIN monthly_fees on student.student_ID = monthly_fees.student_ID WHERE monthly_fees.next_due_date = CURDATE();

*/
}
?>