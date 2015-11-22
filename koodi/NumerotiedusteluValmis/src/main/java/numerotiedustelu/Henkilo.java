package numerotiedustelu;

import java.util.*;
 
public class Henkilo implements Comparable<Henkilo> {
 
    private String nimi;
    private List<String> puhelinnumerot;
    private Osoite osoite;
 
    public Henkilo(String nimi) {
        this.nimi = nimi;
        puhelinnumerot = new ArrayList<>();
    }

    public Henkilo() {
    }
    
    public Osoite getOsoite() {
        return osoite;
    }

    public void setOsoite(Osoite osoite) {
        this.osoite = osoite;
    }
 
    public String getNimi() {
        return nimi;
    }
 
    public List<String> getPuhelinnumerot() {
        return puhelinnumerot;
    }
 
    public void lisaaPuhelinnumero(String uusi) {
        puhelinnumerot.add(uusi);
    }
 
    @Override
    public String toString() {
        return nimi + " " + puhelinnumerot;
    }
 
    @Override
    public int compareTo(Henkilo t) {
        return nimi.compareTo(t.nimi);
    }
 
}
