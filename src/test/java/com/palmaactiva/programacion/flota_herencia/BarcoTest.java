package com.palmaactiva.programacion.flota_herencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BarcoTest {

    @Test
    public void estaHundidoTest() {
        Barco miBarco = new Barco(4);
        Posicion[] posicionesBarco =  miBarco.getPosiciones();
        for(Posicion pos : posicionesBarco){
            miBarco.getCeldaPosicion(pos).disparar(new Disparo(pos));
        }
        assertEquals(true, miBarco.estaHundido());
    }
    
    @Test
    public void noEstaHundidoTest() {
        Barco miBarco = new Barco(4);
        Posicion[] posicionesBarco =  miBarco.getPosiciones();
        Posicion pos;
        for(int index= 0; index < posicionesBarco.length -2; index++){
            pos = posicionesBarco[index];
            miBarco.getCeldaPosicion(pos).disparar(new Disparo(pos));
        }
        assertEquals(false, miBarco.estaHundido());
    }


    
    @Test
    public void getStringCeldaTest() {
        Barco miBarco = new Barco(4);
        Posicion[] posicionesBarco =  miBarco.getPosiciones();
        Posicion pos;
        for(int index= 0; index < posicionesBarco.length -2; index++){
            pos = posicionesBarco[index];
            assertEquals(miBarco.getStringCelda(pos), " ");
            miBarco.getCeldaPosicion(pos).disparar(new Disparo(pos));
            assertEquals(miBarco.getStringCelda(pos), "X");
        }
        pos = posicionesBarco[posicionesBarco.length - 1];
        assertEquals(miBarco.getStringCelda(pos), " ");
    }
}