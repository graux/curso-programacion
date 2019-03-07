package com.palmaactiva.programacion.juego_vida.simple;

import java.awt.Color;
import java.awt.Graphics;

public class Celula {
    private int fila;
    private int columna;
    private boolean viva;

    public Celula(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public boolean estaViva() {
        return this.viva;
    }
    
    public void setViva(boolean nuevoEstado) {
        this.viva = nuevoEstado;
    }

    public void pintar(Graphics g, float anchoCelula, float altoCelula) {
        g.setColor(Color.BLACK);
        if (this.viva) {
            g.setColor(Color.GREEN);
        }

        int posX = Math.round(anchoCelula * columna);
        int posY = Math.round(altoCelula * fila);
        g.fillRect(posX, posY, Math.round(anchoCelula), Math.round(altoCelula));
    }
}