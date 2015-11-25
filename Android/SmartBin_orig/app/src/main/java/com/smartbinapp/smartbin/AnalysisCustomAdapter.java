package com.smartbinapp.smartbin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Priti_Parate on 10/11/2015.
 */
public class AnalysisCustomAdapter extends BaseAdapter {

    static MyLinkedMap<String, Integer> reqMap = new MyLinkedMap<String, Integer>();
    private static LayoutInflater inflater=null;
    Context context;

    final String TAG="AnalysiscustomAdapter";

    public AnalysisCustomAdapter(Context ctx, MyLinkedMap<String, Integer> fillLevelMap ) {
        // TODO Auto-generated constructor stub
        reqMap = fillLevelMap;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return reqMap.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView ltxtview;
        TextView rtxtview;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView = inflater.inflate(R.layout.filllevel_item, null);

        return rowView;

    }

}

