package com.example.icl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icl.R;
import com.example.icl.model.ItemList;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgItemDetail;
    private TextView tvTituloDetail;
    private TextView tvDescripcionDetail;
    private TextView tvPrecioDetail;
    private ItemList itemDetail;
    private Button button2;

    private String doamin_image = "http://192.168.56.1/phpapp/productos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(getClass().getSimpleName());

        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (v.getContext(), Carrito.class);
                startActivityForResult(intent1, 0);
            }
        });

        initViews();
        initValues();
    }

    /*private void EnviarDatos(){
        String titulo;
        Intent intent = new Intent(this, Carrito.class);
        titulo = tvTituloDetail.getText().toString();
        intent.putExtra("itemDetail", titulo);
        startActivity(intent);
    }*/

    private void initViews() {
        imgItemDetail = findViewById(R.id.imgItemDetail);
        tvTituloDetail = findViewById(R.id.tvTituloDetail);
        tvDescripcionDetail = findViewById(R.id.tvDescripcionDetail);
        tvPrecioDetail = findViewById(R.id.tvPrecioDetail);

    }

    private void initValues(){
        itemDetail = (ItemList) getIntent().getExtras().getSerializable("itemDetail");


        Picasso.get()
                .load(doamin_image+itemDetail.getImg())
                .into(imgItemDetail);
        tvTituloDetail.setText(itemDetail.getNombre());
        tvDescripcionDetail.setText(itemDetail.getDescripcion());
        double d1=itemDetail.getPrecio();
        tvPrecioDetail.setText(String.valueOf(d1) + "€");

    }

}