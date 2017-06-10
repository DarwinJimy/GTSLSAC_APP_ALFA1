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


public class CrudEquipos extends Fragment {

    View myView;

    Button btn_agregarEq;
    EditText NombreEq, MarcaEq, ModeloEq, CapacidadEq, AnioEq, PlacaEq, ColorEq, CodigoEq;
    String nombreEq, marcaEq, modeloEq, capacidadEq, anioEq, placaEq, colorEq, codigoEq, opcion;
    Double horometroEq = 0.0, combustibleEq = 0.0;
    AlertDialog.Builder builder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_crud_equipos, container, false);


        btn_agregarEq = (Button) myView.findViewById(R.id.btn_agregar_equipo);
        NombreEq = (EditText) myView.findViewById(R.id.editTextEquipo);
        MarcaEq = (EditText) myView.findViewById(R.id.editTextMarca);
        ModeloEq = (EditText) myView.findViewById(R.id.editTextModelo);
        CapacidadEq = (EditText) myView.findViewById(R.id.editTextCapacidad);
        AnioEq = (EditText) myView.findViewById(R.id.editTextAnio);
        PlacaEq = (EditText) myView.findViewById(R.id.editTextPlaca);
        ColorEq = (EditText) myView.findViewById(R.id.editTextColor);
        CodigoEq = (EditText) myView.findViewById(R.id.editTextCodigo);

        builder = new AlertDialog.Builder(getContext());

        btn_agregarEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreEq = NombreEq.getText().toString();
                marcaEq = MarcaEq.getText().toString();
                modeloEq = ModeloEq.getText().toString();
                capacidadEq = CapacidadEq.getText().toString();
                anioEq = AnioEq.getText().toString();
                placaEq = PlacaEq.getText().toString();
                colorEq = ColorEq.getText().toString();
                codigoEq = CodigoEq.getText().toString();

                if (nombreEq.equals("")) {
                    builder.setMessage("Algo salió mal");
                    builder.setMessage("Debe Ingresar por lo menos el nombre de equipo");
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
                            opcion = "InsertarEquipo";
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("Nombre", nombreEq);
                            params.put("Marca", marcaEq);
                            params.put("Modelo", modeloEq);
                            params.put("Capacidad", capacidadEq);
                            params.put("Anio", anioEq);
                            params.put("Placa", placaEq);
                            params.put("Color", colorEq);
                            params.put("Codigo", codigoEq);
                            params.put("Horometro", horometroEq.toString());
                            params.put("Combustible", combustibleEq.toString());
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
                    NombreEq.setText("");

                } else if (codigo.equals("reg_success")) {
                    Toast.makeText(getContext(), "REGISTRO COMPLETADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    NombreEq.setText("");
                    MarcaEq.setText("");
                    ModeloEq.setText("");
                    CapacidadEq.setText("");
                    AnioEq.setText("");
                    PlacaEq.setText("");
                    ColorEq.setText("");
                    CodigoEq.setText("");
                } else if (codigo.equals("reg_failed")) {
                    NombreEq.setText("");
                    MarcaEq.setText("");
                    ModeloEq.setText("");
                    CapacidadEq.setText("");
                    AnioEq.setText("");
                    PlacaEq.setText("");
                    ColorEq.setText("");
                    CodigoEq.setText("");
                    Toast.makeText(getContext(), "REGISTRO NO COMPLETADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
