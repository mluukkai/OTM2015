package puhelinmuistio.domain;

import java.util.ArrayList;
import java.util.List;

public class Henkilo implements Comparable<Henkilo> {

    private String nimi;
    private String katu;
    private String kaupunki;
    private List<String> numerot;

    public Henkilo(String nimi) {
        this.nimi = nimi;
        this.numerot = new ArrayList<String>();
    }

    public String getNimi() {
        return nimi;
    }

    public void lisaaNumero(String nro) {
        numerot.add(nro);
    }

    public List<String> numerot() {
        return numerot;
    }

    public String osoite() {
        if (katu == null) {
            return null;
        }
        return katu + " " + kaupunki;
    }

    public void asetaOsoite(String katu, String kaup) {
        this.katu = katu;
        this.kaupunki = kaup;
    }

    @Override
    public int compareTo(Henkilo t) {
        return nimi.compareTo(t.nimi);
    }

    public void tulostaPuhelinJaOsoite() {
        if (this.osoite() != null) {
            System.out.println(" osoite: " + this.osoite());
        } else {
            System.out.println(" osoite ei tiedossa");
        }
        if (this.numerot().isEmpty()) {
            System.out.println(" ei puhelinta");
        } else {
            System.out.println(" puhelinnumerot:");
            for (String nro : this.numerot()) {
                System.out.println("  " + nro);
            }
        }
    }
}
