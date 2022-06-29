package com.example.prova02;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProdutoDetalhes extends AppCompatActivity {

    private ImageView ivProdutoDet;
    private TextView tvNmProduto, tvDescricaoProduto, tvNumero;
    private ImageButton btSubtract, btAdd;
    private Button btAdicionarAoCarrinho;
    private Toolbar toolbarProdutoDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhes);

        ivProdutoDet = findViewById(R.id.ivProdutoDet);
        tvNmProduto = findViewById(R.id.tvNmProduto);
        tvDescricaoProduto = findViewById(R.id.tvDescricaoProduto);
        tvNumero = findViewById(R.id.tvNumero);
        btSubtract = findViewById(R.id.btSubtract);
        btAdd = findViewById(R.id.btAdd);
        btAdicionarAoCarrinho = findViewById(R.id.btAdicionarAoCarrinho);

        toolbarProdutoDetalhes=findViewById(R.id.toolbarProdutoDetalhes);
        setSupportActionBar(toolbarProdutoDetalhes);
        getSupportActionBar().setTitle("Detalhes Produto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}