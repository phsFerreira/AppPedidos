package com.example.prova02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder>{

    private ArrayList<Produto> listaProdutos;
    private Context context;
    private ItemClickListener listener;
    private Double desconto;

    public ProdutoAdapter(Context c, ArrayList<Produto> lista, ItemClickListener itemClickListener){
        context=c;
        listaProdutos=lista;
        listener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.item_cardapio, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        desconto=listaProdutos.get(position).getDesconto();
        holder.getImgProduto().setImageBitmap(listaProdutos.get(position).getImgProduto());
        holder.itemView.setOnClickListener(view -> listener.onItemClick(listaProdutos.get(position)));
        if(desconto>0){
            holder.getNomeProduto().setText("*"+listaProdutos.get(position).getNomeProduto());
        }
        else
            holder.getNomeProduto().setText(listaProdutos.get(position).getNomeProduto());
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public interface ItemClickListener{
        void onItemClick(Produto produto);
        void btEditarClick(Produto produto);
        void btExcluirClick(int position, Produto produto);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgProduto;
        private final TextView nomeProduto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduto=itemView.findViewById(R.id.imgProduto);
            nomeProduto=itemView.findViewById(R.id.tvNomeProduto);
            ImageButton btEditar=itemView.findViewById(R.id.btEditarProduto);
            ImageButton btExcluir=itemView.findViewById(R.id.btExcluirProduto);

            btEditar.setOnClickListener(view -> {
                int position=getAdapterPosition();
                listener.btEditarClick(listaProdutos.get(position));
            });

            btExcluir.setOnClickListener(view -> {
                int position=getAdapterPosition();
                listener.btExcluirClick(position, listaProdutos.get(position));
            });
        }

        public TextView getNomeProduto(){
            return nomeProduto;
        }

        public ImageView getImgProduto(){
            return imgProduto;
        }

//        public Double getDesconto(){
//            return desconto;
//        }
    }
}
