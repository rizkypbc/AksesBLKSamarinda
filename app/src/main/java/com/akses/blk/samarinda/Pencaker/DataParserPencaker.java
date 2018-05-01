package com.akses.blk.samarinda.Pencaker;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.akses.blk.samarinda.Adapter.PencariAdapter;
import com.akses.blk.samarinda.Model.Pencari;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ASUS on 22/08/2017.
 */

public class DataParserPencaker extends AsyncTask<Void, Void, Boolean> {

    ArrayList<Pencari> pencaris = new ArrayList<>();
    Context context;
    String jsonData;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    public DataParserPencaker(Context context, String jsonData, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.jsonData = jsonData;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        swipeRefreshLayout.setRefreshing(false);
        if (aBoolean) {

            recyclerView.setAdapter(new PencariAdapter(context, pencaris));
        } else {

            Toast.makeText(context, "Koneksi Anda Tidak Stabil, Gagal Menampilkan Data", Toast.LENGTH_LONG).show();
        }
    }

    private Boolean parseData() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            pencaris.clear();
            Pencari pencari;

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                String username = jsonObject.getString("username");
                String password = jsonObject.getString("password");
                String noktp = jsonObject.getString("noktp");
                String nama = jsonObject.getString("nama_pencari");
                String jk = jsonObject.getString("jk_pencari");
                String ttl = jsonObject.getString("ttl_pencari");
                String provinsi = jsonObject.getString("provinsi");
                String kabupaten = jsonObject.getString("kab");
                String asal = jsonObject.getString("asal");
                String pendidikan = jsonObject.getString("pendidikan");
                String ketrampilan = jsonObject.getString("ketrampilan");
                String pengalaman = jsonObject.getString("pengalaman");
                String perusahaan = jsonObject.getString("perusahaan");
//                String posisi = jsonObject.getString("posisi");

                pencari = new Pencari();

                pencari.setUsername(username);
                pencari.setPassword(password);
                pencari.setNoktp(noktp);
                pencari.setNama_pencari(nama);
                pencari.setJk_pencari(jk);
                pencari.setTtl_pencari(ttl);
                pencari.setProvinsi(provinsi);
                pencari.setKab(kabupaten);
                pencari.setAsal(asal);
                pencari.setPendidikan(pendidikan);
                pencari.setKetrampilan(ketrampilan);
                pencari.setPengalaman(pengalaman);
                pencari.setPerusahaan(perusahaan);
//                pencari.setPosisi(posisi);

                pencaris.add(pencari);
            }

            return true;

        } catch (JSONException ex) {

            ex.printStackTrace();
        }

        return false;
    }
}
