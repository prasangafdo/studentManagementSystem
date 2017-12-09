<?php
require 'conn.php';
$username = $_POST['user_name'];
$password = $_POST['password'];

//echo "Login success";
// /echo $username."<br/>".$password;

$sql = "select * from login_details where username = '$username' && user_Password ='$password'";

$result = mysqli_query($conn ,$sql);
if(mysqli_num_rows($result) > 0) {
echo "Login success!";
}
else {
echo "Failed";
}
?>