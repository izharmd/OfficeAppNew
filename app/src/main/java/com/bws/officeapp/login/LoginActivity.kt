package com.bws.officeapp.login

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.format.Formatter
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.Calendar.MyEventModel.MyEventAdapter
import com.bws.officeapp.DashboardOfficeAppActivity
import com.bws.officeapp.Lirary.AppConstants
import com.bws.officeapp.R

import com.bws.officeapp.databinding.ActivityLoginBinding
import com.bws.officeapp.login.loginmodel.LoginPram
import com.bws.officeapp.login.loginviewmodel.LoginFactory
import com.bws.officeapp.login.loginviewmodel.LoginRepository
import com.bws.officeapp.login.loginviewmodel.LoginViewModel
import com.bws.officeapp.registration.RagistrationActivity

import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import com.bws.officeapp.utils.SharedPreference
import com.bws.officeapp.utils.ToastMessage
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.google.gson.Gson
import kotlinx.android.synthetic.main.dialog_forgot_password.*

import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    // lateinit var database: DataBase
    lateinit var sharedPref: SharedPreference

    var isRememberMe = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        sharedPref = SharedPreference(this)


        binding.llSignUp.setOnClickListener() {
            startActivity(Intent(this@LoginActivity, RagistrationActivity::class.java))
        }

        binding.checkRememberMe.setOnCheckedChangeListener { buttonView, isChecked ->
            isRememberMe = isChecked
        }

        binding.txtForgotPassword.setOnClickListener() {

        }


        /*  database =
              Room.databaseBuilder(applicationContext, DataBase::class.java, "officeAppDB")
                  .build()

          GlobalScope.launch {
              database.loginDao()
                  .insertUserDetails(LoginTable(0, "izhar@gmail.com", "9163252224", "izhar@123"))
              database.userDao().insetUser(UserTable(0, "Izhar Ansari", "Giridih"))
          }
  */


        binding.btnLogin.setOnClickListener() {

            val isAllCheck = CheckAllFields()
            if (isAllCheck) {

                val sessionId = Random(100000000000).nextInt().toString()
                val wifiManager =
                    applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val ipAddress = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
                val deviceID =
                    Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

                val loginPram = LoginPram(
                    binding.edtUserName.text.toString(), binding.edtPassword.text.toString(),
                    sessionId, "1234445", ipAddress, "57.00000", deviceID
                )

                /*  val loginPram = LoginPram(
                  "test112@gmail.com", "Test@123",
                  sessionId,"1234445",ipAddress,"57.00000",deviceID)

  */

                val json = Gson()
                val j = json.toJson(loginPram).toString()
                System.out.println("LOGIN PRAM===" + loginPram.toString())
                val loginApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)
                val loginRepository = LoginRepository(loginApi, loginPram)
                val loginFactory = LoginFactory(loginRepository, this)
                val loginViewModel =
                    ViewModelProvider(this, loginFactory).get(LoginViewModel::class.java)

                val loadingDialog = LoadingDialog.progressDialog(this)

                loginViewModel.loginResult.observe(this, Observer {
                    when (it) {
                        is Response.NoInternet -> {
                            ToastMessage.message(this, it?.noInternetMessage.toString())
                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                        is Response.Loading -> {
                            loadingDialog.show()
                        }
                        is Response.Success -> {
                            loadingDialog.dismiss()
                            val userDetails = it.data?.data?.get(0)

                            if (isRememberMe == true) {
                                sharedPref.saveBoolean("IS_LOGIN", true)
                            } else {
                                sharedPref.saveBoolean("IS_LOGIN", false)
                            }

                            sharedPref.saveString("KEY_USER_ID", userDetails!!.UserID.toString())
                            sharedPref.saveString("KEY_TITLE", userDetails!!.Title)
                            sharedPref.saveString("KEY_FIRST_NAME", userDetails!!.FirstName)
                            sharedPref.saveString("KEY_LAST_NAME", userDetails!!.LastName)
                            sharedPref.saveString("KEY_EMAIL_ID", userDetails!!.EmailID)
                            sharedPref.saveString("KEY_DESIGNATION", userDetails!!.Designation)
                            sharedPref.saveString("KEY_DOB", userDetails!!.DOB)
                            sharedPref.saveString("KEY_ROLL_ID", userDetails!!.RoleID.toString())
                            sharedPref.saveString("KEY_ROLL_NAME", userDetails!!.RoleName)
                            sharedPref.saveString("KEY_MOBILE", userDetails!!.MobileNo.toString())
                            //  sharedPref.saveString("KEY_DOJ", userDetails!!.DOJ)

                            System.out.println("USER DETAILS==" + userDetails)

                            clearViewModel()
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    DashboardOfficeAppActivity::class.java
                                )
                            )

                            finish()
                        }
                        is Response.Error -> {
                            loadingDialog.dismiss()
                            clearViewModel()
                            ToastMessage.message(this, it.errorMessage.toString())
                        }
                    }
                })
            }
        }

    }


    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }

    fun CheckAllFields(): Boolean {
        if (binding.edtUserName.length() === 0) {
            binding.edtUserName.setError("This field is required")
            return false
        }
        if (binding.edtPassword.length() === 0) {
            binding.edtPassword.setError("This field is required")
            return false
        }
        // after all validation return true.
        return true
    }


}