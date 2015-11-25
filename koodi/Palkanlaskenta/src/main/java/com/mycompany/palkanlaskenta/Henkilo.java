package com.mycompany.palkanlaskenta;

import java.util.ArrayList;
import java.util.List;

public class Henkilo {
    private int tunnus;
    private String nimi;
    private String osoite;
    private String email;
    private String puhnro;
    
    private String tili;
    private int tuntipalkka;

    private ArrayList<Integer> maksamattomatTunnit;
    
    public Henkilo(int tunnus, String nimi, String tili, String osoite, String email, String puhnro, int tuntipalkka) {
        this.tunnus = tunnus;
        this.nimi = nimi;
        this.osoite = osoite;
        this.email = email;
        this.puhnro = puhnro;        
        this.tili = tili;
        this.tuntipalkka = tuntipalkka;
        this.maksamattomatTunnit = new ArrayList<>();
    }

    public int getTunnus() {
        return tunnus;
    }

    public String getNimi() {
        return nimi;
    }

    public String getTili() {
        return tili;
    }

    public int getTuntipalkka() {
        return tuntipalkka;
    }
    
    public void lisaaTunnit(int tunnit) {
        maksamattomatTunnit.add(tunnit);
    }

    public List<Integer> laskePalkat() {
        List<Integer> palkat = new ArrayList<>();
        
        for (int tunti : maksamattomatTunnit) {
            palkat.add(tunti*tuntipalkka);
        }
        
        maksamattomatTunnit = new ArrayList<>();
        
        return palkat;
    }
}
