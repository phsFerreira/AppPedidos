package com.example.prova02.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.prova02.Pedido;
import com.example.prova02.PedidosProdutosReferencia;

import java.util.List;

@Dao
public interface PedidoDAO {

    @Insert
    long insert(Pedido pedido);

    @Update
    void update(Pedido pedido);

    @Delete
    void delete(Pedido pedido);

    //QUERIES

    @Query("SELECT * FROM pedidos")
    List<Pedido> getAll();

    @Query("SELECT * FROM pedidos WHERE idPedido IN (:fids)")
    List<Pedido> loadAllByIds(int[] fids);

    @Query("SELECT * FROM pedidos WHERE idUsuario LIKE :idUsr LIMIT 1")
    Pedido findById(int idUsr);
//
//    @Query("SELECT * FROM pedidos WHERE endereco_entrega LIKE :enderecoEntrega")
//    List<Pedido> loadAllByEndereco(String enderecoEntrega);
}
