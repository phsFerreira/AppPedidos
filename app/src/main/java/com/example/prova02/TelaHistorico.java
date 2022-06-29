package com.example.prova02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaHistorico extends AppCompatActivity {

    private provaDatabase provaDB;
    private Toolbar toolbar;
    private Pedido pedido=new Pedido();
    private Intent it;
    private PedidoAdapter adapter;
    private RecyclerView rvListaHistorico;
    private ArrayList<Pedido> pedidos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historico);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        toolbar=findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Histórico de pedidos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onResume() {
        super.onResume();
        pedidos = (ArrayList<Pedido>) provaDB.pedidoDAO().getAll();
        rvListaHistorico=(RecyclerView) findViewById(R.id.rvListaHistorico);
        rvListaHistorico.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter=new PedidoAdapter(this, pedidos);

        rvListaHistorico.setAdapter(adapter);
    }
}