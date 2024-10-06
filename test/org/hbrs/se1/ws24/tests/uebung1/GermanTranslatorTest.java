package org.hbrs.se1.ws24.tests.uebung1;
import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws24.exercises.uebung1.control.GermanTranslator;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {

        private GermanTranslator translator = new GermanTranslator();


    @Test
    public void gueltigeZahlTest() {
        assertEquals("sechs" , this.translator.translateNumber(6));
    }

    @Test
    public void NullTest(){
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)", translator.translateNumber(0));
    }

    @Test
    public void KleinerEinsTest(){
        assertEquals("Übersetzung der Zahl -5 nicht möglich (1.0)", translator.translateNumber(-5));
    }

    @Test
    public void GroesserZehnTest(){
        assertEquals("Übersetzung der Zahl 12 nicht möglich (1.0)", translator.translateNumber(12));
    }

}