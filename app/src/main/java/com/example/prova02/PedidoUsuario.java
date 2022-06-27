package com.example.prova02;

import androidx.room.Embedded;
import androidx.room.Relation;

public class PedidoUsuario {
    @Embedded
    public Pedido pedido;
    @Relation(
            parentColumn = "idPedido",
            entityColumn = "idUsuario"
    )
    public Usuario usuario;
}
