<?php
require 'conn.php';
$firstName = $_POST['firstName'];
$lastName = $_POST['lastName'];
$address = $_POST['address'];
$dateOfBirth = $_POST['dateofBirth'];
$grade = $_POST['grade'];
$parentNum = $_POST['parentNum'];

//echo $grade;
//echo  $parentNum;

$insert_to_users ="INSERT INTO user_details (first_Name, last_Name, user_Address, date_Of_Birth) VALUES('$firstName', '$lastName', '$address', '$dateOfBirth')";

mysqli_query($conn, $insert_to_users);


$select_UID = "SELECT user_ID FROM user_details where first_Name = '$firstName' AND last_Name ='$lastName' AND user_Address = '$address' AND date_Of_Birth = '$dateOfBirth'";//Selecting grade

$result11 = mysqli_query($conn, $select_UID);

if (mysqli_num_rows($result11) > 0) {
    
	  $row = mysqli_fetch_assoc($result11);
     $student_UID = $row["user_ID"];
    // echo $student_UID;


	$insert_Student = "INSERT INTO student(user_ID, grade, parent_Contact_Num) VALUES ('$student_UID', '$grade', '$parentNum')"; 
	
	if (mysqli_query($conn, $insert_Student)) {
		//echo  "Insert student success";

	 $select_Student_ID = "SELECT student_ID FROM student where parent_Contact_Num = '$parentNum'";//Getting student_ID

	 $result = mysqli_query($conn, $select_Student_ID);



		if (mysqli_num_rows($result) > 0) {
		    // output data of each row

			  $row = mysqli_fetch_assoc($result);
		      $student_ID = $row["student_ID"];
		     // echo $student_ID;

	$register_Student = "INSERT INTO student_registration (student_ID, is_registered) VALUES ('$student_ID', 1)";
	$user_Role = "INSERT INTO user_Role (user_ID, user_Role) VALUES ('$student_UID', 'student')";
	$nullFees ="INSERT INTO term_fees (student_ID, is_paid, paid_date, next_due_date, fee) VALUES ('$student_ID', 0, NULL, NULL, 0)"; 

  
	mysqli_query($conn, $register_Student);
	mysqli_query($conn, $user_Role);
  	if(mysqli_query($conn, $nullFees)){
   	echo "User registration completed. student ID is: ".$student_ID;
	   }
	}

}
else
	echo "Failed";

}
 else {
	
echo "Something went wrong. Please recheck your details";

}

?>