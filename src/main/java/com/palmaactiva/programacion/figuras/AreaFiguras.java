package com.palmaactiva.programacion.figuras;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class AreaFiguras extends JComponent implements Actualizable {
    private FabricaFiguras fabricaFiguras;

    public AreaFiguras() {
        this.fabricaFiguras = new FabricaFiguras(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fabricaFiguras.crearFiguraAleatoria();
            }
        });
    }

    public void crearFiguras() {
        this.fabricaFiguras.crearLinea();
        this.fabricaFiguras.crearLinea();
        this.fabricaFiguras.crearLinea();
        this.fabricaFiguras.crearCuadrado();
        this.fabricaFiguras.crearCuadrado();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        fabricaFiguras.pintarFiguras(g);
    }

    @Override
    public void actualizar() {
        this.repaint();
    }
}