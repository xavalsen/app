package com.example.icl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.icl.R;

import java.util.Hashtable;
import java.util.Map;

public class Registro extends AppCompatActivity {

    String URL_SERVIDOR = "http://192.168.56.1/phpapp/insertar_usuarios.php";



    EditText edtUsuarioR, edtEmailR, edtTelefonoR, edtPasswordR;
    Button buttonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtUsuarioR = findViewById(R.id.edtUsuarioR);
        edtEmailR = findViewById(R.id.edtEmailR);
        edtTelefonoR = findViewById(R.id.edtTelefonoR);
        edtPasswordR = findViewById(R.id.edtPasswordR);
       // edtPasswordR2 = findViewById(R.id.edtPasswordR2);
        buttonReg = findViewById(R.id.buttonReg);

        buttonReg.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View view){
                    registrar();
                }
        });

    }

    public void registrar(){
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                       //Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        if (response.equals("ERROR 1")) {
                            //Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Registro.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                        } else if(response.equals("ERROR 2")) {
                            Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Registro.this, "Fallo el registro.", Toast.LENGTH_SHORT).show();
                        }else if (response.equals("MENSAJE")) {
                           // Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Registro.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(Registro.this, "ERROR CON LA CONEXION", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("nombre", edtUsuarioR.getText().toString().trim());
                parametros.put("telefono", edtTelefonoR.getText().toString().trim());
                parametros.put("password", edtPasswordR.getText().toString().trim());
                parametros.put("email", edtEmailR.getText().toString().trim());


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Registro.this);
        requestQueue.add(stringRequest);
    }
}

