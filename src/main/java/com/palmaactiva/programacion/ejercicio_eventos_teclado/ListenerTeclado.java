package com.palmaactiva.programacion.ejercicio_eventos_teclado;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerTeclado implements KeyListener {

    // No debe usarse el tipo App, debería usarse una interfaz
    private App miApp;

    public ListenerTeclado(App miApp) {
        this.miApp = miApp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Se usa sólamente para detectar cuando se ha persionado y soltado una tecla.
        // No se recomienda su uso a no ser que se tenga claro para qué se quiere usar.
        // miApp.eventoTecla("Pulsación", e.getKeyCode(), KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        miApp.eventoTecla("Presionada", e.getKeyCode(), KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        miApp.eventoTecla("Soltada", e.getKeyCode(), KeyEvent.getKeyText(e.getKeyCode()));
    }
}