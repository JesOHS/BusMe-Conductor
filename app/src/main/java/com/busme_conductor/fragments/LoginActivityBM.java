package com.busme_conductor.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.busme_conductor.R;
import com.busme_conductor.models.DAO.RegistroDAO;

public class LoginActivityBM extends AppCompatActivity {

    public void login(View v) {
        Button btnIngresar = (Button) v;
        RegistroDAO registroDAO = new RegistroDAO();
        Log.i("DEBUG", "VAMO A VER en el metodo de login");
        Log.i("DEBUG", registroDAO.read("1234", "S-8253").toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
