package com.example.witch.gtslsac_app_1.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.witch.gtslsac_app_1.Config;
import com.example.witch.gtslsac_app_1.MySingleton;
import com.example.witch.gtslsac_app_1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CrudClientes extends Fragment {
    View myView;

    Button btn_agregarCli;
    EditText NombreCli, LogoCli;
    String nombreCli, logoCli, opcion;
    AlertDialog.Builder builder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_crud_clientes, container, false);


        btn_agregarCli = (Button) myView.findViewById(R.id.btn_agregar_cliente);
        NombreCli = (EditText) myView.findViewById(R.id.editTextEmpresa);
        LogoCli = (EditText) myView.findViewById(R.id.editTextLogo);


        builder = new AlertDialog.Builder(getContext());

        btn_agregarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreCli = NombreCli.getText().toString();
                logoCli = LogoCli.getText().toString();

                if (nombreCli.equals("")) {
                    builder.setMessage("Algo salió mal");
                    builder.setMessage("Debe Ingresar por lo menos el nombre de la Empresa");
                    displayAlert("input_error");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.adaptador_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String codigo = jsonObject.getString("codigo");
                                        if (codigo.equals("reg_failed")) {
                                            builder.setTitle("Error en el Registro....");
                                            displayAlert(jsonObject.getString("mensaje"));
                                        } else {
                                            String mensaje = jsonObject.getString("mensaje");
                                            builder.setTitle("Respuesta del Servidor...");
                                            builder.setMessage(mensaje);
                                            displayAlert(codigo);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            opcion = "InsertarCliente";
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("Empresa", nombreCli);
                            params.put("Logo", logoCli);
                            params.put("Opcion", opcion);
                            return params;
                        }
                    };
                    MySingleton.getInstance(getContext()).addRequestQueue(stringRequest);
                }
            }
        });
        return myView;
    }


    private void displayAlert(final String codigo) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (codigo.equals("input_error")) {
                    NombreCli.setText("");
                    LogoCli.setText("");
                } else if (codigo.equals("reg_success")) {
                    Toast.makeText(getContext(), "REGISTRO COMPLETADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "REGISTRO COMPLETADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    NombreCli.setText("");
                    LogoCli.setText("");
                } else if (codigo.equals("reg_failed")) {
                    NombreCli.setText("");
                    LogoCli.setText("");
                    Toast.makeText(getContext(), "REGISTRO NO COMPLETADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
