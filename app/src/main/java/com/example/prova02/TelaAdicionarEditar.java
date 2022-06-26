package com.example.prova02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaAdicionarEditar extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNome, txtPreco, txtDesconto, txtDescricao;
    private Button btCadastrar;
    private provaDatabase provaDB;
    private Toolbar toolbar;
    private Produto prod;
    private Intent it;
    private int produto_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_editar);

        //recebe id passado pelo botao de editar produto
        it=this.getIntent();
        if(it!=null){
            Bundle params=it.getExtras();
            if(params!=null){
                produto_id=params.getInt("id_produto");
            }
        }

        //TOOLBAR
        toolbar=findViewById(R.id.toolbarCadastrar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        txtNome=findViewById(R.id.txtNomeProduto);
        txtPreco=findViewById(R.id.txtPreco);
        txtDesconto=findViewById(R.id.txtDesconto);
        txtDescricao=findViewById(R.id.txtDescricao);
        btCadastrar=findViewById(R.id.btCadastrarProduto);
        btCadastrar.setOnClickListener(this);

        if(produto_id!=0){
            getSupportActionBar().setTitle("Editar Produto");
            prod=provaDB.produtoDAO().findById(produto_id);
            txtNome.setText(prod.nome);
            txtPreco.setText(prod.preco.toString());
            txtDesconto.setText(prod.desconto.toString());
            txtDescricao.setText(prod.descricao.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSelecionarImagem:

                break;

            case R.id.btCadastrarProduto:
                String nome;
                String preco;
                String desconto;
                String descricao;
                nome=txtNome.getText().toString();
                preco=txtPreco.getText().toString();
                desconto=txtDesconto.getText().toString();
                descricao=txtDescricao.getText().toString();

                if(produto_id==0){
                    if(nome.isEmpty() || preco.isEmpty() || desconto.isEmpty() || descricao.isEmpty())
                        Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    else{
                        Produto prod=new Produto();
                        prod.nome=nome;
                        prod.preco=Double.parseDouble(preco);
                        prod.desconto=Double.parseDouble(desconto);
                        prod.descricao=descricao;
                        provaDB.produtoDAO().insert(prod);

                        Intent it=new Intent(this, GerenciarCardapio.class);
                        startActivity(it);
                    }
                }
                else{
                    prod=provaDB.produtoDAO().findById(produto_id);

                    nome=txtNome.getText().toString();
                    preco=txtPreco.getText().toString();
                    desconto=txtDesconto.getText().toString();
                    descricao=txtDescricao.getText().toString();

                    prod.nome=nome;
                    prod.preco=Double.parseDouble(preco);
                    prod.desconto=Double.parseDouble(desconto);
                    prod.descricao=descricao;

                    provaDB.produtoDAO().update(prod);

                    Intent it=new Intent(this, GerenciarCardapio.class);
                    startActivity(it);
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it=new Intent(this, GerenciarCardapio.class);
        startActivity(it);
        return true;
    }


}