package com.bws.officeapp.leavestatus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyLeaveFactory
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyLeaveRepo
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyViewModel
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusFactory
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusRepo
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusViewModel
import com.bws.officeapp.utils.DateHeader
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import com.bws.officeapp.utils.SharedPreference
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_leave_status.*
import kotlinx.android.synthetic.main.toolba_reminder.*
import java.util.ArrayList

class LeaveStatusActivity : AppCompatActivity() {

    lateinit var sharePref:SharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_status)
        supportActionBar?.hide()
        sharePref = SharedPreference(this)
        DateHeader().dateToHeader(this, textDate, textUserName,
            resources.getText(R.string.WELCOME_TO_LEAVE_APP).toString()
        )
        recyLeaveStatus.layoutManager = LinearLayoutManager(this)

        val pram = Param.PramUserLeaveDetails(sharePref.getValueString("KEY_USER_ID").toString(), "10", "1")


        val leaveStatusVM = ViewModelProvider(
            this,
            LeaveStatusFactory(
                LeaveStatusRepo(
                    RetrofitHelper.getInstance().create(ApiInterface::class.java),
                    pram
                ), this
            )
        ).get(LeaveStatusViewModel::class.java)

        val loadingDialog = LoadingDialog.progressDialog(this)


        leaveStatusVM.liveData.observe(this, Observer {

            when (it) {

                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.NoInternet -> {
                    loadingDialog.dismiss()
                }
                is Response.Success -> {

                    val dividerDrawable =
                        ContextCompat.getDrawable(this@LeaveStatusActivity, R.drawable.line_divider)
                    recyLeaveStatus.addItemDecoration(DividerItemDecoration(dividerDrawable))

                    val adapter = LeaveStatusAdapter(this,it)
                    recyLeaveStatus.adapter = adapter
                    adapter.notifyDataSetChanged()

                    loadingDialog.dismiss()
                }
                is Response.Error -> {
                    loadingDialog.dismiss()
                }
            }
        })

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this,imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this,imvBack)

    }
}