package numerotiedustelu;
import java.util.*;
 
public class Henkilo implements Comparable<Henkilo> {
 
    private String nimi;
    private Set<String> puhelinnumerot;
    private String katu;
    private String kaupunki;
 
    public Henkilo(String nimi) {
        this.nimi = nimi;
        puhelinnumerot = new TreeSet<>();
    }
 
    public String getKatu() {
        return katu;
    }
 
    public void setOsoite(String katu, String kaupunki) {
        this.katu = katu;
        this.kaupunki = kaupunki;
    }
 
    public String getKaupunki() {
        return kaupunki;
    }
 
    public String getNimi() {
        return nimi;
    }
 
    public Set<String> getPuhelinnumerot() {
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