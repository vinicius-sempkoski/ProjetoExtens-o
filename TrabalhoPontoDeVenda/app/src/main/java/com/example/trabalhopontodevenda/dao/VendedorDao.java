package com.example.trabalhopontodevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalhopontodevenda.helper.SQLiteDataHelper;
import com.example.trabalhopontodevenda.model.Vendedor;

import java.util.ArrayList;

public class VendedorDao implements IGenericDao<Vendedor>{

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunas = {"USUARIO", "SENHA"};
    private String tabela = "VENDEDOR";
    private Context context;
    private static VendedorDao instancia;

    public static VendedorDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new VendedorDao(context);
        }else{
            return instancia;
        }
    }

    private VendedorDao(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "PDV", null, 1);

        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Vendedor obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getUsuario());
            valores.put(colunas[1], obj.getSenha());

            return baseDados.insert(tabela, null, valores);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: VendedorDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Vendedor obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getSenha());

            String[]identificador = {String.valueOf(obj.getUsuario())};

            return baseDados.update(tabela,  valores, colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: VendedorDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Vendedor obj) {
        try{
            String[]identificador = {String.valueOf(obj.getUsuario())};

            return baseDados.delete(tabela, colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("PDV", "ERRO: VendedorDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Vendedor> getAll() {
        ArrayList<Vendedor> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    Vendedor vendedor = new Vendedor();
                    vendedor.setUsuario(cursor.getString(0));
                    vendedor.setSenha(cursor.getString(1));

                    lista.add(vendedor);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: VendedorDao.getAll() "+ex.getMessage());
        }

        return lista;
    }

    public Vendedor getById(int id) {
        return null;
    }

    @Override
    public Vendedor getById(String id) {
        try{
            String[]identificador = {(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Vendedor vendedor = new Vendedor();
                vendedor.setUsuario(cursor.getString(0));
                vendedor.setSenha(cursor.getString(1));

                return vendedor;
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: VendedorDao.getById() "+ex.getMessage());
        }
        return null;
    }
}

