package com.smartbinapp.smartbin;

/**
 * Created by Priti_Parate on 9/24/2015.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.smartbinapp.smartbin.BinInfo;
import com.smartbinapp.smartbin.MyLinkedMap;
import com.smartbinapp.smartbin.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FillLevelsFragment extends Fragment {

    public static com.smartbinapp.smartbin.MyLinkedMap<String, Double> fillLevelMap = new MyLinkedMap<String, Double>();

    BinInfo allBinsInfo = BinInfo.getInstance();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "bins";
    private static final String TAG_PID = "ID";
    private static final String TAG_NAME = "Fill";
    private static final String TAG_LOC = "Location";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.filllevel, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }




    @Override
    public void onStart () {

        super.onStart();

        LinearLayout llFillLevels = (LinearLayout) getActivity().findViewById(R.id.fillLevelsActivity);
        ListView stringListView = (ListView) llFillLevels.findViewById(R.id.fillLevel_list);

        try {

            ProgressDialog pDialog=null;

            // Loading bins in Background Thread
            allBinsInfo.setContext(getActivity().getBaseContext());//need to recheck
            allBinsInfo.getAllRegBins();



            while(BinInfo.bins==null ){ //need to recheck again

    //            pDialog = new ProgressDialog(getActivity().getBaseContext());
    //            pDialog.setMessage("Loading bins. Please wait...");
    //            pDialog.setIndeterminate(false);
    //            pDialog.setCancelable(false);
                //pDialog.show();
            }
            if(BinInfo.bins!=null && pDialog!=null ){
                pDialog.dismiss();
            }

            Log.e("Priti-filllevel", BinInfo.bins.toString());


            for (int i = 0; BinInfo.bins!=null && i < BinInfo.bins.length(); i++) {

                    JSONObject c = BinInfo.bins.getJSONObject(i);
                Log.e("Priti-filllevel ",c.getString(TAG_NAME));
                Log.e("Priti-filllevel ",c.getString(TAG_LOC)+" "+c.getString(TAG_PID));
                    //fillLevelMap.put("Indira nagar, Bin1", Double.parseDouble(c.getString(TAG_NAME)));
                    fillLevelMap.put(c.getString(TAG_LOC)+c.getString(TAG_PID), Double.parseDouble(c.getString(TAG_NAME)));



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        stringListView.setAdapter(new FillLevelCustomAdapter(getActivity(), fillLevelMap));


    }
}
