package com.example.asus.sampleblki.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.sampleblki.Adapter.BeritaAdapter;
import com.example.asus.sampleblki.Model.Berita;
import com.example.asus.sampleblki.MySingleton;
import com.example.asus.sampleblki.R;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 09/05/2017.
 */

public class FragmentOne extends Fragment {

    SearchView sv;

    final String TAG = "Fragment One";

    RecyclerView rvItem;
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

        String url = "http://192.168.65.2/kios/berita.php";
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
                            Log.d(TAG, error.getMessage());
                            Toast.makeText(getContext(), "Something Wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });


        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        return rootView;
    }
}
