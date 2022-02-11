package com.bws.officeapp.policy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R
import com.bws.officeapp.utils.SharedPreference

class PolicyAdapter(val activity: Context,val mList: ArrayList<PolicyModel>) :
    RecyclerView.Adapter<PolicyAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_policy_document, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val policyModel = mList[position]
        holder.txtPolicyDocument.text = policyModel.policy

        val sharePref = SharedPreference(activity)

            holder.itemView.setOnClickListener(){

                sharePref.saveString("POLICY_DOCUMENT",policyModel.policyPdf)
                sharePref.saveString("POLICY_NAME",policyModel.policy)
                context?.startActivity(Intent(context,ViewPolicyActivity::class.java))
            }

    }

    override fun getItemCount(): Int {

        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtPolicyDocument: TextView = itemView.findViewById(R.id.txtPolicyDocument)

    }
}