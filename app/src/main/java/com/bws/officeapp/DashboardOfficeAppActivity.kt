package com.bws.officeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.expense.ExpenseOverViewActivity
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.bws.officeapp.policy.PolicyDocumentActivity
import com.bws.officeapp.timesheet.TimeSheetDashboardActivity
import com.bws.officeapp.utils.DateHeader
import kotlinx.android.synthetic.main.activity_dashboard_office.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class DashboardOfficeAppActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_office)
        supportActionBar?.hide()
        DateHeader().dateToHeader(this, textDate, textUserName,"Internal Office App")

        cardViewExpenseApp.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,ExpenseOverViewActivity::class.java))
        }

        cardViewLeaveApp.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,DashBoardLeaveAppActivity::class.java))
        }

        carViewTimeSheetApp.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,TimeSheetDashboardActivity::class.java))
        }

        cardViewProfile.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,ProfileActivity::class.java))
        }

        cardViewPolicyDocument.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,PolicyDocumentActivity::class.java))
        }

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this,imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this,imvBack)
    }
}