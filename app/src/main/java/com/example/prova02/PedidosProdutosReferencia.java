package com.example.prova02;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

@Entity(primaryKeys = {"idPedido","idProduto"})
public class PedidosProdutosReferencia {
    public int idPedido;
    public int idProduto;
}