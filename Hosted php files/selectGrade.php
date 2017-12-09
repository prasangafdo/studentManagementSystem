<?php
require 'conn.php';
$student_ID = $_POST['studentID'];

$select_grade = "SELECT grade FROM student where student_ID = '$student_ID'";//Selecting grade

$result = mysqli_query($conn, $select_grade);

if (mysqli_num_rows($result) > 0) {
    
	  $row = mysqli_fetch_assoc($result);
     $student_Grade = $row["grade"];
     echo $student_Grade;
 }
?>