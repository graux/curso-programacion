package com.palmaactiva.programacion.personas_dni;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Persona {

    private String nombre;
    private String apellidos;
    private String dni;
    private Date fechaNacimiento;
    private static final DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

    public Persona(String nombre, String apellidos, String dni, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    String getDNI() {
        return this.dni;
    }

    public String toString() {
        return this.nombre + " " + this.apellidos + " [" + this.dni + "] " + formatoFecha.format(this.fechaNacimiento);
    }
}
