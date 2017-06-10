package com.example.witch.gtslsac_app_1.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.witch.gtslsac_app_1.Adapters.ListaOperadoresAdapter;
import com.example.witch.gtslsac_app_1.Config;
import com.example.witch.gtslsac_app_1.Listas.ListaOperador;
import com.example.witch.gtslsac_app_1.MySingleton;
import com.example.witch.gtslsac_app_1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class tab_operadores extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager IManager;
    private List<ListaOperador> ListaOperadores;
    AlertDialog.Builder builder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView;
        myView = inflater.inflate(R.layout.fragment_tab_operadores, container, false);

        builder = new AlertDialog.Builder(getContext());

        recyclerView = (RecyclerView) myView.findViewById(R.id.recicladorOperadores);
        recyclerView.setHasFixedSize(true);
        IManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(IManager);

        ListaOperadores = new ArrayList<>();
        loadRecyclerViewData();
        return myView;
    }
    public void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando Informacion...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Config.adaptador_listar_operadores_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("operadores");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                ListaOperador item = new ListaOperador(
                                        o.getString("Nombres"),
                                        o.getString("Apellidos")
                                );
                                ListaOperadores.add(item);
                            }
                            adapter = new ListaOperadoresAdapter(ListaOperadores, getContext());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });
        MySingleton.getInstance(getContext()).addRequestQueue(stringRequest);
    }

    private void displayAlert(String mensaje) {
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}