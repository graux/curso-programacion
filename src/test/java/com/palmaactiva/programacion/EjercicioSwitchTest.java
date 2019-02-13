package com.palmaactiva.programacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testGetNombreDiaSemana() {
        int[] diasSemana = { -1, 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        String[] nombresDiasSemana = { null, null, "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Fin de Semana",
                "Fin de Semana", null };
        String valorEsperado, valorResultado = null;
        int numeroDia = 0;
        for (int index = 0; index < diasSemana.length; index++) {
            numeroDia = diasSemana[index];
            valorEsperado = nombresDiasSemana[index];
            valorResultado = EjercicioSwitch.getNombreDiaSemana(numeroDia);

            assertEquals(valorEsperado, valorResultado, "El día de la semana no coincide");
        }
    }

    @Test
    void testGetNumeroDiaSemana() {
        int[] diasSemana = { -1, -1, 1, 2, 3, 4, 5, 6, 7, 7, -1 };
        String[] nombresDiasSemana = { null, "blabla", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado",
                "Domingo", "Fin de Semana", "Marzo" };
        int valorEsperado, valorResultado;
        String nombreDia = null;
        for (int index = 0; index < diasSemana.length; index++) {
            nombreDia = nombresDiasSemana[index];
            valorEsperado = diasSemana[index];
            valorResultado = EjercicioSwitch.getNumeroDiaSemana(nombreDia);

            assertEquals(valorEsperado, valorResultado, "El día de la semana no coincide");
        }
    }

    @Test
    void testGetNumeroDiaSemanaSimplificado() {
        int[] diasSemana = { -1, -1, 1, 2, 3, 4, 5, 6, 7, 7, -1 };
        String[] nombresDiasSemana = { null, "blabla", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado",
                "Domingo", "Fin de Semana", "Marzo" };
        int valorEsperado, valorResultado;
        String nombreDia = null;
        for (int index = 0; index < diasSemana.length; index++) {
            nombreDia = nombresDiasSemana[index];
            valorEsperado = diasSemana[index];
            valorResultado = EjercicioSwitch.getNumeroDiaSemanaSimplificado(nombreDia);

            assertEquals(valorEsperado, valorResultado, "El día de la semana no coincide");
        }
    }
}
