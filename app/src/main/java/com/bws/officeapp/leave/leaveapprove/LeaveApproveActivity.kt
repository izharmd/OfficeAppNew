package com.bws.officeapp.leave.leaveapprove

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityLeaveApproveBinding
import com.bws.officeapp.databinding.ActivityProfile2Binding
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.bws.officeapp.leave.leaveapprove.leaveapproveviewmodel.LeaveApproveFactory
import com.bws.officeapp.leave.leaveapprove.leaveapproveviewmodel.LeaveApproveRepo
import com.bws.officeapp.leave.leaveapprove.leaveapproveviewmodel.LeaveApproveViewModel
import com.bws.officeapp.leave.leavesummery.LeaveSummeryFactory
import com.bws.officeapp.leave.leavesummery.LeaveSummeryRepo
import com.bws.officeapp.leave.leavesummery.LeaveSummeryViewModel
import com.bws.officeapp.utils.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.toolba_reminder.*

class LeaveApproveActivity : AppCompatActivity() {

    lateinit var sharePref: SharedPreference

    lateinit var binding: ActivityLeaveApproveBinding

    var IsApproved = "Y"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_leave_approve)
        actionBar?.hide()
        sharePref = SharedPreference(this)
        DateHeader().dateToHeader(this, textDate, textUserName,
            resources.getText(R.string.WELCOME_TO_LEAVE_APP).toString()
        )

        binding.txtEmpId.text = sharePref.getValueString("EMPLOYEE_ID")
        binding.txtEmpName.text = sharePref.getValueString("EMPLOYEE_NAME")
        binding.txtDayOfLeave.text = sharePref.getValueString("DAY_OF_LEAVE")
        binding.txtLeaveFrom.text = sharePref.getValueString("LEAVE_FROM")
        binding.txtLeaveTo.text = sharePref.getValueString("LEAVE_TO")
        binding.txtLeaveStatus.text = sharePref.getValueString("REASON")

        clickEvent()
    }

    private fun clickEvent() {
        binding.txtReject.setOnClickListener {
            IsApproved = "N"
            leaveApproveReject()
        }

        binding.txtApprove.setOnClickListener {
            IsApproved = "Y"
            leaveApproveReject()
        }
    }

    private fun leaveApproveReject() {
        val pram = Param.PramApproveLeave(
            sharePref.getValueString("EMPLOYEE_ID").toString(),
            "1",
            IsApproved,
            binding.edtReason.text.toString()
        )
        val applyVM = ViewModelProvider(
            this,
            LeaveApproveFactory(
                LeaveApproveRepo(
                    RetrofitHelper.getInstance().create(ApiInterface::class.java),
                    pram
                ), this
            )
        ).get(LeaveApproveViewModel::class.java)

        val gson = Gson()
        System.out.println("APPROVE JSON"+gson.toJson(pram))

        val loadingDialog = LoadingDialog.progressDialog(this)

        applyVM.liveData.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.NoInternet -> {
                    loadingDialog.dismiss()
                    clearViewModel()
                }
                is Response.Success -> {
                    loadingDialog.dismiss()
                    ToastMessage.message(this,it.data?.sMessage.toString())
                    clearViewModel()
                }
                is Response.Error -> {
                    loadingDialog.dismiss()
                    ToastMessage.message(this, it.errorMessage.toString())
                    clearViewModel()
                }
            }
        })

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this, imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this, imvBack)

    }


    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }
}