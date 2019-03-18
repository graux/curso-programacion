package com.palmaactiva.programacion.ejercicio_diccionario_json;

import java.util.HashMap;
import java.util.Map;

public class Diccionario {
    public enum Idioma {
        INGLÉS, ESPAÑOL
    }

    private Map<String, Traduccion> diccionarioInglés;
    private Map<String, Traduccion> diccionarioEspañol;

    public Diccionario() {
        this.diccionarioInglés = new HashMap<>();
        this.diccionarioEspañol = new HashMap<>();
    }

    public void añadirTérmino(Traduccion nuevaTraduccion) {
        String keyEspañol = nuevaTraduccion.get(Idioma.ESPAÑOL).toLowerCase();
        String keyInglés = nuevaTraduccion.get(Idioma.INGLÉS).toLowerCase();
        this.diccionarioEspañol.put(keyEspañol, nuevaTraduccion);
        this.diccionarioInglés.put(keyInglés, nuevaTraduccion);
    }

    public String traducir(Idioma idiomaOrigen, String termino) {
        Idioma idiomaDestino = null;
        Map<String, Traduccion> diccionario = null;
        if (idiomaOrigen == Idioma.ESPAÑOL) {
            idiomaDestino = Idioma.INGLÉS;
            diccionario = diccionarioEspañol;
        } else {
            idiomaDestino = Idioma.ESPAÑOL;
            diccionario = diccionarioInglés;
        }
        String key = termino.toLowerCase();
        if (diccionario.containsKey(key)) {
            return diccionario.get(key).get(idiomaDestino);
        } else {
            return null;
        }
    }

    public int getNumTerminos() {
        return this.diccionarioEspañol.size();
    }

    public Traduccion[] getTraducciones() {
        return this.diccionarioInglés.values().toArray(new Traduccion[this.diccionarioInglés.size()]);
    }
}