package com.bws.officeapp.expense.utils

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import com.bws.officeapp.ProfileActivity
import com.bws.officeapp.R
import com.bws.officeapp.expense.AddNewExpenseActivity
import com.bws.officeapp.expense.ViewExpansegraphActivity
import com.bws.officeapp.expense.claim.AddNewClaimActivity
import com.bws.officeapp.expense.pendingapproval.PendingApprovalActivity
import com.bws.officeapp.expense.receipts.AddMyReceiptsActivity
import com.bws.officeapp.expense.trip.AddNewTripActivity
import com.bws.officeapp.login.LoginActivity
import com.bws.officeapp.utils.SharedPreference

class MyPopUpMenu {

    lateinit var sharePref: SharedPreference

    fun popuLateMenu(context: Activity, imag: ImageView) {

        imag.setOnClickListener() {
            val popupMenu: PopupMenu = PopupMenu(context, imag)
            popupMenu.menuInflater.inflate(R.menu.menu_expense, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.ddExpense ->
                        context.startActivity(Intent(context, AddNewExpenseActivity::class.java))
                    R.id.expenseDetails ->
                        context.startActivity(Intent(context, ViewExpansegraphActivity::class.java))

                    R.id.pendingApproval ->
                        context.startActivity(Intent(context, PendingApprovalActivity::class.java))

                    R.id.addClaim ->
                        context.startActivity(Intent(context, AddNewClaimActivity::class.java))

                    R.id.addTrip ->
                        context.startActivity(Intent(context, AddNewTripActivity::class.java))

                    R.id.addReceipt ->
                        context.startActivity(Intent(context, AddMyReceiptsActivity::class.java))

                }
                true
            })
            popupMenu.show()
        }

    }

    fun populateMenuLeave(context: Activity, imag: ImageView) {

        sharePref = SharedPreference(context)

        imag.setOnClickListener() {
            val popupMenu: PopupMenu = PopupMenu(context, imag)
            popupMenu.menuInflater.inflate(R.menu.menu_leave, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.profile -> {
                        context.startActivity(Intent(context, ProfileActivity::class.java))
                    }
                    R.id.logout -> {
                        sharePref.removeValue("KEY_USER_ID")
                        sharePref.removeValue("IS_LOGIN")
                        createDialog(context)
                    }
                }
                true
            })
            popupMenu.show()
        }
    }

    fun backToActivity(context: Activity, imvBack: ImageView) {
        imvBack.setOnClickListener {
            context.finish()
        }
    }


    fun createDialog(context: Activity) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Office App")
        builder.setMessage("Are you sure want to logout?")
        builder.setPositiveButton("Yes") { dialog, which ->

            val i = Intent(
                context,
                LoginActivity::class.java
            )        // Specify any activity here e.g. home or splash or login etc
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            context.startActivity(i)
            dialog.cancel()
        }

        builder.setNegativeButton("No") { dialog, which ->

        }
        builder.show()
    }
}