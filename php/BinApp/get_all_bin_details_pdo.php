<?php
$mysqli = new mysqli('127.0.0.1','root','','binapp');
$myArray = array();
if ($result = $mysqli->query("SELECT * FROM bins")) {
	$myArray["bins"] = array();
    while($row = $result->fetch_array(MYSQL_ASSOC)) {
            array_push($myArray["bins"], $row);
    }
	$myArray["success"]=1;
    echo json_encode($myArray);
}

$result->close();
$mysqli->close();
?>