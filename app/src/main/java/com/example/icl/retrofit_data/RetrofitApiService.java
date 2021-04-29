package com.example.icl.retrofit_data;

import com.example.icl.model.ItemList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiService {
    @GET("phpapp/ver_productos.php")
    Call<List<ItemList>> getItemsDB();
}
