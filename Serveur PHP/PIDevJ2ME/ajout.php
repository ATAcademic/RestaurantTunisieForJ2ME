 <?php
require_once('connect.php');  // kima el include ou importe ki nsta7a9 bech nconnecti 

mysql_select_db($database_localhost,$con);

 $com=$_GET['com'];
 $resto=$_GET['resto'];
 $client=$_GET['client'];

mysql_query("INSERT INTO commentaire VALUES ('','$com','$client','$resto') ");
echo "successfully added";
?>