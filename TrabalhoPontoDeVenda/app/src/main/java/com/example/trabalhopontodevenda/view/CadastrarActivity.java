package com.example.trabalhopontodevenda.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhopontodevenda.R;
import com.example.trabalhopontodevenda.controller.VendedorController;

public class CadastrarActivity extends AppCompatActivity {

    private EditText edUsuarioCadastrar;
    private EditText edSenhaCadastrar;
    private EditText edConfirmarSenhaCadastrar;
    private Button btCadastrarTelaCadastrar;
    private VendedorController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        controller = new VendedorController(this);
        edUsuarioCadastrar = findViewById(R.id.edUsuarioCadastrar);
        edSenhaCadastrar = findViewById(R.id.edSenhaCadastrar);
        edConfirmarSenhaCadastrar = findViewById(R.id.edConfirmarSenhaCadastrar);
        btCadastrarTelaCadastrar = findViewById(R.id.btCadastrarTelaCadastrar);
        btCadastrarTelaCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDados();
            }
        });
    }

    public void abrirEntrar(View view) {
        Intent intent = new Intent(CadastrarActivity.this, MainActivity.class);

        startActivity(intent);
    }

    public void salvarDados(){
        String retorno = controller.cadastrarVendedor(edUsuarioCadastrar.getText().toString(), edSenhaCadastrar.getText().toString(), edConfirmarSenhaCadastrar.getText().toString());


        if(retorno != null){
            if(retorno.contains("Usuário")){
                edUsuarioCadastrar.setError(retorno);
                edUsuarioCadastrar.requestFocus();
            }
            if(retorno.contains("Senha")){
                edSenhaCadastrar.setError(retorno);
                edSenhaCadastrar.requestFocus();
            }
            if(retorno.contains("Senhas")){
                edConfirmarSenhaCadastrar.setError(retorno);
                edConfirmarSenhaCadastrar.requestFocus();
            }
        }else{
            Toast.makeText(this, "Vendedor cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CadastrarActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}