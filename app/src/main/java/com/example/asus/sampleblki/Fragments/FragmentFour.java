package com.example.asus.sampleblki.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.example.asus.sampleblki.FormLoginPerusahaan;
import com.example.asus.sampleblki.FormPerusahaan;
import com.example.asus.sampleblki.R;

/**
 * Created by ASUS on 09/05/2017.
 */

public class FragmentFour extends Fragment implements View.OnClickListener {

    public FragmentFour() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        ImageView button = (ImageView) view.findViewById(R.id.btnPerusahaan);
        ImageView login = (ImageView) view.findViewById(R.id.btnLoginPerusahaan);
        login.setOnClickListener(this);
        button.setOnClickListener(this);
        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPerusahaan:
                Intent intent = new Intent(getActivity(), FormPerusahaan.class);
                startActivityForResult(intent, 1);
                break;

            case R.id.btnLoginPerusahaan:
                Intent intentLogin = new Intent(getActivity(), FormLoginPerusahaan.class);
                startActivityForResult(intentLogin, 1);

        }

    }

}
