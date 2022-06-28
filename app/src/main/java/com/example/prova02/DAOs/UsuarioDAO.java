package com.example.prova02.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.prova02.Usuario;
import com.example.prova02.UsuarioPedidos;
//import com.example.prova02.UsuarioPedidos;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Insert
    long insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    //QUERIES

    @Query("SELECT * FROM usuarios")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuarios WHERE idUsuario IN (:fids)")
    List<Usuario> loadAllByIds(int[] fids);

    @Query("SELECT * FROM usuarios WHERE idUsuario LIKE :idUsuario LIMIT 1")
    Usuario findById(int idUsuario);

    @Query("SELECT * FROM usuarios WHERE nome_usuario LIKE :nomeUsuario LIMIT 1")
    Usuario findByName(String nomeUsuario);

    @Transaction
    @Query("SELECT * FROM usuarios")
    List<UsuarioPedidos> getUsuarioPedidos();
}
