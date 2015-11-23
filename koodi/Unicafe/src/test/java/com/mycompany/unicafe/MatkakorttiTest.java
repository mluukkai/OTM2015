package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MatkakorttiTest {

    Matkakortti kortti;

    @Before
    public void setUp() {
        kortti = new Matkakortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void konstruktoriAsettaaArvonOikein() {
        
        assertEquals(10, kortti.saldo());      
    }
}
