package com.example.asus.sampleblki;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.sampleblki.Model.Berita;
import com.squareup.picasso.Picasso;

/**
 * Created by ASUS on 07/06/2017.
 */

public class BeritaDetail extends AppCompatActivity {

    ImageView ivImage;
    TextView tvTitle, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.berita_detail);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDesc = (TextView) findViewById(R.id.tvDesc);

        if (getIntent().getSerializableExtra("berita") != null) {
            Berita berita = (Berita) getIntent().getSerializableExtra("berita");
//            String fullUrl = "http://10.223.242.157/kios/" + berita.photoberita;
            String fullUrl = "http://putrarizky1410.16mb.com/kios/" + berita.photoberita;
            Picasso.with(this)
                    .load(fullUrl)
                    .placeholder(R.drawable.news)
                    .error(android.R.drawable.stat_notify_error)
                    .into(ivImage);

            tvTitle.setText(berita.judulberita);
            tvDesc.setText(berita.deskripsi);
        }
    }
}
