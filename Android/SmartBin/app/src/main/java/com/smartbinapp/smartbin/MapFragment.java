package com.smartbinapp.smartbin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Priti_Parate on 9/24/2015.
 */
public class MapFragment extends Fragment implements View.OnClickListener {

    MapView mapView;
    GoogleMap map;

    //BinInfo allBinsInfo = BinInfo.getInstance();

    private static final String TAG_LATITUDE = "Latitude";
    private static final String TAG_LONGITUDE = "Longitude";
    private static final String TAG_PID = "Id";
    private static final String TAG_Fill = "Fill";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ROUTES = "shortest_route";

    //private static String url_shortest_route = "http://100.66.41.139:80/binapp/bins_tsp.php?lat=12.9767&lon=77.5767&rad=20";
    private static String url_shortest_route = "http://192.168.0.4/binapp/bins_tsp.php";
    JSONArray routes=null;

    private static final LatLng Bangalore1 = new LatLng(12.9667, 77.5667);

    private static final LatLng Bangalore2 = new LatLng(12.8667, 77.5767);
    private static final LatLng Bangalore3 = new LatLng(12.9667, 77.5867);



    double coordinates[][] = {{12.9667,77.5667},{12.8667,77.5767},{12.9667,77.5867}};


    final String TAG = "PathGoogleMapActivity";

    private GetOptimalRoute optRout = new GetOptimalRoute();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        ((LaunchActivity) getActivity()).getSupportActionBar().setTitle(R.string.Map_Fragment);
        View v = inflater.inflate(R.layout.map, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();

//        if(LaunchActivity.data_for_search){
//            LaunchActivity.buildDialogAsSearched(getActivity()).show();
//        }

        getBinsPositionOnMap();

        if(routes==null)
            optRout.execute();

        ImageButton routeBtn = (ImageButton) v.findViewById(R.id.optimal_route_btn);
        routeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((LaunchActivity) getActivity()).getSupportActionBar().setTitle(R.string.Map_Route);


                Log.d("routes ", routes.toString());
                // Loading bins in Background Thread


                if(routes!=null){
//                    for(int i=0;i<routes.length()-2;i++) {

                        try {
//                            JSONObject sourceObj = BinInfo.bins.getJSONObject(routes.getInt(0));
//                            JSONObject destObj = BinInfo.bins.getJSONObject(routes.getInt(1));
//
//                            LatLng source = new LatLng(Double.parseDouble(sourceObj.getString(TAG_LATITUDE)), Double.parseDouble(sourceObj.getString(TAG_LONGITUDE)));
//                            LatLng dest = new LatLng(Double.parseDouble(destObj.getString(TAG_LATITUDE)),Double.parseDouble(destObj.getString(TAG_LONGITUDE)));
//                            String url = getMapsApiDirectionsUrl(source, dest);
//                            Log.d(TAG, "hehehe in oncreate " + 0+" "+url);
//                            ReadTask downloadTask = new ReadTask();
//                            downloadTask.execute(url);

                            for(int i=0;i<routes.length()-1;i++) {

                                Log.d(TAG, "i _ i+1 " + i + " " + routes.getInt(i)+ " _ "+ routes.getInt(i+1));

                                JSONObject sourceObj = LaunchActivity.bins.getJSONObject(routes.getInt(i));
                                LatLng source = new LatLng(Double.parseDouble(sourceObj.getString(TAG_LATITUDE)), Double.parseDouble(sourceObj.getString(TAG_LONGITUDE)));

                                JSONObject destObj = LaunchActivity.bins.getJSONObject(routes.getInt(i+1));
                                LatLng dest = new LatLng(Double.parseDouble(destObj.getString(TAG_LATITUDE)),Double.parseDouble(destObj.getString(TAG_LONGITUDE)));

                                Log.d(TAG, "source _ dest " + i + " " + source.toString()+ " _ "+ dest.toString());

//                                LatLng source = new LatLng(coordinates[i][0], coordinates[i][1]);
//                                LatLng dest = new LatLng(coordinates[i + 1][0], coordinates[i + 1][1]);

                                String url = getMapsApiDirectionsUrl(source, dest);
                                Log.d(TAG, "hehehe in oncreate " + i + " " + url);
                                ReadTask downloadTask = new ReadTask();
                                downloadTask.execute(url);

                                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(Bangalore1, 13));
                                getBinsPositionOnMap();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



//                    }




                    }

            }
        });

        return v;
    }

    private void addMarkers() {
        if (map != null) {
            map.addMarker(new MarkerOptions().position(Bangalore1)
                    .title("First Point"));
            map.addMarker(new MarkerOptions().position(Bangalore2)
                    .title("Second Point"));
            map.addMarker(new MarkerOptions().position(Bangalore3)
                    .title("Third Point"));
            Log.d(TAG, "hehehe " + " in markup");
        }
    }



    public void getBinsPositionOnMap (){

        try {

            //allBinsInfo.setContext(getActivity().getBaseContext());//need to recheck
            //allBinsInfo.getAllRegBins();

            while(LaunchActivity.bins==null){}

            for (int i = 0; LaunchActivity.bins!=null && i < LaunchActivity.bins.length(); i++) {

                JSONObject c = LaunchActivity.bins.getJSONObject(i);
                if(c!=null) {
                    float currFill = Float.parseFloat(c.getString(TAG_Fill));
                    if(currFill <= 40){

                        map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(c.getString(TAG_LATITUDE)), Double.parseDouble(c.getString(TAG_LONGITUDE))))
                                        .draggable(false)
                                        .title("Fill:" + currFill + " Humidity  Temperature")
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        );

                    }
                    else if (currFill > 40 && currFill <=75)
                    {
                        map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(c.getString(TAG_LATITUDE)), Double.parseDouble(c.getString(TAG_LONGITUDE))))
                                        .draggable(false)
                                        .title("Bin" + i + " Fill:" + currFill + " Humidity  Temperature")
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                        );

                    }
                    else
                    {
                        map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(c.getString(TAG_LATITUDE)), Double.parseDouble(c.getString(TAG_LONGITUDE))))
                                        .draggable(false)
                                        .title("Fill:" + currFill + " Humidity  Temperature")
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        );

                    }

                }
            }

            //map.addMarker(new MarkerOptions().position(new LatLng(12.9718915, 77.6411545)));

            //map.getUiSettings().setMyLocationButtonEnabled(false);
            map.setMyLocationEnabled(true);
            CameraUpdate cameraUpdate=CameraUpdateFactory.newLatLngZoom(Bangalore1,13); // get center position and zoom level
            MapsInitializer.initialize(this.getActivity());

            map.animateCamera(cameraUpdate);
        } catch (JSONException e) {
            e.printStackTrace();
        }




        //draw path

//            int size_coordinates = coordinates.length;


//            for(int i=0;i<size_coordinates-1;i++) {
//                LatLng source = new LatLng(coordinates[i][0],coordinates[i][1]);
//                LatLng dest = new LatLng(coordinates[i+1][0],coordinates[i+1][1]);;
//                //String url = getMapsApiDirectionsUrl(source, dest);
//    //            Log.d(TAG, "hehehe in oncreate " + i + " " + url);
//    //            ReadTask downloadTask = new ReadTask();
//    //            downloadTask.execute(url);
//
////                cameraUpdate = CameraUpdateFactory.newLatLngZoom(Bangalore1,13);
//
//                addMarkers();
//            }




        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
//        try {

//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }

        // Updates the location and zoom of the MapView
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(Bangalore1,13);
//        map.animateCamera(cameraUpdate);
    }


    private String getMapsApiDirectionsUrl(LatLng source,LatLng dest) {
        String waypoints = "waypoints=optimize:true|"
                + source.latitude + "," + source.longitude
                + "|" + "|" + dest.latitude + ","
                + dest.longitude;
        String url="";
        String testurl="";
        try {
            String URL1 = "";
            String URL2 = "";
            String sensor = "sensor=false";
            //String params = waypoints + "&" + sensor;
            String origin = "origin=" + source.latitude + "," + source.longitude;
            String destination = "destination=" + dest.latitude + "," + dest.longitude;
            String params = origin + "&" + destination + "&%20" + waypoints + "&" + sensor;
            String output = "json";
            URL1 = URLEncoder.encode(output, "UTF-8");
            URL2 = URLEncoder.encode(params, "UTF-8");
            url = "https://maps.googleapis.com/maps/api/directions/"
                    + URL1 + "?" + URL2;
            testurl = "https://maps.googleapis.com/maps/api/directions/"
                    + output + "?" + params;
            Log.d(TAG, "hehehe " + " in getmapsapi "+url);
            Log.d(TAG, "hehehe " + " in getmapsapi test "+testurl);
        }
        catch(Exception e)
        {
            Log.d(TAG,e.toString());
        }
        //return url;
        return testurl;
    }


//    private String getMapsApiDirectionsUrl(LatLng source,LatLng dest) {
//        String waypoints = "waypoints=optimize:true|"
//                + source.latitude + "," + source.longitude
//                + "|" + "|" + dest.latitude + ","
//                + dest.longitude;
//        String url="";
//        String testurl="";
//        try {
//            String URL1 = "";
//            String URL2 = "";
//            String sensor = "sensor=false";
//            //String params = waypoints + "&" + sensor;
//            String origin = "origin=" + source.latitude + "," + source.longitude;
//            String destination = "destination=" + dest.latitude + "," + dest.longitude;
//            String params = origin + "&" + destination + "&%20" + waypoints + "&" + sensor;
//            String output = "json";
//            URL1 = URLEncoder.encode(output, "UTF-8");
//            URL2 = URLEncoder.encode(params, "UTF-8");
//            url = "https://maps.googleapis.com/maps/api/directions/"
//                    + URL1 + "?" + URL2;
//            testurl = "https://maps.googleapis.com/maps/api/directions/"
//                    + output + "?" + params;
//            Log.d(TAG, "hehehe " + " in getmapsapi "+url);
//            Log.d(TAG, "hehehe " + " in getmapsapi test "+testurl);
//        }
//        catch(Exception e)
//        {
//            Log.d(TAG,e.toString());
//        }
//        //return url;
//        return testurl;
//    }
//    private void addMarkers() {
//        if (map != null) {
//            map.addMarker(new MarkerOptions().position(Bangalore1)
//                    .title("First Point"));
//            map.addMarker(new MarkerOptions().position(Bangalore2)
//                    .title("Second Point"));
//            map.addMarker(new MarkerOptions().position(Bangalore3)
//                    .title("Third Point"));
//            Log.d(TAG, "hehehe " + " in markup");
//        }
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(LaunchActivity.data_for_search){
            LaunchActivity.buildDialogAsSearched(getActivity()).show();
        }
        getBinsPositionOnMap();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
//        ((BaseContainerFragment)getParentFragment()).replaceFragment(new MapFragment(), true);
//        if(LaunchActivity.data_for_search){
//            buildDialog(getActivity().getApplicationContext()).show();
//
//        }
    }


    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            Log.d(TAG,"hehehe in readtask assync background url "+url[0]);
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
                Log.d(TAG,"hehehe in readtask assync data "+data);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG,"hehehe in readtask assync onpostexecute "+ result);
            super.onPostExecute(result);
            new ParserTask().execute(result);
        }
    }

    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {


        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes1 = null;

            try {
                Log.d(TAG,"hehehe in parsertask background "+jsonData[0].toString());
                jObject = new JSONObject(jsonData[0]);
                JSONParser jParser = new JSONParser();
                routes1 = jParser.pathJSONParser(jObject);
                Log.d(TAG,"hehehe in routes "+routes1.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes1;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;
            Log.d(TAG,"hehehe on parsertask postexecute sample ");
            // Log.d(TAG,"hehehe on parsertask postexecute "+routes.toString());
            if(routes!=null) {
                // traversing through routes
                for (int i = 0; i < routes.size(); i++) {
                    points = new ArrayList<LatLng>();
                    polyLineOptions = new PolylineOptions();
                    List<HashMap<String, String>> path = routes.get(i);

                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    polyLineOptions.addAll(points);
                    polyLineOptions.width(2);
                    polyLineOptions.color(Color.BLUE);
                }
            }
            if(polyLineOptions!=null)
                map.addPolyline(polyLineOptions);
        }
    }






    private class GetOptimalRoute extends AsyncTask<String, String, String> {

        ProgressDialog pDialog;
        //Creating JSON Parser object
        JSONParser jParser = new JSONParser();
        JSONObject json = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(getActivity().getBaseContext()!=null) {
                if (pDialog != null) {
                    pDialog = null;
                }
                pDialog = new ProgressDialog(getActivity().getBaseContext());
                pDialog.setMessage("getting optimal route. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                // pDialog.show();
            }
        }

        protected String doInBackground(String... args) {

            try {

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("lat", Double.toString(12.9767)));
                params.add(new BasicNameValuePair("lon", Double.toString(77.5767)));
                params.add(new BasicNameValuePair("rad", Integer.toString(20)));

                // getting JSON string from URL
                json = jParser.makeHttpRequest(url_shortest_route, "GET", params);

                if (json != null) {

                    // Check your log cat for JSON reponse
                    Log.d("shortest route ", json.toString());

                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) { //bins found

                        // Getting Array of Bins
                        routes = json.getJSONArray(TAG_ROUTES);

                    }
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         ***/
        protected void onPostExecute(String str) {
            // dismiss the dialog after getting all products
            super.onPostExecute(str);
            if(pDialog!=null)
                pDialog.dismiss();
        }
    }


//    private class ReadTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... url) {
//            Log.d(TAG,"hehehe in readtask assync background url "+url[0]);
//            String data = "";
//            try {
//                HttpConnection http = new HttpConnection();
//                data = http.readUrl(url[0]);
//                Log.d(TAG,"hehehe in readtask assync background "+data);
//            } catch (Exception e) {
//                Log.d("Background Task", e.toString());
//            }
//            return data;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Log.d(TAG,"hehehe in readtask assync onpostexecute "+ result);
//            super.onPostExecute(result);
//            new ParserTask().execute(result);
//        }
//    }
//
//    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
//
//        @Override
//        protected List<List<HashMap<String, String>>> doInBackground(
//                String... jsonData) {
//
//            JSONObject jObject;
//            List<List<HashMap<String, String>>> routes = null;
//
//            try {
//                Log.d(TAG,"hehehe in parsertask background "+jsonData[0].toString());
//                jObject = new JSONObject(jsonData[0]);
//                JSONParser parser = new JSONParser();
//                routes = parser.pathJSONParser(jObject);
//                Log.d(TAG,"hehehe in routes "+routes.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return routes;
//        }
//
//        @Override
//        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
//            ArrayList<LatLng> points = null;
//            PolylineOptions polyLineOptions = null;
//            Log.d(TAG,"hehehe on parsertask postexecute sample ");
//            // Log.d(TAG,"hehehe on parsertask postexecute "+routes.toString());
//            if(routes!=null) {
//                // traversing through routes
//                for (int i = 0; i < routes.size(); i++) {
//                    points = new ArrayList<LatLng>();
//                    polyLineOptions = new PolylineOptions();
//                    List<HashMap<String, String>> path = routes.get(i);
//
//                    for (int j = 0; j < path.size(); j++) {
//                        HashMap<String, String> point = path.get(j);
//
//                        double lat = Double.parseDouble(point.get("lat"));
//                        double lng = Double.parseDouble(point.get("lng"));
//                        LatLng position = new LatLng(lat, lng);
//
//                        points.add(position);
//                    }
//
//                    polyLineOptions.addAll(points);
//                    polyLineOptions.width(2);
//                    polyLineOptions.color(Color.BLUE);
//                }
//            }
//            if(polyLineOptions!=null)
//                map.addPolyline(polyLineOptions);
//        }
//   }

}
