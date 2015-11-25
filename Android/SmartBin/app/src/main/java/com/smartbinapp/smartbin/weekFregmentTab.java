package com.smartbinapp.smartbin;

/**
 * Created by Shilpa_Choudhary on 9/19/2015.
 */

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class weekFregmentTab extends Fragment {

    //GraphView graphView = new GraphView(getActivity());
    private final static String TAG = "WeekFregmant";
    private GraphicalView mChart;
    private XYSeries tempSeries ;
    private XYSeries humiditySeries ;
    private XYMultipleSeriesDataset dataset;
    private XYSeriesRenderer tempRenderer;
    private XYSeriesRenderer humidityRenderer;
    private XYMultipleSeriesRenderer multiRenderer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int z[]={0,1,2,3,4,5,6};
        int x[]={2,4,8,10,12,14,10};
        int w[]={0,1,2,3,4,5,6};
        int y[]={5,4,8,10,12,14,10};
        double range[] = {0.0,0.1,10.0};

        Log.d("weektab", "Inside create view");
        View rootView = inflater.inflate(R.layout.weekfregmentlayout, container, false);
        TextView txtView = (TextView) rootView.findViewById(R.id.text_id1);

        // Creating an  XYSeries for Temperature
        tempSeries = new XYSeries("Temperature ");


        // Creating an  XYSeries for Humidity
        humiditySeries = new XYSeries("Humidity");

        //  Adding data to the X Series.
        for(int i=0;i<z.length;i++)
        {
            tempSeries.add(z[i],x[i]);

        }

        //  Adding data to the X Series.
        for(int i=0;i<w.length;i++)
        {
            humiditySeries.add(w[i],y[i]);

        }
        // Creating a dataset to hold each series
        dataset = new XYMultipleSeriesDataset();

        // Adding Visits Series to the dataset
        dataset.addSeries(tempSeries);
        dataset.addSeries(humiditySeries);

        // Creating XYSeriesRenderer to customize visitsSeries
        tempRenderer = new XYSeriesRenderer();
        tempRenderer.setColor(Color.BLUE);
        tempRenderer.setPointStyle(PointStyle.CIRCLE);
        tempRenderer.setFillPoints(true);
        tempRenderer.setLineWidth(6);
        tempRenderer.setDisplayChartValues(true);
        tempRenderer.setChartValuesTextAlign(Paint.Align.CENTER);

        // Creating XYSeriesRenderer to customize visitsSeries
        humidityRenderer = new XYSeriesRenderer();
        humidityRenderer.setColor(Color.GREEN);
        humidityRenderer.setPointStyle(PointStyle.CIRCLE);
        humidityRenderer.setFillPoints(true);
        humidityRenderer.setLineWidth(6);
        humidityRenderer.setDisplayChartValues(true);
        // humidityRenderer.setChartValuesTextAlign(Paint.Align.RIGHT);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        multiRenderer = new XYMultipleSeriesRenderer();

        multiRenderer.setChartTitle("Bin Temperature and Humidity Reading Chart");
        multiRenderer.setXTitle("days");
        multiRenderer.setYTitle("Reading");
        multiRenderer.setZoomButtonsVisible(true);
        multiRenderer.setXAxisMin(0);
        multiRenderer.setXAxisMax(7);
        multiRenderer.setZoomEnabled(false);
        multiRenderer.setYAxisMin(0);
        multiRenderer.setBackgroundColor(0X0000);
        //multiRenderer.setYAxisMax(5);
        //multiRenderer.setInitialRange(range,2);
        multiRenderer.setBarSpacing(4);

        // Adding visitsRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(tempRenderer);
        multiRenderer.addSeriesRenderer(humidityRenderer);



        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout chartContainer = (LinearLayout) rootView.findViewById(R.id.graph);
        Log.v(TAG, "shilpa in mainactivity actionbar custom activity");
        // Specifying chart types to be drawn in the graph
        // Number of data series and number of types should be same
        // Order of data series and chart type will be same
        String[] types = new String[] { LineChart.TYPE, BarChart.TYPE };

        Log.v(TAG, "shilpa in mainactivity actionbar custom activity3");

        try {
            mChart = (GraphicalView) ChartFactory.getCombinedXYChartView(getActivity().getBaseContext(), dataset, multiRenderer, types);
        }
        catch(Exception e){

            Log.v(TAG,"hehehe "+ e.toString());
        }

        Log.v(TAG, "shilpa in mainactivity actionbar custom activity1");
        // Adding the Line Chart to the LinearLayout
        chartContainer.addView(mChart);



        Log.v(TAG, "hehehe in mainactivity actionbar week activity");
        return rootView;
    }

}
