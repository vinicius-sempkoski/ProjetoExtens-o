package com.example.trabalhopontodevenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhopontodevenda.R;
import com.example.trabalhopontodevenda.model.Produto;
import com.example.trabalhopontodevenda.model.Venda;

import java.util.ArrayList;

public class NotaFiscalListAdapter extends RecyclerView.Adapter<NotaFiscalListAdapter.ViewHolder> {

    private ArrayList<Venda> listaVendas;
    private Context context;

    public NotaFiscalListAdapter(ArrayList<Venda> listaVendas,Context context){
        this.listaVendas = listaVendas;
        this.context = context;
    }

    @NonNull
    @Override
    public NotaFiscalListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_nota_fiscal, parent,false);

        return new NotaFiscalListAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaFiscalListAdapter.ViewHolder holder, int position){
        Venda vendaSelecionada = listaVendas.get(position);
        holder.tvPedido.setText(vendaSelecionada.getPedido());
        holder.tvTotal.setText(String.valueOf(vendaSelecionada.getValor()));
        holder.tvPago.setText(String.valueOf(vendaSelecionada.getValor()));
    }


    @Override
    public int getItemCount(){
        return this.listaVendas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPedido;
        public TextView tvTotal;
        public TextView tvPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvPedido = itemView.findViewById(R.id.tvPedido);
            this.tvTotal = itemView.findViewById(R.id.tvTotal);
            this.tvPago = itemView.findViewById(R.id.tvPago);
        }
    }
}
