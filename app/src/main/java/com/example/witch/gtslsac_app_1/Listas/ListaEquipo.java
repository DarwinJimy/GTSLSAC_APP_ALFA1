package com.example.witch.gtslsac_app_1.Listas;

/**
 * Created by witch on 08/06/2017.
 */

public class ListaEquipo {
    private String nombreEquipo;
    private String marcaEquipo;
    private String modeloEquipo;
    private String capacidadEquipo;
    private String anioEquipo;
    private String placaEquipo;
    private String colorEquipo;
    private String codigoEquipo;

    public ListaEquipo(String nombreEquipo, String marcaEquipo, String modeloEquipo, String capacidadEquipo, String anioEquipo, String placaEquipo, String colorEquipo, String codigoEquipo) {
        this.nombreEquipo = nombreEquipo;
        this.marcaEquipo = marcaEquipo;
        this.modeloEquipo = modeloEquipo;
        this.capacidadEquipo = capacidadEquipo;
        this.anioEquipo = anioEquipo;
        this.placaEquipo = placaEquipo;
        this.colorEquipo = colorEquipo;
        this.codigoEquipo = codigoEquipo;
    }





    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getMarcaEquipo() {
        return marcaEquipo;
    }

    public String getModeloEquipo() {
        return modeloEquipo;
    }

    public String getCapacidadEquipo() {
        return capacidadEquipo;
    }

    public String getAnioEquipo() {
        return anioEquipo;
    }

    public String getPlacaEquipo() {
        return placaEquipo;
    }

    public String getColorEquipo() {
        return colorEquipo;
    }

    public String getCodigoEquipo() {
        return codigoEquipo;
    }
}
