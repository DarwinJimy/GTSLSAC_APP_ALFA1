package com.example.witch.gtslsac_app_1.Listas;

/**
 * Created by witch on 05/06/2017.
 */
public class ListaCliente {
    private String nombreEmpresa;
    private String logoEmpresa;

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public String getLogoEmpresa() {
        return logoEmpresa;
    }

    public ListaCliente(String nombreEmpresa, String logoEmpresa) {
        this.logoEmpresa = logoEmpresa;
        this.nombreEmpresa = nombreEmpresa;
    }
}
