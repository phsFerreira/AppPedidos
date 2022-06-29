package com.example.prova02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.prova02.Fragments.FragmentFecharPedido;

import java.util.ArrayList;

public class TelaCarrinho extends AppCompatActivity {

    private provaDatabase provaDB;
    private Toolbar toolbar;
    private Produto prod=new Produto();
    private Intent it;
    private Bundle params;
    private ArrayList<Produto> produtos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carrinho);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        //TOOLBAR
        toolbar=findViewById(R.id.toolbarCarrinho);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Carrinho");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        it=this.getIntent();
        if(it!=null){
            params=it.getExtras();
            if(params!=null){
                ArrayList<Integer> produtosSelecionados=(ArrayList<Integer>) params.getSerializable("lista");

                for(int i=1;i<=produtosSelecionados.size();i++){
                    prod=provaDB.produtoDAO().findById(i);
                    produtos.add(prod);
                }

                params.putSerializable("lista", produtos);
            }
        }

        //FRAGMENT
        getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, FragmentFecharPedido.class, params).commit();
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