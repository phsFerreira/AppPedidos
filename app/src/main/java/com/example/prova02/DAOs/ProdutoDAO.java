package com.example.prova02.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.prova02.Produto;
import com.example.prova02.Usuario;

import java.util.List;

@Dao
public interface ProdutoDAO {

    @Insert
    long insert(Produto produto);

    @Update
    void update(Produto produto);

    @Delete
    void delete(Produto produto);

    //QUERIES

    @Query("SELECT * FROM produtos")
    List<Produto> getAll();

    @Query("SELECT * FROM produtos WHERE idProduto IN (:fids)")
    List<Produto> loadAllByIds(int[] fids);

    @Query("SELECT * FROM produtos WHERE idProduto LIKE :idProduto LIMIT 1")
    Produto findById(int idProduto);

    @Query("SELECT * FROM produtos WHERE nome_produto LIKE :nomeProduto LIMIT 1")
    Produto findByName(String nomeProduto);

    @Query("SELECT * FROM produtos WHERE porcentagem_desconto>0")
    List<Produto> getAllByDesconto();

    @Query("SELECT * FROM produtos WHERE porcentagem_desconto==0")
    List<Produto> getAllBySemDesconto();
}
