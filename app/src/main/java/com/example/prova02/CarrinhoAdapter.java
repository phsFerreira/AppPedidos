package com.example.prova02;

import android.content.Context;
import android.media.Image;
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

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ViewHolder> {

    private ArrayList<Produto> listaProdutos;
    private Context context;
    private ItemClickListener listener;
    private Double valorPedido;
    //private int quantidade;

    public CarrinhoAdapter(Context c, ArrayList<Produto> lista, ItemClickListener itemClickListener){
        context=c;
        listaProdutos=lista;
        listener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_carrinho, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImgProduto().setImageBitmap(listaProdutos.get(position).getImgProduto());
        holder.getNomeProduto().setText(listaProdutos.get(position).getNomeProduto());
        holder.getPrecoProduto().setText(listaProdutos.get(position).getPrecoProduto().toString());
        //holder.getQuantidadeProduto().setText(listaProdutos.get(position));
        //holder.getQuantidadeProduto().setText();
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public interface ItemClickListener{
        void onItemClick(Produto produto);
        void btAdicionarItemClick(int position, Produto produto);
        void btRemoverItemClick(int position, Produto produto);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgProduto;
        private final TextView nomeProduto;
        private final TextView precoProduto;
        private final TextView quantidadeProduto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduto=itemView.findViewById(R.id.imgProdutoSelecionado);
            nomeProduto=itemView.findViewById(R.id.tvNomeProdSelecionado);
            precoProduto=itemView.findViewById(R.id.tvPrecoProdSelecionado);
            quantidadeProduto=itemView.findViewById(R.id.tvQuantidade);
            ImageButton btAdicionarItem=itemView.findViewById(R.id.btMaisProduto);
            ImageButton btRemoverItem=itemView.findViewById(R.id.btMenosProduto);
            RecyclerView rvProdutos=itemView.findViewById(R.id.rvItemsPromo);

//            rvProdutos.setOnClickListener(view ->{
//                int position=getAdapterPosition();
//                listener.onItemClick(position, listaProdutos.get(position));
//            });

            btAdicionarItem.setOnClickListener(view -> {
                int position=getAdapterPosition();
                listener.btAdicionarItemClick(position, listaProdutos.get(position));
            });

            btRemoverItem.setOnClickListener(view -> {
                int position=getAdapterPosition();
                listener.btRemoverItemClick(position, listaProdutos.get(position));
            });
        }

        public ImageView getImgProduto(){
            return imgProduto;
        }

        public TextView getNomeProduto() {
            return nomeProduto;
        }

        public TextView getPrecoProduto() {
            return precoProduto;
        }

        public TextView getQuantidadeProduto(){
            return quantidadeProduto;
        }
    }
}
