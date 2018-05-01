package com.akses.blk.samarinda.Perusahaan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akses.blk.samarinda.R;

/**
 * Created by ASUS on 01/09/2017.
 */

public class PerusahaanHolder extends RecyclerView.ViewHolder {

    public TextView noktp;
    public TextView nama;
    public TextView jenis_kelamin;
    public TextView ttl_pencari;
    public TextView perusahaan;
    public TextView telp_bursa;
    public TextView posisi;
    public TextView tanggal_daftar;
    public TextView pengalaman;


    public PerusahaanHolder(View itemViewPerusahaan) {
        super(itemViewPerusahaan);

        noktp = (TextView) itemViewPerusahaan.findViewById(R.id.txtNoktpPerusaahaan);
        nama = (TextView) itemViewPerusahaan.findViewById(R.id.txtNamaPencariPerusaahaan);
        jenis_kelamin = (TextView) itemViewPerusahaan.findViewById(R.id.txtJenisKelaminPerusaahaan);
        ttl_pencari = (TextView) itemViewPerusahaan.findViewById(R.id.txtTtlPerusaahaan);
        telp_bursa = (TextView) itemViewPerusahaan.findViewById(R.id.txtNoTelpPerusahaan);
        perusahaan = (TextView) itemViewPerusahaan.findViewById(R.id.txtPerusahaanPerusaahaan);
        posisi = (TextView) itemViewPerusahaan.findViewById(R.id.txtPosisiPerusahaan);
        tanggal_daftar = (TextView) itemViewPerusahaan.findViewById(R.id.txtTanggalDaftarPerusaahaan);
        pengalaman = (TextView) itemViewPerusahaan.findViewById(R.id.txtPengalamanPerusaahaan);
    }
}
