package com.example.asus.sampleblki.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asus.sampleblki.FormPelatihan;
import com.example.asus.sampleblki.FormPencariKerja;
import com.example.asus.sampleblki.R;

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
        Button button = (Button) view.findViewById(R.id.btnKerja);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FormPelatihan.class);
        startActivityForResult(intent, 1);
    }
}
