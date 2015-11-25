<?php
class TSP {


	private $locations 	= array();		// all locations to visit
	private $longitudes = array();
	private $latitudes 	= array();
	private $shortest_route = array();	// holds the shortest route
	private $shortest_routes = array();	// any matching shortest routes
	private $shortest_distance = 0;		// holds the shortest distance
	private $all_routes = array();		// array of all the possible combinations and there distances
	private $adj_matrix = array();

	// add a location
	public function add($name,$longitude,$latitude){
		$this->locations[$name] = array('longitude'=>$longitude,'latitude'=>$latitude);
	}
	
	public function make_matrix($locations)
	{
		
		foreach ($locations as $location=>$coords){
			$this->longitudes[$location] = $coords['longitude'];
			$this->latitudes[$location] = $coords['latitude'];
		}
		
		foreach (array_keys($locations) as $row) {
			foreach (array_keys($locations) as $col)
			{
				$adj_matrix[$row][$col] = $this->distance($this->latitudes[$row],$this->longitudes[$row],$this->latitudes[$col],$this->longitudes[$col]);
			}
        }
	
		echo "<br>serialize values adj matrix in make matrix<br>";
		print_r(array_values($adj_matrix));
		echo "<br><br>";
		echo "<br>serialize keys adj matrix in make matrix<br>";
		print_r(array_keys($adj_matrix));
		echo "<br><br>";
	}
	// the main function that des the calculations
	public function compute(){
		$locations = $this->locations;

		//$this->make_matrix($locations);
		echo "serialize locations in compute<br>";
	echo serialize($locations);
	
	$this->make_matrix($locations);
	
		/*foreach ($locations as $location=>$coords){
			$this->longitudes[$location] = $coords['longitude'];
			$this->latitudes[$location] = $coords['latitude'];
		}
		$locations = array_keys($locations);

		$this->all_routes = $this->array_permutations($locations);


		foreach ($this->all_routes as $key=>$perms){
			$i=0;
			$total = 0;
			foreach ($perms as $value){
				if ($i<count($this->locations)-1){
					$total+=$this->distance($this->latitudes[$perms[$i]],$this->longitudes[$perms[$i]],$this->latitudes[$perms[$i+1]],$this->longitudes[$perms[$i+1]]);
				}
				$i++;
			}
			$this->all_routes[$key]['distance'] = $total;
			if ($total<$this->shortest_distance || $this->shortest_distance ==0){
				$this->shortest_distance = $total;
				$this->shortest_route = $perms;
				$this->shortest_routes = array();
			}
			if ($total == $this->shortest_distance){
				$this->shortest_routes[] = $perms;
			}
		}*/
	}
	// work out the distance between 2 longitude and latitude pairs
	function distance($lat1, $lon1, $lat2, $lon2) { 
		if ($lat1 == $lat2 && $lon1 == $lon2) return 0;
		$unit = 'M';	// miles please!
		$theta = $lon1 - $lon2; 
		$dist = sin(deg2rad($lat1)) * sin(deg2rad($lat2)) +  cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * cos(deg2rad($theta)); 
		$dist = acos($dist); 
		$dist = rad2deg($dist); 
		$miles = $dist * 60 * 1.1515;
		$unit = strtoupper($unit);


		if ($unit == "K") {
			return ($miles * 1.609344); 
		} else if ($unit == "N") {
			return ($miles * 0.8684);
		} else {
			return $miles;
		}
	}
	// work out all the possible different permutations of an array of data
	private function array_permutations($items, $perms = array( )) {
		static $all_permutations;
		if (empty($items)) {
			$all_permutations[] = $perms;
		}  else {
			for ($i = count($items) - 1; $i >= 0; --$i) {
				$newitems = $items;
				$newperms = $perms;
				list($foo) = array_splice($newitems, $i, 1);
				array_unshift($newperms, $foo);
				$this->array_permutations($newitems, $newperms);
			}
		}
		return $all_permutations;
	}
	// return an array of the shortest possible route
	public function shortest_route(){
		return $this->shortest_route;
	}
	// returns an array of any routes that are exactly the same distance as the shortest (ie the shortest backwards normally)
	public function matching_shortest_routes(){
		return $this->shortest_routes;
	}
	// the shortest possible distance to travel
	public function shortest_distance(){
		return $this->shortest_distance;
	}
	// returns an array of all the possible routes
	public function routes(){
		return $this->all_routes;
	}
}

$tsp = new TSP;

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

    $sql = "Select Id, Latitude,Longitutde, Fill,
                acos(sin(:lat)*sin(radians(Latitude)) + cos(:lat)*cos(radians(Latitude))*cos(radians(Longitutde)-:lon)) * :R As D
            From (
                Select Id, Fill, Latitude, Longitutde
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

	//$myArray = array();
	//$myArray["bins"] = array();
	//$tsp->add("Source",$lat,$lon);    trying it for ssource
    while($row = $points->fetch(PDO::FETCH_ASSOC)) {
            //array_push($myArray["bins"], $row);
			$tsp->add($row["Id"],$row["Latitude"],$row["Longitutde"]);
			print $row["Id"] . "\t";
    }

	
//$tsp->add('test1',50.413708,-3.083364);

//$tsp->add('london',51.500152,-0.126236);

//$tsp->add('birmingham',52.483003,-1.893561);

//$tsp->add('manchester',53.480712,-2.234377);

//$tsp->add('newquay',50.413608,-5.083364);

$tsp->compute();



//echo 'Shortest Distance: '.$tsp->shortest_distance();

//echo '<br />Shortest Route: ';

print_r($tsp->shortest_route());

//echo '<br />Num Routes: '.count($tsp->routes());

//echo '<br />Matching shortest Routes: ';

//print_r($tsp->matching_shortest_routes());

//echo '<br />All Routes: ';

//print_r($tsp->routes());
?>