<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();

 error_reporting(E_ALL ^ E_DEPRECATED);
 
// get all products from products table
$result = mysql_query("SELECT * FROM bins") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["bins"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["Id"] = $result["Id"];
            $product["Fill"] = $result["Fill"];
            $product["Latitude"] = $result["Latitude"];
            $product["Longitude"] = $result["Longitude"];
            $product["created_at"] = $result["created_at"];
            $product["updated_at"] = $result["updated_at"];
 
        // push single product into final response array
        array_push($response["bins"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>