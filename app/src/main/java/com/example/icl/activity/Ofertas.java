package com.example.icl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.icl.R;
import com.example.icl.adaptador.RecyclerAdapter;
import com.example.icl.model.ItemList;
import com.example.icl.retrofit_data.RetrofitApiService;
import com.example.icl.retrofit_data.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ofertas extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener{
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    private RetrofitApiService retrofitApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);

                Button vct= (Button) findViewById(R.id.vCesta);
                vct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent (v.getContext(), Carrito.class);
                        startActivityForResult(intent1, 0);
                    }
                });
                initViews();
                initValues();
                initListener();
            }

            private void initViews(){
                rvLista = findViewById(R.id.rvLista);
                svSearch = findViewById(R.id.svSearch);
            }

            private void initValues() {
                retrofitApiService = RetrofitClient.getApiService();

                LinearLayoutManager manager = new LinearLayoutManager(this);
                rvLista.setLayoutManager(manager);

                getItemsSQL();

        /*items = getItems();
        adapter = new RecyclerAdapter(items, this);
        rvLista.setAdapter(adapter);*/
            }

            private void initListener() {
                svSearch.setOnQueryTextListener(this);
            }

   /* private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("Pizza Familiar", "Pizza tamaño XXL para toda la familia", R.drawable.uno, 3.20));
        itemLists.add(new ItemList("Pizza BBQ", "Pizza a la BBQ con queso y jamon", R.drawable.dos, 2.10));
        itemLists.add(new ItemList("Pizza Redondeada", "Contiene...", R.drawable.tres, 1.25));
        itemLists.add(new ItemList("Pizza de Campo", "Contiene setas de campo", R.drawable.cuatro, 3.10));
        itemLists.add(new ItemList("Pizza Vegetariana", "Contiene todo tipo de verduras.", R.drawable.cinco, 2.85));

        return itemLists;
    }*/

            private void getItemsSQL(){
                retrofitApiService.getItemsDB().enqueue(new Callback<List<ItemList>>() {
                    @Override
                    public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                        items = response.body();
                        adapter = new RecyclerAdapter(items, Ofertas.this);
                        rvLista.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<ItemList>> call, Throwable t) {
                        Toast.makeText(Ofertas.this, "Error: " +t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void itemClick(ItemList item) {
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("itemDetail", item);
                startActivity(intent);
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
}