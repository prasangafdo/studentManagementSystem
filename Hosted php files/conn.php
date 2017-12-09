<?php
define('db_server', 'localhost');
define('db_username', 'id2343550_prabodha');
define('db_password', 'lykan');
define('db_name', 'id2343550_db1');

$conn = mysqli_connect(db_server, db_username, db_password, db_name);

if(!$conn){
	echo "Error";
}
else
//echo "DB connection success! This is student management system";
/*

//This will establish the connection with the database.
$db_name = "id2343550_db1";
$mysql_username = "id2343550_prasanga";
$mysql_password = "Lykan";
$server_name = "localhost";
$con = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

if($con){
echo "DB connection success!";
}

else
echo "Connection failed!";
*/

?>