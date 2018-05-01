package com.akses.blk.samarinda.Perusahaan;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.akses.blk.samarinda.Adapter.PerusahaanAdapter;
import com.akses.blk.samarinda.Model.Pencari;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ASUS on 01/09/2017.
 */

public class DataParserPerusahaan extends AsyncTask<Void, Void, Boolean> {

    ArrayList<Pencari> pencariArrayList = new ArrayList<>();

    Context context;
    String jsonData;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    public DataParserPerusahaan(Context context, String jsonData, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.jsonData = jsonData;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseDataPerusahaan();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        swipeRefreshLayout.setRefreshing(false);
        if (aBoolean) {

            recyclerView.setAdapter(new PerusahaanAdapter(context, pencariArrayList));
        } else {

            Toast.makeText(context, "Koneksi Anda Tidak Stabil", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseDataPerusahaan() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            pencariArrayList.clear();
            Pencari pencariPerusahaan;

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                String noktp = jsonObject.getString("noktp");
                String nama = jsonObject.getString("nama_pencari");
                String jk = jsonObject.getString("jk_pencari");
                String ttl = jsonObject.getString("ttl_pencari");
                String telp_bursa = jsonObject.getString("telp_bursa");
                String perusahaan = jsonObject.getString("perusahaan");
                String posisi = jsonObject.getString("posisi");
                String tanggal_daftar = jsonObject.getString("tanggal_daftar");
                String pengalaman = jsonObject.getString("pengalaman");

                pencariPerusahaan = new Pencari();

                pencariPerusahaan.setNoktp(noktp);
                pencariPerusahaan.setNama_pencari(nama);
                pencariPerusahaan.setJk_pencari(jk);
                pencariPerusahaan.setTtl_pencari(ttl);
                pencariPerusahaan.setTelp_bursa(telp_bursa);
                pencariPerusahaan.setPerusahaan(perusahaan);
                pencariPerusahaan.setPosisi(posisi);
                pencariPerusahaan.setTanggal_daftar(tanggal_daftar);
                pencariPerusahaan.setPengalaman(pengalaman);

                pencariArrayList.add(pencariPerusahaan);
            }

            return true;
        } catch (JSONException ex) {

            ex.printStackTrace();
        }

        return false;
    }
}
