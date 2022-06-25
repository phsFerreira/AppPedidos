//package com.example.prova02;
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.ForeignKey;
//import androidx.room.PrimaryKey;
//
//import java.util.List;
//
//@Entity(tableName = "pedidos",
//    foreignKeys = {@ForeignKey(entity = Usuario.class,
//    parentColumns = "id",
//    childColumns = "fid",
//    onDelete = ForeignKey.CASCADE)})
//
//public class Pedido {
//
//    @PrimaryKey(autoGenerate = true)
//    public int id;
//
//    @ColumnInfo(name="produtos")
//    public Produto produto;
//
//    @ColumnInfo(name="metodo_pagamento")
//    public String pagamento;
//
//    @ColumnInfo(name="endereco_entrega")
//    public String endereco;
//
//    public int fid;
//}
