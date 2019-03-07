package com.palmaactiva.programacion.juego_vida.avanzado_lista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.Timer;

public class AreaCelulas extends JComponent implements ActionListener {
    private int numCelulasHorizontales;
    private int numCelulasVerticales;
    private double anchoCelula;
    private double altoCelula;
    private Timer relojCiclos;
    private int contadorCiclos;
    // Todo crear una estructura lara células
    private List<Celula> celulas;
    private int tiempoCiclos = 0;

    public AreaCelulas(int numCelulasHorizontales, int numCelulasVerticales) {
        long startTime = System.currentTimeMillis();
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
        this.celulas = new ArrayList<Celula>(numCelulasHorizontales * numCelulasVerticales);
        Random aleatorio = new Random();
        Celula nuevaCelula;
        for (int indiceFila = 0; indiceFila < this.numCelulasVerticales; indiceFila++) {
            for (int indiceColumna = 0; indiceColumna < this.numCelulasHorizontales; indiceColumna++) {
                nuevaCelula = new Celula(indiceFila, indiceColumna);
                this.celulas.add(nuevaCelula);
                if (aleatorio.nextInt(10) >= 9) {
                    nuevaCelula.setVivaSiguienteCiclo(true);
                }
            }
        }
        this.inicializarCelulasVecinas();
        this.repaint();
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo Inicialización: " + (endTime - startTime) + "ms");
    }

    private void inicializarCelulasVecinas() {
        int filaActual, columnaActual, filaAnterior, filaPosterior, columnaAnterior, columnaPosterior;
        Celula celulaVecina;
        for (Celula celula : this.celulas) {
            filaActual = celula.getFila();
            columnaActual = celula.getColumna();
            filaAnterior = filaActual - 1;
            filaPosterior = filaActual + 1;
            columnaAnterior = columnaActual - 1;
            columnaPosterior = columnaActual + 1;

            for (int fila = filaAnterior; fila <= filaPosterior; fila++) {
                for (int columna = columnaAnterior; columna <= columnaPosterior; columna++) {
                    if (filaActual != fila || columnaActual != columna) {
                        celulaVecina = this.getCelula(fila, columna);
                        if (celulaVecina != null) {
                            celula.addCelulaVecina(celulaVecina);
                        }
                    }
                }
            }
        }
    }

    private Celula getCelula(int fila, int columna) {
        for (Celula celula : this.celulas) {
            if (celula.esCelula(fila, columna)) {
                return celula;
            }
        }
        return null;
    }

    private void addCelula(int fila, int columna) {
        System.out.println("Añadir Célula en la fila, columna: " + fila + ", " + columna);
        this.getCelula(fila, columna).setVivaSiguienteCiclo(true);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long startTime = System.nanoTime();
        this.contadorCiclos++;
        this.actualizarEstado();
        for (Celula celula : celulas) {
            celula.calcularNuevoEstado();
        }

        this.repaint();
        long endTime = System.nanoTime();
        tiempoCiclos += endTime - startTime;
        if (this.contadorCiclos % 50 == 0) {
            System.out.println("Nuevo ciclo: " + this.contadorCiclos);
            System.out.println("--> Tiempo Ciclo Medio: " + (tiempoCiclos / 50) + "ns");
            tiempoCiclos = 0;
        }
    }

    private void actualizarEstado() {
        for (Celula celula : celulas) {
            celula.actualizarEstado();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.pintarFondo(g);

        for (Celula celula : celulas) {
            celula.pintar(g, this.anchoCelula, this.altoCelula);
            celula.calcularNuevoEstado();
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

        this.anchoCelula = ancho / (double) this.numCelulasHorizontales;
        this.altoCelula = alto / (double) this.numCelulasVerticales;

        g.setColor(Color.GRAY);
        for (double posX = anchoCelula; posX < ancho; posX += anchoCelula) {
            g.drawLine((int) Math.round(posX), 0, (int) Math.round(posX), alto);
        }
        for (double posY = altoCelula; posY < alto; posY += altoCelula) {
            g.drawLine(0, (int) Math.round(posY), ancho, (int) Math.round(posY));
        }
    }
}