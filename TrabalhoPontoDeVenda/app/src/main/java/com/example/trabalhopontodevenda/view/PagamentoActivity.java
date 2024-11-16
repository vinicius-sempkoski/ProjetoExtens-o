package com.example.trabalhopontodevenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.trabalhopontodevenda.R;
import com.example.trabalhopontodevenda.model.ValorTotal;
import com.example.trabalhopontodevenda.model.Venda;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PagamentoActivity extends AppCompatActivity {

    private TextView tvValorTotalPagamento;
    private TextView tvTroco;
    private EditText edValorTotalPago;
    private TextView btEfetuarPagamento;
    private Spinner spFormaPagamento;
    private String[] pagamento = new String[] {"Dinheiro", "Débito", "Crédito"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        tvValorTotalPagamento = findViewById(R.id.tvValorTotalPagamento);
        tvTroco = findViewById(R.id.tvTroco);
        edValorTotalPago = findViewById(R.id.edValorTotalPago);
        btEfetuarPagamento = findViewById(R.id.btEfetuarPagamento);
        spFormaPagamento = findViewById(R.id.spFormaPagamento);
        carregarMetodoPagamento();

        double valorTotal = ValorTotal.getInstance().getValorTotal();
        tvValorTotalPagamento.setText(String.valueOf(valorTotal));

        edValorTotalPago.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculaTroco();
            }

            @Override
            public void afterTextChanged(Editable s) {
                calculaTroco();
            }
        });

        btEfetuarPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagamentoEfetuado();
            }
        });
    }

    public void pagamentoEfetuado() {
        Intent intent = new Intent(PagamentoActivity.this, OpcoesVendaActivity.class);

        startActivity(intent);

        double valorTotal = ValorTotal.getInstance().getValorTotal();
        Venda.getInstance().setPedido(gerarNumeroPedido());
        Venda.getInstance().setValor(valorTotal);
        Venda.getInstance().setValorPago(Double.parseDouble(String.valueOf(edValorTotalPago.getText())));
    }

    public void calculaTroco() {
        double valorTotal = ValorTotal.getInstance().getValorTotal();
        double pago = Double.parseDouble(String.valueOf(edValorTotalPago.getText()));
        double troco = pago - valorTotal;
        tvTroco.setText(String.valueOf(troco));
    }

    public void voltarMenu(View view) {
        Intent intent = new Intent(PagamentoActivity.this, OpcoesVendaActivity.class);

        startActivity(intent);
    }

    private void carregarMetodoPagamento(){
        ArrayAdapter adapter = null;
        adapter = new ArrayAdapter<>(PagamentoActivity.this, android.R.layout.simple_dropdown_item_1line, pagamento);
        spFormaPagamento.setAdapter(adapter);
    }

    private String gerarNumeroPedido() {
        String prefixo = "PED";
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMdd");
        String dataAtual = formatoData.format(new Date());
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100000);

        String numeroFormatado = String.format("%05d", numeroAleatorio);

        return prefixo + dataAtual + "-" + numeroFormatado;
    }
}