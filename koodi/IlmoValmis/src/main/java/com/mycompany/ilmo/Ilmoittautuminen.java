package com.mycompany.ilmo;

public class Ilmoittautuminen {
    private String kurssi;
    private int ryhma;

    public Ilmoittautuminen() {
    }
    
    public Ilmoittautuminen(String kurssi, int ryhma) {
        this.kurssi = kurssi;
        this.ryhma = ryhma;
    }

    public String getKurssi() {
        return kurssi;
    }

    public int getRyhma() {
        return ryhma;
    }

    public void setRyhma(int ryhma) {
        this.ryhma = ryhma;
    }
    
    @Override
    public String toString() {
        return kurssi+ tyhjaa()+" ryhmä "+ryhma;
    }
    
    private String tyhjaa() {
        String tyhjat = " ";

        int i = 8;
        while( i-->kurssi.length()){
            tyhjat += " ";
        }
        
        return tyhjat;
    }
    
}
