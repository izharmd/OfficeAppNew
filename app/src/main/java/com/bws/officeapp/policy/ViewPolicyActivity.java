package com.bws.officeapp.policy;

import android.os.Bundle;

import com.bws.officeapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_policy);

        PDFView pdfView=findViewById(R.id.pdfView);
        pdfView.fromAsset("leave_policy.pdf").load();
        pdfView.zoomTo(1f);
        pdfView.enableSwipe(true);
    }
}
