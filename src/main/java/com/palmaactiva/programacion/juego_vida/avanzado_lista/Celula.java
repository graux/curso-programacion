package com.palmaactiva.programacion.juego_vida.avanzado_lista;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Celula {
    private int fila;
    private int columna;
    private boolean viva;
    private boolean siguienteEstadoViva;

    private List<Celula> celulasVecinas;

    public Celula(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.celulasVecinas = new ArrayList<>(8);
    }

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return this.columna;
    }

    public boolean estaViva() {
        return this.viva;
    }

    public void addCelulaVecina(Celula vecina) {
        this.celulasVecinas.add(vecina);
    }

    public void setVivaSiguienteCiclo(boolean nuevoEstado) {
        this.siguienteEstadoViva = nuevoEstado;
    }

    public boolean esCelula(int fila, int columna) {
        return this.fila == fila && this.columna == columna;
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

    public void calcularNuevoEstado() {
        int numVecinasVivas = 0;
        for (Celula vecina : this.celulasVecinas) {
            if (vecina.estaViva()) {
                numVecinasVivas++;
            }
        }
        if (this.viva) {
            if (numVecinasVivas < 2 || numVecinasVivas > 3) {
                this.siguienteEstadoViva = false;
            }
        } else if (numVecinasVivas == 3) {
            this.siguienteEstadoViva = true;
        }
    }
}