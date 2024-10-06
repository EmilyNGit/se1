package org.hbrs.se1.ws24.exercises.uebung1.control;

public class Factory {

    public static GermanTranslator newGermanTranslator(String date){
        GermanTranslator translator = new GermanTranslator();
        translator.setDate(date);
        return translator;
    }
}
