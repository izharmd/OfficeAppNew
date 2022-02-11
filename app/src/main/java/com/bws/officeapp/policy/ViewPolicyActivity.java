package com.bws.officeapp.policy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bws.officeapp.R;
import com.bws.officeapp.expense.utils.MyPopUpMenu;
import com.bws.officeapp.utils.DateHeader;
import com.bws.officeapp.utils.SharedPreference;
import com.github.barteksc.pdfviewer.PDFView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewPolicyActivity extends AppCompatActivity {

    TextView textUserName,textDate,txtDocHeader;
    ImageView imv_Shutdown,imvBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_policy);
        SharedPreference sharePref = new SharedPreference(this);

        textUserName = findViewById(R.id.textUserName);
        textDate = findViewById(R.id.textDate);
        txtDocHeader = findViewById(R.id.txtDocHeader);
        imv_Shutdown = findViewById(R.id.imv_Shutdown);
        imvBack = findViewById(R.id.imvBack);

        MyPopUpMenu myPopUpMenu = new MyPopUpMenu();
        myPopUpMenu.populateMenuLeave(this,imv_Shutdown);
        myPopUpMenu.backToActivity(this,imvBack);

        DateHeader dateHeader = new DateHeader();
        dateHeader.dateToHeader(this,textDate,textUserName, "Welcome To Office App");

        txtDocHeader.setText(sharePref.getValueString("POLICY_NAME"));

        PDFView pdfView=findViewById(R.id.pdfView);
        pdfView.fromAsset(sharePref.getValueString("POLICY_DOCUMENT")).load();
        pdfView.zoomTo(1f);
        pdfView.enableSwipe(true);
        pdfView.getPageCount();
        pdfView.getCurrentPage();
        pdfView.canScrollVertically(1);

    }
}
