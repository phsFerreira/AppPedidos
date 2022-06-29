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

import java.io.Serializable;
import java.util.ArrayList;

public class TelaPedido extends AppCompatActivity {

    private provaDatabase provaDB;
    private Toolbar toolbar;
    private Intent it;
    private Bundle params;

    private ProdutoPromoAdapter adapter;
    private ProdutosAdapter adapter2;
    private RecyclerView rvProdutosPromo, rvProdutos;
    private ArrayList<Produto> produtosPromo=new ArrayList<>();
    private ArrayList<Produto> produtos=new ArrayList<>();

    private ArrayList<Integer> produtosSelecionados=new ArrayList<Integer>();
    private Produto prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedido);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        it=this.getIntent();
        params=it.getExtras();

        //Recycler view dos produtos em promocao
        produtosPromo= (ArrayList<Produto>) provaDB.produtoDAO().getAllByDesconto();
        rvProdutosPromo=(RecyclerView) findViewById(R.id.rvItemsPromo);
        rvProdutosPromo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter=new ProdutoPromoAdapter(this, produtosPromo, new ProdutoPromoAdapter.ItemClickListener() {

            @Override
            public void onItemClick(int position, Produto produto) {
                prod=produtosPromo.get(position);
                int id=prod.idProduto;

                it=new Intent(TelaPedido.this, ProdutoDetalhes.class);

                params.putInt("id_produto", id);
                it.putExtras(params);
                startActivity(it);
            }

            @Override
            public void btAdicionarRemoverClick(int position, Produto produto) {
                prod=produtosPromo.get(position);
                int id=prod.idProduto;

                produtosSelecionados.add(id);
            }
        });
        rvProdutosPromo.setAdapter(adapter);

        //recycler view dos produtos sem desconto
        produtos= (ArrayList<Produto>) provaDB.produtoDAO().getAllBySemDesconto();
        rvProdutos=(RecyclerView) findViewById(R.id.rvItems);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter2=new ProdutosAdapter(this, produtos, new ProdutosAdapter.ItemClickListener() {

            @Override
            public void onItemClick(int position, Produto produto) {
                prod=produtos.get(position);
                int id=prod.idProduto;

                it=new Intent(TelaPedido.this, ProdutoDetalhes.class);

                params.putInt("id_produto", id);
                it.putExtras(params);
                startActivity(it);
            }

            @Override
            public void btAdicionarItemClick(int position, Produto produto) {
                prod=produtos.get(position);
                int id=prod.idProduto;

//                for(int i=1;i<=produtosSelecionados.size();i++){
//                    if(id==produtosSelecionados.get(i)){
//                        Toast.makeText(TelaPedido.this, "Esse produto já está no carrinho.", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        produtosSelecionados.add(id);
//                        Toast.makeText(TelaPedido.this, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                prod=produtos.get(position);
//                int id=prod.idProduto;

                for(int i=1;i<=produtosSelecionados.size();i++){
                    id=produtosSelecionados.get(i);
                    if(id==prod.idProduto)
                        Toast.makeText(TelaPedido.this, "Produto já está no carrinho.", Toast.LENGTH_SHORT).show();
                    else
                        produtosSelecionados.add(prod.idProduto);
                    }
            }

            @Override
            public void btRemoverItemCLick(int position, Produto produto) {
                prod=produto;
                int id=prod.idProduto;

                if(produtosSelecionados.size()>0){
                    produtosSelecionados.remove(position);
                    Toast.makeText(TelaPedido.this, "Produto removido do carrinho.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(TelaPedido.this, "O carrinho está vazio.", Toast.LENGTH_SHORT).show();
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
                it.putExtra("lista", (Serializable) produtosSelecionados);
                it.putExtras(params);
                startActivity(it);
                break;

            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}