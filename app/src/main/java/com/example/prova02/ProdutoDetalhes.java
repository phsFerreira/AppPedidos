package com.example.prova02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProdutoDetalhes extends AppCompatActivity implements View.OnClickListener {

    private provaDatabase provaDB;
    private ImageView ivProdutoDet;
    private TextView tvNmProduto, tvDescricaoProduto, tvNumero;
    private ImageButton btSubtract, btAdd;
    private Button btAdicionarAoCarrinho;
    private Toolbar toolbarProdutoDetalhes;
    private int idProduto;
    private Intent it;
    private Produto prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhes);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        it=this.getIntent();
        if(it!=null){
            Bundle params=it.getExtras();
            idProduto=params.getInt("id_produto");
            prod=provaDB.produtoDAO().findById(idProduto);
        }


        ivProdutoDet = findViewById(R.id.ivProdutoDet);
        tvNmProduto = findViewById(R.id.tvNmProduto);
        tvDescricaoProduto = findViewById(R.id.tvDescricaoProduto);
        tvNumero = findViewById(R.id.tvNumero);
        btSubtract = findViewById(R.id.btSubtract);
        btSubtract.setOnClickListener(this);
        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);
        btAdicionarAoCarrinho = findViewById(R.id.btAdicionarAoCarrinho);
        btAdicionarAoCarrinho.setOnClickListener(this);

        tvDescricaoProduto.setText(prod.descricao);
        tvNmProduto.setText(prod.nome);
        ivProdutoDet.setImageBitmap(prod.imagem);

        toolbarProdutoDetalhes=findViewById(R.id.toolbarProdutoDetalhes);
        setSupportActionBar(toolbarProdutoDetalhes);
        getSupportActionBar().setTitle("Detalhes Produto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btSubtract:
                int quant=0;tvNumero.setText(String.valueOf(quant));
                break;

            case R.id.btAdd:
                quant=1;
                tvNumero.setText(String.valueOf(quant));
                break;

            case R.id.btAdicionarAoCarrinho:
                Toast.makeText(this, "Item adicionado.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}