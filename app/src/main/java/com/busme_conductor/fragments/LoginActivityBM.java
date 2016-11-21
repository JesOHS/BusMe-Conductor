package com.busme_conductor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.busme_conductor.R;
import com.busme_conductor.models.DAO.RegistroDAO;

public class LoginActivityBM extends AppCompatActivity {
    Button btnIngresar;
    EditText edtCamion, edtContrasenna;


    public void login(View v) {
        edtCamion = (EditText)findViewById(R.id.edtcamion);
        edtContrasenna = (EditText)findViewById(R.id.edtcontrasenna);
        Button btnIngresar = (Button) v;

        RegistroDAO registroDAO = new RegistroDAO();
        Log.i("DEBUG", "VAMO A VER en el metodo de login");
        Log.i("DEBUG", registroDAO.read("1234", "S-8253").toString());
        if(registroDAO.read(edtContrasenna.getText().toString(),edtCamion.getText().toString())!=null) {
            Intent abrirMapa = new Intent(getApplicationContext(), BusMeConductor.class);
            startActivity(abrirMapa);
        }else{

            Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
