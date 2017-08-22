package com.akses.blk.samarinda;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.akses.blk.samarinda.prefs.PencariInfo;
import com.akses.blk.samarinda.prefs.PencariSession;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPencari extends AppCompatActivity {

    private static final String TAG = HalamanPencari.class.getSimpleName();
    private EditText txtUsernamePencari, txtPasswordPencari;
    private Button btnLoginPencari;
    private ProgressDialog progressDialogPencari;
    private PencariSession pencariSession;
    private PencariInfo pencariInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pencari);


        txtUsernamePencari = (EditText) findViewById(R.id.username_pencari);
        txtPasswordPencari = (EditText) findViewById(R.id.password_pencari);
        btnLoginPencari = (Button) findViewById(R.id.btn_login_pencari);
        progressDialogPencari = new ProgressDialog(this);
        pencariSession = new PencariSession(this);
        pencariInfo = new PencariInfo(this);

        if (pencariSession.isPencariLoggedIn()) {

            startActivity(new Intent(this, HalamanPencari.class));
            finish();
        }

        btnLoginPencari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {

                    case R.id.btn_login_pencari:
                        if ("".equals(txtUsernamePencari.getText().toString())) {

                            txtUsernamePencari.setError("Username Tidak Boleh Kosong");
                            txtUsernamePencari.requestFocus();
                            return;
                        }

                        if ("".equals(txtPasswordPencari.getText().toString())) {

                            txtPasswordPencari.setError("Password Tidak Boleh Kosong");
                            txtPasswordPencari.requestFocus();
                            return;
                        }

                        String usernamePencari = txtUsernamePencari.getText().toString().trim();
                        String passwordPencari = txtPasswordPencari.getText().toString().trim();

                        loginPencari(usernamePencari, passwordPencari);
                        break;
                }

            }
        });
    }

    private void loginPencari(final String username, final String password) {

        String tag_string_req_pencari = "req_login_pencari";
        progressDialogPencari.setMessage("Logging in...");
        progressDialogPencari.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Common.LOGIN_PENCARI, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Login Response : " + response.toString());

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");

                    if (!error) {

                        JSONObject pencari = jsonObject.getJSONObject("user");
                        String usernamePencari = pencari.getString("username");
                        String namaPencari = pencari.getString("nama_pencari");

                        pencariInfo.setNamaPencari(namaPencari);
                        pencariInfo.setUsernamePencari(usernamePencari);

                        pencariSession.setPencariIsLoggedIn(true);

                        startActivity(new Intent(LoginPencari.this, HalamanPencari.class));
                        finish();
                    } else {

                        String errorMsg = jsonObject.getString("error_msg");
                        toast(errorMsg);
                        progressDialogPencari.hide();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    toast("Json error : " + ex.getMessage());
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, "Login Error: " + error.getMessage());
                toast("Koneksi Internet Anda TIdak Stabil");
                progressDialogPencari.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        AndroidLoginController.getInstance().addToRequestQueue(stringRequest, tag_string_req_pencari);
    }

    private void toast(String tag) {

        Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
    }

}
