package com.mycompany.ilmo;

import java.util.ArrayList;
import java.util.List;

public class Osakunta {
    private String nimi;
    private int perustettu;
    private List<Opiskelija> jasenet;
    
    public Osakunta(String nimi, int perustettu) {
        this.nimi = nimi;
        this.perustettu = perustettu;
        this.jasenet = new ArrayList<>();
    }
    
    public Osakunta() {
        this.jasenet = new ArrayList<>();
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
