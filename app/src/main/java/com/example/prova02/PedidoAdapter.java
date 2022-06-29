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

import java.util.ArrayList;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.ViewHolder> {

    private ArrayList<Pedido> listaPedidos;
    private Context context;
    private ProdutoAdapter.ItemClickListener listener;

    public PedidoAdapter(Context c, ArrayList<Pedido> lista) {
        context = c;
        listaPedidos = lista;
    }

    @NonNull
    @Override
    public PedidoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_historico, parent, false);
        return new PedidoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.ViewHolder holder, int position) {
        holder.getNumeroPedido().setText(String.valueOf(listaPedidos.get(position).idPedido));
        holder.getMetodopagamentoPedido().setText(listaPedidos.get(position).pagamento);
        holder.getPrecoPedido().setText(String.valueOf(listaPedidos.get(position).valor));

    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView numeroPedido;
        private final TextView metodopagamentoPedido;
        private final TextView precoPedido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numeroPedido = itemView.findViewById(R.id.tvNumeoPedido);
            metodopagamentoPedido = itemView.findViewById(R.id.tvMetodoPagamentoPedido);
            precoPedido = itemView.findViewById(R.id.tvPrecoPedido);

        }

        public TextView getNumeroPedido() {return numeroPedido; }

        public TextView getMetodopagamentoPedido() {return metodopagamentoPedido;}

        public TextView getPrecoPedido() {return precoPedido;
        }
    }

}
