//package com.example.prova02;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder>{
//
//    private ArrayList<Produto> listaProdutos;
//    private Context context;
//    private ItemClickListener listener;
//
//    public ProdutoAdapter(Context c, ArrayList<Produto> lista, ItemClickListener itemClickListener){
//        context=c;
//        lista=listaProdutos;
//        listener=itemClickListener;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v=LayoutInflater.from(context).inflate(R.layout.item_cardapio, parent, false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.getNomeProduto().setText(listaProdutos.get(position).);
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public interface ItemClickListener{
//        void onItemClick(Produto produto);
//        void btEditarClick(Produto produto);
//        void btExcluirClick(int position, Produto produto);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        private final ImageView imgProduto;
//        private final TextView nomeProduto;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            imgProduto=itemView.findViewById(R.id.imgProduto);
//            nomeProduto=itemView.findViewById(R.id.tvNomeProduto);
//            ImageButton btEditar=itemView.findViewById(R.id.btEditarProduto);
//            ImageButton btExcluir=itemView.findViewById(R.id.btExcluirProduto);
//
//            btEditar.setOnClickListener(view -> {
//                int position=getAdapterPosition();
//            });
//        }
//
//        public TextView getNomeProduto(){
//            return nomeProduto;
//        }
//    }
//}
