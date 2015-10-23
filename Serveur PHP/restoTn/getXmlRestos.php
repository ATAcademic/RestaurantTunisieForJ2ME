<?php   
      require_once('connect.php'); 
      $patern= '/[0-9]+.[0-9]+:[0-9]+.[0-9]+/';
      mysql_select_db($database_localhost,$con);  
      $query_search = "SELECT DISTINCT restaurant.id id, restaurant.nom nom, AVG(evaluation.note) moyen FROM  restaurant, evaluation where restaurant.id = evaluation.id_receiver group by restaurant.id";  
      $query_exec = mysql_query($query_search) or die(mysql_error());  
      
if($query_exec!=null){  
      $xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
      $root_element = "restaurant"; 
      $xml .= "<$root_element>";
	while($result_array = mysql_fetch_assoc($query_exec))
     {
 
      foreach($result_array as $key => $value)
      {
         //$key holds the table column name
         $xml .= "<$key>";
 
         //embed the SQL data in a CDATA element to avoid XML entity issues
         $xml .= "<![CDATA[$value]]>"; 
 
         //and close the element
         $xml .= "</$key>";
      }
 
 
   }
//close the root element
$xml .= "</$root_element>";
 
//send the xml header to the browser
header ("Content-Type:text/xml"); 
 
//output the XML data
echo $xml;
 }  
 ?>  