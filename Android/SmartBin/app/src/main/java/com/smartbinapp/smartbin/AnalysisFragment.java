package com.smartbinapp.smartbin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Priti_Parate on 9/24/2015.
 */
public class AnalysisFragment extends Fragment implements View.OnClickListener{

    public static com.smartbinapp.smartbin.MyLinkedMap<String, Double> analysisMap = null;

    //BinInfo allBinsInfo = BinInfo.getInstance();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Bins = "bins";
    private static final String TAG_ID = "Id";
    private static final String TAG_Fill = "Fill";
    private static final String TAG_Loc = "Location";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //final LinearLayout llFillLevels = (LinearLayout) inflater.inflate(R.layout.analysis, container, false);
//        if(LaunchActivity.data_for_search){
//            LaunchActivity.buildDialogAsSearched(getActivity()).show();
//        }
        this.setHasOptionsMenu(true);
        return inflater.inflate(R.layout.analysis, container, false);


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        ((BaseContainerFragment)getParentFragment()).replaceFragment(new AnalysisFragment(), true);
    }

    @Override
    public void onStart () {

        super.onStart();

        ((LaunchActivity) getActivity()).getSupportActionBar().setTitle(R.string.Analysis_Fragment);

        LinearLayout llFillLevels = (LinearLayout) getActivity().findViewById(R.id.analysisActivity);
        ListView stringListView = (ListView) llFillLevels.findViewById(R.id.compareBinsList);
        List<Map.Entry<String, Double>> list=null;

        try {

            Map<String, Double> binValues = new LinkedHashMap<String, Double>();
            for (int i = 0; LaunchActivity.bins!=null && i < LaunchActivity.bins.length(); i++) {

                JSONObject c = LaunchActivity.bins.getJSONObject(i);
                binValues.put(c.getString(TAG_Loc)+", Bin"+c.getString(TAG_ID), Double.parseDouble(c.getString(TAG_Fill)));
            }

            Set<Map.Entry<String, Double>> set = binValues.entrySet();

            list = new ArrayList<Map.Entry<String, Double>>(set);
            Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });

            analysisMap = new MyLinkedMap<String, Double>();
            for (Map.Entry<String, Double> entry : list)
            {
                analysisMap.put( entry.getKey(), entry.getValue() );
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        stringListView.setAdapter(new AnalysisCustomAdapter(getActivity(), analysisMap));
//        stringListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("Module Item Trigger", "Module item was triggered");
//                //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), BinDetailsActivity1.class);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_analytics, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_export) {

        }

        return super.onOptionsItemSelected(item);
    }
}
