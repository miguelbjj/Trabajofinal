package com.example.trabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{
EditText us, pas, nom, ap;
Button reg;
daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        us=(EditText)findViewById(R.id.editTextTextPersonName2);
        nom=(EditText)findViewById(R.id.editTextTextPersonName3);
        ap=(EditText)findViewById(R.id.editTextTextPersonName4);
        pas=(EditText)findViewById(R.id.editTextTextPersonName5);
        reg=(Button)findViewById(R.id.button3);
        reg.setOnClickListener(this);
        dao=new daoUsuario(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button3:
                Usuario u = new Usuario();
                u.setUsuario(us.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                u.setPassword(pas.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this, "ERROR: Campos Vacios", Toast.LENGTH_LONG).show();
                }else if (dao.insertUsuario(u)){
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(RegistroActivity.this,Menu1Activity.class);
                    startActivity(i2);
                    finish();
                }else
                    Toast.makeText(this, "Usuario ya registrado.", Toast.LENGTH_LONG).show();
                break;
        }
    }

    //public void onClick3(View view) {
    //    Intent miIntent=new Intent(RegistroActivity.this,Menu1Activity.class);
    //    startActivity(miIntent);
   // }
}