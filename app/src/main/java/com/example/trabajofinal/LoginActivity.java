package com.example.trabajofinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
EditText user, pass;
Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText)findViewById(R.id.editTextTextPersonName);
        pass=(EditText)findViewById(R.id.editTextTextPassword);
        btnEntrar=(Button)findViewById(R.id.button);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUs("http://192.168.1.3/trabajofinal/validar.php");
            }
        });

    }

    private void validarUs(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Ingreso Exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,Menu1Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario y/o Password incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("usuario", user.getText().toString());
                parametros.put("password", pass.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void onClick(View view) {
        Intent miIntent2=new Intent(LoginActivity.this,RegistroActivity.class);
         startActivity(miIntent2);
        }
}