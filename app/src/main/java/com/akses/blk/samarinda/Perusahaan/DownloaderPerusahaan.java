package com.akses.blk.samarinda.Perusahaan;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.akses.blk.samarinda.Pencaker.Connector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by ASUS on 01/09/2017.
 */

public class DownloaderPerusahaan extends AsyncTask<Void, Void, String> {

    Context context;
    String urlAddress;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    public DownloaderPerusahaan(Context context, String urlAddress, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.urlAddress = urlAddress;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!swipeRefreshLayout.isRefreshing()) {

            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadDataPerusahaan();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        if (jsonData == null) {

            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(context, "Koneksi Anda Tidak Stabil", Toast.LENGTH_SHORT).show();
        } else {

            new DataParserPerusahaan(context, jsonData, recyclerView, swipeRefreshLayout).execute();
        }
    }

    private String downloadDataPerusahaan() {

        HttpURLConnection con = Connector.connect(urlAddress);
        if (con == null) {

            return null;
        }
        try {

            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = br.readLine()) != null) {

                jsonData.append(line + "\n");
            }

            br.close();
            is.close();

            return jsonData.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
