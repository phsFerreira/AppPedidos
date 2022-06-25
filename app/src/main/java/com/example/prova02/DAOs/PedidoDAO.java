package com.example.prova02.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prova02.Pedido;

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

    @Query("SELECT * FROM pedidos WHERE id IN (:fids)")
    List<Pedido> loadAllByIds(int[] fids);

    @Query("SELECT * FROM pedidos WHERE id LIKE :idPedido LIMIT 1")
    Pedido findById(int idPedido);

    @Query("SELECT * FROM pedidos WHERE endereco_entrega LIKE :enderecoEntrega")
    List<Pedido> loadAllByEndereco(String enderecoEntrega);
}
