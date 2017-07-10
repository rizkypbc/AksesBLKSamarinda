package com.akses.blk.samarinda.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.akses.blk.samarinda.Adapter.BeritaAdapter;
import com.akses.blk.samarinda.Model.Berita;
import com.akses.blk.samarinda.MySingleton;
import com.akses.blk.samarinda.R;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

/**
 * Created by ASUS on 09/05/2017.
 */

public class FragmentOne extends Fragment {


    final String TAG = "Fragment One";

    public FragmentOne(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.recycler_view_item, container, false);


//        ArrayList<Berita> berita = new ArrayList<>();

//
//        berita.add(new Berita(R.drawable.news, "Ceritanya Judul Berita", "Tanggal Beritanya"));
//        berita.add(new Berita(R.drawable.news, "Ceritanya Judul Berita", "Tanggal Beritanya"));
//        berita.add(new Berita(R.drawable.news, "Ceritanya Judul Berita", "Tanggal Beritanya"));
//        berita.add(new Berita(R.drawable.news, "Ceritanya Judul Berita", "Tanggal Beritanya"));
//        berita.add(new Berita(R.drawable.news, "Ceritanya Judul Berita", "Tanggal Beritanya"));
//        berita.add(new Berita(R.drawable.news, "Ceritanya Judul Berita", "Tanggal Beritanya"));

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rvItem);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);




//        sv=(SearchView) rootView.findViewById(R.id.searchView1);
//        sv.setQueryHint("Search..");
//        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                return true;
//            }
//        });




//        BeritaAdapter adapter = new BeritaAdapter(getActivity(), berita);
//        recyclerView.setAdapter(adapter);

//        String url = "http://192.168.43.212/aksesblksamarinda/kios/berita.php";
        String url = "http://aksesblk-samarinda.com/aksesblksamarinda/uploadberita/filterBerita.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                ArrayList<Berita> beritaList = new JsonConverter<Berita>()
                        .toArrayList(response, Berita.class);

                 BeritaAdapter adapter = new BeritaAdapter(getContext(), beritaList);

                recyclerView.setAdapter(adapter);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {
                            Log.d(TAG, error.getMessage(), null);
                            Toast.makeText(getContext(), "Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show();
                        }
                    }
                });


        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        return rootView;

    }


}
