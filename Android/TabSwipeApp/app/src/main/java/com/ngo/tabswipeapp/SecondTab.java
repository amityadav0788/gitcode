package com.ngo.tabswipeapp;

/**
 * Created by A_K_Yadav on 9/16/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn2crack.tab.R;

public class SecondTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ios = inflater.inflate(R.layout.secondtab, container, false);
        ((TextView)ios.findViewById(R.id.textView)).setText("iOS");
        return ios;
    }}
