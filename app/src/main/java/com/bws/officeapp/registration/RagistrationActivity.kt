package com.bws.officeapp.registration


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityRegistrationBinding
import com.bws.officeapp.login.LoginActivity
import com.bws.officeapp.registration.designationviewmodel.DesignationFactory
import com.bws.officeapp.registration.designationviewmodel.DesignationPram
import com.bws.officeapp.registration.designationviewmodel.DesignationRepository
import com.bws.officeapp.registration.designationviewmodel.DesignationViewModel
import com.bws.officeapp.registration.registrationmodel.PramRegistration
import com.bws.officeapp.registration.registrationviewmodel.RegistrationFactory
import com.bws.officeapp.registration.registrationviewmodel.RegistrationRepository
import com.bws.officeapp.registration.registrationviewmodel.RegistrationViewModel
import com.bws.officeapp.utils.Common
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import com.google.gson.Gson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RagistrationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    DatePickerDialog.OnDateSetListener {

    lateinit var registrationViewModel: RegistrationViewModel
    lateinit var bindingActivity: ActivityRegistrationBinding


    var arrListDesignation = ArrayList<String>()
    var arrListDesignationID = ArrayList<String>()

    var selectDesignation = ""

    private var gender = "M"
    var designationId = ""
    var strDOB = ""
    var strDOJ = ""

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var currentDay = 0
    var currentMonth: Int = 0
    var currentYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        bindingActivity.spDesignation!!.setOnItemSelectedListener(this)


        initView()

        onClickEvent()
    }

    private fun initView() {

        val pram = DesignationPram("UserDesignationList")
        val designationApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val designationRepository = DesignationRepository(designationApi, pram)
        val designationFactory = DesignationFactory(designationRepository, this)
        val designationViewModel =
            ViewModelProvider(this, designationFactory).get(DesignationViewModel::class.java)

        val loadingDialog = LoadingDialog.progressDialog(this)

        designationViewModel.liveData.observe(this, Observer {
            when (it) {
                is Response.NoInternet -> {
                    Toast.makeText(this, "NO INTERNET", Toast.LENGTH_LONG).show()
                }
                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.Success -> {
                    for (i in 0..it.data!!.data.size - 1) {
                        arrListDesignation.add(it.data!!.data.get(i).RoleName)
                        arrListDesignationID.add(it.data!!.data.get(i).RoleID.toString())
                    }

                    val aa =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, arrListDesignation)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    bindingActivity.spDesignation!!.setAdapter(aa)

                    loadingDialog.dismiss()
                }
                is Response.Error -> {
                    Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_LONG).show()
                    loadingDialog.dismiss()
                }
            }
        })
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        currentDay = p3
        currentYear = p1
        currentMonth = p2 + 1

        // bindingActivity.txtDOB.setText(String.format("%02d",currentDay)+"-"+String.format("%02d",currentMonth)+"-"+currentYear)
    }


    private fun onClickEvent() {

        bindingActivity.rdGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rdMale -> {
                    gender = "M"
                }
                R.id.rdFemale -> {
                    gender = "F"
                }
            }
        }


        bindingActivity.txtDOB.setOnClickListener() {

            Common().dateDialog(this, bindingActivity.txtDOB)
            /*val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    bindingActivity.txtDOB.text = "" + dayOfMonth + "-" + month + 1 + "-" + year
                },
                year,
                month,
                day
            )
            dpd.show()*/

        }

        bindingActivity.txtDOJ.setOnClickListener {
            Common().dateDialog(this, bindingActivity.txtDOJ)

            /*val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    bindingActivity.txtDOJ.text = "" + dayOfMonth + "-" + month + 1 + "-" + year
                },
                year,
                month,
                day
            )
            dpd.show()*/
        }



        bindingActivity.btnRegister.setOnClickListener() {
            val originalFormat = SimpleDateFormat("dd-MM-yyyy")
            var dateFrom: Date
            var dateTo: Date
            try {
                dateFrom = originalFormat.parse(bindingActivity.txtDOB.text.toString())
                dateTo = originalFormat.parse(bindingActivity.txtDOJ.text.toString())
                strDOB = DateUtils.toSimpleString(dateFrom)
                strDOJ = DateUtils.toSimpleString(dateTo)
            } catch (ex: ParseException) {
                // Handle Exception.
            }

            val isAllCheck = CheckAllFields()
             if (isAllCheck) {

                val regisPram = PramRegistration(
                    designationId,
                    bindingActivity.edtTitle.text.toString(),
                    bindingActivity.edtFName.text.toString(),
                    bindingActivity.edtLName.text.toString(),
                    bindingActivity.edtPhoneNo.text.toString(),
                    bindingActivity.edtEmail.text.toString(),
                    selectDesignation,
                    strDOB,
                    gender,
                    bindingActivity.edtPassword.text.toString(),
                    strDOJ,
                )

            val json = Gson()

            val j = json.toJson(regisPram).toString()

               /* val regisPram1 = PramRegistration(
                 "2",
                 "Mr",
                 "Izhar",
                 "Ansari",
                 "1236547896",
                 "test11@gmail.com",
                 "Employee",
                 strDOB,
                 "M",
                 "Test@123",
                 strDOJ
             )*/

          //  val j1 = json.toJson(regisPram1).toString()

                System.out.println("REGISTRATION====" + j)

                val regFactory = RegistrationFactory(
                    RegistrationRepository(
                        RetrofitHelper.getInstance().create(ApiInterface::class.java), regisPram
                    ), this
                )
                registrationViewModel =
                    ViewModelProvider(this, regFactory).get(RegistrationViewModel::class.java)
                val loadingDialog = LoadingDialog.progressDialog(this)
                registrationViewModel.registrationLiveData.observe(this, Observer {
                    when (it) {
                        is Response.NoInternet -> {

                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                        is Response.Loading -> {

                            loadingDialog.show()
                        }
                        is Response.Success -> {
                            Common().successDialog(this,"Registration successful.")
                           /* Toast.makeText(this, it.data?.sMessage.toString(), Toast.LENGTH_LONG)
                                .show()*/
                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                        is Response.Error -> {
                            Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_LONG)
                                .show()
                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                    }
                })
          }
        }

        bindingActivity.llAllreadyAccount.setOnClickListener() {
            startActivity(Intent(this@RagistrationActivity, LoginActivity::class.java))
            finish()
        }
    }

    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }


    fun CheckAllFields(): Boolean {
        if (bindingActivity.edtTitle.length() === 0) {
            bindingActivity.edtTitle.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }
        if (bindingActivity.edtFName.length() === 0) {
            bindingActivity.edtFName.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }
        if (bindingActivity.edtLName.length() === 0) {
            bindingActivity.edtLName.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }

        if (bindingActivity.edtEmail.length() === 0) {
            bindingActivity.edtEmail.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }

        if (bindingActivity.edtPhoneNo.length() === 0) {
            bindingActivity.edtPhoneNo.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }

        if (bindingActivity.edtPhoneNo.length() === 0) {
            bindingActivity.edtPhoneNo.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }


        if (bindingActivity.edtPassword.text.toString() != (bindingActivity.edtConfirmPassWord.text.toString())) {
            bindingActivity.edtPassword.setError("Password mismatch")
            return false
        }


        if (bindingActivity.edtConfirmPassWord.length() === 0) {
            bindingActivity.edtConfirmPassWord.setError("This field is required")
            return false
        }

        // after all validation return true.
        return true
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when (parent) {
            bindingActivity.spDesignation -> {
                designationId = arrListDesignationID.get(position)
                selectDesignation = parent?.getItemAtPosition(position).toString()
            }
        }


        Log.d("q23we4r5", "werty")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // TODO("Not yet implemented")
    }

    object DateUtils {
        fun toSimpleString(date: Date): String {
            val format = SimpleDateFormat("yyyy.MM.dd")
            return format.format(date)
        }
    }


    fun saturdaysundaycount(d1: Date?, d2: Date?): Int {
        val c1 = Calendar.getInstance()
        c1.time = d1
        val c2 = Calendar.getInstance()
        c2.time = d2
        var sundays = 0
        var saturday = 0
        while (!c1.after(c2)) {
            if (c1[Calendar.DAY_OF_WEEK] === Calendar.SATURDAY) {
                saturday++
            }
            if (c1[Calendar.DAY_OF_WEEK] === Calendar.SUNDAY) {
                sundays++
            }
            c1.add(Calendar.DATE, 1)
        }
        println("Saturday Count = $saturday")
        println("Sunday Count = $sundays")
        return saturday + sundays
    }
}