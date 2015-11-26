package com.mycompany.ilmo;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Osakunta {
    @Id
    private ObjectId id;     
    private String nimi;
    private int perustettu;
    @Reference
    private List<Opiskelija> jasenet;
    
    public Osakunta(String nimi, int perustettu) {
        this.nimi = nimi;
        this.perustettu = perustettu;
        this.jasenet = new ArrayList<>();
    }
    
    public Osakunta() {
        this.jasenet = new ArrayList<>();
    }

    public String getNimi() {
        return nimi;
    }
    
    public void liita(Opiskelija o) {
        jasenet.add(o);
    }

    public List<Opiskelija> jasenet() {
        return jasenet;
    }    
    
    @Override
    public String toString() {
        return nimi+ " ("+perustettu+") " +jasenet.size()+ " jäsentä";
    }
    
    
}
