package com.example.witch.gtslsac_app_1.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.witch.gtslsac_app_1.Listas.ListaCliente;
import com.example.witch.gtslsac_app_1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by witch on 05/06/2017.
 */

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ListaClientesViewHolder> {

    private List<ListaCliente> listItems;
    private Context context;

    public ListaClientesAdapter(List<ListaCliente> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    public static class ListaClientesViewHolder extends RecyclerView.ViewHolder {
        public TextView empresa;
        public ImageView logo;
        public RelativeLayout relativeLayout;

        public ListaClientesViewHolder(View itemView) {
            super(itemView);
            empresa = (TextView) itemView.findViewById(R.id.nomEmpresa);
            logo = (ImageView) itemView.findViewById(R.id.logo);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.layoutContenedorClientes);

        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public ListaClientesViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clientes_card, parent, false);
        return new ListaClientesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListaClientesViewHolder viewHolder, int position) {
        final ListaCliente listaCliente = listItems.get(position);
        String url= "http://www.gtslsac.com/jorge/logos/";
        String urlLocal ="R.drawable.";
        Picasso.with(context).load(url+ listaCliente.getLogoEmpresa()).into(viewHolder.logo);
        viewHolder.empresa.setText(listaCliente.getNombreEmpresa());
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clickeaste: " + listaCliente.getNombreEmpresa(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

