package com.smartbinapp.smartbin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shilpa_Choudhary on 10/4/2015.
 */
public class DatePickerFragment extends DialogFragment {

    DatePickerDialog.OnDateSetListener ondateSet;

    public DatePickerFragment() {
    }

    private int year, month, day;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a new instance of DatePickerDialog and return it
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day =  c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker= new DatePickerDialog(getActivity(), ondateSet, year, month, day);
        Date today = new Date();
        c.setTime(today);
        datePicker.getDatePicker().setMaxDate(c.getTime().getTime());
        c.add(Calendar.MONTH, -6); //subtract 6 month
        datePicker.getDatePicker().setMinDate(c.getTime().getTime());
        return datePicker;
        // return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
        ondateSet = ondate;
    }
}
