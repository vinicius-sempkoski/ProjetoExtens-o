package com.example.trabalhopontodevenda.controller;

import android.content.Context;

import com.example.trabalhopontodevenda.dao.ProdutoDao;
import com.example.trabalhopontodevenda.model.Produto;

import java.util.ArrayList;

public class ProdutoController {

    private Context context;

    public ProdutoController(Context context) {
        this.context = context;
    }

    public String adicionarProduto(String codigo, String descricao, String preco, String quantidade){
        try{
            if(codigo.equals("") || codigo.isEmpty()){
                return "Informe o Código do Produto!";
            }
            if(descricao.equals("") || descricao.isEmpty()){
                return "Informe a Descrição do Produto!";
            }
            if(preco.equals("") || preco.isEmpty()){
                return "Informe o Preço do Produto!";
            }
            if(quantidade.equals("") || quantidade.isEmpty()) {
                return "Informe a Quantidade do Produto!";
            }

            Produto produto = ProdutoDao.getInstancia(context).getById(Integer.parseInt(codigo));
            if(produto != null){
                return "O Código ("+codigo+") já está cadastrado!";
            }else{
                produto = new Produto();
                produto.setCodigo(Integer.parseInt(codigo));
                produto.setDescricao(descricao);
                produto.setQuantidade(Integer.parseInt(quantidade));
                produto.setPreco(Double.parseDouble(preco));

                ProdutoDao.getInstancia(context).insert(produto);
            }

        }catch (Exception ex){
            return "Erro ao Gravar Produto.";
        }
        return null;
    }

    public ArrayList<Produto> retornarTodosProdutos(){
        return ProdutoDao.getInstancia(context).getAll();
    }


}
