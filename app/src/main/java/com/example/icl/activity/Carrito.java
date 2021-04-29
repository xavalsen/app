package com.example.icl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.icl.R;

public class Carrito extends AppCompatActivity {

    String titulo;
    TextView tvTituloDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        Button btnPagar = (Button) findViewById(R.id.btnPagar);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (v.getContext(), Pagar.class);
                startActivityForResult(intent1, 0);
            }
        });

        /*tvTituloDetail = (TextView) findViewById(R.id.edtEmail);
        Bundle bundle = this.getIntent().getExtras();
        titulo = bundle.getString( "itemDetail");
        tvTituloDetail.setText(titulo);*/
    }
}