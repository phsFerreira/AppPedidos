package com.example.prova02;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pedidos")
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    public int idPedido;

    @Embedded
    public Produto idProduto;

    @Embedded
    public Usuario idUsuario;

    @ColumnInfo(name="metodo_pagamento")
    public String pagamento;

    @ColumnInfo(name="endereco_entrega")
    public String endereco;

}
