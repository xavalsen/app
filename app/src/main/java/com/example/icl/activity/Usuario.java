package com.example.icl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.icl.R;

import java.util.HashMap;
import java.util.Map;

public class Usuario extends AppCompatActivity {

    TextView usuariotxt, edtNombre, edtDireccion, edtTelefono, edtEmail;
    String email;
    Button btnEditar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtDireccion = (EditText)findViewById(R.id.edtDireccion);
        edtTelefono = (EditText)findViewById(R.id.edtTelefono);
        btnEditar=(Button)findViewById(R.id.btnEditar);
        btnVolver = (Button)findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (v.getContext(), Menu.class);
                startActivityForResult(intent1, 0);
            }
        });

        //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();


        //recibiendo email del login
        edtEmail = (TextView) findViewById(R.id.edtEmail);
        Bundle bundle = this.getIntent().getExtras();
        email = bundle.getString( "email");
        edtEmail.setText(email);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ejecutarServicio("http://192.168.56.1/phpapp/editar_usuarios.php");
            }
        });
    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> parametros = new HashMap<>();
                parametros.put("nombre",edtNombre.getText().toString());
                parametros.put("telefono",edtTelefono.getText().toString());
                parametros.put("direccion",edtDireccion.getText().toString());
                parametros.put("email",edtEmail.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}