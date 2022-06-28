package com.example.prova02;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "produtos",
        foreignKeys = {@ForeignKey( entity = Pedido.class,
                                    parentColumns = "idPedido",
                                    childColumns = "idPedido",
                                    onDelete = ForeignKey.CASCADE)})
public class Produto {

    @PrimaryKey(autoGenerate = true)
    public int idProduto;

    @ColumnInfo(name = "imagem")
    public Bitmap imagem;

    @ColumnInfo(name = "nome_produto")
    public String nome;

    @ColumnInfo(name="preço")
    public Double preco;

    @ColumnInfo(name="porcentagem_desconto")
    public Double desconto;

    @ColumnInfo(name="descricao_produto")
    public String descricao;

    @ColumnInfo(name="idPedido")
    public int idPedido;

    public String getNomeProduto(){
        return nome;
    }

    public Bitmap getImgProduto(){
        return imagem;
    }

    public int getId(){
        return idProduto;
    }

    public String getDescricao(){
        return descricao;
    }
    public double getDesconto(){
        return desconto;
    }
}
