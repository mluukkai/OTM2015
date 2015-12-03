package puhelinmuistio.domain;

import java.util.HashMap;
import java.util.Map;

public class Muistio {

    public Map<String, Henkilo> henkilotNimenPerusteella;
    public Map<String, Henkilo> henkilotNumeronPerusteella;

    public Muistio() {
        henkilotNimenPerusteella = new HashMap<String, Henkilo>();
        henkilotNumeronPerusteella = new HashMap<String, Henkilo>();
    }

    public void lisaaHenkilolleNumero(String nimi, String numero) {
        Henkilo h = haeHenkiloNimenPerusteella(nimi);
        if (h == null) {
            h = new Henkilo(nimi);
            henkilotNimenPerusteella.put(nimi, h);
        }
        h.lisaaNumero(numero);
        henkilotNumeronPerusteella.put(numero, h);
    }
    public Henkilo haeHenkiloNumeronPerusteella(String numero) {
        return henkilotNumeronPerusteella.get(numero);
    }
    
    public Henkilo haeHenkiloNimenPerusteella(String nimi) {
        return henkilotNimenPerusteella.get(nimi);
    }

    public void lisaaOsoite(String nimi, String katu, String kaup) {
        Henkilo h = haeHenkiloNimenPerusteella(nimi);
        if (h == null) {
            h = new Henkilo(nimi);
            henkilotNimenPerusteella.put(nimi, h);
        }
        h.asetaOsoite(katu, kaup);
    }

    public void posta(String nimi) {
        henkilotNimenPerusteella.remove(nimi);
        henkilotNumeronPerusteella.remove(nimi);
    }
}
