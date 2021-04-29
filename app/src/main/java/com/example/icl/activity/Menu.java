package com.example.icl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.icl.R;
import com.google.android.material.snackbar.Snackbar;

public class Menu extends AppCompatActivity {

    EditText ConfUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        RelativeLayout pro = (RelativeLayout)findViewById(R.id.Productos);
        RelativeLayout ofe = (RelativeLayout)findViewById(R.id.Ofertas);
        RelativeLayout rm = (RelativeLayout)findViewById(R.id.ResMes);
        RelativeLayout con = (RelativeLayout)findViewById(R.id.Contacto);
        TextView conf = (TextView) findViewById(R.id.ConfUsuario);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (v.getContext(), Usuario.class);
                startActivityForResult(intent1, 0);
            }
        });


       pro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Toast.makeText(Menu.this,Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent (v.getContext(), MainProductos.class);
                startActivityForResult(intent1, 0);
            }
        });


        ofe.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1 = new Intent (v.getContext(), Ofertas.class);
                startActivityForResult(intent1, 0);
            }
        });

        rm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1 = new Intent (v.getContext(), Reservas.class);
                startActivityForResult(intent1, 0);}


        });
        con.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1 = new Intent (v.getContext(), Contacto.class);
                startActivityForResult(intent1, 0);}


        });

    }

   /* public void test(View v){
        Toast.makeText(Menu.this, "Error",Toast.LENGTH_LONG).show();
        Intent intent1 = new Intent (v.getContext(), Producto.class);
        //startActivityForResult(intent1, 0);
    }*/
}