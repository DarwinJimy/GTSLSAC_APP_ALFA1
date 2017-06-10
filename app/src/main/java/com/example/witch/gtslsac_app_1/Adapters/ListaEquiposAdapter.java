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


import com.example.witch.gtslsac_app_1.Listas.ListaEquipo;
import com.example.witch.gtslsac_app_1.R;

import java.util.List;

/**
 * Created by witch on 08/06/2017.
 */

public class ListaEquiposAdapter extends RecyclerView.Adapter<ListaEquiposAdapter.ListaEquiposViewHolder> {

    private List<ListaEquipo> listItems;
    private Context context;

    public ListaEquiposAdapter(List<ListaEquipo> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    public static class ListaEquiposViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreEquipo, marcaEquipo, modeloEquipo, capacidadEquipo, anioEquipo, placaEquipo, colorEquipo, codigoEquipo;
        public ImageView logo;
        public RelativeLayout relativeLayout;

        public ListaEquiposViewHolder(View itemView) {
            super(itemView);
            nombreEquipo = (TextView) itemView.findViewById(R.id.tvNombreEquipo);
            marcaEquipo = (TextView) itemView.findViewById(R.id.tvMarcaEquipo);
            modeloEquipo = (TextView) itemView.findViewById(R.id.tvModeloEquipo);
            capacidadEquipo = (TextView) itemView.findViewById(R.id.tvCapacidadEquipo);
            anioEquipo = (TextView) itemView.findViewById(R.id.tvAnioEquipo);
            placaEquipo = (TextView) itemView.findViewById(R.id.tvPlacaEquipo);
            colorEquipo = (TextView) itemView.findViewById(R.id.tvColorEquipo);
            codigoEquipo = (TextView) itemView.findViewById(R.id.tvCodigoEquipo);

            //logo = (ImageView) itemView.findViewById(R.id.logo);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.layoutContenedorEquipos);

        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public ListaEquiposAdapter.ListaEquiposViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipos_card, parent, false);
        return new ListaEquiposAdapter.ListaEquiposViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListaEquiposAdapter.ListaEquiposViewHolder viewHolder, int position) {
        final ListaEquipo listaEquipo = listItems.get(position);
        //String url= "http://www.gtslsac.com/jorge/logos/";
        //String urlLocal ="R.drawable.";
        //Picasso.with(context).load(url+ listaEquipo.getNombreEquipo()).into(viewHolder.logo);
        viewHolder.nombreEquipo.setText(listaEquipo.getNombreEquipo());
        viewHolder.marcaEquipo.setText(listaEquipo.getMarcaEquipo());
        viewHolder.modeloEquipo.setText(listaEquipo.getModeloEquipo());
        viewHolder.capacidadEquipo.setText(listaEquipo.getCapacidadEquipo());
        viewHolder.anioEquipo.setText(listaEquipo.getAnioEquipo());
        viewHolder.placaEquipo.setText(listaEquipo.getPlacaEquipo());
        viewHolder.colorEquipo.setText(listaEquipo.getColorEquipo());
        viewHolder.codigoEquipo.setText(listaEquipo.getCodigoEquipo());



        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clickeaste: " + listaEquipo.getNombreEquipo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
