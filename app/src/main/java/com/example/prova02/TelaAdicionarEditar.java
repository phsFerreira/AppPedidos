package com.example.prova02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TelaAdicionarEditar extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNome, txtPreco, txtDesconto, txtDescricao;
    private Button btCadastrar;
    private ImageButton btImagem;
    private Bitmap imagemSelecionada, imagem;
    private provaDatabase provaDB;
    private Toolbar toolbar;
    private Produto prod;
    private Intent it, intentImagem;
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
        btImagem=findViewById(R.id.btSelecionarImagem);
        btCadastrar.setOnClickListener(this);
        btImagem.setOnClickListener(this);

        if(produto_id!=0){
            getSupportActionBar().setTitle("Editar Produto");
            prod=provaDB.produtoDAO().findById(produto_id);
            txtNome.setText(prod.nome);
            txtPreco.setText(prod.preco.toString());
            txtDesconto.setText(prod.desconto.toString());
            txtDescricao.setText(prod.descricao.toString());
            btImagem.setImageBitmap(prod.imagem);
            imagemSelecionada = prod.imagem;
        }
    }

    ActivityResultLauncher<Intent> abreGaleria = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null && result.getResultCode() == RESULT_OK) {
                        if (result.getData() != null) {
                            intentImagem=result.getData();
                            Uri uri=intentImagem.getData();
                            btImagem.setImageURI(uri);
                            BitmapDrawable img =(BitmapDrawable) btImagem.getDrawable();
                            imagemSelecionada = img.getBitmap();
                        }
                    }
                }
            }
    );

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSelecionarImagem:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                abreGaleria.launch(intent);
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
                        prod.imagem=imagemSelecionada;
                        provaDB.produtoDAO().insert(prod);

                        finish();
                    }
                }
                else{
                    prod=provaDB.produtoDAO().findById(produto_id);

                    nome=txtNome.getText().toString();
                    preco=txtPreco.getText().toString();
                    desconto=txtDesconto.getText().toString();
                    descricao=txtDescricao.getText().toString();
                    imagem=btImagem.getDrawingCache();

                    prod.nome=nome;
                    prod.preco=Double.parseDouble(preco);
                    prod.desconto=Double.parseDouble(desconto);
                    prod.descricao=descricao;
                    prod.imagem=imagemSelecionada;

                    provaDB.produtoDAO().update(prod);

                    finish();
                }
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