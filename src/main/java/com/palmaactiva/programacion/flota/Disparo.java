package com.palmaactiva.programacion.flota;

public class Disparo {
    public int fila;
    public int columna;
    private boolean valido = false;
    private static final String LETRAS_VALIDAS = "abcdefghi";

    public Disparo(String posicionTexto) {
        // Por defecto el disparo no es válido
        this.valido = false;
        // Se comprueba que la posición tiene exáctamente dos carácteres: a2
        if (posicionTexto.length() == 2) {
            // Pasamos el texto a minúscula y nos quedamos con la primera letra.
            char letra = posicionTexto.toLowerCase().charAt(0);
            // Comprobamos que la letra existe en nuestra cadena de texto de letras válidas.
            if (LETRAS_VALIDAS.contains(Character.toString(letra))) {
                // La columna será la misma que la posición de esa letra en la String
                this.columna = LETRAS_VALIDAS.indexOf(letra);
                // Sacamos el texto del número/fila, segundo carácter
                char numero = posicionTexto.charAt(1);
                try {
                    // Convertimos el número (que está en base 1) a int y le restamos 1 para que sea base(0)
                    this.fila = Integer.parseInt(Character.toString(numero)) - 1;
                    // Comprobamos que la fila esté dentro de nuestro tablero.
                    if (this.fila >= 0 && this.fila < Flota.NUM_FILAS) {
                        // Si hemos llegado hasta aquí es que la posición es válida.
                        // Y tenemos guardado fila y columna con los valores correctos.
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