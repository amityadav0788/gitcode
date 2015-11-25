package com.ngo.binappsync;

/**
 * Created by A_K_Yadav on 9/30/2015.
 */
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.prgguru.example.R;

public class SampleBC extends BroadcastReceiver {

    private static final String TAG = "SampleBC";
    static int noOfTimes = 0;

    // Method gets called when Broad Case is issued from MainActivity for every 10 seconds
    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d(TAG,"hehehe in SampleBC onreceive "+context.toString() + " "+intent.toString());
        // TODO Auto-generated method stub
        noOfTimes++;
        Toast.makeText(context, "BC Service Running for " + noOfTimes + " times", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        // Checks if new records are inserted in Remote MySQL DB to proceed with Sync operation
        client.post("http://10.94.146.7:2700/mysqlsqlitesync/getdbrowcount.php",params ,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                System.out.println(response);
                Log.d(TAG,"hehehe in SampleBC response "+response);
                try {
                    // Create JSON object out of the response sent by getdbrowcount.php
                    JSONObject obj = new JSONObject(response);
                    System.out.println(obj.get("count"));
                    // If the count value is not zero, call MyService to display notification
                    if(obj.getInt("count") != 0){
                        final Intent intnt = new Intent(context, MyService.class);
                        // Set unsynced count in intent data
                        intnt.putExtra("intntdata", "Unsynced Rows Count "+obj.getInt("count"));
                        // Call MyService
                        context.startService(intnt);
                    }else{
                        Toast.makeText(context, "Sync not needed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // TODO Auto-generated method stub
                if(statusCode == 404){
                    Toast.makeText(context, "404", Toast.LENGTH_SHORT).show();
                }else if(statusCode == 500){
                    Toast.makeText(context, "500", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}