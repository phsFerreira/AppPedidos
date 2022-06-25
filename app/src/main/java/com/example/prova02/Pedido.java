package com.example.prova02;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "pedidos")
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="produtos")
    public List<Produto> produtos;

    @ColumnInfo(name="metodo_pagamento")
    public String pagamento;

    @ColumnInfo(name="endereco_entrega")
    public String endereco;
}
