package com.palmaactiva.programacion.juego_vida.avanzado;

import java.awt.Color;
import java.awt.Graphics;

public class Celula {
    private int fila;
    private int columna;
    private boolean viva;
    private boolean siguienteEstadoViva;

    public Celula(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public boolean estaViva() {
        return this.viva;
    }

    public void setViva(boolean nuevoEstado) {
        this.viva = nuevoEstado;
        this.siguienteEstadoViva = nuevoEstado;
    }

    public void setVivaSiguienteCiclo(boolean nuevoEstado) {
        this.siguienteEstadoViva = nuevoEstado;
    }

    public void pintar(Graphics g, double anchoCelula, double altoCelula) {
        g.setColor(Color.BLACK);
        if (!this.viva) {
            if (this.siguienteEstadoViva) {
                g.setColor(Color.GREEN);
            }
        } else {
            if (this.siguienteEstadoViva) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.RED);
            }
        }

        int posX = (int) Math.round(anchoCelula * columna);
        int posY = (int) Math.round(altoCelula * fila);
        g.fillRect(posX, posY, (int) Math.round(anchoCelula), (int) Math.round(altoCelula));
    }

    public void actualizarEstado() {
        this.viva = this.siguienteEstadoViva;
    }
}