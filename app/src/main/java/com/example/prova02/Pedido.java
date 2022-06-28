package com.example.prova02;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "pedidos",
        foreignKeys = {@ForeignKey( entity = Usuario.class,
                                    parentColumns = "idUsuario",
                                    childColumns = "idUsuario",
                                    onDelete = ForeignKey.CASCADE)})
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    public int idPedido;

    @ColumnInfo(name="idUsuario")
    public int idUsuario;

    @ColumnInfo(name="metodo_pagamento")
    public String pagamento;

    @ColumnInfo(name="endereco_entrega")
    public String endereco;

}
