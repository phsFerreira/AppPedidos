package com.example.prova02.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.prova02.Pedido;
import com.example.prova02.PedidoUsuario;

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

    @Transaction
    @Query("SELECT * FROM pedidos WHERE idUsuario = :pid")
    public List<PedidoUsuario> getPedidosUsuario(int pid);

//    @Transaction
//    @Query("SELECT * FROM pedidos WHERE idProduto = :produtoId")
//    pulbic List<P>
//
//    @Query("SELECT * FROM pedidos WHERE id IN (:fids)")
//    List<Pedido> loadAllByIds(int[] fids);
//
    @Query("SELECT * FROM pedidos WHERE idPedido LIKE :id LIMIT 1")
    Pedido findById(int id);
//
//    @Query("SELECT * FROM pedidos WHERE endereco_entrega LIKE :enderecoEntrega")
//    List<Pedido> loadAllByEndereco(String enderecoEntrega);
}
