package com.palmaactiva.programacion.figuras;

import java.awt.Color;

import javax.swing.JFrame;

public class App extends JFrame {

    private AreaFiguras areaFiguras;

    private App() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Figuras Programación Java");
        this.getContentPane().setBackground(Color.WHITE);
        this.areaFiguras = new AreaFiguras();
        this.add(this.areaFiguras);
    }

    public static void main(String[] args) {
        App miApp = new App();
        miApp.setVisible(true);
        miApp.areaFiguras.crearFiguras();
    }
}