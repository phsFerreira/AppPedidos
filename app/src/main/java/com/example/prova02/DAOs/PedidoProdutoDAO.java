package com.example.prova02.DAOs;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.prova02.PedidoComProdutos;
import com.example.prova02.PedidosProdutosReferencia;

import java.util.List;

@Dao
public interface PedidoProdutoDAO {

    @Transaction
    @Query("SELECT * FROM pedidos")
    List<PedidoComProdutos> getPedidoComProdutos();

}
