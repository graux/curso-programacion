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
        // Comprobamos si la posición pasada por parámetros es igual a la posición de esta celda
        return this.fila == fila && this.columna == columna;
    }

    public boolean equals(Celda obj) {
        // Comprobamos si dos celdas son la misma, comprobando sus posiciones e ignorando si estan o no hundidas
        return this.fila == obj.fila && this.columna == obj.columna;
    }

    public boolean estaHundida() {
        return this.hundida;
    }

    public boolean disparar(Disparo disparo) {
        // Se efectua un disparo si y solo si el disparo es en esta celda.
        if (this.sobrePosicion(disparo.fila, disparo.columna)) {
            // Si era la misma celda, se marca como hundida y devolvemos que sí ha habido impacto
            this.hundida = true;
            return true;
        }
        // No hay impacto
        return false;
    }
}