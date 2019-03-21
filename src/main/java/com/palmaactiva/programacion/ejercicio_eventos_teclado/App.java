package com.palmaactiva.programacion.ejercicio_eventos_teclado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class App extends JFrame {

    private JLabel etiquetaTecla;

    private App() {
        this.initUI();
        this.initEvents();
    }

    private void initUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Figuras Programación Java");
        this.getContentPane().setBackground(Color.BLACK);
        this.getContentPane().setLayout(new BorderLayout());
        this.etiquetaTecla = new JLabel("Presiona una tecla...");
        this.etiquetaTecla.setFont(new Font("Noto", Font.BOLD, 38));
        this.etiquetaTecla.setHorizontalAlignment(SwingConstants.CENTER);
        this.etiquetaTecla.setForeground(Color.WHITE);
        this.getContentPane().add(this.etiquetaTecla);
    }

    private void initEvents() {
        this.addKeyListener(new ListenerTeclado(this));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.this.etiquetaTecla.setText("Click de Ratón: "+e.getClickCount());

                // Se pueden sobreescribir otros métodos como:
                // mouseEntered, mouseExited, mouseMoved
                // mousePressed, mouseReleased, etc.
            }
        });
    }

    public static void main(String[] args) {
        App miApp = new App();
        miApp.setVisible(true);
    }

	public void eventoTecla(String tipoEvento, int keyCode, String keyText) {
        this.etiquetaTecla.setText("<html>"+
        "Evento Tecla: "+tipoEvento+"<br/>"+
        "Código: "+keyCode+"<br/>"+
        "Texto: "+keyText+
        "</html>");
	}
}