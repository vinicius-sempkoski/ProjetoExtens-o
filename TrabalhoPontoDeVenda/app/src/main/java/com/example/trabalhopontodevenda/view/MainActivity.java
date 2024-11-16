package com.example.trabalhopontodevenda.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhopontodevenda.R;
import com.example.trabalhopontodevenda.controller.VendedorController;
import com.example.trabalhopontodevenda.dao.ProdutoDao;

public class MainActivity extends AppCompatActivity {
    private EditText edUsuarioEntrar;
    private EditText edSenhaEntrar;
    private Button btEntrarTelaEntrar;
    private VendedorController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new VendedorController(this);
        edUsuarioEntrar = findViewById(R.id.edUsuarioEntrar);
        edSenhaEntrar = findViewById(R.id.edSenhaEntrar);
        btEntrarTelaEntrar = findViewById(R.id.btEntrarTelaEntrar);
        btEntrarTelaEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerLogin();
            }
        });
    }

    public void abrirCadastrar(View view) {
        Intent intent = new Intent(MainActivity.this, CadastrarActivity.class);

        startActivity(intent);
    }

    public void fazerLogin(){
        String retorno = controller.verificarVendedor(edUsuarioEntrar.getText().toString(), edSenhaEntrar.getText().toString());


        if(retorno != null){
            if(retorno.contains("Usuário")){
                edUsuarioEntrar.setError(retorno);
                edUsuarioEntrar.requestFocus();
            }
            if(retorno.contains("Senha")){
                edSenhaEntrar.setError(retorno);
                edSenhaEntrar.requestFocus();
            }
        }else{
            Intent intent = new Intent(MainActivity.this, OpcoesVendaActivity.class);
            startActivity(intent);
        }
    }
}