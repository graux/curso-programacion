package com.palmaactiva.programacion.juego_vida;

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

    public AreaCelulas(int numCelulasHorizontales, int numCelulasVerticales) {
        this.numCelulasHorizontales = numCelulasHorizontales;
        this.numCelulasVerticales = numCelulasVerticales;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int columna = (int) Math.floor(e.getX() / anchoCelula);
                int fila = (int) Math.floor(e.getY() / altoCelula);
                addCelula(fila, columna);
            }
        });

        this.relojCiclos = new Timer(500, this);
    }

    public void empezarJuego() {
        this.relojCiclos.start();
    }

    private void addCelula(int fila, int columna) {
        System.out.println("Añadir Célula en la fila, columna: " + fila + ", " + columna);
        // TODO Completar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.contadorCiclos++;
        System.out.println("Nuevo ciclo: " + this.contadorCiclos);
        // TODO Aplicar el algoritmo del juego de la vida a las células
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.pintarRegilla(g);
        
        // TODO pintar cada célula
    }

    private void pintarRegilla(Graphics g) {
        int ancho = this.getWidth();
        int alto = this.getHeight();

        this.anchoCelula = ancho / (float) this.numCelulasHorizontales;
        this.altoCelula = alto / (float) this.numCelulasVerticales;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ancho, alto);

        g.setColor(Color.GRAY);
        for (float posX = anchoCelula; posX < ancho; posX += anchoCelula) {
            g.drawLine(Math.round(posX), 0, Math.round(posX), alto);
        }
        for (float posY = altoCelula; posY < alto; posY += altoCelula) {
            g.drawLine(0, Math.round(posY), ancho, Math.round(posY));
        }
    }
}