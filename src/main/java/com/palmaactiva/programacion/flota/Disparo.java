package com.palmaactiva.programacion.flota;

public class Disparo {
    public int fila;
    public int columna;
    private boolean valido = false;
    private static final String LETRAS_VALIDAS = "abcdefghi";

    public Disparo(String posicionTexto) {
        this.valido = false;
        if (posicionTexto.length() == 2) {
            char letra = posicionTexto.toLowerCase().charAt(0);
            if (LETRAS_VALIDAS.contains(Character.toString(letra))) {
                this.columna = LETRAS_VALIDAS.indexOf(letra);
                char numero = posicionTexto.charAt(1);
                try {
                    this.fila = Integer.parseInt(Character.toString(numero)) - 1;
                    if (this.fila >= 0 && this.fila < Flota.NUM_FILAS) {
                        this.valido = true;
                    }
                } catch (NumberFormatException nfe) {
                    // El número no es váido
                }
            }
        }
    }

    public boolean esValido() {
        return this.valido;
    }

    public boolean sobrePosicion(int fila, int columna) {
        return this.fila == fila && this.columna == columna;
    }
}