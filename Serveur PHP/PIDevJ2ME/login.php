 <?php
require_once('connect.php');

mysql_select_db($database_localhost,$con);

$log=$_GET['login'];
$pass=$_GET['password'];


$select = "SELECT * FROM utilisateurs u,client c where u.id=c.id and (u.nom='".$log."' and u.password='".$pass."') or (u.email='".$log."' and u.password='".$pass."')";
$result = mysql_query($select) or die ('Erreur : '.mysql_error() );
$total = mysql_num_rows($result);

if($total) {
	$test=false;
	while($row = mysql_fetch_array($result)) 
	{		
				$test=true;			
	}
	if($test){
		echo "successfully";
	}
	

}
else
{
	echo "Erreur";
	mysql_free_result($result);
}


?>