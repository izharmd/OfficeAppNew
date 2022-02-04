package com.bws.officeapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.login.LoginActivity
import com.bws.officeapp.utils.SharedPreference

class SplashActivity:AppCompatActivity() {

    lateinit var sharePfre:SharedPreference
    private val SPLASH_TIME_OUT = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        //supportActionBar?.hide()



        sharePfre = SharedPreference(this)

        Handler().postDelayed(
            {
                val isLogIn = sharePfre.getValueBoolean("IS_LOGIN",false)
                if(isLogIn == false) {
                    val i = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }else{
                    val i = Intent(this@SplashActivity, DashboardOfficeAppActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }, SPLASH_TIME_OUT)
    }
}