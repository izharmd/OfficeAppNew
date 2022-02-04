package com.bws.officeapp.Lirary;

import static com.bws.officeapp.Lirary.AppConstants.main_calendar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bws.officeapp.Calendar.MyEventModel.MyEventModel;
import com.bws.officeapp.R;

/**
 * Created by HCL on 01-10-2016.
 */
public class ShowWeekViewEventsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ShowEventsModel> showEventsModelList;

    private OnWeekDayViewClickListener onWeekDayViewClickListener;


    RecyclerView.Adapter adapter;
    RecyclerView recy_View;
    LinearLayoutManager linearLayoutManager;

    public ShowWeekViewEventsListAdapter(Context context, ArrayList<ShowEventsModel> showEventsModelList) {
        this.context = context;
        this.showEventsModelList = showEventsModelList;
    }

    public void setOnWeekDayViewClickListener(OnWeekDayViewClickListener onWeekDayViewClickListener) {
        this.onWeekDayViewClickListener = onWeekDayViewClickListener;
    }

    class ShowEventsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_parrent;

        public ShowEventsViewHolder(View itemView) {
            super(itemView);
            ll_parrent = (LinearLayout) itemView.findViewById(R.id.ll_parrent);

        }

        public void setShowEvents(final ShowEventsModel model) {

            ll_parrent.removeAllViews();

            View row_event_layout = LayoutInflater.from(context).inflate(R.layout.day_week_event, null, false);

            TextView tv_event_simbol = (TextView) row_event_layout.findViewById(R.id.tv_event_simbol);
            TextView tv_event_name = (TextView) row_event_layout.findViewById(R.id.tv_event_name);
            TextView tv_event_date = (TextView) row_event_layout.findViewById(R.id.tv_event_date);
            TextView tv_event_time = (TextView) row_event_layout.findViewById(R.id.tv_event_time);
            LinearLayout ll_month_events = (LinearLayout) row_event_layout.findViewById(R.id.ll_month_events);
            View v_divider = (View) row_event_layout.findViewById(R.id.v_divider);

            v_divider.setVisibility(View.GONE);

            // set event color
            for (int i = 0; i < AppConstants.eventList.size(); i++) {

                if (AppConstants.eventList.get(i).getStrDate().equals(AppConstants.sdfDate.format(model.getDates()))
                        && GlobalMethods.convertDate(AppConstants.eventList.get(i).getStrStartTime(), AppConstants.sdfHourMinute, AppConstants.sdfHour).equals(GlobalMethods.convertDate(model.getHours(), AppConstants.sdfHourMinute, AppConstants.sdfHour))) {

                    //tv_event_name.setText(AppConstants.eventList.get(i).getStrName());
                    //tv_event_date.setText(AppConstants.eventList.get(i).getStrDate());
                    //tv_event_time.setText(String.format("%s to %s", AppConstants.eventList.get(i).getStrStartTime(), AppConstants.eventList.get(i).getStrEndTime()));
                    tv_event_time.setText(AppConstants.eventList.get(i).getStrName());

                    if (AppConstants.eventList.get(i).getImage() != -1) {
                        tv_event_simbol.setBackgroundResource(AppConstants.eventList.get(i).getImage());
                    } else {
                        tv_event_simbol.setBackgroundResource(R.drawable.event_view);
                    }

                    if (AppConstants.eventCellBackgroundColor != -1) {
                        ll_month_events.setBackgroundColor(AppConstants.eventCellBackgroundColor);
                    }

                    if (!AppConstants.strEventCellBackgroundColor.equals("null")) {
                  //      ll_month_events.setBackgroundColor(Color.parseColor(AppConstants.strEventCellBackgroundColor));
                    }

                    if (AppConstants.eventCellTextColor != -1) {
                        tv_event_name.setTextColor(AppConstants.eventCellTextColor);
                        tv_event_date.setTextColor(AppConstants.eventCellTextColor);
                        tv_event_time.setTextColor(AppConstants.eventCellTextColor);
                    }

                    if (!AppConstants.strEventCellTextColor.equals("null")) {
                        tv_event_name.setTextColor(Color.parseColor(AppConstants.strEventCellTextColor));
                        tv_event_date.setTextColor(Color.parseColor(AppConstants.strEventCellTextColor));
                        tv_event_time.setTextColor(Color.parseColor(AppConstants.strEventCellTextColor));
                    }

                }

            }

            ll_parrent.addView(row_event_layout);


            ll_parrent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AppConstants.arrMyeventModel.clear();

                    for (int i = 0; i < AppConstants.eventList.size(); i++) {
                        if (AppConstants.eventList.get(i).getStrDate().equals(AppConstants.sdfDate.format(model.getDates()))) {
                            //  eventModelList.add(new EventModel(AppConstants.eventList.get(i).getStrDate(), AppConstants.eventList.get(i).getStrStartTime(), AppConstants.eventList.get(i).getStrEndTime(), AppConstants.eventList.get(i).getStrName()));
                            EventModel eventModel = AppConstants.eventList.get(i);

                            String string = eventModel.getStrName();
                            String dt2 = eventModel.getStrDate();

                            MyEventModel myEventModel = new MyEventModel();
                            myEventModel.setEventName(eventModel.getStrName());
                            myEventModel.setEventStartDate(eventModel.getStrDate() + " " + eventModel.getStrStartTime());
                            myEventModel.setEventEndDate(eventModel.getStrEndDate() + " " + eventModel.getStrEndTime());
                            AppConstants.arrMyeventModel.add(myEventModel);
                            //Toast.makeText(context,"WEEK  "+string,Toast.LENGTH_LONG).show();

                        }
                    }


                    int m = AppConstants.arrMyeventModel.size();
                    if(m != 0 ){
                       /* Common.calendarStartDate = YearFromate.format(main_calendar.getTime())
                                + "-" + MonthFromate.format(main_calendar.getTime())
                                + "-" + main_calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
*/
                       // String str = main_calendar.getD

                        int currentYear = main_calendar.get(Calendar.YEAR);
                        int currentMonth = main_calendar.get(Calendar.MONTH) + 1;
                        int currentDay = main_calendar.get(Calendar.DAY_OF_MONTH);

                        Toast.makeText(context,"Today's Date: " + currentYear + currentMonth + currentDay, Toast.LENGTH_SHORT).show();

                        System.out.println("Today's Date: " + currentYear + currentMonth + currentDay);
                        //eventDialog();
                    }else {
                        //showAlert(context,"No event or task");
                    }

                   // eventDialog();

                   // Toast.makeText(context,"Week Event",Toast.LENGTH_LONG).show();
                    String srt = model.getHours();
                   // Toast.makeText(context,srt,Toast.LENGTH_LONG).show();
                    onWeekDayViewClickListener.onClick(AppConstants.sdfDate.format(model.getDates()), model.getHours());
                }
            });


        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.row_week_view_show_events, parent, false);
        return new ShowEventsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ShowEventsModel showEventsModel = showEventsModelList.get(position);

        ShowEventsViewHolder showEventsViewHolder = (ShowEventsViewHolder) holder;
        showEventsViewHolder.setShowEvents(showEventsModel);

    }

    @Override
    public int getItemCount() {
        return showEventsModelList.size();
    }


    //DIALOG  TASK MESSAGE
    public void eventDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_my_event);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT ,LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        ImageView imv_cross = dialog.findViewById(R.id.imv_cross);

        recy_View = (RecyclerView) dialog.findViewById(R.id.recy_View);
        recy_View.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        recy_View.setLayoutManager(linearLayoutManager);

       /* Drawable dividerDrawable = ContextCompat.getDrawable(context, R.drawable.line_divider);
        recy_View.addItemDecoration(new com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration(dividerDrawable));
        adapter = new MyEventAdapter(context, AppConstants.arrMyeventModel);
        recy_View.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

        imv_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.arrMyeventModel.clear();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
