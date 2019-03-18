package com.palmaactiva.programacion.flota_herencia;

public class Disparo extends Posicion {
    private static final String LETRAS_VALIDAS = "abcdefghi";

    // Constructor privado, sólo se puede invocar desde dentro de esta clase.
    private Disparo(int fila, int columna) {
        super(fila, columna);
    }

    public Disparo(Posicion pos) {
        super(pos.fila, pos.columna);
    }

    // Método estático para crear nuevos disparos a partir de texto
    public static Disparo parseDisparo(String posicionTexto) throws NumberFormatException, DisparoFormatException {
        int fila;
        int columna;
        if (posicionTexto.length() == 2) {
            char letra = posicionTexto.toLowerCase().charAt(0);
            if (LETRAS_VALIDAS.contains(Character.toString(letra))) {
                columna = LETRAS_VALIDAS.indexOf(letra);
                char numero = posicionTexto.charAt(1);

                fila = Integer.parseInt(Character.toString(numero)) - 1;
                if (fila >= 0 && fila < Flota.NUM_FILAS) {
                    // Si todo va bien, creamos un nuevo disparo con el constructor privado y la
                    // fila/columna procesadas.
                    return new Disparo(fila, columna);
                }
            }
        }
        // Si hemos llegado hasta aquí es que ha habido algún error de formato, lanzamos
        // excepción.
        throw new DisparoFormatException(posicionTexto);
    }

    @Override
    public String getStringCelda(Posicion pos) {
        // Por defecto un disparo es agua.
        return "~";
    }
}