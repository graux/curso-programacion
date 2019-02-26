package com.palmaactiva.programacion.flota;

import java.util.Random;

public class Barco {
    private Celda[] celdas;

    public Barco(int numCeldas) {
        this.celdas = new Celda[numCeldas];
        int filaMax = Flota.NUM_FILAS;
        int colMax = Flota.NUM_COLUMNAS;

        Random aleatorio = new Random();
        boolean esHorizontal = aleatorio.nextBoolean();

        if (esHorizontal) {
            colMax = Flota.NUM_COLUMNAS - numCeldas;
        } else {
            filaMax = Flota.NUM_FILAS - numCeldas;
        }

        int filaInicial = aleatorio.nextInt(filaMax);
        int columnaInicial = aleatorio.nextInt(colMax);

        Celda nuevaCelda = null;
        for (int indice = 0; indice < numCeldas; indice++) {
            if (esHorizontal) {
                nuevaCelda = new Celda(filaInicial, columnaInicial + indice);
            } else {
                nuevaCelda = new Celda(filaInicial + indice, columnaInicial);
            }
            this.celdas[indice] = nuevaCelda;
        }
    }

    public boolean sobrePosicion(int fila, int columna) {
        for (Celda miCelda : this.celdas) {
            if (miCelda.sobrePosicion(fila, columna)) {
                return true;
            }
        }
        return false;
    }

    public boolean seSolapaCon(Barco nuevoBarco) {
        for (Celda miCelda : this.celdas) {
            for (Celda otraCelda : nuevoBarco.celdas) {
                if (miCelda.equals(otraCelda)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean estaHundido() {
        for (Celda celda : this.celdas) {
            if (!celda.estaHundida()) {
                return false;
            }
        }
        return true;
    }

    public boolean disparar(Disparo disparo) {
        for (Celda celda : this.celdas) {
            if (celda.disparar(disparo)) {
                return true;
            }
        }
        return false;
    }

    public boolean posicionHundida(int fila, int columna) {
        for (Celda celda : this.celdas) {
            if (celda.sobrePosicion(fila, columna)) {
                return celda.estaHundida();
            }
        }
        return false;
    }
}