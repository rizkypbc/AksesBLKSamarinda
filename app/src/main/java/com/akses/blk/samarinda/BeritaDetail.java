package com.akses.blk.samarinda;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akses.blk.samarinda.Model.Berita;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.juliomarcos.ImageViewPopUpHelper;
import com.squareup.picasso.Picasso;

/**
 * Created by ASUS on 07/06/2017.
 */

public class BeritaDetail extends AppCompatActivity implements View.OnClickListener  {

    ImageView ivImage;
    TextView tvTitle, tvDesc;

    boolean isImageFitToScreen;


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



        final ImagePopup imagePopup = new ImagePopup(this);
//        imagePopup.setWindowHeight(2000); // Optional
//        imagePopup.setWindowWidth(2000); // Optional
        imagePopup.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        imagePopup.setScaleType(ImageView.ScaleType.FIT_XY);
//        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setHideCloseIcon(true);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional
        ivImage.setOnClickListener(this);
//        ivImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                imagePopup.initiatePopup(ivImage.getDrawable());
////                if(isImageFitToScreen) {
////                    isImageFitToScreen=false;
////                    ivImage.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
////                    ivImage.setAdjustViewBounds(true);
////                    ivImage.
////                }else{
////                    isImageFitToScreen=true;
////                    ivImage.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
////                    ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
////                }
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        ImageViewPopUpHelper.enablePopUpOnClick(this, ivImage);
    }
}
