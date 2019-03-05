package com.palmaactiva.programacion.flota_herencia;

public class DisparoFormatException extends Exception{
    private String textoPosicion;

    public DisparoFormatException(String textoPosicion){
        this.textoPosicion = textoPosicion;
    }

    @Override
    public String toString() {
        return "Error procesando la posición '"+this.textoPosicion+"'.";
    }
}