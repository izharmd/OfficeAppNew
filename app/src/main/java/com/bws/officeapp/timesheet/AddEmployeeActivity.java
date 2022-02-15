package com.bws.officeapp.timesheet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bws.officeapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddEmployeeActivity extends AppCompatActivity {

    TextView txtAllocatedTo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        txtAllocatedTo = findViewById(R.id.txtAllocatedTo);

        txtAllocatedTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
