package com.mycompany.ilmo;

import com.mongodb.MongoClient;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

public class Ilmo {
    private Datastore store;
    private Scanner lukija;

    public Ilmo() {
        lukija = new Scanner(System.in);
        Morphia morphia = new Morphia();
        MongoClient mc = new MongoClient("80.69.172.211:27017");
        store = morphia.createDatastore(mc, "otm4");        
    }
    
    Query<Opiskelija> opiskelijat(){
        return store.createQuery(Opiskelija.class);
    }

    Query<Osakunta> osakunnat(){
        return store.createQuery(Osakunta.class);
    }    
    
    public void suorita() {        
        int valinta = 9;
        if (valinta==1){
            for (Opiskelija opiskelija : opiskelijat().asList()) {
                System.out.println(opiskelija);
            }            
        } else if (valinta==2) {
            System.out.print("kuka: ");
            String op = lukija.nextLine();
            System.out.println(opiskelijat().field("nimi").equal(op).get());
        } else if (valinta==3) {
            System.out.print("aine: ");
            String op = lukija.nextLine();
            for (Opiskelija opiskelija : opiskelijat().field("paaAine").equal(op).asList()) {
                System.out.println(opiskelija);
            }             
         } else if (valinta==4) {
            String op = "Petri";
            Opiskelija o = opiskelijat().field("nimi").equal(op).get();
            Ilmoittautuminen lama = o.haeIlmoittautuminen("lama");
            lama.setRyhma(4);
            o.poistaIlmottautuminen("tikape");
            o.ilmoittaudu("idm", 1);
            store.save(o);
        } else if (valinta==5) {
            System.out.print("kurssi: ");
            String op = lukija.nextLine();
            for (Opiskelija opiskelija : opiskelijat().field("ilmoittautumiset.kurssi").equal(op).asList()) {
                System.out.print(opiskelija.getNimi()+" ");
            }       
            System.out.println("");
        } else if (valinta==6) {
            
            Query<Opiskelija> query  = opiskelijat();

            query.and(
                opiskelijat().criteria("ilmoittautumiset.kurssi").equal("otm"),
                opiskelijat().criteria("paaAine").equal("TKTL")
            );
            for (Opiskelija opiskelija : query.asList()) {
                System.out.print(opiskelija.getNimi()+" ");
            }       
            System.out.println("");
        } else if (valinta==7) {
            
            Query<Opiskelija> query  = opiskelijat();

            query.and(
                opiskelijat().criteria("paaAine").notEqual("TKTL"),
                opiskelijat().or(
                    opiskelijat().criteria("ilmoittautumiset.kurssi").equal("otm"),
                    opiskelijat().criteria("ilmoittautumiset.kurssi").equal("jtkt"),
                    opiskelijat().criteria("ilmoittautumiset.kurssi").equal("ohja")
                )
            );
            
            for (Opiskelija opiskelija : query.asList()) {
                System.out.print(opiskelija.getNimi()+" ");
            }       
            System.out.println("");
        } else if (valinta==8) {
            Osakunta savo = new Osakunta("Savolainen osakunta", 1905);
            Osakunta hamis = new Osakunta("Hämäläisosakunta", 1653);
            store.save(savo, hamis);            
            Random arpa = new Random();
            for (Opiskelija opiskelija : opiskelijat().asList()) {
                if ( arpa.nextBoolean()) {
                    hamis.liita(opiskelija);
                } else {
                    savo.liita(opiskelija);
                }
            }
            store.save(savo, hamis);  
        } else if (valinta==9) {
            for (Osakunta osakunta : osakunnat().asList()) {
                System.out.println(osakunta.getNimi());
                for (Opiskelija opiskelija : osakunta.jasenet()) {
                    System.out.println("  "+opiskelija.getNimi());
                }
            }
        }
        
    }

    public void alustaKanta() {        
        String [] opiskelijat = {"Arto", "Pekka", "Niina", "Emma", "Eero", "Mikko", "Elina", "Lauri", "Riikka", "Leo", "Olli-Pekka"};
        List<String> kurssit = Arrays.asList("tito", "ohja", "jtkt", "otm", "tilpe");
        String[] paaAineet = {"TKTL", "TKTL", "TKTL", "TKTL", "MAT", "MAT", "FYS"};
        
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
