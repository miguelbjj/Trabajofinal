package com.example.trabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    EditText usu, nom, ape, pas;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usu = findViewById(R.id.editTextTextPersonName2);
        nom = findViewById(R.id.editTextTextPersonName3);
        ape = findViewById(R.id.editTextTextPersonName4);
        pas = findViewById(R.id.editTextTextPersonName5);
        btn = findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

    }

    private void insertar() {
        String usuario = usu.getText().toString().trim();
        String nombre = nom.getText().toString().trim();
        String apellido = ape.getText().toString().trim();
        String password = pas.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        if (usuario.isEmpty()) {
            usu.setError("complete los campos");
        } else if (nombre.isEmpty()) {
            nom.setError("complete los campos");
        } else if (apellido.isEmpty()) {
            ape.setError("complete los campos");
        } else if (password.isEmpty()) {
            pas.setError("complete los campos");
        }else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.3/trabajofinal/insertar.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos insertados")) {
                        Toast.makeText(RegistroActivity.this, "datos ingresados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(RegistroActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        finish();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistroActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{

                    Map<String ,String>params=new HashMap<String, String>();

                    params.put("usuario", usuario);
                    params.put("nombre", nombre);
                    params.put("apellido", apellido);
                    params.put("password", password);
                    return params;
                }
            };
            RequestQueue requestQueue=Volley.newRequestQueue(RegistroActivity.this);
            requestQueue.add(request);
        }
    }
}

