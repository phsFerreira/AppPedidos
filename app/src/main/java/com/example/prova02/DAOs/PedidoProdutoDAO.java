package com.example.prova02.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.prova02.PedidoComProdutos;
import com.example.prova02.PedidosProdutosReferencia;
import com.example.prova02.Produto;

import java.util.List;

@Dao
public interface PedidoProdutoDAO {

    @Insert
    long insert(PedidosProdutosReferencia pedidoProduto);

    @Transaction
    @Query("SELECT * FROM pedidos")
    List<PedidoComProdutos> getPedidoComProdutos();

}
