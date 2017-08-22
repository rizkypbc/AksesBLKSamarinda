package com.akses.blk.samarinda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akses.blk.samarinda.prefs.PencariInfo;
import com.akses.blk.samarinda.prefs.PencariSession;


public class HalamanPencari extends AppCompatActivity {

    private TextView txtGreeting, txtUsername;
    private ImageView btnLogout, btnLiatData;
    private PencariInfo pencariInfo;
    private PencariSession pencariSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_pencari);

        pencariInfo = new PencariInfo(this);
        pencariSession = new PencariSession(this);
        txtGreeting = (TextView) findViewById(R.id.greeting);
//        txtUsername = (TextView) findViewById(R.id.username);
        btnLogout = (ImageView) findViewById(R.id.btn_logout_pencari);
        btnLiatData = (ImageView) findViewById(R.id.btnLiatData);

        btnLiatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HalamanPencari.this, ListPencari.class));
            }
        });

        if (!pencariSession.isPencariLoggedIn()) {

            startActivity(new Intent(this, LoginPencari.class));
            finish();
        }

//        String usernamePencari = pencariInfo.getKeyUsernamePencari();
        String namaPencari = pencariInfo.getKeyNamaPencari();

        txtGreeting.setText("Selamat Datang " + namaPencari);
//        txtUsername.setText(usernamePencari);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pencariSession.setPencariIsLoggedIn(false);
                pencariInfo.clearPencariInfo();
                startActivity(new Intent(HalamanPencari.this, LoginPencari.class));
                finish();
            }
        });

    }
}
