package com.palmaactiva.programacion.flota_herencia;

public class Celda extends Posicion {
    private boolean hundida;

    public Celda(int fila, int columna) {
        super(fila, columna);
    }

    public boolean estaHundida() {
        return this.hundida;
    }

    public boolean disparar(Disparo disparo) {
        if (this.sobrePosicion(disparo)) {
            this.hundida = true;
            return true;
        }
        return false;
    }
}