package com.riviam.samplepath;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.javapapers.android.maps.path.R;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private static final LatLng Bangalore1 = new LatLng(12.9667,
            77.5667);
    private static final LatLng Bangalore2 = new LatLng(12.8667, 77.5767);
    private static final LatLng Bangalore3 = new LatLng(12.9667, 77.5867);

    double coordinates[][] = {{12.9667,77.5667},{12.8667,77.5767},{12.9667,77.5867}};
    //private static final LatLng LOWER_MANHATTAN = new LatLng(40.722543,
            //-73.998585);
   // private static final LatLng BROOKLYN_BRIDGE = new LatLng(40.7057, -73.9964);
   // private static final LatLng WALL_STREET = new LatLng(40.7064, -74.0094);

    GoogleMap googleMap;
    final String TAG = "PathGoogleMapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"hehehe "+ " in create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        googleMap = fm.getMap();

        MarkerOptions options = new MarkerOptions();
        options.position(Bangalore1);
        options.position(Bangalore2);
        options.position(Bangalore3);
        googleMap.addMarker(options);

        int size_coordinates = coordinates.length;

        for(int i=0;i<size_coordinates-1;i++) {
            LatLng source = new LatLng(coordinates[i][0],coordinates[i][1]);
            LatLng dest = new LatLng(coordinates[i+1][0],coordinates[i+1][1]);;
            String url = getMapsApiDirectionsUrl(source, dest);
            Log.d(TAG, "hehehe in oncreate " + i+" "+url);
            ReadTask downloadTask = new ReadTask();
            downloadTask.execute(url);

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bangalore1,
                    13));
            addMarkers();
        }

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
    private void addMarkers() {
        if (googleMap != null) {
            googleMap.addMarker(new MarkerOptions().position(Bangalore1)
                    .title("First Point"));
            googleMap.addMarker(new MarkerOptions().position(Bangalore2)
                    .title("Second Point"));
            googleMap.addMarker(new MarkerOptions().position(Bangalore3)
                    .title("Third Point"));
            Log.d(TAG, "hehehe " + " in markup");
        }
    }

    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            Log.d(TAG,"hehehe in readtask assync background url "+url[0]);
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
                Log.d(TAG,"hehehe in readtask assync background "+data);
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
            List<List<HashMap<String, String>>> routes = null;

            try {
                Log.d(TAG,"hehehe in parsertask background "+jsonData[0].toString());
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
                Log.d(TAG,"hehehe in routes "+routes.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
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
                googleMap.addPolyline(polyLineOptions);
        }
    }
}