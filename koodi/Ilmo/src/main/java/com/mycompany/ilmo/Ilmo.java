package com.mycompany.ilmo;

import com.mongodb.MongoClient;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class Ilmo {
    private Datastore store;
    private Scanner lukija;

    public Ilmo() {
        lukija = new Scanner(System.in);
        Morphia morphia = new Morphia();
        MongoClient mc = new MongoClient("80.69.172.211:27017");
        store = morphia.createDatastore(mc, "TIETOKANTASI_NIMI_TÄHÄN");        
    }

    public void suorita() {
        // sovelluksen koodi tänne
        Opiskelija o = new Opiskelija("Petri", "TKTL");
        o.ilmoittaudu("tikape", 2);
        o.ilmoittaudu("tilpe", 1);
        o.ilmoittaudu("lama", 3);
        System.out.println(o);   
    }

    public void alustaKanta() {        
        String [] opiskelijat = {"Arto", "Pekka", "Niina", "Emma", "Eero", "Mikko", "Elina", "Lauri", "Riikka"};
        List<String> kurssit = Arrays.asList("tito", "ohja", "jtkt", "otm", "tilpe");
        String[] paaAineet = {"TKTL", "TKTL", "MAT", "FYS"};
        
        Random arpa = new Random();
        
        for (String opiskelija : opiskelijat) {
            Collections.shuffle(kurssit);
            Opiskelija o = new Opiskelija(opiskelija, paaAineet[arpa.nextInt(paaAineet.length)]);
            int lkm = 1+arpa.nextInt(kurssit.size()-1);
            for (int i = 0; i < lkm; i++) {
                String kurssi = kurssit.get(i);
                o.ilmoittaudu(kurssi, 1+arpa.nextInt(5));
            }
            store.save(o);
        }
                
    }

}
