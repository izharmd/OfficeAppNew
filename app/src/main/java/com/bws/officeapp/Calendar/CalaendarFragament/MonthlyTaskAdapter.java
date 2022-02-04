package com.bws.officeapp.Calendar.CalaendarFragament;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bws.officeapp.Common;
import com.bws.officeapp.R;

import java.util.List;

public class MonthlyTaskAdapter extends RecyclerView.Adapter<MonthlyTaskAdapter.ViewHolder> {
    private List<MonthlyTaskModel> list;
    Context context;


    public MonthlyTaskAdapter(Context context,List<MonthlyTaskModel> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_clendar_monthly_task, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MonthlyTaskModel  myList = list.get(position);
        holder.txt_task_name.setText(myList.getTaskName());
        holder.txt_from_date.setText(myList.getFromDate());
        holder.txt_to_date.setText(myList.getToDate());

       /* if(Common.hideThreeDot.equalsIgnoreCase("MONTHLY_EVENT")){
            holder.imv_option_menu.setVisibility(View.VISIBLE);
        }else {
            holder.imv_option_menu.setVisibility(View.GONE);
        }*/


        holder.imv_option_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(context, v);
                menu.getMenu().add("Edit");
                menu.getMenu().add("Delete");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String str = item.toString();
                        if (str.equalsIgnoreCase("Edit")) {
                           // context.startActivity(new Intent(context, EditeventActivity.class));
                        }else {
                           // showAlertWithCancel(context, "Delete Event", "Are you sure you want to delete this event?");
                        }
                        return false;
                    }
                });
                menu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_task_name,txt_from_date,txt_to_date;
        public ImageView imv_option_menu;
        public ViewHolder(final View itemView) {
            super(itemView);
            txt_task_name = itemView.findViewById(R.id.txt_task_name);
            txt_from_date = itemView.findViewById(R.id.txt_from_date);
            txt_to_date = itemView.findViewById(R.id.txt_to_date);
            imv_option_menu = itemView.findViewById(R.id.imv_option_menu);
        }
    }
}