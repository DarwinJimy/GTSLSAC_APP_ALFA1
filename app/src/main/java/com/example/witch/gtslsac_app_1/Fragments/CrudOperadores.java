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


public class CrudOperadores extends Fragment {


    View myView1;

    Button btn_agregarOp;
    EditText NombresOp, ApellidosOp;
    boolean estadoOp;
    String nombresOp, apellidosOp, opcion;
    AlertDialog.Builder builder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView1 = inflater.inflate(R.layout.fragment_crud_operadores, container, false);

        btn_agregarOp = (Button) myView1.findViewById(R.id.btn_agregar_operador);
        NombresOp = (EditText) myView1.findViewById(R.id.editTextNombres);
        ApellidosOp = (EditText) myView1.findViewById(R.id.editTextApellidos);

        builder = new AlertDialog.Builder(getContext());

        btn_agregarOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombresOp = NombresOp.getText().toString();
                apellidosOp = ApellidosOp.getText().toString();

                if (nombresOp.equals("")) {
                    builder.setMessage("Algo salió mal");
                    builder.setMessage("Debe Ingresar por lo menos el nombre del Operador");
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
                            opcion = "InsertarOperador";
                            estadoOp = true;
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("Nombres", nombresOp);
                            params.put("Apellidos", apellidosOp);
                            params.put("Estado", String.valueOf(estadoOp));
                            params.put("Opcion", opcion);
                            return params;
                        }
                    };
                    MySingleton.getInstance(getContext()).addRequestQueue(stringRequest);
                }
            }
        });
        return myView1;
    }

    private void displayAlert(final String codigo) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (codigo.equals("input_error")) {
                    NombresOp.setText("");
                    ApellidosOp.setText("");

                } else if (codigo.equals("reg_success")) {
                    Toast.makeText(getContext(), "REGISTRO COMPLETADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    NombresOp.setText("");
                    ApellidosOp.setText("");
                } else if (codigo.equals("reg_failed")) {
                    NombresOp.setText("");
                    ApellidosOp.setText("");
                    Toast.makeText(getContext(), "REGISTRO NO COMPLETADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
