package com.example.prova02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsuario, txtSenha;
    private Button btLogin, btRegistrar;
    private provaDatabase provaDB;
    private Usuario usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provaDB=provaDatabase.getInstance(getApplicationContext());

        txtUsuario=findViewById(R.id.txtUsuario);
        txtSenha=findViewById(R.id.txtSenha);
        btLogin=findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        btRegistrar=findViewById(R.id.btRegistrar);
        btRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btLogin:
                    hideSoftKeyboard(view);
                    String nomeUsuario=txtUsuario.getText().toString();
                    String senha=txtSenha.getText().toString();

                    if(nomeUsuario.isEmpty() || senha.isEmpty())
                        Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    else{
                        usr=provaDB.usuarioDAO().findByName(nomeUsuario);
                        if(usr!=null){
                            if(senha.equals(usr.senha)){
                                Intent it=new Intent(this, TelaHome.class);
                                Bundle params=new Bundle();
                                params.putString("nomeUsuario", nomeUsuario);
                                it.putExtras(params);
                                startActivity(it);
                            }
                            else
                                Toast.makeText(this, "Nome de usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(this, "Usuário não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                break;

            case R.id.btRegistrar:
                Intent it=new Intent(this, TelaRegistrar.class);
                startActivity(it);
                break;
        }
    }

    //metodo para esconder o teclado
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}