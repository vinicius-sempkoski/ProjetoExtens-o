package com.example.trabalhopontodevenda.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhopontodevenda.R;
import com.example.trabalhopontodevenda.adapter.NotaFiscalListAdapter;
import com.example.trabalhopontodevenda.model.ValorTotal;
import com.example.trabalhopontodevenda.model.Venda;

import java.util.ArrayList;

public class NotaFiscalActivity extends AppCompatActivity {

    ArrayList<Venda> listaAlunos;
    private RecyclerView rvNotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_fiscal);

        rvNotas = findViewById(R.id.rvNotas);

        Venda venda = new Venda();
        String pedido = Venda.getInstance().getPedido();
        venda.setPedido(pedido);
        double total = Venda.getInstance().getValor();
        venda.setValor(total);
        double pago = Venda.getInstance().getValorPago();
        venda.setValorPago(pago);

        listaAlunos.add(venda);

        atualizarListaNotaFiscal();
    }

    public void voltarMenu(View view) {
        Intent intent = new Intent(NotaFiscalActivity.this, OpcoesVendaActivity.class);

        startActivity(intent);
    }

    private void atualizarListaNotaFiscal(){
        NotaFiscalListAdapter adapter = new NotaFiscalListAdapter(listaAlunos, this);
        rvNotas.setLayoutManager(new LinearLayoutManager(this));
        rvNotas.setAdapter(adapter);
    }
}