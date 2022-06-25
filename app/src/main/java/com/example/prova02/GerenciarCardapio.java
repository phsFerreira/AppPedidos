package com.example.prova02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class GerenciarCardapio extends AppCompatActivity {

    private provaDatabase provaDB;
    private Toolbar toolbar;
    private Produto prod=new Produto();

    private ProdutoAdapter adapter;
    private RecyclerView rvProdutos;
    private ArrayList<Produto> produtos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_cardapio);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        produtos= (ArrayList<Produto>) provaDB.produtoDAO().getAll();
        rvProdutos=(RecyclerView) findViewById(R.id.rvLista);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter=new ProdutoAdapter(this, produtos, new ProdutoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Produto produto) {

            }

            @Override
            public void btEditarClick(Produto produto) {
                Toast.makeText(GerenciarCardapio.this, "Clicou", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void btExcluirClick(int position, Produto produto) {
                prod=provaDB.produtoDAO().findById(position);
                provaDB.produtoDAO().delete(prod);

                produtos.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        rvProdutos.setAdapter(adapter);

        toolbar=findViewById(R.id.toolbarTeste);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista de Produtos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbarcardapio_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
                finish();
                return true;

            case R.id.btAdicionarProduto:
                Intent it=new Intent(this, TelaAdicionarEditar.class);
                startActivity(it);;
                break;
            }
        return super.onOptionsItemSelected(item);
    }

//    public void excluirProduto(int i, String id){
//        try {
//            prod
//            provaDB.produtoDAO().delete();
//        }catch (SQLiteException e){
//            e.printStackTrace();
//        }
//    }
}