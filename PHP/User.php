<?php

$response = array();
$response["name"] = "Erick";
$response["apellido_paterno"] = "Martínez";
$response["apellido_materno"] = " ";
$response["edad"] = 27;
$response["hobbies"] = array("Code", "Learn new things", "Talk", "Videogames");

echo json_encode($response);

?>