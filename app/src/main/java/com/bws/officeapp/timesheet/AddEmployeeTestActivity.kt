package com.bws.officeapp.timesheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bws.officeapp.Api.Param
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityAddEmployeeBinding
import com.bws.officeapp.utils.DateHeader
import com.devstune.searchablemultiselectspinner.SearchableItem
import com.devstune.searchablemultiselectspinner.SearchableMultiSelectSpinner
import com.devstune.searchablemultiselectspinner.SelectionCompleteListener
import kotlinx.android.synthetic.main.toolba_reminder.*

class AddEmployeeTestActivity:AppCompatActivity() {

    lateinit var binding: ActivityAddEmployeeBinding

    var userList: MutableList<SearchableItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_employee)

        DateHeader().dateToHeader(this, textDate, textUserName,
            resources.getText(R.string.WELCOME_TO_LEAVE_APP).toString()
        )

        userList.add(SearchableItem("Mukesh","0"))
        userList.add(SearchableItem("Izhar","1"))
        userList.add(SearchableItem("Brotin","2"))
        userList.add(SearchableItem("Tanmoy","3"))
        userList.add(SearchableItem("Kanchan","4"))


        binding.txtAllocatedTo.setOnClickListener() {
            SearchableMultiSelectSpinner.show(this, "Search Employee", "Done", userList, object :
                SelectionCompleteListener {
                override fun onCompleteSelection(selectedItems: ArrayList<SearchableItem>) {

                    var arr = ArrayList<String>()
                    for (i in 0..selectedItems.size - 1) {
                        val itm = selectedItems.get(i)
                        arr.add(itm.text)
                    }
                    var str = arr.toString()


                    var myPiece = str.substring(1, str.length - 1)

                    binding.txtAllocatedTo.text = myPiece


                }
            })
        }


    }
}