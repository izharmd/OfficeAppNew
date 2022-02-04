package com.bws.officeapp.Calendar.CalendarEvent;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.bws.officeapp.Lirary.EventModel;
import com.bws.officeapp.Lirary.GetEventListListener;
import com.bws.officeapp.Lirary.MyDynamicCalendar;
import com.bws.officeapp.Lirary.OnDateClickListener;
import com.bws.officeapp.Lirary.OnEventClickListener;
import com.bws.officeapp.Lirary.OnWeekDayViewClickListener;
import com.bws.officeapp.R;

import java.util.ArrayList;
import java.util.Date;

public class CalendarEventActivity extends AppCompatActivity {
    private MyDynamicCalendar myCalendar;
    LinearLayout ll_month_view,ll_week_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
       /* myCalendar = (MyDynamicCalendar) findViewById(R.id.myCalendar);
        ll_month_view = findViewById(R.id.ll_month_view);
        ll_week_view = findViewById(R.id.ll_week_view);


        ll_month_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonthViewWithBelowEvents();
            }
        });

        ll_week_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeekView();
            }
        });

       

        showMonthViewWithBelowEvents();



        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });



        myCalendar.addEvent("05-10-2020", "06-10-2020","8:15", "8:30", "Wellbieng Task");
        myCalendar.addEvent("05-10-2020","06-10-2020", "8:30", "8:45", "Tst Task");
        myCalendar.addEvent("05-10-2020","08-10-2020", "8:45", "9:00", "Test Event");
        myCalendar.addEvent("8-10-2020","10-10-2020", "8:00", "8:30", "Event");
        myCalendar.addEvent("08-10-2020", "11-10-2020","9:00", "10:00", "Event is done");
        myCalendar.addEvent("07-10-2020", "9-10-2020","9:00", "10:00", "Rejaul task");
        myCalendar.addEvent("07-10-2020","09-10-2020", "9:00", "10:00", "Test");
        myCalendar.addEvent("07-10-2020","09-10-2020", "9:00", "10:00", "Android Test");

        myCalendar.getEventList(new GetEventListListener() {
            @Override
            public void eventList(ArrayList<EventModel> eventList) {

                Log.e("tag", "eventList.size():-" + eventList.size());
                for (int i = 0; i < eventList.size(); i++) {

                    Toast.makeText(CalendarEventActivity.this,eventList.get(i).getStrName(),Toast.LENGTH_LONG).show();

                    Log.e("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                }

            }
        });


        //        myCalendar.deleteAllEvent();

        myCalendar.setBelowMonthEventTextColor("#425684");
//        myCalendar.setBelowMonthEventTextColor(R.color.black);

        myCalendar.setBelowMonthEventDividerColor("#635478");
//        myCalendar.setBelowMonthEventDividerColor(R.color.black);

        myCalendar.setHolidayCellBackgroundColor("#654248");
//        myCalendar.setHolidayCellBackgroundColor(R.color.black);

        myCalendar.setHolidayCellTextColor("#d590bb");
//        myCalendar.setHolidayCellTextColor(R.color.black);

        myCalendar.setHolidayCellClickable(false);
        myCalendar.addHoliday("2-11-2016");
        myCalendar.addHoliday("8-11-2016");
        myCalendar.addHoliday("12-11-2016");
        myCalendar.addHoliday("13-11-2016");
        myCalendar.addHoliday("8-10-2016");
        myCalendar.addHoliday("10-12-2016");
    }


    private void showMonthViewWithBelowEvents() {

        myCalendar.showMonthViewWithBelowEvents();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
                //String s = myCalendar.getEventList(ArrayList<EventModel> ev);


                String date1 = String.valueOf(date.getDate());
                Toast.makeText(CalendarEventActivity.this,date1,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }


    private void showWeekView() {

        myCalendar.showWeekView();

        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showWeekView","from setOnEventClickListener onClick");
            }

            @Override
            public void onLongClick() {
                Toast.makeText(CalendarEventActivity.this,"Week View On Event",Toast.LENGTH_LONG).show();

            }
        });

        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {
                Log.e("showWeekView", "from setOnWeekDayViewClickListener onClick");
                Log.e("tag", "date:-" + date + " time:-" + time);

            }

            @Override
            public void onLongClick(String date, String time) {
                Log.e("showWeekView", "from setOnWeekDayViewClickListener onLongClick");
                Log.e("tag", "date:-" + date + " time:-" + time);

            }
        });*/


    }


}