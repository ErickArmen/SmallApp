<?php 
    include "../../inc/bpinfo.inc"; 
?>

<?php

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require_once "../../../vendor/autoload.php";

$response = array();

if(isset($_POST['receiver']) ){

    $receiver = $_POST['receiver'];

    $mail = new PHPMailer(true);                 
    try {
        $mail->CharSet = 'UTF-8';                                   
        $mail->isSMTP();                                     
        $mail->Host = 'smtp.gmail.com';  
        $mail->SMTPAuth = true;                              
        $mail->Username = EMAIL;                 
        $mail->Password = PASS;                           
        $mail->SMTPSecure = 'tls';                            
        $mail->Port = 587;                                  

        $mail->setFrom(EMAIL);
        $mail->addAddress($receiver); 
        
        $mail->isHTML(true);                                  
        $mail->Subject = 'Message from SmallApp';

        $htmlBody = "<p><b> Message from SmallApp: </b><br><br> This message was sent with SmallApp. Don't bother to answer.<br><br> 
        Regards,<br><br>SmallApp</p>";

        $mail->Body = $htmlBody;

        $mail->send();

        $response["success"] = 0;
        $response["message"] = "Email Sent!";
        echo json_encode($response);
    } catch (Exception $e) {
        $response["success"] = 1;
        $response["message"] = "Message could not be sent!";

        echo json_encode($response);
    }
}else {
    // required field is missing
    $response["success"] = 2;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}

?>