package com.bws.officeapp.timesheet;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bws.officeapp.R;
import com.bws.officeapp.utils.ToastMessage;
import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.skyhope.eventcalenderlibrary.listener.CalenderDayClickListener;
import com.skyhope.eventcalenderlibrary.model.DayContainerModel;
import com.skyhope.eventcalenderlibrary.model.Event;

import java.util.Calendar;

import android.os.Bundle;

import com.riontech.calendar.CustomCalendar;
import com.riontech.calendar.dao.EventData;
import com.riontech.calendar.dao.dataAboutDate;
import com.riontech.calendar.utils.CalendarUtils;

import java.util.ArrayList;
import java.util.Random;

public class TestCalendarActivity extends AppCompatActivity {

    private CustomCalendar customCalendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_calendar_activity);
        customCalendar = (CustomCalendar) findViewById(R.id.customCalendar);

        String[] arr = {"2022-02-10", "2022-02-11", "2022-02-15", "2022-02-16", "2022-02-25"};
        for (int i = 0; i < 5; i++) {
            int eventCount = 1;
            customCalendar.addAnEvent(arr[i], eventCount, getEventDataList(eventCount));
        }


    }

    public ArrayList<EventData> getEventDataList(int count) {
        ArrayList<EventData> eventDataList = new ArrayList();

        for (int i = 0; i < count; i++) {
            EventData dateData = new EventData();
            ArrayList<dataAboutDate> dataAboutDates = new ArrayList();

            dateData.setSection("Project Name");
            dataAboutDate dataAboutDate = new dataAboutDate();

            dataAboutDate.setTitle("Project status");
            dataAboutDate.setSubject("Not completed");
            dataAboutDates.add(dataAboutDate);

            dateData.setData(dataAboutDates);
            eventDataList.add(dateData);
        }

        return eventDataList;
    }
}