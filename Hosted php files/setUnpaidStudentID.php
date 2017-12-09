<?php
session_start();
require 'conn.php';
$student_ID = $_POST['student_ID'];
//$student_ID = '30';
$_SESSION['student_ID'] = 30;
echo json_encode($student_ID);

?>