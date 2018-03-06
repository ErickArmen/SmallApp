<?php 
    include "../../inc/bpinfo.inc"; 
?>


<?php

  $response = array();

  if ( isset($_POST['user']) && isset($_POST['pass']) ) {

     $clientUser = $_POST['user'];
     $clientPass = $_POST['pass'];

     $endUser = END_USER;
     $endPass = END_PASS;

     if($clientUser == $endUser && $clientPass == $endPass){
         $response["success"] = 0;
         $response["message"] = "Welcome!";

     }else if($clientUser != $endUser){
         $response["success"] = 1;
         $response["message"] = "Wrong User!";

     }else if($clientPass != $endPass){
         $response["success"] = 2;
         $response["message"] = "Wrong Password!";
     }

     echo json_encode($response);

  }else{
      $response["success"] = 3;
      $response["message"] = "Missing Fields";

      echo json_encode($response);
  }
?>