package com.mycompany.palkanlaskenta;

import java.util.ArrayList;
import java.util.List;


public class Palkanlaskenta extends MaksupalveluRajapinta {
    private List<String> maksut;
    private List<Henkilo> tyontekijat;
    
    public Palkanlaskenta(){
        maksut = new ArrayList<>();
        tyontekijat = new ArrayList<>();        
    }
        
    public boolean lisaaTyontekija(int tunnus, String nimi, String tilinro, String osoite, String email, String puhnro, int tuntipalkka){
        for (Henkilo henkilo : tyontekijat) {
            if (tunnus==henkilo.getTunnus()) {
                return false;
            }
        }
        
        tyontekijat.add(new Henkilo(tunnus, nimi, tilinro, osoite, email, puhnro, tuntipalkka));
        return true;
    }
        
    public boolean lisaaTunnit(int tunnus, int tunnit) {
        for (Henkilo henkilo : tyontekijat) {
            if (tunnus==henkilo.getTunnus()) {
                henkilo.lisaaTunnit(tunnit);
                return true;
            }
        }        
        
        return false;
    }

    public boolean uusiTuntipalkka(int tunnus, int tuntipalkka) {
        // TODO: to be implements
        return false;
    }
    
    public void maksaPalkat(){
        for (Henkilo henkilo : tyontekijat) {
            List<Integer> hlonPalkat = henkilo.laskePalkat();
            
            int hlonPalkkaYht = 0;
            for (int palkka : hlonPalkat) {
                hlonPalkkaYht += palkka;
            }
            
            suoritaTilisiirto(henkilo.getNimi(), henkilo.getTili(), hlonPalkkaYht);
            
            String csv = ""+henkilo.getNimi()+
                         ";"+henkilo.getTili()+
                         ";"+hlonPalkkaYht;    
            
            maksut.add(csv);
        }          
    }

    public List<String> tyontekijat(String format) {
        // TODO: add support for JSON and XML
        if (!format.equals("csv")) {
            throw new IllegalArgumentException("supported formats: csv");
        }
        
        List<String> csvt = new ArrayList<>();
        for (Henkilo henkilo : tyontekijat) {
            String csv = ""+henkilo.getTunnus()+
                          ";"+henkilo.getNimi()+
                          ";"+henkilo.getTili()+
                          ";"+henkilo.getTuntipalkka();
            csvt.add(csv);
        }
        
        return csvt;
    }    
    
    public List<String> maksuhistoria(String format) {
        // TODO: add support for JSON and XML
        if (!format.equals("csv")) {
            throw new IllegalArgumentException("supported formats: csv");
        }
        
        return maksut;
    }

}
