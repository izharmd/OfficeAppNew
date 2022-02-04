package com.bws.officeapp.Calendar.CalaendarFragament;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.bws.officeapp.Common;
import com.bws.officeapp.Lirary.EventModel;
import com.bws.officeapp.Lirary.GetEventListListener;
import com.bws.officeapp.Lirary.MyDynamicCalendar;
import com.bws.officeapp.Lirary.OnDateClickListener;
import com.bws.officeapp.R;

import java.util.ArrayList;
import java.util.Date;


public class CalendarViewFragment extends Fragment {

    View rootview;

    private MyDynamicCalendar myCalendar;
    LinearLayout ll_month_view, ll_week_view, ll_day_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.calendar_view, viewGroup, false);


        myCalendar = (MyDynamicCalendar) rootview.findViewById(R.id.myCalendar);

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

     /*   myCalendar.addEvent("05-02-2022", "","8:15", "8:30", "Wellbieng Task","");
        myCalendar.addEvent("05-10-2020","", "8:30", "8:45", "Tst Task","");
        myCalendar.addEvent("05-10-2020","", "8:45", "9:00", "Test Event");
        myCalendar.addEvent("8-10-2020","", "8:00", "8:30", "Event");
        myCalendar.addEvent("08-10-2020", "","9:00", "10:00", "Event is done");
        myCalendar.addEvent("07-10-2020", "","9:00", "10:00", "Rejaul task");
        myCalendar.addEvent("07-10-2020","", "9:00", "10:00", "Test");
        myCalendar.addEvent("07-10-2020","", "9:00", "10:00", "Android Test");
*/

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



        return rootview;
    }


    @Override
    public void onResume() {
        super.onResume();
       // Common.calendarFragmentOnResume = "Calendar";
        //((CalendarActivity) getActivity()).setTab();
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