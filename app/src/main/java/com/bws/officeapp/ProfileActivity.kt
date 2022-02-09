package com.bws.officeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bws.officeapp.databinding.ActivityProfile2Binding
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.bws.officeapp.utils.DateHeader
import com.bws.officeapp.utils.SharedPreference
import kotlinx.android.synthetic.main.toolba_reminder.*
import java.text.SimpleDateFormat
import java.util.*

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfile2Binding
    lateinit var sharePref: SharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile2)
        supportActionBar?.hide()
        DateHeader().dateToHeader(
            this, textDate, textUserName,
            resources.getText(R.string.WELCOME_TO_LEAVE_APP).toString()
        )
        sharePref = SharedPreference(this)


        binding.txtName.text =
            sharePref.getValueString("KEY_TITLE") + " " + sharePref.getValueString("KEY_FIRST_NAME") + " " + sharePref.getValueString(
                "KEY_LAST_NAME"
            )
        binding.txtEmpId.text = sharePref.getValueString("KEY_USER_ID")
        binding.txtDesignation.text = sharePref.getValueString("KEY_DESIGNATION")
        binding.txtDateOfJoining.text = sharePref.getValueString("KEY_DOJ")
        binding.txtEmail.text = sharePref.getValueString("KEY_EMAIL_ID")
        binding.txtDOB.text = sharePref.getValueString("KEY_DOB")
        binding.txtMobileNo.text = sharePref.getValueString("KEY_MOBILE")

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this,imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this,imvBack)
    }
}