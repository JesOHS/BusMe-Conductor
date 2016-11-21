package com.busme_conductor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.busme_conductor.R;

public class LoginActivityBM extends AppCompatActivity {
    Button btnIngresar;
    EditText edtCamion, edtContrasenna;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnIngresar = (Button)findViewById(R.id.btningresar);
        edtCamion = (EditText)findViewById(R.id.edtcamion);
        edtContrasenna = (EditText)findViewById(R.id.edtcontrasenna);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtCamion.getText().toString().equals("1") && edtContrasenna.getText().toString().equals("1")) {
                    Intent abrirMapa = new Intent(getApplicationContext(), BusMeConductor.class);
                    startActivity(abrirMapa);
                }else{

                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
    }

}
