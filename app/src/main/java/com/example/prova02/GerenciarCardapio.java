package com.example.prova02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class GerenciarCardapio extends AppCompatActivity {

    private provaDatabase provaDB;
    private Toolbar toolbar;

    private ProdutoAdapter adapter;
    private RecyclerView rvProdutos;
    private ArrayList<Produto> produtos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_cardapio);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        //produtos=provaDB;

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
                Toast.makeText(this, "clicou", Toast.LENGTH_SHORT).show();
                break;
            }
        return super.onOptionsItemSelected(item);
    }
}