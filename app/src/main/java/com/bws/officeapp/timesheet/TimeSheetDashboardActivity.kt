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
import com.bws.officeapp.expense.utils.MyPopUpMenu
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


        val time = 480
        val d = time / (8 * 60)
        val h = time % (8 * 60) / 60
        val m = time % (8 * 60) % 60
       val t =  d.toString() +" Days "+ h.toString() +" Hours " + m.toString() +"Min"
        System.out.println("TTTTTTT===="+t)

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this,imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this,imvBack)

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