package com.palmaactiva.programacion.flota;

import java.util.ArrayList;
import java.util.Scanner;

public class Flota {
    private ArrayList<Barco> losBarcos;
    private ArrayList<Disparo> disparosAgua;
    private int disparosDisponibles = 30;
    public static final int NUM_FILAS = 9;
    public static final int NUM_COLUMNAS = 9;

    public Flota() {
        // Iniciamos las colecciones de barcos y de disparos al agua
        this.losBarcos = new ArrayList<Barco>();
        this.disparosAgua = new ArrayList<Disparo>();
        // Cremamos los barcos con un método
        this.crearBarco(5);
        this.crearBarco(4);
        this.crearBarco(2);
        this.crearBarco(2);
        this.crearBarco(2);
    }

    private void crearBarco(int numCeldas) {
        Barco nuevoBarco = null;
        do {
            // Creamos un barco nuevo
            nuevoBarco = new Barco(numCeldas);
            // Y para los barcos creados anteriormente...
            for (Barco barco : this.losBarcos) {
                // Comprobamos si se solapan los dos barcos (el nuevo con el de bucle)
                if (barco.seSolapaCon(nuevoBarco)) {
                    // Si se solapan, descartamos el nuevo barco usando null y salimos del bucle (este barco no sirve, necesitamos otro)
                    nuevoBarco = null;
                    break;
                }
            }
            // Repetiremos esto mientras que nuevoBarco es null, es decir, mientras que el nuevoBarco se solape con otro. 
        } while (nuevoBarco == null);

        // Añado el nuevo barco a mi lista de barcos.
        this.losBarcos.add(nuevoBarco);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Mostramos los nombres de las columnas y una línea
        sb.append(" |a|b|c|d|e|f|g|h|i|\n");
        sb.append("--------------------\n");
        // Declaramos algunas variables para ver el estado de cada celda
        boolean hayBarco = false;
        boolean hayImpacto = false;
        boolean hayAgua = false;
        // Recorremos todas las filas
        for (int indiceFila = 0; indiceFila < NUM_FILAS; indiceFila++) {
            // Mostramos el número de la fila: 1, 2, 3, 4
            sb.append((indiceFila + 1));
            // Recorremos las columnas de la fila actual
            for (int indiceColumna = 0; indiceColumna < NUM_COLUMNAS; indiceColumna++) {
                // Empezamos con una barra
                sb.append("|");
                // Re-iniciamos al valor por defecto las variables
                hayBarco = false;
                hayAgua = false;
                hayImpacto = false;
                // Comprobamos todas las posiciones guardadas de disparos al agua
                for (Disparo disparo : this.disparosAgua) {
                    // Si la coordenada es la misma
                    if (disparo.sobrePosicion(indiceFila, indiceColumna)) {
                        // Estamos en una celda de agua
                        hayAgua = true;
                    }
                }
                // Si no estamos en una celda de agua
                if (!hayAgua) {
                    // Para cada uno de mis barcos
                    for (Barco barco : this.losBarcos) {
                        // Compruebo si el barco está en la celda actual (del bucle)
                        if (barco.sobrePosicion(indiceFila, indiceColumna)) {
                            // Estamos en un barco
                            hayBarco = true;
                            // Le pedimos el estado del la celda/posición al barco
                            hayImpacto = barco.posicionHundida(indiceFila, indiceColumna);
                            break;
                        }
                    }
                }
                if (hayAgua) {
                    // Si hay agua dibujamos ~
                    sb.append("~");
                } else if (hayImpacto) {
                    // Si ya se ha impactado dibujamos X
                    sb.append("X");
                } else {
                    // Haya barco o nada, si todavía no se ha disparado, dibujamos un espacio en blanco
                    sb.append(" ");
                }
            }
            // Ponemos una barra al final y cambiamos de línea \n
            sb.append("|\n");
        }
        // Dibujamos una línea final y la cantidad de disparos restantes.
        sb.append("--------------------\n");
        sb.append("Disparos Restantes: " + this.disparosDisponibles + "\n");
        // Devolvemos la String resultante.
        return sb.toString();
    }

    public boolean puedeContinuar() {
        // Quedan disparos?
        if (this.disparosDisponibles > 0) {
            // Miramos todos los Barcos
            for (Barco barco : this.losBarcos) {
                // Si no está hundido, aún se puede seguir disparando.
                if (!barco.estaHundido()) {
                    return true;
                }
            }
        }
        // Si llega hasta aquí es que o no quedan barcos o no quedan disparos.
        return false;
    }

    public boolean esVictoria() {
        for (Barco barco : this.losBarcos) {
            // Si un barco no está hundido
            if (!barco.estaHundido()) {
                // Aún no se ha ganado.
                return false;
            }
        }
        // Si todos los barcos se han hundido -> Victoria
        return true;
    }

    public void disparar(Disparo disparo) {
        // Decrementamos los disparos disponibles.
        this.disparosDisponibles--;
        for (Barco barco : this.losBarcos) {
            // Para cada barco vemos si hay impacto.
            if (barco.disparar(disparo)) {
                // Si hay impacto, hemos terminado, el bucle y el método no tienen que continuar.
                return;
            }
        }
        // Si no se encuentra un barco, entonces se guarda el disparo como posición de agua.
        this.disparosAgua.add(disparo);
    }

    public static void main(String[] args) {
        Flota juegoFlota = new Flota();
        Scanner teclado = new Scanner(System.in);
        Disparo disparo = null;
        do {
            // Mostramos el tablero/estado del juego
            System.out.println(juegoFlota);
            do {
                System.out.println("¿A qué posición quieres disparar?");
                // Pedimos una posición a la que disparar
                disparo = new Disparo(teclado.nextLine());
                // Pedimos la posición mientras que la anterior posición no haya sido válida
            } while (!disparo.esValido());
            // Se efectua el disparo
            juegoFlota.disparar(disparo);
            // Se continua mientras que el juego no termine
        } while (juegoFlota.puedeContinuar());

        if (juegoFlota.esVictoria()) {
            // Si se gana
            System.out.println("Felicidades has aniquilado la flota enemiga!");
        } else {
            // Si se pierde
            System.out.println("GAME OVER, no te quedan disparos!");
        }

        // Cerramos el teclado porque ya no lo vamos a usar más.
        teclado.close();
    }
}