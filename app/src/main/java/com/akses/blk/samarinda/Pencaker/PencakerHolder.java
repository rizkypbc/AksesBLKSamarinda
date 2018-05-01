package com.akses.blk.samarinda.Pencaker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akses.blk.samarinda.R;

/**
 * Created by ASUS on 22/08/2017.
 */

public class PencakerHolder extends RecyclerView.ViewHolder {


    public TextView noktp;
    public TextView nama;
    public TextView jenis_kelamin;
    public TextView ttl_pencari;
    public TextView perusahaan;


    public PencakerHolder(View itemView) {
        super(itemView);
        noktp = (TextView) itemView.findViewById(R.id.txtNoktp);
        nama = (TextView) itemView.findViewById(R.id.txtNamaPencari);
        jenis_kelamin = (TextView) itemView.findViewById(R.id.txtJenisKelamin);
        ttl_pencari = (TextView) itemView.findViewById(R.id.txtTtl);
        perusahaan = (TextView) itemView.findViewById(R.id.txtPerusahaan);
    }
}
