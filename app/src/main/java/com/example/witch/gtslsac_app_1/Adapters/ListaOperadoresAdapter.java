package com.example.witch.gtslsac_app_1.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.witch.gtslsac_app_1.Listas.ListaOperador;
import com.example.witch.gtslsac_app_1.R;

import java.util.List;

/**
 * Created by witch on 08/06/2017.
 */

public class ListaOperadoresAdapter extends RecyclerView.Adapter<ListaOperadoresAdapter.ListaOperadoresViewHolder>{

    private List<ListaOperador> listItems;
    private Context context;

    public ListaOperadoresAdapter(List<ListaOperador> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    public static class ListaOperadoresViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreOperador;
        public TextView apellidoOperador;

        public RelativeLayout relativeLayout;

        public ListaOperadoresViewHolder(View itemView) {
            super(itemView);
            nombreOperador = (TextView) itemView.findViewById(R.id.nomOperador);
            apellidoOperador = (TextView) itemView.findViewById(R.id.apeOperador);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.layoutContenedorOperadores);

        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public ListaOperadoresAdapter.ListaOperadoresViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.operadores_card, parent, false);
        return new ListaOperadoresAdapter.ListaOperadoresViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListaOperadoresAdapter.ListaOperadoresViewHolder viewHolder, int position) {
        final ListaOperador listaOperador = listItems.get(position);

        viewHolder.nombreOperador.setText(listaOperador.getNombresOperador());
        viewHolder.apellidoOperador.setText(listaOperador.getApellidosOperador());
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clickeaste: " + listaOperador.getNombresOperador(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
