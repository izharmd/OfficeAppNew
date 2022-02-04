/*
package com.bws.officeapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.desai.vatsal.mydynamiccalendar.EventModel;
import com.desai.vatsal.mydynamiccalendar.GetEventListListener;
import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;

import java.util.ArrayList;
import java.util.Date;

public class TestActuvity extends AppCompatActivity {

    private MyDynamicCalendar myCalendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_test);
        myCalendar = (MyDynamicCalendar) findViewById(R.id.myCalendar);

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

        myCalendar.addEvent("05-02-2022", "","8:15", "8:30" );
        myCalendar.addEvent("05-10-2020","", "8:30", "8:45");
        myCalendar.addEvent("05-10-2020","", "8:45", "9:00");
        myCalendar.addEvent("8-10-2020","", "8:00", "8:30");
        myCalendar.addEvent("08-10-2020", "","9:00", "10:00");
        myCalendar.addEvent("07-10-2020", "","9:00", "10:00");
        myCalendar.addEvent("07-10-2020","", "9:00", "10:00");
        myCalendar.addEvent("07-10-2020","", "9:00", "10:00");


        myCalendar.getEventList(new GetEventListListener() {
            @Override
            public void eventList(ArrayList<EventModel> eventList) {

                Log.e("tag", "eventList.size():-" + eventList.size());
                for (int i = 0; i < eventList.size(); i++) {

                    // Toast.makeText(getContext(),eventList.get(i).getStrName(),Toast.LENGTH_LONG).show();

                    Log.e("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                }

            }
        });




        myCalendar.setBelowMonthEventTextColor("#425684");
//        myCalendar.setBelowMonthEventTextColor(R.color.black);

        myCalendar.setBelowMonthEventDividerColor("#635478");
//        myCalendar.setBelowMonthEventDividerColor(R.color.black);

        myCalendar.setHolidayCellBackgroundColor("#654248");
//        myCalendar.setHolidayCellBackgroundColor(R.color.black);

        myCalendar.setHolidayCellTextColor("#d590bb");
//        myCalendar.setHolidayCellTextColor(R.color.black);




    }



    private void showMonthViewWithBelowEvents() {

        myCalendar.showMonthViewWithBelowEvents();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
                //String s = myCalendar.getEventList(ArrayList<EventModel> ev);
                String date1 = String.valueOf(date.getDate());
                // Toast.makeText(getContext(),date1,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }
}
*/
