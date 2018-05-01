package com.akses.blk.samarinda.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akses.blk.samarinda.Model.Pencari;
import com.akses.blk.samarinda.Perusahaan.PerusahaanHolder;
import com.akses.blk.samarinda.R;

import java.util.ArrayList;

/**
 * Created by ASUS on 01/09/2017.
 */

public class PerusahaanAdapter extends RecyclerView.Adapter<PerusahaanHolder> {

    Context context;
    ArrayList<Pencari> pencariArrayList;

    public PerusahaanAdapter(Context context, ArrayList<Pencari> pencariArrayList) {
        this.context = context;
        this.pencariArrayList = pencariArrayList;
    }

    @Override
    public PerusahaanHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.content_perusahaan, parent, false);
        return new PerusahaanHolder(view);
    }

    @Override
    public void onBindViewHolder(PerusahaanHolder holder, int position) {

        final Pencari pencariPerusahaan = pencariArrayList.get(position);

        holder.nama.setText(pencariPerusahaan.getNama_pencari());
        holder.noktp.setText(pencariPerusahaan.getNoktp());
        holder.jenis_kelamin.setText(pencariPerusahaan.getJk_pencari());
        holder.ttl_pencari.setText(pencariPerusahaan.getTtl_pencari());
        holder.telp_bursa.setText(pencariPerusahaan.getTelp_bursa());
        holder.perusahaan.setText(pencariPerusahaan.getPerusahaan());
        holder.posisi.setText(pencariPerusahaan.getPosisi());
        holder.tanggal_daftar.setText(pencariPerusahaan.getTanggal_daftar());
        holder.pengalaman.setText(pencariPerusahaan.getPengalaman());
    }

    @Override
    public int getItemCount() {
        return pencariArrayList.size();
    }
}
