package com.mycompany.webkauppa.rajapinnat;

public class PankkiRajapinta {

    public boolean maksa(String nimi, String luottokortti, int hinta) {
        if (luottokortti.length() < 2) {
            return false;
        }

        // suorita maksu verkkopankin rajapintaa käyttäen

        return true;
    }
    
}
