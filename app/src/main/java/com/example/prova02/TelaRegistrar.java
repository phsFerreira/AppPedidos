package com.example.prova02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaRegistrar extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNomeCompleto, txtEmail, txtNomeUsuario, txtSenha, txtConfirmarSenha;
    private Button btRegistrar;
    private provaDatabase provaDB;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_registrar);

        //TOOLBAR
        toolbar=findViewById(R.id.toolbarRegistrar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        txtNomeCompleto=findViewById(R.id.txtNomeCompleto);
        txtEmail=findViewById(R.id.txtEmail);
        txtNomeUsuario=findViewById(R.id.txtNomeUsuarioRegistro);
        txtSenha=findViewById(R.id.txtSenhaRegistro);
        txtConfirmarSenha=findViewById(R.id.txtConfirmarSenha);
        btRegistrar=findViewById(R.id.btRegistrarse);
        btRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nomeCompleto, email, nomeUsuario, senha, confirmarSenha;
        nomeCompleto=txtNomeCompleto.getText().toString();
        email=txtEmail.getText().toString();
        nomeUsuario=txtNomeUsuario.getText().toString();
        senha=txtSenha.getText().toString();
        confirmarSenha=txtConfirmarSenha.getText().toString();

        if(nomeCompleto.isEmpty() || email.isEmpty() || nomeUsuario.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty())
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        else{
            if(senha.equals(confirmarSenha)){
                Usuario usr=new Usuario();
                usr.nome=nomeCompleto;
                usr.email=email;
                usr.usuario=nomeUsuario;
                usr.senha=senha;
                provaDB.usuarioDAO().insert(usr);

                Intent it=new Intent(this, MainActivity.class);
                startActivity(it);
            }
        }
    }
}