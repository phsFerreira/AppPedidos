package com.example.prova02;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "produtos")
public class Produto {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Ignore
    @ColumnInfo(name = "imagem")
    public Blob imagem;

    @ColumnInfo(name = "nome_produto")
    public String nome;

    @ColumnInfo(name="preço")
    public Double preco;

    @ColumnInfo(name="porcentagem_desconto")
    public Double desconto;
}
