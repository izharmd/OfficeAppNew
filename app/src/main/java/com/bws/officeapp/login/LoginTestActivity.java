package com.bws.officeapp.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bws.officeapp.Calendar.CalendarActivity;
import com.bws.officeapp.Calendar.MyEventModel.MyEventAdapter;
import com.bws.officeapp.DashboardOfficeAppActivity;
import com.bws.officeapp.Lirary.AppConstants;
import com.bws.officeapp.R;
import com.bws.officeapp.registration.RagistrationActivity;
import com.bws.officeapp.utils.LoadingDialog;
import com.bws.officeapp.utils.PreferenceConnector;
import com.bws.officeapp.utils.SharedPreference;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class LoginTestActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword;
    TextView btnLogin, txtForgotPassword;
    LinearLayout ll_SignUp;

    CheckBox checkRememberMe;

    boolean rememberMeCheck = false;

    EditText edtEmailChangePass, edtResetPassword, edtOTP;

    Button btnSendEmail, btnResetPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);
        // getSupportActionBar().hide();

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        ll_SignUp = findViewById(R.id.ll_SignUp);

        checkRememberMe = findViewById(R.id.checkRememberMe);

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doalogSendEmail();
            }
        });

        checkRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreference sharedPref = new SharedPreference(LoginTestActivity.this);
                if (isChecked == true) {

                    rememberMeCheck = isChecked;
                } else {
                    rememberMeCheck = isChecked;

                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtForgotPassword.getText().toString();
                String pass = edtUserName.getText().toString();

                if (email.length() == 0) {
                    txtForgotPassword.setError("Email required");
                } else if (pass.length() == 0) {
                    edtUserName.setError("Password required");
                } else {
                    callLogin();
                }
            }
        });


        ll_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginTestActivity.this, RagistrationActivity.class));
            }
        });
    }

    public void callLogin() {
        Dialog dialog = LoadingDialog.Companion.progressDialog(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("EmailID", edtUserName.getText().toString());
            jsonObject.put("Password", edtPassword.getText().toString());
            jsonObject.put("SessionID", "123455");
            jsonObject.put("DeviceToken", "12334567assdfghh");
            jsonObject.put("IPAddress", "0.0.0.0");
            jsonObject.put("DeviceLocation", "50.000000");
            jsonObject.put("DeviceID", "12345");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("JSON==" + jsonObject.toString());

        HttpEntity entity;
        try {
            entity = new StringEntity(String.valueOf(jsonObject), "UTF-8");
        } catch (IllegalArgumentException e) {
            Log.d("HTTP", "StringEntity: IllegalArgumentException");
            return;
        }
        String contentType = "application/json; charset=utf-8";

        String url = "http://bitwavesolutions.co.uk/OfficeApp/api/service/Userlogin";
        client.addHeader("API_KEY", "A862A321-15CA-4265-B188-3959E38A94D2");

        client.post(this, url, entity, contentType, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String asynchResult = new String(response);
                dialog.dismiss();

                try {
                    JSONObject object = new JSONObject(asynchResult);
                    String status = object.getString("sStatus");
                    String msg = object.getString("sMessage");
                    if (status.equalsIgnoreCase("SUCCESS")) {
                        JSONArray array = object.getJSONArray("data");
                        SharedPreference sharedPref = new SharedPreference(LoginTestActivity.this);
                        for (int i = 0; i < array.length(); i++) {
                            if (rememberMeCheck == true) {
                                sharedPref.saveBoolean("IS_LOGIN", true);
                            } else {
                                sharedPref.saveBoolean("IS_LOGIN", false);
                            }

                            JSONObject jsonObject1 = array.getJSONObject(i);
                            sharedPref.saveString("KEY_USER_ID", jsonObject1.getString("UserID"));
                            sharedPref.saveString("KEY_TITLE", jsonObject1.getString("Title"));
                            sharedPref.saveString("KEY_FIRST_NAME", jsonObject1.getString("FirstName"));
                            sharedPref.saveString("KEY_LAST_NAME", jsonObject1.getString("LastName"));
                            sharedPref.saveString("KEY_EMAIL_ID", jsonObject1.getString("EmailID"));
                            sharedPref.saveString("KEY_DESIGNATION", jsonObject1.getString("Designation"));
                            sharedPref.saveString("KEY_DOB", jsonObject1.getString("DOB"));
                            sharedPref.saveString("KEY_ROLL_ID", String.valueOf(jsonObject1.getInt("RoleID")));
                            sharedPref.saveString("KEY_ROLL_NAME", jsonObject1.getString("RoleName"));
                            sharedPref.saveString("KEY_MOBILE", jsonObject1.getString("MobileNo"));
                            sharedPref.saveString("KEY_DOJ", jsonObject1.getString("DOJ"));

                        }
                        startActivity(new Intent(LoginTestActivity.this, DashboardOfficeAppActivity.class));
                        finish();

                    } else {
                        Toast.makeText(LoginTestActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginTestActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("HTTP", "Post...");
                dialog.dismiss();
                Toast.makeText(LoginTestActivity.this, String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });


    }


    public void doalogSendEmail() {
        final Dialog dialog = new Dialog(LoginTestActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_forgot_password);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        ImageView imv_cross = dialog.findViewById(R.id.imv_cross);
        edtEmailChangePass = dialog.findViewById(R.id.edtEmailChangePass);
        edtResetPassword = dialog.findViewById(R.id.edtResetPassword);
        edtOTP = dialog.findViewById(R.id.edtOTP);

        btnSendEmail = dialog.findViewById(R.id.btnSendEmail);
        btnResetPassword = dialog.findViewById(R.id.btnResetPassword);

        edtResetPassword.setVisibility(View.GONE);
        edtOTP.setVisibility(View.GONE);
        btnResetPassword.setVisibility(View.GONE);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtEmailChangePass.getText().toString().length() == 0) {
                    edtEmailChangePass.setError("Enter email id");
                }else {
                    callSendEmail(edtEmailChangePass.getText().toString());
                }
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtEmailChangePass.getText().toString())){
                    edtEmailChangePass.setError("Email required");
                }else if(TextUtils.isEmpty(edtResetPassword.getText().toString())){
                    edtResetPassword.setError("Enter new  password");
                }else if(TextUtils.isEmpty(edtOTP.getText().toString())){
                    edtOTP.setError("Enter OTP");
                }else {
                    callResetPassword(edtEmailChangePass.getText().toString(), edtResetPassword.getText().toString(), edtOTP.getText().toString());
                }
            }
        });


        imv_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.arrMyeventModel.clear();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void callSendEmail(String email) {

        Dialog dialog = LoadingDialog.Companion.progressDialog(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("EmailID", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("JSON==" + jsonObject.toString());

        HttpEntity entity;
        try {
            entity = new StringEntity(String.valueOf(jsonObject), "UTF-8");
        } catch (IllegalArgumentException e) {
            Log.d("HTTP", "StringEntity: IllegalArgumentException");
            return;
        }
        String contentType = "application/json; charset=utf-8";

        String url = "http://bitwavesolutions.co.uk/OfficeApp/api/service/ResetPassword";
        client.addHeader("API_KEY", "A862A321-15CA-4265-B188-3959E38A94D2");

        client.post(this, url, entity, contentType, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String asynchResult = new String(response);
                dialog.dismiss();

                try {
                    JSONObject object = new JSONObject(asynchResult);
                    String status = object.getString("sStatus");
                    String msg = object.getString("sMessage");
                    if (status.equalsIgnoreCase("SUCCESS")) {
                        Toast.makeText(LoginTestActivity.this, msg, Toast.LENGTH_LONG).show();
                        edtResetPassword.setVisibility(View.VISIBLE);
                        edtOTP.setVisibility(View.VISIBLE);
                        btnResetPassword.setVisibility(View.VISIBLE);
                        btnSendEmail.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(LoginTestActivity.this, msg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginTestActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("HTTP", "Post...");
                dialog.dismiss();
                Toast.makeText(LoginTestActivity.this, String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });

    }


    public void callResetPassword(String email, String pass, String otp) {

        Dialog dialog = LoadingDialog.Companion.progressDialog(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("EmailID", email);
            jsonObject.put("NewPassword", pass);
            jsonObject.put("ActivationCode", otp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("JSON==" + jsonObject.toString());

        HttpEntity entity;
        try {
            entity = new StringEntity(String.valueOf(jsonObject), "UTF-8");
        } catch (IllegalArgumentException e) {
            Log.d("HTTP", "StringEntity: IllegalArgumentException");
            return;
        }
        String contentType = "application/json; charset=utf-8";

        String url = "http://bitwavesolutions.co.uk/OfficeApp/api/service/ResetPassword";
        client.addHeader("API_KEY", "A862A321-15CA-4265-B188-3959E38A94D2");

        client.post(this, url, entity, contentType, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String asynchResult = new String(response);
                dialog.dismiss();

                try {
                    JSONObject object = new JSONObject(asynchResult);
                    String status = object.getString("sStatus");
                     String msg = object.getString("sMessage");

                     if(status.equalsIgnoreCase("SUCCESS")){
                         Toast.makeText(LoginTestActivity.this, msg, Toast.LENGTH_LONG).show();
                     }else {
                         Toast.makeText(LoginTestActivity.this, msg, Toast.LENGTH_LONG).show();
                     }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginTestActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("HTTP", "Post...");
                dialog.dismiss();
                Toast.makeText(LoginTestActivity.this, String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });

    }

}
