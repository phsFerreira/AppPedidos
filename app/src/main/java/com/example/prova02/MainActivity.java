package com.example.prova02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsuario, txtSenha;
    private Button btLogin, btRegistrar, btPopular;
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

        btPopular = findViewById(R.id.btPopular);
        btPopular.setOnClickListener(this);

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

            case R.id.btPopular:
                  Toast.makeText(this, "Produto adicionado!", Toast.LENGTH_SHORT).show();
                Produto prod=new Produto();
                prod.nome="sushi";
                prod.preco=Double.parseDouble(String.valueOf(10));
                prod.desconto=Double.parseDouble(String.valueOf(15));
                prod.descricao="Sushi (すし, 寿司, 鮨) é um prato da culinária japonesa que possui origem numa antiga técnica de conservação da carne de peixe em arroz avinagrado. ";
                ImageView imgView = new ImageView(this);
                imgView.setImageResource(R.drawable.sushi);
                Bitmap bitmap = ((BitmapDrawable)imgView.getDrawable()).getBitmap();
                prod.imagem = bitmap;
                provaDB.produtoDAO().insert(prod);
                break;
        }
    }

    //metodo para esconder o teclado
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}