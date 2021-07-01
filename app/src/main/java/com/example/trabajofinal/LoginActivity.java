package com.example.trabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
EditText user, pass;
Button btnEntrar, btnRegistrar;
daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText)findViewById(R.id.editTextTextPersonName);
        pass=(EditText)findViewById(R.id.editTextTextPassword);
        btnEntrar=(Button)findViewById(R.id.button);
        btnRegistrar=(Button)findViewById(R.id.button2);
        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao=new daoUsuario(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if (u.equals("")&&p.equals("")){
                    Toast.makeText(this, "ERROR: Campos Vacios",Toast.LENGTH_LONG).show();
                }else if (dao.login(u,p)==1){
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(this, "Ingreso exitoso",Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(LoginActivity.this,Menu1Activity.class);
                    i2.putExtra("Id", ux.getId());
                    startActivity(i2);
                }else
                    Toast.makeText(this, "Usuario y/o password incorrectos", Toast.LENGTH_LONG).show();
                break;

            case R.id.button2:
                Intent i=new Intent(LoginActivity.this,RegistroActivity.class);
                startActivity(i);
                break;
        }
    }

    //public void onClick(View view) {
        //Intent miIntent=new Intent(LoginActivity.this,RegistroActivity.class);
       // startActivity(miIntent);
    //}

    //public void onClick2(View view) {
        //Intent miIntent=new Intent(LoginActivity.this,Menu1Activity.class);
        //startActivity(miIntent);
    //}
}