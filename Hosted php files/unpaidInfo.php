<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
<form method="POST" action="getUnpaidStudentInfo.php">
	<input type="type" name="student_ID">
	<input type="submit" name="">
</form>

<?php
session_start();
require 'conn.php';
//$_SESSION['student_ID'] = 30;

?>
</body>
</html>