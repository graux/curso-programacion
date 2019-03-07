package com.palmaactiva.programacion.juego_vida.normal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.Timer;

public class AreaCelulas extends JComponent implements ActionListener {
    private int numCelulasHorizontales;
    private int numCelulasVerticales;
    private float anchoCelula;
    private float altoCelula;
    private Timer relojCiclos;
    private int contadorCiclos;
    // Todo crear una estructura lara células
    private Celula[][] celulas;

    public AreaCelulas(int numCelulasHorizontales, int numCelulasVerticales) {
        this.numCelulasHorizontales = numCelulasHorizontales;
        this.numCelulasVerticales = numCelulasVerticales;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    relojCiclos.start();
                } else {
                    relojCiclos.stop();
                    int columna = (int) Math.floor(e.getX() / anchoCelula);
                    int fila = (int) Math.floor(e.getY() / altoCelula);
                    addCelula(fila, columna);
                }
            }
        });

        this.relojCiclos = new Timer(50, this);
        this.celulas = new Celula[this.numCelulasVerticales][this.numCelulasHorizontales];
        for (int indiceFila = 0; indiceFila < this.numCelulasVerticales; indiceFila++) {
            for (int indiceColumna = 0; indiceColumna < this.numCelulasHorizontales; indiceColumna++) {
                this.celulas[indiceFila][indiceColumna] = new Celula(indiceFila, indiceColumna);
            }
        }
    }

    private void addCelula(int fila, int columna) {
        System.out.println("Añadir Célula en la fila, columna: " + fila + ", " + columna);
        this.celulas[fila][columna].setViva(true);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.contadorCiclos++;
        System.out.println("Nuevo ciclo: " + this.contadorCiclos);
        this.actualizarEstado();

        int filaAnterior, filaPosterior, columnaAnterior, columnaPosterior;
        int numVecinasVivas = 0;
        for (int indiceFila = 0; indiceFila < this.numCelulasVerticales; indiceFila++) {
            for (int indiceColumna = 0; indiceColumna < this.numCelulasHorizontales; indiceColumna++) {
                filaAnterior = indiceFila - 1;
                filaPosterior = indiceFila + 1;
                columnaAnterior = indiceColumna - 1;
                columnaPosterior = indiceColumna + 1;

                numVecinasVivas = 0;
                for (int fila = filaAnterior; fila <= filaPosterior; fila++) {
                    for (int columna = columnaAnterior; columna <= columnaPosterior; columna++) {
                        if ((indiceFila != fila || indiceColumna != columna) && esCelulaViva(fila, columna)) {
                            numVecinasVivas++;
                        }
                    }
                }

                if (this.celulas[indiceFila][indiceColumna].estaViva()) {
                    if (numVecinasVivas < 2 || numVecinasVivas > 3) {
                        this.celulas[indiceFila][indiceColumna].setVivaSiguienteCiclo(false);
                    }
                } else if (numVecinasVivas == 3) {
                    this.celulas[indiceFila][indiceColumna].setVivaSiguienteCiclo(true);
                }
            }
        }

        this.repaint();
    }

    private void actualizarEstado() {
        for (int indiceFila = 0; indiceFila < this.numCelulasVerticales; indiceFila++) {
            for (int indiceColumna = 0; indiceColumna < this.numCelulasHorizontales; indiceColumna++) {
                this.celulas[indiceFila][indiceColumna].actualizarEstado();
            }
        }
    }

    private boolean esCelulaViva(int fila, int columna) {
        if (fila >= 0 && columna >= 0 && fila < this.numCelulasVerticales && columna < this.numCelulasHorizontales) {
            return this.celulas[fila][columna].estaViva();
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.pintarFondo(g);

        for (int indiceFila = 0; indiceFila < this.numCelulasVerticales; indiceFila++) {
            for (int indiceColumna = 0; indiceColumna < this.numCelulasHorizontales; indiceColumna++) {
                this.celulas[indiceFila][indiceColumna].pintar(g, this.anchoCelula, this.altoCelula);
            }
        }

        this.pintarRegilla(g);
    }

    private void pintarFondo(Graphics g) {
        int ancho = this.getWidth();
        int alto = this.getHeight();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ancho, alto);
    }

    private void pintarRegilla(Graphics g) {
        int ancho = this.getWidth();
        int alto = this.getHeight();

        this.anchoCelula = ancho / (float) this.numCelulasHorizontales;
        this.altoCelula = alto / (float) this.numCelulasVerticales;

        g.setColor(Color.GRAY);
        for (float posX = anchoCelula; posX < ancho; posX += anchoCelula) {
            g.drawLine(Math.round(posX), 0, Math.round(posX), alto);
        }
        for (float posY = altoCelula; posY < alto; posY += altoCelula) {
            g.drawLine(0, Math.round(posY), ancho, Math.round(posY));
        }
    }
}