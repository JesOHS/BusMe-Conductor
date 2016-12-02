package com.busme_conductor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


import com.busme_conductor.R;
import com.busme_conductor.modelos.DAO.RegistroDAO;
import com.busme_conductor.modelos.DTO.Registro;

public class LoginActivityBM extends AppCompatActivity {
    EditText edtCamion, edtContrasenna;

    public void login(View v) {
        RegistroDAO registroDAO = new RegistroDAO();
        edtCamion = (EditText)findViewById(R.id.edtCamion);
        edtContrasenna = (EditText)findViewById(R.id.edtContrasenna);
        String idCamion = edtCamion.getText().toString();
        String clave = edtContrasenna.getText().toString();
        Registro registro = registroDAO.read(clave, idCamion);
        if(registro != null) {
            Intent abrirMapa = new Intent(getApplicationContext(), BusMeConductor.class);
            abrirMapa.putExtra("Registro", registro);
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
