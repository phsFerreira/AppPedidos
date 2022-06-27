package com.example.prova02;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuarios")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    public int idUsuario;

    @ColumnInfo(name="nome")
    public String nome;

    @ColumnInfo(name="email")
    public String email;

    @ColumnInfo(name="nome_usuario")
    public String usuario;

    @ColumnInfo(name="senha")
    public String senha;
}
