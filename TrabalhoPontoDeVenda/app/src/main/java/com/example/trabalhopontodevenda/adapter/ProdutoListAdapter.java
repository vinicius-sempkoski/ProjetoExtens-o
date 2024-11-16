package com.example.trabalhopontodevenda.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.trabalhopontodevenda.model.Produto;
import com.example.trabalhopontodevenda.R;

import java.util.ArrayList;

public class ProdutoListAdapter extends RecyclerView.Adapter<ProdutoListAdapter.ViewHolder>{

    private ArrayList<Produto> listaProdutos;
    private Context context;

    public ProdutoListAdapter(ArrayList<Produto> listaProdutos,Context context){
        this.listaProdutos = listaProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_produto_venda, parent,false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Produto produtoSelecionado = listaProdutos.get(position);
        holder.tvCodigoProduto.setText(String.valueOf(produtoSelecionado.getCodigo()));
        holder.tvDescricaoProduto.setText(produtoSelecionado.getDescricao());
        holder.tvPrecoProduto.setText(String.valueOf(produtoSelecionado.getQuantidade()));
        holder.tvQuantidadeProduto.setText(String.valueOf(produtoSelecionado.getPreco()));
    }


    @Override
    public int getItemCount(){
            return this.listaProdutos.size();
            }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCodigoProduto;
        public TextView tvDescricaoProduto;
        public TextView tvPrecoProduto;
        public TextView tvQuantidadeProduto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvCodigoProduto = itemView.findViewById(R.id.tvCodigoVenda);
            this.tvDescricaoProduto = itemView.findViewById(R.id.tvDescricaoProdutoVenda);
            this.tvPrecoProduto = itemView.findViewById(R.id.tvPrecoProdutoVenda);
            this.tvQuantidadeProduto = itemView.findViewById(R.id.tvQuantidadeProdutoVenda);
        }
    }

    public void clear() {
        listaProdutos.clear();
        notifyDataSetChanged();
    }
}

