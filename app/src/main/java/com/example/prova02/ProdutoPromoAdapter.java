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

import com.example.prova02.Produto;

import java.util.ArrayList;

public class ProdutoPromoAdapter extends RecyclerView.Adapter<ProdutoPromoAdapter.ViewHolder>{

    private ArrayList<Produto> listaProdutosPromo;
    private Context context;
    private ItemClickListener listener;

    public ProdutoPromoAdapter(Context c, ArrayList<Produto> lista, ItemClickListener itemClickListener){
        context=c;
        listaProdutosPromo=lista;
        listener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.itemrvpromo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImgProduto().setImageBitmap(listaProdutosPromo.get(position).getImgProduto());
        holder.getNomeProduto().setText(listaProdutosPromo.get(position).getNomeProduto());
        holder.getPrecoProduto().setText(listaProdutosPromo.get(position).getPrecoProduto().toString());
    }

    @Override
    public int getItemCount() {
        return listaProdutosPromo.size();
    }

    public interface ItemClickListener{
        void onItemClick(int position, Produto produto);
        void btAdicionarRemoverClick(int position, Produto produto);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgProduto;
        private final TextView nomeProduto;
        private final TextView precoProduto;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            imgProduto=itemView.findViewById(R.id.imgProdutoPromo);
            nomeProduto=itemView.findViewById(R.id.tvNomeProdPromo);
            precoProduto=itemView.findViewById(R.id.tvPrecoProduto);
            ImageButton btAdicionarRemover=itemView.findViewById(R.id.btAddProdutoPromo);

            itemView.setOnClickListener(view ->{
                int position=getAdapterPosition();
                listener.onItemClick(position, listaProdutosPromo.get(position));
            });

            btAdicionarRemover.setOnClickListener(view -> {
                int position=getAdapterPosition();
                listener.btAdicionarRemoverClick(position, listaProdutosPromo.get(position));
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
