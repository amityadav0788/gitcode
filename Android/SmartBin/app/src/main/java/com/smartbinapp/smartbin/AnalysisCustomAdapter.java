package com.smartbinapp.smartbin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Priti_Parate on 10/11/2015.
 */
public class AnalysisCustomAdapter extends BaseAdapter {

    static MyLinkedMap<String, Double> reqMap = new MyLinkedMap<String, Double>();
    private static LayoutInflater inflater=null;
    Context context;

    final String TAG="AnalysisCustomAdapter";

    public AnalysisCustomAdapter(Context ctx, MyLinkedMap<String, Double> analysisMap ) {
        // TODO Auto-generated constructor stub
        reqMap = analysisMap;
        context=ctx;
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
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView = inflater.inflate(R.layout.analysis_filllevel_item, null);

        holder.ltxtview=(TextView) rowView.findViewById(R.id.analysisLtxtView);
        holder.rtxtview=(TextView) rowView.findViewById(R.id.analysisRtxtView);
        holder.ltxtview.setText(reqMap.getKey(position));
        holder.rtxtview.setText(Double.toString(reqMap.getValue(position))+"%");

        RelativeLayout chktxt = (RelativeLayout) rowView.findViewById(R.id.chkTextlayout);

        if(reqMap.getValue(position) > 80 ){
            chktxt.setBackgroundResource(R.drawable.red_button_border);
            //holder.rtxtview.startAnimation(AnimationUtils.loadAnimation(context,android.R.anim.slide_out_right));
        }
        else if(reqMap.getValue(position)<=80 && reqMap.getValue(position) >50){
            chktxt.setBackgroundResource(R.drawable.orange_button_border);
        }
        else if(reqMap.getValue(position) <=50)
        {
            chktxt.setBackgroundResource(R.drawable.green_button_border);
        }



//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
////                Toast.makeText(context, "You Clicked ", Toast.LENGTH_LONG).show();
//                Log.d(TAG, "hi " + " listview listener");
//                //Intent intent = new Intent( context, BinDetailsActivity.class);
//                //startActivity(intent);
//            }
//        });
        return rowView;
    }

}

