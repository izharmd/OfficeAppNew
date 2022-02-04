package com.bws.officeapp.timesheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.bws.officeapp.ProfileActivity
import com.bws.officeapp.R
import com.bws.officeapp.timesheet.dailitimesheet.DailyTimeSheetActivity
import com.bws.officeapp.timesheet.searchproject.SearchProjectActivity
import com.bws.officeapp.Calendar.CalendarActivity
import com.bws.officeapp.utils.DateHeader
import com.bws.timesheet.projectstatus.ProjectStatusActvity
import kotlinx.android.synthetic.main.activity_timesheet.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class TimeSheetDashboardActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet)
        supportActionBar?.hide()
        DateHeader().dateToHeader(this, textDate, textUserName,
            resources.getText(R.string.WELCOME_TO_LEAVE_APP).toString()
        )
        cardViewDailyTimeSheet.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity, DailyTimeSheetActivity::class.java))
        }

        carViewManageProject.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity, SearchProjectActivity::class.java))
        }

        cardViewCalendar.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity,CalendarActivity::class.java))
        }

        cardViewProjectStatus.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity,ProjectStatusActvity::class.java))
        }

        imvBack.setOnClickListener(){
            finish()
        }
    }
}