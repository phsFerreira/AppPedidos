package com.example.prova02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaAdicionarEditar extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNome, txtPreco, txtDesconto;
    private Button btCadastrar;
    private provaDatabase provaDB;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_editar);

        //TOOLBAR
        toolbar=findViewById(R.id.toolbarCadastrar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        txtNome=findViewById(R.id.txtNomeProduto);
        txtPreco=findViewById(R.id.txtPreco);
        txtDesconto=findViewById(R.id.txtDesconto);
        btCadastrar=findViewById(R.id.btCadastrarProduto);
        btCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nome;
        String preco;
        String desconto;
        nome=txtNome.getText().toString();
        preco=txtPreco.getText().toString();
        desconto=txtDesconto.getText().toString();

        if(nome.isEmpty() || preco.isEmpty() || desconto.isEmpty())
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        else{
            Produto prod=new Produto();
            prod.nome=nome;
            prod.preco=  Double.parseDouble(preco);
            prod.desconto= Double.parseDouble(desconto);
            provaDB.produtoDAO().insert(prod);

            Intent it=new Intent(this, GerenciarCardapio.class);
            startActivity(it);
        }
    }
}