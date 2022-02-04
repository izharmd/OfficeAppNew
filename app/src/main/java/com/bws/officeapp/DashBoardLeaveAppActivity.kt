package com.bws.officeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.bws.officeapp.leave.applyleave.LeaveActivity
import com.bws.officeapp.leave.leavesummery.LeaveSummeryActivity
import com.bws.officeapp.leavestatus.LeaveStatusActivity
import com.bws.officeapp.utils.DateHeader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class DashBoardLeaveAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        DateHeader().dateToHeader(this, textDate, textUserName,
            resources.getText(R.string.WELCOME_TO_LEAVE_APP).toString()
        )

        cardViewLeaveRecord.setOnClickListener(){
            startActivity(Intent(this@DashBoardLeaveAppActivity, LeaveSummeryActivity::class.java))
        }

        cardViewApplyLeave.setOnClickListener(){
            startActivity(Intent(this@DashBoardLeaveAppActivity, LeaveActivity::class.java))
        }

        carViewLeaveStatus.setOnClickListener(){
            startActivity(Intent(this@DashBoardLeaveAppActivity,LeaveStatusActivity::class.java))
        }

        cardViewProfile.setOnClickListener(){
            startActivity(Intent(this@DashBoardLeaveAppActivity,ProfileActivity::class.java))
        }

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this,imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this,imvBack)
    }
}