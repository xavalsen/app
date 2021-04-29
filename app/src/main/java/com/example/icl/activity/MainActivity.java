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
import com.google.android.material.snackbar.Snackbar;

import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String URL_SERVIDOR = "http://192.168.56.1/phpapp/validar_usuario.php";

    public final static String USUARIO = "com.paquete.aplicacion.MENSAJE";

    EditText edtUsuario,edtPassword;
    Button button, button2;

    //admin@gmail.com
    //12345

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pasar el edtUsuario a la Activity Usuario.java
        edtUsuario=(EditText)findViewById(R.id.edtUsuario);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
        Button btn = (Button) findViewById(R.id.button);
        TextView rec = (TextView) findViewById(R.id.Recuperar);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (v.getContext(), Recuperar.class);
                startActivityForResult(intent1, 0);
            }
        });

    }

    //Envair Correo a otra Activity
    private void EnviarDatos(){
        String email;
        Intent intent = new Intent(this, Usuario.class);
        email = edtUsuario.getText().toString();
        intent.putExtra("email", email);
        startActivity(intent);
    }


    public void login(){
        EnviarDatos();
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            // En este apartado se programa lo que deseamos hacer en caso de no haber errores

                        if(response.equals("ERROR 1")) {
                Toast.makeText(MainActivity.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
            } else if(response.equals("ERROR 2")) {
                Toast.makeText(MainActivity.this, "No existe ese registro.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Inicio de Sesion exitoso.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Menu.class);
                            startActivity(intent);
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            // En caso de tener algun error en la obtencion de los datos
            Toast.makeText(MainActivity.this, "ERROR AL INICIAR SESION", Toast.LENGTH_LONG).show();
        }
    }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new Hashtable<String,String>();
                parametros.put("email",edtUsuario.getText().toString());
                parametros.put("password",edtPassword.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}