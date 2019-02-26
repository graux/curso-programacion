package com.palmaactiva.programacion.flota;

public class Celda {
    private int fila;
    private int columna;
    private boolean hundida;

    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public boolean sobrePosicion(int fila, int columna) {
        return this.fila == fila && this.columna == columna;
    }

    public boolean equals(Celda obj) {
        return this.fila == obj.fila && this.columna == obj.columna;
    }

    public boolean estaHundida() {
        return this.hundida;
    }

    public boolean disparar(Disparo disparo) {
        if (this.sobrePosicion(disparo.fila, disparo.columna)) {
            this.hundida = true;
            return true;
        }
        return false;
    }
}