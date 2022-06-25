package com.example.prova02;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UsuarioPedidos {
    @Embedded public Usuario usuario;
    @Relation(parentColumn = "id",
                entityColumn = "fid")

    public List<Pedido> pedidos;
}
