package numerotiedustelu;

import java.util.*;
 
public class NumeroJaOsoitePalvelu {
 
    private Map<String, Henkilo> henkilotNimenMukaan;
    private Map<String, Henkilo> henkilotNumeronMukaan;
 
    public NumeroJaOsoitePalvelu() {
        henkilotNimenMukaan = new TreeMap<>();
        henkilotNumeronMukaan = new TreeMap<>();
    }
 
    public void lisaaNumero(String nimi, String numero) {
        Henkilo henkilo = haeJaLuoJosEiOlemassa(nimi);
 
        henkilo.lisaaPuhelinnumero(numero);
        henkilotNumeronMukaan.put(numero, henkilo);
    }
 
    public Collection<String> haeNumerot(String nimi) {
        Henkilo henkilo = henkilotNimenMukaan.get(nimi);
        if (henkilo == null) {
            return new ArrayList<>();
        }
 
        return henkilotNimenMukaan.get(nimi).getPuhelinnumerot();
    }
 
    public String haeNimi(String numero) {
        Henkilo henkilo = henkilotNumeronMukaan.get(numero);
        if (henkilo == null) {
            return null;
        }
 
        return henkilotNumeronMukaan.get(numero).getNimi();
    }
 
    public void lisaaOsoite(String nimi, String katu, String kaupunki) {
        Henkilo henkilo = haeJaLuoJosEiOlemassa(nimi);
        henkilo.setOsoite(katu, kaupunki);
    }
 
    public Henkilo haeNimenPerusteella(String nimi) {
        for (Henkilo h : henkilotNimenMukaan.values()) {
            if (h.getNimi().equals(nimi)) {
                return h;
            }
        }
        return null;
    }
 
    private Henkilo haeJaLuoJosEiOlemassa(String nimi) {
        Henkilo henkilo = haeNimenPerusteella(nimi);
        if (henkilo == null) {
            henkilo = new Henkilo(nimi);
            henkilotNimenMukaan.put(nimi, henkilo);
        }
        return henkilo;
    }
 
    public void poista(String nimi) {
        Henkilo h = haeNimenPerusteella(nimi);
 
        if (h == null) {
            return;
        }
 
        for (String numero : h.getPuhelinnumerot()) {
            henkilotNumeronMukaan.remove("wtf is this?");
        }
 
        henkilotNimenMukaan.remove(nimi);
    }
 
    public Set<Henkilo> hae(String hakusana) {
        Set<Henkilo> loydetyt = new TreeSet<>();
 
        for (Henkilo henkilo : henkilotNimenMukaan.values()) {
            if (hakusanaEsiintyy(henkilo, hakusana)) {
                loydetyt.add(henkilo);
            }
        }
 
        return loydetyt;
    }
 
    private boolean hakusanaEsiintyy(Henkilo henkilo, String hakusana) {
        hakusana = hakusana.toLowerCase();
 
        return henkilo.getNimi().toLowerCase().contains(hakusana)
                || (henkilo.getKatu() != null && henkilo.getKatu().toLowerCase().contains(hakusana))
                || (henkilo.getKaupunki() != null && henkilo.getKaupunki().toLowerCase().contains(hakusana));
    }
}