package com.akses.blk.samarinda.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.akses.blk.samarinda.FormPencaker;
import com.akses.blk.samarinda.R;

/**
 * Created by ASUS on 09/05/2017.
 */

public class FragmentThree extends Fragment implements View.OnClickListener {

    public FragmentThree() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        ImageView button = (ImageView) view.findViewById(R.id.btnOrang);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FormPencaker.class);
        startActivityForResult(intent, 1);
    }

}
