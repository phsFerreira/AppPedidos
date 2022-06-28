package com.example.prova02;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UsuarioPedidos {
        @Embedded
        public Usuario Usuario;
        @Relation(
                parentColumn = "idUsuario",
                entityColumn = "idUsuario"
        )
        public List<Pedido> Pedidos;
}
