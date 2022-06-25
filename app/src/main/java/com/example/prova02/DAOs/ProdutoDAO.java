package com.example.prova02.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
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
    void delete(Usuario usuario);

    //QUERIES

    @Query("SELECT * FROM produtos")
    List<Produto> getAll();

    @Query("SELECT * FROM produtos WHERE id IN (:fids)")
    List<Produto> loadAllByIds(int[] fids);

    @Query("SELECT * FROM produtos WHERE id LIKE :idProduto LIMIT 1")
    Produto findById(int idProduto);

    @Query("SELECT * FROM produtos WHERE nome_produto LIKE :nomeProduto LIMIT 1")
    Produto findByName(String nomeProduto);
}
