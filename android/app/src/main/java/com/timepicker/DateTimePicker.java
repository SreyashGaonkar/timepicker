package com.timepicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.ParseException;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTimePicker extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    DateTimePicker(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "DateTimePicker";
    }
    @ReactMethod
    public void showTimePicker(Callback callback){
        Toast.makeText(reactContext,"test",Toast.LENGTH_LONG).show();
        Calendar now = Calendar.getInstance();
        int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = now.get(java.util.Calendar.MINUTE);

        // Whether show time in 24 hour format or not.
        boolean is24Hour = false;

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String time = hour+":"+minute;
                callback.invoke(null,time);

            }
        };
        // TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, onTimeSetListener, hour, minute, is24Hour);
        TimePickerDialog timePickerDialog = new TimePickerDialog(reactContext.getCurrentActivity(), android.R.style.Theme_Holo_Light_Dialog, onTimeSetListener, hour, minute, is24Hour);
        timePickerDialog.show();
    }

    @ReactMethod
    private void showDatePickerDialog(Callback callback){
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String date = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());

                callback.invoke(null,date);
            }
        };

        // Get current year, month and day.
        Calendar now = Calendar.getInstance();
        int year = now.get(java.util.Calendar.YEAR);
        int month = now.get(java.util.Calendar.MONTH);
        int day = now.get(java.util.Calendar.DAY_OF_MONTH);

        // Create the new DatePickerDialog instance.
        //  DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, onDateSetListener, year, month, day);
        DatePickerDialog datePickerDialog = new DatePickerDialog(reactContext.getCurrentActivity(), android.R.style.Theme_Holo_Light_Dialog, onDateSetListener, year, month, day);
        datePickerDialog.show();
    }

    @ReactMethod
    private void showCustomeDatePickerDialog(String minDate, String maxDate, Callback callback){
        Log.d("time stamp:", minDate);
        Log.d("time stamp:", maxDate);

        try {
            // convert string date to date
            Date mindate=new SimpleDateFormat("dd/MM/yyyy").parse(minDate);
            Date maxdate = new SimpleDateFormat("dd/MM/yyyy").parse(maxDate);


            DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String date = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());

                    callback.invoke(null, date);
                }
            };

            // Get current year, month and day.
            Calendar now = Calendar.getInstance();
            int year = now.get(java.util.Calendar.YEAR);
            int month = now.get(java.util.Calendar.MONTH);
            int day = now.get(java.util.Calendar.DAY_OF_MONTH);

            // Create the new DatePickerDialog instance.
            //  DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, onDateSetListener, year, month, day);
            DatePickerDialog datePickerDialog = new DatePickerDialog(reactContext.getCurrentActivity(), android.R.style.Theme_Holo_Light_Dialog, onDateSetListener, year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(maxdate.getTime());
            datePickerDialog.getDatePicker().setMinDate(mindate.getTime());
            datePickerDialog.show();
        }catch (Exception e){
            callback.invoke(e.toString(),null);
        }
    }

}
