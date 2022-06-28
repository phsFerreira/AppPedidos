package com.example.prova02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class TelaCarrinho extends AppCompatActivity {

    private provaDatabase provaDB;
    private Toolbar toolbar;
    private Produto prod=new Produto();

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