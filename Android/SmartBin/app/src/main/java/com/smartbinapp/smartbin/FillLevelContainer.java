package com.smartbinapp.smartbin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Priti_Parate on 9/22/2015.
 */
public class FillLevelContainer extends BaseContainerFragment{
    private boolean IsViewInited;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Priti", "FillLevelTab");
        return inflater.inflate(R.layout.container_framelayout, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!IsViewInited) {
            IsViewInited = true;
            initView();
        }
    }

    private void initView() {
        replaceFragment(new FillLevelsFragment(), false);
    }

}



//    @Override
//    public void onClick(View arg0) {
//        // TODO Auto-generated method stub
//        ((BaseContainerFragment)getParentFragment()).replaceFragment(new MapFragment(), true);
//    }





//    private void setview(LinearLayout llFillLevels, RelativeLayout params){
//        //button will be displayed in this layout
//
//
//        //Create four
//        for(int j=0;j<=10;j++) {
////            // Create LinearLayout
////            LinearLayout llLayout = new LinearLayout(this);
////            llLayout.setOrientation(LinearLayout.VERTICAL);
//
//            // Create Button
//            final Button btn = new Button(getActivity().getApplicationContext() );
//
//            // Give button an ID
//            btn.setId(j+1);
//            if(j<2){
//                btn.setBackgroundResource(R.drawable.red_button_border);
//            }
//            else if(j<6){
//                btn.setBackgroundResource(R.drawable.orange_button_border);
//            }
//            else{
//                btn.setBackgroundResource(R.drawable.green_button_border);
//            }
//            btn.setText("Bin " + j + 1);
//            // set the layoutParams on the button
//
////            TextView ltxtView = (TextView) params.findViewById(R.id.ltxtView);
////            ltxtView.setText("Loc1, Bin1");
//
//
//
//            //inal android.widget.RelativeLayout llbtn = (android.widget.RelativeLayout) getResources().getLayout(R.layout.filllevel_button);
//
//
//            //btn.setLayoutParams(llFillLevels.getLayoutParams());
////            btn.setMinimumWidth(launch.getMeasuredWidth());
//
//            final int index = j;
//            // Set click listener for button
//            btn.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//
//                    android.util.Log.i("location button", "index :" + index);
//
//                    //Toast.makeText(getApplicationContext(), "Clicked Button Index :" + index, Toast.LENGTH_LONG).show();
//
//                }
//            });
//
//            //Add button to LinearLayout
//            llFillLevels.addView(btn);
//            //Add button to LinearLayout defined in XML
////            launch.addView(llLayout);
//        }
//
//    }


//oncreateViewCommented <code></code>
//----------------------------------------------

//        final LinearLayout llFillLevels = (LinearLayout) inflater.inflate(R.layout.filllevel, container, false);
//        final RelativeLayout params = (RelativeLayout) inflater.inflate(R.layout.filllevel_item, container,false);
//
//        setview((LinearLayout)llFillLevels.findViewById(R.id.fillLevelsActivity), params);

//        String[] stringArray = new String[10];
//        for(int i=0; i < stringArray.length; i++){
//            stringArray[i] = "String " + i;
//        }
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.filllevel_item, stringArray );
//        ListView stringListView = (ListView) llFillLevels.findViewById(R.id.fillLevel_list);
//
//        fillLevelMap.put("Indira nagar, Bin1", 98);
//        fillLevelMap.put("Indira nagar, Bin2", 96);
//        fillLevelMap.put("Bin3", 76);
//        fillLevelMap.put("Bin4", 64);
//        fillLevelMap.put("Bin5", 55);
//        fillLevelMap.put("Bin6", 30);
//        fillLevelMap.put("Bin7", 20);
//
//        stringListView.setAdapter(new CustomAdapter(getActivity(), fillLevelMap ));

//    private void setListView() {
//        String[] stringArray = new String[10];
//        for(int i=0; i < stringArray.length; i++){
//            stringArray[i] = "String " + i;
//        }
//        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
//        ListView stringListView = (ListView) getActivity().findViewById(R.id.fillLevel_list);
//        stringListView.setAdapter(arrayAdapter);
//    }


///onActivityCreated
//--------------------
////        setListView();
//        try {
//            bins = LaunchActivity.json.getJSONArray(TAG_PRODUCTS);
//            //fillLevelMap.put(bins.get(1).toString(), 98.0 );
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        if(LaunchActivity.BinList.size()==0){
//
//            android.util.Log.i("BinSize", "Size :" + LaunchActivity.BinList.size());
//
//
//            fillLevelMap.put("Indira nagar, Bin1", 98.0);
//            fillLevelMap.put("Indira nagar, Bin2", 96.0);
//            fillLevelMap.put("Bin3", 76.0);
//            fillLevelMap.put("Bin4", 64.0);
//            fillLevelMap.put("Bin5", 55.0);
//            fillLevelMap.put("Bin6", 30.0);
//            fillLevelMap.put("Bin7", 20.0);
//
//        }
//        else {
//        RelativeLayout params = (RelativeLayout) getActivity().findViewById(R.id.flbtnlayout);
//        setview(llFillLevels, params);
