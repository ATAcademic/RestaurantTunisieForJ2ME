 <?php
require_once('connect.php');  // kima el include ou importe ki nsta7a9 bech nconnecti 

mysql_select_db($database_localhost,$con);

 $note=$_GET['note'];
 $resto=$_GET['resto'];
 $client=$_GET['client'];



 //$time=time();
$time=date('Y-m-d H:i:s');


mysql_query("INSERT INTO evaluation VALUES ('','$time','$note','$client','$resto') ");
header("Content-Type: plain/text"); 
echo "successfully added";
?>