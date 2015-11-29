package com.mycompany.webkauppa.domain;

import com.mycompany.webkauppa.db.OstostapahtumaDAOMongo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Ostostapahtumat {

    private OstostapahtumaDAOMongo dao;

    public Ostostapahtumat(OstostapahtumaDAOMongo dao) {
        this.dao = dao;
    }
    
    public void lisaaOstostapahtuma(Ostoskori kori, String nimi, String kortti, String osoite){
        String maksu = ( new Date() + " " + nimi + " " + kortti + " " +kori.hinta() );
        String toimitus = nimi+ " "+osoite + merkkijonona(kori.ostokset());
        dao.save(new Ostostapahtuma(maksu, toimitus));
    }
    
    public List<Ostostapahtuma> ostostapahtumat(){
        return dao.findAll();
    }
    
    public List<String> toimitukset(){
        return ostostapahtumat().stream().map(o->o.getToimitus()).collect(Collectors.toList());
    }
    
    public List<String> maksut(){
        return ostostapahtumat().stream().map(o->o.getMaksu()).collect(Collectors.toList());
    }
    
    private String merkkijonona(List<Ostos> ostokset) {
        String mj = "";
        for (Ostos ostos : ostokset) {
            mj += " "+ostos.tuotteenNimi() + " x " +ostos.lukumaara() + "\n";
        }
        
        return mj;        
    }    
}
