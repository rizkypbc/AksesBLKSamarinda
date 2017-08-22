package com.akses.blk.samarinda;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;

import com.akses.blk.samarinda.Adapter.PencariAdapter;
import com.akses.blk.samarinda.Api.SearchAPI;
import com.akses.blk.samarinda.Model.Pencari;
import com.akses.blk.samarinda.Model.Value;
import com.akses.blk.samarinda.Pencaker.DownloaderPencaker;

import java.net.URI;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class ListPencari extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final String URL = "http://aksesblk-samarinda.com/aksesblksamarinda/loginUserPencaker/";
    static String urlAddress = "http://aksesblk-samarinda.com/aksesblksamarinda/loginUserPencaker/readPencaker.php";
    RecyclerView recyclerView;
    private ArrayList<Pencari> pencaris = new ArrayList<>();
    private PencariAdapter pencariAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pencari);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        new DownloaderPencaker(ListPencari.this, urlAddress, recyclerView, swipeRefreshLayout).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new DownloaderPencaker(ListPencari.this, urlAddress, recyclerView, swipeRefreshLayout).execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Cari Nama Anda");
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        recyclerView.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchAPI api = retrofit.create(SearchAPI.class);
        Call<Value> call = api.search(newText);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();

                recyclerView.setVisibility(View.VISIBLE);
                if (value.equals("1")) {

                    pencaris = response.body().getResult();
                    pencariAdapter = new PencariAdapter(ListPencari.this, pencaris);
                    recyclerView.setAdapter(pencariAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

        return true;
    }
}
