package com.claudioshigemi.cadprodutos.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.claudioshigemi.cadprodutos.model.Produtos;

import java.util.ArrayList;

/**
 * Created by Claudio on 19/11/2017.
 */

public class ProdutosBd  extends SQLiteOpenHelper{

    private static final String DATABASE = "bdprodutos";
    private static final int VERSION = 1;

    public ProdutosBd(Context context){
        super(context,DATABASE,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String produto = "CREATE TABLE produtos(id INTEGER PRIMARY KEY AUTOINCREMET NOT NULL, " +
                         "nomeproduto TEXT NOT NULL, " +
                         "descricao TEXT NOT NULL, quantidade INTEGER);";
        db.execSQL(produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "DROP TABLE IF EXISTS produtos";
        db.execSQL(produto);
    }


    //salva o produto
    public void salvarProduto(Produtos produto){
        ContentValues values = new ContentValues();

        values.put("nomeproduto", produto.getNomeProduto() );
        values.put("descricao",produto.getDescricao() );
        values.put("quantidade",produto.getQuantidade() );

        getWritableDatabase().insert("produtos",null,values );
    }

    //lista - mostrar

    public ArrayList<Produtos> getLista(){
        String [] columns = {"id","nomeproduto","descricao","quantidade"};

        Cursor cursor = getWritableDatabase().query("produtos", columns,null,null,
                null,null,null,null);
        ArrayList<Produtos> produtos = new ArrayList<Produtos>();

        while (cursor.moveToNext() ){
            Produtos produto = new Produtos();
            produto.setId(cursor.getLong(0));
            produto.setNomeProduto(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produto.setQuantidade(cursor.getInt(3));

            produtos.add(produto);
        }
        return  produtos;
    }


}















