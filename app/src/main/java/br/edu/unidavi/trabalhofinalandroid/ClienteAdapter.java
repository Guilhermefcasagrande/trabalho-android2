package br.edu.unidavi.trabalhofinalandroid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    private final OnClienteClickListener listener;
    private List<Cliente> clientes = new ArrayList<>();

    public ClienteAdapter (OnClienteClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(
                android.R.layout.simple_list_item_1,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Cliente cliente = clientes.get(i);
        viewHolder.nome.setText(clientes.get(i).getNome());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(cliente);
            }
        });
//        if(cliente.isDone()){
//            viewHolder.nome.setTextColor(Color.RED);
//        } else {
//            viewHolder.nome.setTextColor(Color.BLACK);
//        }
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }


    public void setUp(List<Cliente> tasks) {
        this.clientes.clear();
        this.clientes.addAll(tasks);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(android.R.id.text1);
        }
    }

    interface OnClienteClickListener{
        void onClick(Cliente cliente);
    }
}
