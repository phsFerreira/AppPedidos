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
import android.widget.Toast;

import com.example.prova02.CarrinhoAdapter;
import com.example.prova02.Produto;
import com.example.prova02.R;
import com.example.prova02.provaDatabase;

import java.util.ArrayList;

public class FragmentFecharPedido extends Fragment implements View.OnClickListener{

    Button btFecharPedido;
    provaDatabase provaDB;

    CarrinhoAdapter adapter;
    RecyclerView rvProdutosCarrinho;
    private ArrayList<Produto> produtos=new ArrayList<>();

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

        provaDB=provaDatabase.getInstance(getActivity().getApplicationContext());

        //Recycler view dos produtos do carrinho
        produtos= (ArrayList<Produto>) provaDB.produtoDAO().getAllByDesconto();
        rvProdutosCarrinho=(RecyclerView) view.findViewById(R.id.rvCarrinho);
        rvProdutosCarrinho.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        adapter=new CarrinhoAdapter(getActivity(), produtos, new CarrinhoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Produto produto) {

            }

            @Override
            public void btAdicionarItemClick(int position, Produto produto) {

            }

            @Override
            public void btRemoverItemClick(int position, Produto produto) {

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
                Toast.makeText(getActivity(), "Teste", Toast.LENGTH_SHORT).show();
        }
    }
}