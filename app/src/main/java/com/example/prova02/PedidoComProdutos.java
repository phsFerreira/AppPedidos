package com.example.prova02;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class PedidoComProdutos {
    @Embedded public Pedido pedido;
    @Relation(
            parentColumn = "idPedido",
            entityColumn = "idProduto",
            associateBy = @Junction(PedidosProdutosReferencia.class)
    )
    public List<Produto> produtos;
}
