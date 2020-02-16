<html>
   <body>
   <h1>SOAP</h1>
   <h3>Enter IBAN</h3>  
<form method="post" action="index.php">    
    <textarea  type="text" name="IBANumber" rows="10" cols="30"></textarea>  
    <br>
    <input type="submit" value="Test IBAN" name="submit"> 
</form>
   </body>
</html>



<?php

function FormatText(){

  $text = $_POST["IBANumber"];
 
   foreach(preg_split("/((\r?\n)|(\r\n?))/", $text) as $line){
    display($line);
   
} 
}


function display($number)
{ 
    try{
     
  
    $xml_data = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:iban="http://iban.com/iban">
    <soapenv:Header/>
    <soapenv:Body>
        <iban:getIbanRequest>
          <iban:Iban>'.$number.'</iban:Iban>
       </iban:getIbanRequest>
    </soapenv:Body>
    </soapenv:Envelope>';
    $URL = "http://localhost:8080/ws";
    
    $ch = curl_init($URL);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: text/xml'));
    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, "$xml_data");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    $response = curl_exec($ch);
    curl_close($ch);
    
    
    $xml = new  SimpleXMLElement($response);
    $xml->registerXPathNamespace("ns", "http://iban.com/iban");
    $status = $xml->xpath('//ns:IBANumber');
    $status1 = $xml->xpath('//ns:isValid');

    if($status != null && $status1 != null ){
      $status = (string)  $status[0];
      $status1 = (string)  $status1[0];
      print($status);
      echo '<br>';
      print($status1);
      echo '<br>';
    }
    
    
    }catch(Exception $e)
    {
      echo  $e->getMessage();
    }

}

if(isset($_POST['submit']))
{
 
FormatText();

} 
?>
