package com.example.prova02.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prova02.CarrinhoAdapter;
import com.example.prova02.Produto;
import com.example.prova02.R;
import com.example.prova02.provaDatabase;

import java.util.ArrayList;

public class FragmentFecharPedido extends Fragment implements View.OnClickListener{

    Button btFecharPedido;
    provaDatabase provaDB;
    TextView tvPreco, tvQuantidade, tvValorTotal;
    RadioGroup rgPagamento;
    String metodoPagamento;
    View viewCarrinho;
    CarrinhoAdapter adapter;
    RecyclerView rvProdutosCarrinho;
    ArrayList<Produto> produtos=new ArrayList<>();

    public FragmentFecharPedido() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fechar_pedido, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //BANCO
        provaDB=provaDatabase.getInstance(getActivity().getApplicationContext());

        tvQuantidade=view.findViewById(R.id.tvQuantidade);
        tvPreco=view.findViewById(R.id.tvPrecoProdSelecionado);
        tvValorTotal=view.findViewById(R.id.tvValorTotal);
        rgPagamento=(RadioGroup) view.findViewById(R.id.radioGroup);
        rgPagamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton btSelecionado=(RadioButton) radioGroup.findViewById(id);
                metodoPagamento=btSelecionado.getText().toString();
            }
        });

        //RECEBENDO LISTA PASSADA POR PARAMETRO PELA ACTIVITY
        if(getArguments()!=null){
            produtos= (ArrayList<Produto>) getArguments().getSerializable("lista");
        }

        //Recycler view dos produtos do carrinho
        //produtos= (ArrayList<Produto>) provaDB.produtoDAO().getAllByDesconto();
        rvProdutosCarrinho=(RecyclerView) view.findViewById(R.id.rvCarrinho);
        rvProdutosCarrinho.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        adapter=new CarrinhoAdapter(getActivity(), produtos, new CarrinhoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Produto produto) {
            }

            @Override
            public void btAdicionarItemClick(int position, Produto produto) {
                viewCarrinho=view;
                TextView tvQuantidade= view.findViewById(R.id.tvQuantidade);
                int quantidade = Integer.parseInt(tvQuantidade.getText().toString());
                quantidade++;
                tvQuantidade.setText(String.valueOf(quantidade));

            }

            @Override
            public void btRemoverItemClick(int position, Produto produto) {
                viewCarrinho=view;
                TextView tvQuantidade= view.findViewById(R.id.tvQuantidade);
                int quantidade = Integer.parseInt(tvQuantidade.getText().toString());
                if (quantidade<=0){
                    tvQuantidade.setText("0");
                }else {
                    quantidade--;
                    tvQuantidade.setText(String.valueOf(quantidade));
                }
            }
        });
        rvProdutosCarrinho.setAdapter(adapter);


        btFecharPedido=view.findViewById(R.id.btFecharPedido);
        btFecharPedido.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btFecharPedido:
                TextView txtPreco = viewCarrinho.findViewById(R.id.tvPrecoProdSelecionado);
                TextView txtQuantidade = viewCarrinho.findViewById(R.id.tvQuantidade);

                float preco = Float.parseFloat(txtPreco.getText().toString());
                int quantidade = Integer.parseInt(txtQuantidade.getText().toString());

                float valorTotal= preco * quantidade;

                TextView txtValorTotal= viewCarrinho.findViewById(R.id.tvValorTotal);
                txtValorTotal.setText("R$ "+ String.valueOf(valorTotal));
                Toast.makeText(getActivity(), "pagamento: "+metodoPagamento, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}