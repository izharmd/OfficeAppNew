package com.bws.officeapp.Calendar;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bws.officeapp.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewEventActivity extends AppCompatActivity {
    RadioButton rd_daily,rd_weekly,rd_monthly,rd_yearly;

    LinearLayout ll_hide_when_check_assign,ll_hide_when_check_recurring,ll_1,ll_2,ll_3,ll_4,ll_5,ll_6,ll_7,ll_8,ll_12,ll_11;


    CheckBox check_recurring,checkRemider;

    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7,spinner8;

    TextView txt_save, txtStartDate, txtEndDate, txt_start_time, txt_end_time,txt_recuring_end_by;

    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startDate;

    final Calendar myCalendarEndDate = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener endDate;

    final Calendar myCalendarRecuEndBy = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener recuEndBy;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_calendar_activity);

    }
}
