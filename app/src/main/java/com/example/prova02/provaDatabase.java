package com.example.prova02;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.prova02.DAOs.PedidoDAO;
import com.example.prova02.DAOs.PedidoProdutoDAO;
import com.example.prova02.DAOs.ProdutoDAO;
import com.example.prova02.DAOs.UsuarioDAO;

@Database(entities = {Usuario.class, Produto.class, Pedido.class, PedidosProdutosReferencia.class}, version=25)
@TypeConverters(Conversor.class)
public abstract class provaDatabase extends RoomDatabase {

    private static final String NOME_DB="prova02-DB";
    private static volatile provaDatabase instancia;

    public provaDatabase(){}

    //fallbackToDestructiveMigration() deleta o banco pra permitir atualizacoes

    public static synchronized provaDatabase getInstance(Context context){
        if(instancia==null)
            instancia = Room.databaseBuilder(context, provaDatabase.class, NOME_DB).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        return instancia;
    }
    public abstract UsuarioDAO usuarioDAO();
    public abstract ProdutoDAO produtoDAO();
    public abstract PedidoDAO pedidoDAO();
    public abstract PedidoProdutoDAO pedidoprodutoDAO();
}
