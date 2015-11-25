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

public class FirstTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View android = inflater.inflate(R.layout.firsttab, container, false);
        ((TextView)android.findViewById(R.id.textView)).setText("Android");
        return android;
    }}
