package com.mycompany.ilmo;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Opiskelija {
    @Id
    private ObjectId id; 
    
    private String nimi;
    private String paaAine;
    @Embedded
    private List<Ilmoittautuminen> ilmoittautumiset;

    public Opiskelija() {
        this.ilmoittautumiset = new ArrayList<>();
    }

    public Opiskelija(String nimi, String paaAine) {
        this.nimi = nimi;
        this.paaAine = paaAine;
        this.ilmoittautumiset = new ArrayList<>();
    }

    public String getNimi() {
        return nimi;
    }

    public void ilmoittaudu(String kurssi, int ryhma) {
        ilmoittautumiset.add(new Ilmoittautuminen(kurssi, ryhma));
    }
    
    public void poistaIlmottautuminen(String kurssi) {
        Ilmoittautuminen ilmo = haeIlmoittautuminen(kurssi);
        if (ilmo!=null) {
            ilmoittautumiset.remove(ilmo);
        }
    }
        
    public Ilmoittautuminen haeIlmoittautuminen(String kurssi) {
        for (Ilmoittautuminen ilmoittautuminen : ilmoittautumiset) {
            if ( ilmoittautuminen.getKurssi().equals(kurssi)) {
                return ilmoittautuminen;
            }
             
        }
        
        return null;
    }
    
    @Override
    public String toString() {
        String ilmot = "";
        for (Ilmoittautuminen ilmo : ilmoittautumiset) {
            ilmot += " "+ilmo+ "\n";
        }
        return nimi+" ("+paaAine+")\n"+ilmot;
    }

}
