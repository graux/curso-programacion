package com.palmaactiva.programacion.ejercicio_ingles_espanol;

import com.palmaactiva.programacion.ejercicio_ingles_espanol.Diccionario.Idioma;

public class Traduccion {
    private String español;
    private String inglés;

    public Traduccion(String terminoEspañol, String terminoIngles) {
        this.español = terminoEspañol.toLowerCase();
        this.inglés = terminoIngles.toLowerCase();
    }

    public String get(Idioma idioma) {
        switch (idioma) {
        case ESPAÑOL:
            return this.español;
        case INGLÉS:
            return this.inglés;
        }
        return null;
    }
}