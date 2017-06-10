package com.example.witch.gtslsac_app_1.Listas;

/**
 * Created by witch on 08/06/2017.
 */

public class ListaOperador {
    private String nombresOperador;
    private String apellidosOperador;

    public String getNombresOperador() {
        return nombresOperador;
    }
    public String getApellidosOperador() {
        return apellidosOperador;
    }

    public ListaOperador(String nombresOperador, String apellidosOperador) {
        this.nombresOperador = nombresOperador;
        this.apellidosOperador = apellidosOperador;
    }
}
