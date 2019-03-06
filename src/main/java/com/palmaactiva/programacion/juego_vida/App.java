package com.palmaactiva.programacion.juego_vida;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class App extends JFrame {

    private AreaCelulas areaCelulas;

    private App() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Juego de la Vida Java");
        this.getContentPane().setBackground(Color.WHITE);
        this.areaCelulas = new AreaCelulas(50, 40);
        this.add(areaCelulas);

    }

    public static void main(String[] args) {
        App miApp = new App();
        miApp.setVisible(true);
        miApp.areaCelulas.empezarJuego();
    }
}