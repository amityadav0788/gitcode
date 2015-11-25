package com.smartbinapp.smartbin;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

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

    BinInfo allBinsInfo = BinInfo.getInstance();

    private static final String TAG_LATITUDE = "Lat";
    private static final String TAG_LONGITUDE = "longitude";

    private static final LatLng Bangalore1 = new LatLng(12.9667,
            77.5667);
//    private static final LatLng Bangalore2 = new LatLng(12.8667, 77.5767);
//    private static final LatLng Bangalore3 = new LatLng(12.9667, 77.5867);
//
//    double coordinates[][] = {{12.9667,77.5667},{12.8667,77.5767},{12.9667,77.5867}};
    final String TAG = "PathGoogleMapActivity";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.map, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        getBinsPositionOnMap();

        return v;
    }

    public void getBinsPositionOnMap (){
        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();

        try {

            allBinsInfo.setContext(getActivity().getBaseContext());//need to recheck
            allBinsInfo.getAllRegBins();

            while(BinInfo.bins==null){}

            for (int i = 0; BinInfo.bins!=null && i < BinInfo.bins.length(); i++) {
    //        MarkerOptions options = new MarkerOptions().position();
    //
    //        options.position(Bangalore1);
    //        options.position(Bangalore2);
    //        options.position(Bangalore3);
                JSONObject c = BinInfo.bins.getJSONObject(i);
                map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(c.getString(TAG_LATITUDE)), Double.parseDouble(c.getString(TAG_LONGITUDE)) ))
                                                 .draggable(false)
                                                 .title("Bin"+i)
                                                 );
            }

            map.getUiSettings().setMyLocationButtonEnabled(false);
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
        getBinsPositionOnMap ();
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
        ((BaseContainerFragment)getParentFragment()).replaceFragment(new MapFragment(), true);
    }

//    private class UpdateData extends AsyncTask<String, String, String> {
//
//        ProgressDialog pDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            if(getActivity().getBaseContext()!=null) {
//                if (pDialog != null) {
//                    pDialog = null;
//                }
//                pDialog = new ProgressDialog(getActivity().getBaseContext());
//                pDialog.setMessage("Loading bins. Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setCancelable(false);
//                pDialog.show();
//            }
//        }
//
//        protected String doInBackground(String... args) {
//
//            // Loading bins in Background Thread
//            allBinsInfo.setContext(getActivity().getBaseContext());//need to recheck
//            allBinsInfo.getAllRegBins();
//            return null;
//        }
//
//        /**
//         * After completing background task Dismiss the progress dialog
//         ***/
//        protected void onPostExecute(String str) {
//            // dismiss the dialog after getting all products
//            super.onPostExecute(str);
//            if(pDialog!=null)
//                pDialog.dismiss();
//        }
//    }


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
//    }

}
