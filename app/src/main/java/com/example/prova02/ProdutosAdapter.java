package com.example.prova02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ViewHolder> {

    private ArrayList<Produto> listaProdutos;
    private Context context;
    private ItemClickListener listener;

    ProdutosAdapter(Context c, ArrayList<Produto> lista, ItemClickListener itemClickListener){
        context=c;
        listaProdutos=lista;
        listener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_rvprodutos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImgProduto().setImageBitmap(listaProdutos.get(position).getImgProduto());
        holder.getNomeProduto().setText(listaProdutos.get(position).getNomeProduto());
//        String preco=listaProdutos.get(position).getPrecoProduto().toString();
//        holder.getPrecoProduto().setText(String.format("R$ %.2f", preco));
        holder.getPrecoProduto().setText(listaProdutos.get(position).getPrecoProduto().toString());
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public interface ItemClickListener{
        void onItemClick(int position, Produto produto);
        void btAdicionarItemClick(int position, Produto produto);
        void btRemoverItemCLick(int position, Produto produto);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgProduto;
        private final TextView nomeProduto;
        private final TextView precoProduto;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            imgProduto=itemView.findViewById(R.id.imgProdutoRv);
            nomeProduto=itemView.findViewById(R.id.tvNomeProdutoRv);
            precoProduto=itemView.findViewById(R.id.tvPrecoProdutoRv);
            ImageButton btAdicionarItem=itemView.findViewById(R.id.btAddProduto);
            ImageButton btRemoverItem=itemView.findViewById(R.id.btRemoverProduto);

            itemView.setOnClickListener(view ->{
                int position=getAdapterPosition();
                listener.onItemClick(position, listaProdutos.get(position));
            });

            btAdicionarItem.setOnClickListener(view -> {
                int position=getAdapterPosition();
                listener.btAdicionarItemClick(position, listaProdutos.get(position));
            });

            btRemoverItem.setOnClickListener(view -> {
                int position=getAdapterPosition();
                listener.btRemoverItemCLick(position, listaProdutos.get(position));
            });
        }

        public TextView getNomeProduto(){
            return nomeProduto;
        }

        public TextView getPrecoProduto(){
            return precoProduto;
        }

        public ImageView getImgProduto(){
            return imgProduto;
        }
    }
}
