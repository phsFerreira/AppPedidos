package com.example.prova02;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.prova02.DAOs.ProdutoDAO;
import com.example.prova02.DAOs.UsuarioDAO;

@Database(entities = {Usuario.class, Produto.class}, version=1)
public abstract class provaDatabase extends RoomDatabase {

    private static final String NOME_DB="prova02-DB";
    private static volatile provaDatabase instancia;

    public provaDatabase(){}

    public static synchronized provaDatabase getInstance(Context context){
        if(instancia==null)
            instancia = Room.databaseBuilder(context, provaDatabase.class, NOME_DB).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        return instancia;
    }
    public abstract UsuarioDAO usuarioDAO();
    public abstract ProdutoDAO produtoDAO();
}
