package com.akses.blk.samarinda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akses.blk.samarinda.prefs.UserInfo;
import com.akses.blk.samarinda.prefs.UserSession;

/**
 * Created by ASUS on 25/06/2017.
 */

public class WelcomeActivity extends AppCompatActivity {


    private Button logout, tambahBerita;
    private TextView tvUsername, tvEmail;
    private UserInfo userInfo;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        userInfo        = new UserInfo(this);
        userSession     = new UserSession(this);
        logout          = (Button)findViewById(R.id.logout);
        tambahBerita    = (Button)findViewById(R.id.btnTambahBerita);
        tvUsername      = (TextView)findViewById(R.id.key_username);
        tvEmail         = (TextView)findViewById(R.id.key_email);

        if(!userSession.isUserLoggedin()){
            startActivity(new Intent(this, FormLoginPerusahaan.class));
            finish();
        }

        String username = userInfo.getKeyUsername();
        String email    = userInfo.getKeyEmail();

        tvUsername.setText("Selamat Datang " + username);
        tvEmail.setText(email);

        tambahBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  startActivity(new Intent(WelcomeActivity.this, FormTambahBerita.class));
//                Intent intent = new Intent(getApplication(), FormTambahBerita.class);
//                startActivityForResult(intent, 1);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSession.setLoggedin(false);
                userInfo.clearUserInfo();
                startActivity(new Intent(WelcomeActivity.this, FormLoginPerusahaan.class));
                finish();
            }
        });


    }
}

//    TextView tvWelcome;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//        tvWelcome = (TextView) findViewById(R.id.tv_welcome);
//        tvWelcome.setText("Welcome: " + getIntent().getStringExtra("username"));
//
//    }
//
//    public void btnBack_Click(View v){
//        finish();
//    }
//}
