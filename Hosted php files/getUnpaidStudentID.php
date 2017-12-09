<?php
require 'conn.php';
//$date = $_POST['date'];

//$today = date('Y-m-d', strtotime($date)); //Converting current date to mysql date time 
/*$student_ID = $_POST['studentID'];

$select_grade = "SELECT grade FROM student where student_ID = '$student_ID'";//Selecting grade

$result = mysqli_query($conn, $select_grade);

if (mysqli_num_rows($result) > 0) {
    
	  $row = mysqli_fetch_assoc($result);
     $student_Grade = $row["grade"];
     echo $student_Grade;
 }*/

 //Make changes

/* $select_Student_ID = "SELECT student_ID FROM monthly_fees where next_due_date ='$today'";

$result = mysqli_query($conn, $select_Student_ID);
if (mysqli_num_rows($result) > 0) {
    
	  $row = mysqli_fetch_assoc($result);
     $student_Grade = $row["student_ID"];
     echo $student_Grade;
 }
*/

//$query = "SELECT * FROM monthly_fees ";

$query = "SELECT student.student_ID, term_fees.next_due_date from student INNER JOIN term_fees on student.student_ID = term_fees.student_ID WHERE term_fees.next_due_date = CURDATE()";

/*$query = " SELECT users.first_Name,
users.last_Name, 
users.user_Address, 
student.grade, 
student.parent_Contact_Num,   
monthly_fees.next_due_date
FROM users
INNER JOIN student 
ON users.student_ID=student.student_ID 
INNER JOIN monthly_fees
ON monthly_fees.student_ID = student.student_ID";//Contains two join queries
//This is the second query that need to execute
*/
$result = mysqli_query($conn, $query);


if ($result) {
	while ($row=mysqli_fetch_array($result)) {
		$aa[] = $row;
	}
	print(json_encode($aa));

//echo $date;
//Correct Query
	/*SELECT student.student_ID, monthly_fees.next_due_date from student INNER JOIN monthly_fees on student.student_ID = monthly_fees.student_ID WHERE monthly_fees.next_due_date = CURDATE();

*/
}
?>