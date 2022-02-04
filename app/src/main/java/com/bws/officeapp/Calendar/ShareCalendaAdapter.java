/*
package com.bws.officeapp.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bws.officeapp.R;

import java.util.List;

public class ShareCalendaAdapter  extends RecyclerView.Adapter<ShareCalendaAdapter.ViewHolder> {
    private List<ShareCalendarModel> list;
    Context context;
    public ShareCalendaAdapter(Context context,List<ShareCalendarModel> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_share_event, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ShareCalendarModel  myList = list.get(position);
        holder.txt_recipientName.setText(myList.getNameShareWith());

        holder.imv_cancle_receipent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_recipientName;
        public ImageView imv_cancle_receipent;
        public ViewHolder(final View itemView) {
            super(itemView);
            txt_recipientName = itemView.findViewById(R.id.txt_recipientName);
            imv_cancle_receipent = itemView.findViewById(R.id.imv_cancle_receipent);

        }
    }
}
*/
