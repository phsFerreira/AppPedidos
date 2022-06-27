package com.example.prova02;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PedidoProdutos {
    @Embedded
    public Pedido pedido;
    @Relation(
            parentColumn = "idPedido",
            entityColumn = "idProduto"
    )
    public List<Produto> produtos;
}