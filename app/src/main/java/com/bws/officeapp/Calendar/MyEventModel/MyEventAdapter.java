
package com.bws.officeapp.Calendar.MyEventModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bws.officeapp.R;

import java.util.List;

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.ViewHolder> {
    private List<MyEventModel> list;
    Context context;


    public MyEventAdapter(Context context, List<MyEventModel> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_item_event, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MyEventModel myList = list.get(position);
        holder.txtProjectName.setText(myList.getEventName());
        holder.txt_start_date.setText(myList.getEventStartDate());
        holder.txt_end_date.setText(myList.getEventEndDate());
        holder.txtProjectStatus.setText(myList.getProjectStatus());

        String status = myList.getProjectStatus();

        if (status.equalsIgnoreCase("Completed")) {
            holder.txtProjectStatus.setTextColor(context.getResources().getColor(R.color.green));
        } else if(status.equalsIgnoreCase("InProgress")){
            holder.txtProjectStatus.setTextColor(context.getResources().getColor(R.color.red_graph));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProjectName, txt_start_date, txt_end_date,txtProjectStatus;

        public ViewHolder(final View itemView) {
            super(itemView);
            txtProjectName = itemView.findViewById(R.id.txtProjectName);
            txt_start_date = itemView.findViewById(R.id.txt_start_date);
            txt_end_date = itemView.findViewById(R.id.txt_end_date);
            txtProjectStatus = itemView.findViewById(R.id.txtProjectStatus);
        }
    }
}
