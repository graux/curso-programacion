package com.palmaactiva.programacion.juego_vida.avanzado;

import java.awt.Color;

import javax.swing.JFrame;

public class App extends JFrame {

    private AreaCelulas areaCelulas;

    private App() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Juego de la Vida Java - AVANZADO");
        this.getContentPane().setBackground(Color.WHITE);
        this.areaCelulas = new AreaCelulas(160, 120);
        this.add(areaCelulas);
    }

    public static void main(String[] args) {
        App miApp = new App();
        miApp.setVisible(true);
    }

    // Inicialización: 4ms
    // Tiempo Ciclo: 748,091ns
}