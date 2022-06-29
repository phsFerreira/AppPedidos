package com.example.prova02.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prova02.R;


public class FragmentExibirPedido extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentExibirPedido() {
        // Required empty public constructor
    }

    public static FragmentExibirPedido newInstance(String param1, String param2) {
        FragmentExibirPedido fragment = new FragmentExibirPedido();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        TextView tvEndereco = getActivity().findViewById(R.id.tvEnderecoFinal);
//        TextView tvMetodoPagamento = getActivity().findViewById(R.id.tvMetodoPagamentoFinal);
//        TextView tvValortotal = getActivity().findViewById(R.id.tvValorTotalFinal);
//
//        Intent it = this.getActivity().getIntent();
//        Bundle params = it.getExtras();
//
//        int i=0;
//        tvEndereco.setText(params.getString("endereco"));
//        tvMetodoPagamento.setText(params.getString("metodo pagamento"));
//        tvValortotal.setText(params.getString("valor total"));

        return inflater.inflate(R.layout.fragment_exibir_pedido, container, false);
    }
}