package com.smartbinapp.smartbin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priti_Parate on 10/11/2015.
 */


public class BinInfo {

    private static String url_all_bins = "http://192.168.0.5/binapp/get_bins_radius.php?lat=12.9767&lon=77.5767&rad=10";

    private Context ctx;
    private LoadBinsInfo reqbininfo =null;
    public static JSONArray bins=null;

    //create a singleton object
    private static BinInfo instance = new BinInfo();

    //make the constructor private so that this class cannot be instantiated
    private BinInfo(){}

    //Get the only object available
    public static BinInfo getInstance(){
        return instance;
    }

    public void setContext(Context ctx){
        Log.d("BinInfo:", "setContext");
        this.ctx = ctx;
        reqbininfo = new LoadBinsInfo();
    }

    public void getAllRegBins(){
        Log.d("BinInfo:", "getAllRegBins");
        if(reqbininfo!=null) {
            reqbininfo.execute();
//            bins = LoadBinsInfo.binsOfLoadInfo;
            //Log.d("returned bins: ", bins.toString());
        }
//        return bins;
    }

    class LoadBinsInfo extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;
        private Context ctx;


        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_BINS = "bins";

        // Creating JSON Parser object
        JSONParser jParser = new JSONParser();
        JSONObject json = null;


        /***
         * Before starting background thread Show Progress Dialog
         ***/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(ctx!=null) {
                if (pDialog != null) {
                    pDialog = null;
                }
                pDialog = new ProgressDialog(ctx);
                pDialog.setMessage("Loading bins. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }
        }

        /**
         * getting All bins from url
         */
        protected String doInBackground(String... args) {

            try {

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                // getting JSON string from URL
                json = jParser.makeHttpRequest(url_all_bins, "GET", params);

                if (json != null) {

                    // Check your log cat for JSON reponse
                    Log.d("All bins: ", json.toString());

                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) { //bins found

                        // Getting Array of Bins
                        bins = json.getJSONArray(TAG_BINS);

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;


        }

        /**
         * After completing background task Dismiss the progress dialog
         ***/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            if(pDialog!=null)
                pDialog.dismiss();
        }
    }


}









