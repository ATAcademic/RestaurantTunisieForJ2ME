 <?php
require_once('connect.php');

mysql_select_db($database_localhost,$con);

$date=$_GET['date'];
$place=$_GET['place'];
$placee=intval($place); 
$prix=$_GET['prix'];
$prixx=floatval($prix); 
$id=$_GET['id'];
$idresto=intval($id); 
$idclient=$_GET['id_cl'];
$id_client=intval($idclient); 
$e=$_GET['etat'];

$sql=mysql_query("INSERT INTO reservation VALUES ('','$date','$placee','$prixx','$id_client','$idresto','$e') ");
if ($sql) {
	echo "successfully added";
}
else
{
	echo "erreur";
}

?>