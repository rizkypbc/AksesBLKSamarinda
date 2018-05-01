package com.akses.blk.samarinda.Api;

import com.akses.blk.samarinda.Model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ASUS on 22/08/2017.
 */

public interface SearchAPI {

    @FormUrlEncoded
    @POST("searchPencaker.php")
    Call<Value> search(@Field("search") String search);

    @FormUrlEncoded
    @POST("searchPerusahaan.php")
    Call<Value> perusahaan(@Field("search") String search);
}
