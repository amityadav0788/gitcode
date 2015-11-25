<?php
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
/*  Selection of points within specified radius of given lat/lon      (c) Chris Veness 2008-2014  */
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */

    //require 'inc/dbparams.inc.php';  // defines $dsn, $username, $password
	//$mysqli = new mysqli('127.0.0.1','root','','binapp');
    //$db = new PDO($dsn, $username, $password);
	$db = new PDO('mysql:host=localhost;dbname=binapp','root','');
    $db->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_OBJ);

    $lat = $_GET['lat']; // latitude of centre of bounding circle in degrees
    $lon = $_GET['lon']; // longitude of centre of bounding circle in degrees
    $rad = $_GET['rad']; // radius of bounding circle in kilometers

    $R = 6371;  // earth's mean radius, km

    // first-cut bounding box (in degrees)
    $maxLat = $lat + rad2deg($rad/$R);
    $minLat = $lat - rad2deg($rad/$R);
    // compensate for degrees longitude getting smaller with increasing latitude
    $maxLon = $lon + rad2deg($rad/$R/cos(deg2rad($lat)));
    $minLon = $lon - rad2deg($rad/$R/cos(deg2rad($lat)));

    $sql = "Select Id, Latitude,Longitutde, Fill, Location,
                acos(sin(:lat)*sin(radians(Latitude)) + cos(:lat)*cos(radians(Latitude))*cos(radians(Longitutde)-:lon)) * :R As D
            From (
                Select Id, Fill, Latitude, Longitutde, Location
                From bins
                Where Latitude Between :minLat And :maxLat
                  And Longitutde Between :minLon And :maxLon
            ) As FirstCut
            Where acos(sin(:lat)*sin(radians(Latitude)) + cos(:lat)*cos(radians(Latitude))*cos(radians(Longitutde)-:lon)) * :R < :rad
            Order by D";
    $params = array(
        'lat'    => deg2rad($lat),
        'lon'    => deg2rad($lon),
        'minLat' => $minLat,
        'minLon' => $minLon,
        'maxLat' => $maxLat,
        'maxLon' => $maxLon,
        'rad'    => $rad,
        'R'      => $R,
    );
    $points = $db->prepare($sql);
    $points->execute($params);
	
	$myArray = array();
	$myArray["bins"] = array();
    while($row = $points->fetch(PDO::FETCH_ASSOC)) {
            array_push($myArray["bins"], $row);
    }
	$myArray["success"]=1;
    echo json_encode($myArray);
?>
