package com.mycompany.webkauppa.rajapinnat;

public class PankkiRajapinta {
    private static PankkiRajapinta instance;

    public static PankkiRajapinta getInstance(){
        if (instance==null) {
            instance = new PankkiRajapinta();
        }
        return instance;
    }
    
    private PankkiRajapinta() {
    }
    
    public boolean maksa(String nimi, String luottokortti, int hinta) {
        if (luottokortti.length() < 2) {
            return false;
        }

        // suorita maksu verkkopankin rajapintaa käyttäen

        return true;
    }
    
}
