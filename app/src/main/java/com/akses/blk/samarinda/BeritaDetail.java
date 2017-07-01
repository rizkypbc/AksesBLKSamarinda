package com.akses.blk.samarinda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.akses.blk.samarinda.Model.Berita;
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
            String fullUrl = "http://aksesblk-samarinda.com/aksesblksamarinda/img/berita/" + berita.nama_file;
//            String fullUrl = "http://aksesblk-samarinda.com/aksesblksamarinda/kios/" + berita.photoberita;
            Picasso.with(this)
                    .load(fullUrl)
                    .placeholder(R.drawable.news)
                    .error(android.R.drawable.stat_notify_error)
                    .into(ivImage);

//            tvTitle.setText(berita.judulberita);
            tvTitle.setText(berita.judul);
            tvDesc.setText(berita.deskripsi);
        }
    }
}
