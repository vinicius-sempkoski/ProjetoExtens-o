package com.example.trabalhopontodevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalhopontodevenda.helper.SQLiteDataHelper;
import com.example.trabalhopontodevenda.model.Produto;
import com.example.trabalhopontodevenda.model.Vendedor;

import java.util.ArrayList;

public class ProdutoDao implements IGenericDao<Produto> {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[] colunas = {"CODIGO", "DESCRICAO", "PRECO", "QUANTIDADE"};
    private String tabela = "PRODUTO";
    private Context context;
    private static ProdutoDao instancia;

    public static synchronized ProdutoDao getInstancia(Context context) {
        if (instancia == null) {
            instancia = new ProdutoDao(context);
        }
        return instancia;
    }

    private ProdutoDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "PDV", null, 1);
        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Produto obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigo());
            valores.put(colunas[1], obj.getDescricao());
            valores.put(colunas[2], obj.getPreco());
            valores.put(colunas[3], obj.getQuantidade());

            return baseDados.insert(tabela, null, valores);
        } catch (SQLException ex) {
            Log.e("PDV", "ERRO: ProdutoDao.insert() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Produto obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getDescricao());

            String[] identificador = {String.valueOf(obj.getCodigo())};

            return baseDados.update(tabela, valores, colunas[0] + "= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PDV", "ERRO: ProdutoDao.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Produto obj) {
        try {
            String[] identificador = {String.valueOf(obj.getCodigo())};
            return baseDados.delete(tabela, colunas[0] + "= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PDV", "ERRO: ProdutoDao.delete() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Produto> getAll() {
        ArrayList<Produto> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(
                    tabela,
                    colunas,
                    null,
                    null,
                    null,
                    null,
                    colunas[0] + " desc"
            );

            if (cursor.moveToFirst()) {
                do {
                    Produto produto = new Produto();
                    produto.setCodigo(parseInteger(cursor.getString(cursor.getColumnIndex(colunas[0]))));
                    produto.setDescricao(cursor.getString(cursor.getColumnIndex(colunas[1])));
                    produto.setPreco(parseDouble(cursor.getString(cursor.getColumnIndex(colunas[2]))));
                    produto.setQuantidade(parseInteger(cursor.getString(cursor.getColumnIndex(colunas[3]))));

                    lista.add(produto);

                } while (cursor.moveToNext());
            }

        } catch (SQLException ex) {
            Log.e("PDV", "ERRO: ProdutoDao.getAll() " + ex.getMessage());
        }

        return lista;
    }


    private int parseInteger(String value) {
        try {
            if (value != null && !value.isEmpty()) {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            Log.e("PDV", "Erro ao converter para inteiro: " + e.getMessage());
        }
        return 0; // ou um valor padrão adequado
    }

    private double parseDouble(String value) {
        try {
            if (value != null && !value.isEmpty()) {
                return Double.parseDouble(value);
            }
        } catch (NumberFormatException e) {
            Log.e("PDV", "Erro ao converter para double: " + e.getMessage());
        }
        return 0.0; // ou um valor padrão adequado
    }

    public Produto getById(int id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(
                    tabela,
                    colunas,
                    colunas[0] + "= ?",
                    identificador,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                Produto produto = new Produto();
                produto.setCodigo(parseInteger(cursor.getString(0)));
                produto.setDescricao(cursor.getString(1));
                produto.setPreco(parseDouble(cursor.getString(2)));
                produto.setQuantidade(parseInteger(cursor.getString(3)));
                return produto;
            }

        } catch (SQLException ex) {
            Log.e("PDV", "ERRO: ProdutoDao.getById() " + ex.getMessage());
        }
        return null;
    }

    @Override
    public Produto getById(String id) {
        return getById(Integer.parseInt(id));
    }
}
