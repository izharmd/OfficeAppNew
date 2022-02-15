package com.bws.officeapp.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityEditProfileBinding
import com.bws.officeapp.utils.DateHeader
import com.bws.officeapp.utils.SharedPreference
import kotlinx.android.synthetic.main.toolba_reminder.*

class EditProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProfileBinding
    lateinit var sharePref: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)

        supportActionBar?.hide()
        DateHeader().dateToHeader(
            this, textDate, textUserName,
            resources.getText(R.string.WELCOME_TO_LEAVE_APP).toString()
        )
        sharePref = SharedPreference(this)

        binding.edtEmpName.setText(
            sharePref.getValueString("KEY_TITLE") + " " + sharePref.getValueString("KEY_FIRST_NAME") + " " + sharePref.getValueString(
                "KEY_LAST_NAME"
            )
        )
        binding.txtEmpId.text = sharePref.getValueString("KEY_USER_ID")
        binding.edtEmpDesignation.setText(sharePref.getValueString("KEY_DESIGNATION"))
        binding.extDOJ.text = sharePref.getValueString("KEY_DOJ")
        binding.edtEmailID.setText(sharePref.getValueString("KEY_EMAIL_ID"))
        binding.txtDOB.text = sharePref.getValueString("KEY_DOB")
        binding.edtMobileNo.setText(sharePref.getValueString("KEY_MOBILE"))
    }
}