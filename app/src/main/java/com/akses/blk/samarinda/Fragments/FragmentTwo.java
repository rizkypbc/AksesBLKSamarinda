package com.akses.blk.samarinda.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.akses.blk.samarinda.FormPelatihan;
import com.akses.blk.samarinda.FormPelatihanBaru;
import com.akses.blk.samarinda.R;

/**
 * Created by ASUS on 09/05/2017.
 */

public class FragmentTwo extends Fragment implements View.OnClickListener {

//    private Button btnKerja, btnPerusahaan;


    public FragmentTwo(){

    }

//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaceState){
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.btnKerja);
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FormPelatihanBaru.class);
        startActivityForResult(intent, 1);
    }
}
