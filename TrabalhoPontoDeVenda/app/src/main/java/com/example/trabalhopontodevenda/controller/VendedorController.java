package com.example.trabalhopontodevenda.controller;

import android.content.Context;

import com.example.trabalhopontodevenda.dao.VendedorDao;
import com.example.trabalhopontodevenda.model.Vendedor;

import java.util.ArrayList;

public class VendedorController {
    private Context context;
    public ArrayList<Vendedor> vendedores;

    public VendedorController(Context context) {
        this.context = context;
    }

    public String cadastrarVendedor(String usuario, String senha, String confirmarSenha){

        try{
            if(usuario.equals("") || usuario.isEmpty()){
                return "Informe um Usuário!";
            }
            if(senha.equals("") || senha.isEmpty()){
                return "Informe uma Senha!";
            }
            if(!senha.equals(confirmarSenha)){
                return "As Senhas precisam ser iguais!";
            }

            Vendedor vendedor = VendedorDao.getInstancia(context).getById(usuario);
            if(vendedor != null){
                return "O Usuário ("+usuario+") já está cadastrado!";
            }else{
                vendedor = new Vendedor();
                vendedor.setUsuario(usuario);
                vendedor.setSenha(senha);

                VendedorDao.getInstancia(context).insert(vendedor);
            }

        }catch (Exception ex){
            return "Erro ao Gravar Vendedor.";
        }
        return null;
    }

    public String verificarVendedor (String usuario, String senha) {
        try{
            if(usuario.equals("") || usuario.isEmpty()){
                return "Informe um Usuário!";
            }
            if(senha.equals("") || senha.isEmpty()){
                return "Informe uma Senha!";
            }

            Vendedor vendedor = VendedorDao.getInstancia(context).getById(usuario);
            if(vendedor == null) {
                return "O Usuário (" + usuario + ") não está cadastrado!";
            }
        }catch (Exception ex){
            return "Erro ao Gravar Vendedor.";
        }
        return null;
    }

    public ArrayList<Vendedor> retornarTodosVendedores(){
        return VendedorDao.getInstancia(context).getAll();
    }
}
