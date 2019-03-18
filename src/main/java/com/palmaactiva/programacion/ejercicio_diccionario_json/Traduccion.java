package com.palmaactiva.programacion.ejercicio_diccionario_json;

import com.palmaactiva.programacion.ejercicio_diccionario_json.Diccionario.Idioma;
import com.google.gson.annotations.*;

public class Traduccion {
    @SerializedName("es")
    private String español;
    @SerializedName("en")
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