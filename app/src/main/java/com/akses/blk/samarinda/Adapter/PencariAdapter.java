package com.akses.blk.samarinda.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akses.blk.samarinda.Model.Pencari;
import com.akses.blk.samarinda.Pencaker.PencakerHolder;
import com.akses.blk.samarinda.R;

import java.util.ArrayList;

/**
 * Created by ASUS on 22/08/2017.
 */

public class PencariAdapter extends RecyclerView.Adapter<PencakerHolder> {

    Context context;
    ArrayList<Pencari> pencaris;

    public PencariAdapter(Context context, ArrayList<Pencari> pencaris) {
        this.context = context;
        this.pencaris = pencaris;
    }

    @Override
    public PencakerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.content_pencari, parent, false);
        return new PencakerHolder(view);
    }

    @Override
    public void onBindViewHolder(PencakerHolder holder, int position) {

        final Pencari pencari = pencaris.get(position);

        holder.nama.setText(pencari.getNama_pencari());
        holder.noktp.setText(pencari.getNoktp());
        holder.jenis_kelamin.setText(pencari.getJk_pencari());
        holder.ttl_pencari.setText(pencari.getTtl_pencari());
        holder.perusahaan.setText(pencari.getPerusahaan());
    }

    @Override
    public int getItemCount() {
        return pencaris.size();
    }
}
