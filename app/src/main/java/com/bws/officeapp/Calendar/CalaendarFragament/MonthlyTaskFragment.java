package com.bws.officeapp.Calendar.CalaendarFragament;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bws.officeapp.R;

import java.util.ArrayList;
import java.util.List;

public class MonthlyTaskFragment extends Fragment {

    View rootview;

    List<MonthlyTaskModel> arrMonthlyTask;
    RecyclerView.Adapter adapter;
    private RecyclerView recy_View;
    LinearLayoutManager linearLayoutManager;

    ImageView imv_three_dot;

    FragmentManager fragmentManager;
    Fragment fragment = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.calendar_monthly_task, viewGroup, false);

        initView();
        clickEvent();
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
       // Common.calendarFragmentOnResume = "MonthlyTask";
       // ((CalendarActivity)getActivity()).setTab();
    }

    private void clickEvent() {


    }

    private void initView() {

        recy_View = rootview.findViewById(R.id.recy_View);
        recy_View.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recy_View.setLayoutManager(linearLayoutManager);
        arrMonthlyTask = new ArrayList<MonthlyTaskModel>();

        arrMonthlyTask.clear();


        MonthlyTaskModel monthlyTaskModel = new MonthlyTaskModel();
        monthlyTaskModel.setTaskName("Cycling task");
        monthlyTaskModel.setFromDate("12-07-2020");
        monthlyTaskModel.setToDate("12-07-2020");
        arrMonthlyTask.add(monthlyTaskModel);

        MonthlyTaskModel monthlyTaskModel1 = new MonthlyTaskModel();
        monthlyTaskModel1.setTaskName("Cricket task");
        monthlyTaskModel1.setFromDate("12-05-2020");
        monthlyTaskModel1.setToDate("13-05-2020");
        arrMonthlyTask.add(monthlyTaskModel1);

        MonthlyTaskModel monthlyTaskModel2 = new MonthlyTaskModel();
        monthlyTaskModel2.setTaskName("Football task");
        monthlyTaskModel2.setFromDate("22-07-2020");
        monthlyTaskModel2.setToDate("25-07-2020");
        arrMonthlyTask.add(monthlyTaskModel2);

        MonthlyTaskModel monthlyTaskModel3 = new MonthlyTaskModel();
        monthlyTaskModel3.setTaskName("Test task");
        monthlyTaskModel3.setFromDate("28-03-2020");
        monthlyTaskModel3.setToDate("31-03-2020");
        arrMonthlyTask.add(monthlyTaskModel3);

        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.line_divider);
        recy_View.addItemDecoration(new com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration(dividerDrawable));
        adapter = new MonthlyTaskAdapter(getContext(), arrMonthlyTask);
        recy_View.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}