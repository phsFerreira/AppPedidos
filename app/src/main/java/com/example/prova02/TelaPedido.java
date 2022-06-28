package com.example.prova02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaPedido extends AppCompatActivity {

    private provaDatabase provaDB;
    private Toolbar toolbar;
    private Intent it;

    private ProdutoPromoAdapter adapter;
    private ProdutosAdapter adapter2;
    private RecyclerView rvProdutosPromo, rvProdutos;
    private ArrayList<Produto> produtosPromo=new ArrayList<>();
    private ArrayList<Produto> produtos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedido);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        //Recycler view dos produtos em promocao
        produtosPromo= (ArrayList<Produto>) provaDB.produtoDAO().getAllByDesconto();
        rvProdutosPromo=(RecyclerView) findViewById(R.id.rvItemsPromo);
        rvProdutosPromo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter=new ProdutoPromoAdapter(this, produtosPromo, new ProdutoPromoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Produto produto) {
                Toast.makeText(TelaPedido.this, "teste", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void btAdicionarRemoverClick(int position, Produto produto) {
                Toast.makeText(TelaPedido.this, "teste", Toast.LENGTH_SHORT).show();
            }
        });
        rvProdutosPromo.setAdapter(adapter);

        //recicler view dos produtos sem desconto
        produtos= (ArrayList<Produto>) provaDB.produtoDAO().getAllBySemDesconto();
        rvProdutos=(RecyclerView) findViewById(R.id.rvItems);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter2=new ProdutosAdapter(this, produtos, new ProdutosAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Produto produto) {

            }

            @Override
            public void btAdicionarItemClick(int position, Produto produto) {

            }

            @Override
            public void btRemoverItemCLick(int position, Produto produto) {

            }
        });
        rvProdutos.setAdapter(adapter2);

        toolbar=findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cardápio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbarpedido_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btCarrinho:
                it=new Intent(this, TelaCarrinho.class);
                startActivity(it);

            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}