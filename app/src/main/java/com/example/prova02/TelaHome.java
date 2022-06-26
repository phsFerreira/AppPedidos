package com.example.prova02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TelaHome extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = "UIElementsPracticeLog";

    private TextView tvBemVindo, tvNomeUsuario;
    private Button btSair, btIniciarPedido;
    private ImageView imgProduto;
    private provaDatabase provaDB;
    private Usuario usr;
    private Produto prod;
    private ArrayList<Produto> produtos=new ArrayList<>();
    private Intent it;
    private String nomeUsuario;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);

        //BANCO
        provaDB=provaDatabase.getInstance(getApplicationContext());

        //NAVIGATION DRAWER
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("iProva 2022");

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        tvBemVindo=findViewById(R.id.tvBemVindo);
        btIniciarPedido=findViewById(R.id.btIniciarPedido);
        imgProduto=findViewById(R.id.ivProdutoPromo);
        btIniciarPedido.setOnClickListener(this);
        btSair=navigationView.getHeaderView(0).findViewById(R.id.btSair);
        btSair.setOnClickListener(this);

        Intent it=this.getIntent();
        if(it!=null){
            Bundle params=it.getExtras();
            if(params!=null){
                nomeUsuario=params.getString("nomeUsuario");
                usr=provaDB.usuarioDAO().findByName(nomeUsuario);
                String nomeCompleto=usr.nome;
                tvBemVindo.setText("Bem vindo, "+nomeCompleto+"!");

                //seta o nome completo do usuario dentro do nav_drawer
                tvNomeUsuario=navigationView.getHeaderView(0).findViewById(R.id.tvNomeUsuario);
                tvNomeUsuario.setText("Logado como: "+usr.nome);
            }
        }

        produtos= (ArrayList<Produto>) provaDB.produtoDAO().getAllByDesconto();
        prod=getRandomElement(produtos);
        imgProduto.setImageBitmap(prod.imagem);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btIniciarPedido:
                break;

            case R.id.btSair:
                    Intent it=new Intent(this, MainActivity.class);
                    startActivity(it);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarhome_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it=new Intent(this, MainActivity.class);
        startActivity(it);

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_pedido:
                Toast.makeText(this, "Novo Pedido", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_historico:
                Toast.makeText(this, "Historico", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_cardapio:
                it=new Intent(this, GerenciarCardapio.class);
                Bundle params=new Bundle();
                params.putString("nomeUsuario", nomeUsuario);
                it.putExtras(params);
                startActivity(it);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public Produto getRandomElement(List<Produto> lista)
    {
        Random rand = new Random();
        return lista.get(rand.nextInt(lista.size()));
    }
}