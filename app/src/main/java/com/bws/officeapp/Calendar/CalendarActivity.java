package com.bws.officeapp.Calendar;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bws.officeapp.Calendar.CalaendarFragament.CalendarViewFragment;
import com.bws.officeapp.Lirary.AppConstants;
import com.bws.officeapp.Lirary.EventModel;
import com.bws.officeapp.Lirary.GetEventListListener;
import com.bws.officeapp.Lirary.MyDynamicCalendar;
import com.bws.officeapp.Lirary.OnDateClickListener;
import com.bws.officeapp.R;
import com.bws.officeapp.expense.utils.MyPopUpMenu;
import com.bws.officeapp.utils.Common;
import com.bws.officeapp.utils.DateHeader;
import com.bws.officeapp.utils.LoadingDialog;
import com.bws.officeapp.utils.SharedPreference;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class CalendarActivity extends AppCompatActivity {

    private MyDynamicCalendar myCalendar;


    TextView textUserName,textDate;
    ImageView imv_Shutdown,imvBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        myCalendar = (MyDynamicCalendar) findViewById(R.id.myCalendar);
        myCalendar.showMonthViewWithBelowEvents();

        textUserName = findViewById(R.id.textUserName);
        textDate = findViewById(R.id.textDate);
        imv_Shutdown = findViewById(R.id.imv_Shutdown);
        imvBack = findViewById(R.id.imvBack);

        MyPopUpMenu myPopUpMenu = new MyPopUpMenu();
        myPopUpMenu.populateMenuLeave(this,imv_Shutdown);
        myPopUpMenu.backToActivity(this,imvBack);

        DateHeader dateHeader = new DateHeader();
        dateHeader.dateToHeader(this,textDate,textUserName, "Welcome To Office App");
/*
        myCalendar.addEvent("05-02-2022", "10-02-2022","8:40", "8:30", "Goya Sports","Completed");
        myCalendar.addEvent("05-02-2022","10-02-2022", "8:30", "8:45", "AEML AUS","Not Completed");
        myCalendar.addEvent("05-02-2022","10-02-2022", "8:45", "9:00", "Office App","Completed");
        myCalendar.addEvent("08-02-2022","10-02-2022", "8:00", "8:30", "Office App","InProgress");
        myCalendar.addEvent("08-02-2022", "10-02-2022","9:00", "10:00", "EOffice App","Completed");
        myCalendar.addEvent("07-02-2022", "10-02-2022","9:00", "10:00", "Office App","Completed");
        myCalendar.addEvent("07-02-2022","10-02-2022", "9:00", "10:00", "Office App","Completed");
        myCalendar.addEvent("07-02-2022","10-02-2022", "9:00", "10:00", "Office App","Completed");
*/
        callMyJobCalendar();
    }



    public void callMyJobCalendar() {
        Dialog dialog = LoadingDialog.Companion.progressDialog(this);
        JSONObject jsonObject = new JSONObject();

       SharedPreference sr =  new SharedPreference(this);

      String userId =  sr.getValueString("KEY_USER_ID").toString();

        try {
            jsonObject.put("UserID", userId);
            jsonObject.put("SearchType", "By Date Range");
            jsonObject.put("FromDate", "2022-01-01");
            jsonObject.put("ToDate", "2025-01-01");
            jsonObject.put("PageSize", "100");
            jsonObject.put("CurrentPageNo", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("JSON==" + jsonObject.toString());

        HttpEntity entity;
        try {
            entity = new StringEntity(String.valueOf(jsonObject), "UTF-8");
        } catch (IllegalArgumentException e) {
            Log.d("HTTP", "StringEntity: IllegalArgumentException");
            return;
        }
        String contentType = "application/json; charset=utf-8";

        String url = "http://bitwavesolutions.co.uk/OfficeApp/api/service/ProjectAllocationSearch";
        client.addHeader("API_KEY", "A862A321-15CA-4265-B188-3959E38A94D2");

        client.post(this, url, entity, contentType, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String asynchResult = new String(response);
                dialog.dismiss();

                try {
                    JSONObject object = new JSONObject(asynchResult);
                    String status = object.getString("sStatus");
                    String msg = object.getString("sMessage");


                    if (status.equalsIgnoreCase("SUCCESS")) {
                        JSONObject objData = object.getJSONObject("data");

                        JSONArray jsonArray = objData.getJSONArray("ProjectList");

                        AppConstants.eventList.clear();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String pName = jsonObject1.getString("ProjectName");
                            String sDate = jsonObject1.getString("StartDate");
                            String eDate = jsonObject1.getString("AgreedDeliveryDate");

                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date date = formatter.parse(sDate);
                                Date date1 = formatter.parse(eDate);

                                SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
                                String startDate = sm.format(date);
                                String endDate = sm.format(date1);


                                myCalendar.showMonthViewWithBelowEvents();

                                String pStatus = jsonObject1.getString("ProjectStatus");
                                if(pStatus == null){
                                    myCalendar.addEvent(startDate, endDate, "8:40", "8:30", pName, "");
                                }else {
                                    myCalendar.addEvent(startDate, endDate, "8:40", "8:30", pName, pStatus);
                                }

                                myCalendar.setBelowMonthEventTextColor("#425684");
//        myCalendar.setBelowMonthEventTextColor(R.color.black);

                                myCalendar.setBelowMonthEventDividerColor("#635478");
//        myCalendar.setBelowMonthEventDividerColor(R.color.black);

                                myCalendar.setHolidayCellBackgroundColor("#654248");
//        myCalendar.setHolidayCellBackgroundColor(R.color.black);

                                myCalendar.setHolidayCellTextColor("#d590bb");
//        myCalendar.setHolidayCellTextColor(R.color.black);


                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        Toast.makeText(CalendarActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(CalendarActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("HTTP", "Post...");
                dialog.dismiss();
                Toast.makeText(CalendarActivity.this, String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });


    }
}
