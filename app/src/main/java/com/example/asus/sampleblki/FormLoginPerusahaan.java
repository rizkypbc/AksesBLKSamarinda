package com.example.asus.sampleblki;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.sampleblki.Api.AccessServiceAPI;
import com.example.asus.sampleblki.prefs.UserInfo;
import com.example.asus.sampleblki.prefs.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 25/06/2017.
 */

public class FormLoginPerusahaan extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = FormLoginPerusahaan.class.getSimpleName();
    private EditText username, password;
    private Button login;
    private TextView signup;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_perusahaan);

        username = (EditText) findViewById(R.id.txt_username_login);
        password = (EditText) findViewById(R.id.txt_pwd_login);
        login = (Button) findViewById(R.id.loginperusahaan);
//        signup = (TextView) findViewById(R.id.open_signup);
        progressDialog = new ProgressDialog(this);
        session = new UserSession(this);
        userInfo = new UserInfo(this);

        if (session.isUserLoggedin()) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }

        login.setOnClickListener(this);
    }

    private void login(final String username, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Common.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // Now store the user in SQLite
                        JSONObject user = jObj.getJSONObject("user");
                        String uName = user.getString("username");
//                        String email = user.getString("email");

                        // Inserting row in users table
//                        userInfo.setEmail(email);
                        userInfo.setUsername(uName);
                        session.setLoggedin(true);

                        startActivity(new Intent(FormLoginPerusahaan.this, WelcomeActivity.class));
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        toast(errorMsg);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    toast("Json error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                toast("Unknown Error occurred");
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AndroidLoginController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void toast(String x) {
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginperusahaan:
                String uName = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                login(uName, pass);
                break;
        }
    }
}

//    private EditText txtUsername;
//    private EditText txtPassword;
//    private AccessServiceAPI m_ServiceAccess;
//    private ProgressDialog m_ProgressDialog;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_perusahaan);
//
//        txtUsername = (EditText) findViewById(R.id.txt_username_login);
//        txtPassword = (EditText) findViewById(R.id.txt_pwd_login);
//        m_ServiceAccess = new AccessServiceAPI();
//    }
//
//    public void btnLogin_Click(View v) {
//        if ("".equals(txtUsername.getText().toString())) {
//            txtUsername.setError("Username is required!");
//            return;
//        }
//        if ("".equals(txtPassword.getText().toString())) {
//            txtPassword.setError("Password is required!");
//            return;
//        }
//        //Call async task to login
//        new TaskLogin().execute(txtUsername.getText().toString(), txtPassword.getText().toString());
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 1) {
//            txtUsername.setText(data.getStringExtra("username"));
//            txtPassword.setText(data.getStringExtra("password"));
//        }
//    }
//
//    public class TaskLogin extends AsyncTask<String, Void, Integer>{
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //Open progress dialog during login
//            m_ProgressDialog  = ProgressDialog.show(FormLoginPerusahaan.this, "Please wait....",  "Processing...", true);
//        }
//
//
//        @Override
//        protected Integer doInBackground(String... params) {
//            //Create data to pass in param
//            Map<String, String> param = new HashMap<>();
//            param.put("action", "login");
//            param.put("username", params[0]);
//            param.put("password", params[1]);
//
//            JSONObject jObResult;
//            try {
//
//                jObResult = m_ServiceAccess.convertJSONString2Obj(m_ServiceAccess.getJSONStringWithParam_POST
//                        (Common.SERVICE_API_URL_LOGIN_PERUSAHAAN, param));
//                return jObResult.getInt("result");
//            } catch (Exception e){
//
//                return Common.RESULT_ERROR;
//
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(Integer result) {
//            super.onPostExecute(result);
//            m_ProgressDialog.dismiss();
//            if (Common.RESULT_SUCCESS == result){
//                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
//                i.putExtra("username", txtUsername.getText().toString());
//                startActivity(i);
//            } else {
//                Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//}
