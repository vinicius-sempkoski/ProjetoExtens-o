package com.example.trabalhopontodevenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhopontodevenda.R;

public class RelatoriosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);
    }

    public void voltarMenu(View view) {
        Intent intent = new Intent(RelatoriosActivity.this, OpcoesVendaActivity.class);

        startActivity(intent);
    }
}