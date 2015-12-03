package puhelinmuistio;

import java.util.Map;
import java.util.TreeMap;
import puhelinmuistio.domain.Muistio;
import puhelinmuistio.komennot.*;
import puhelinmuistio.util.Lukija;

public class Sovellus {

    private Lukija lukija;
    private Map<String, Komento> komennot;
    private Komento ohje;

    public Sovellus() {
        this.lukija = new Lukija();

        this.komennot = new TreeMap<>();
        Muistio muistio = new Muistio();
        luoKomennot(muistio);
        this.ohje = new Ohje(lukija, muistio, null, null, komennot.values());
    }

    public void suorita() {
        ohje.suorita();

        boolean jatketaan = true;
        while (jatketaan) {
            String syote = lukija.lueMerkkijono("komento: ");

            Komento komento = komennot.get(syote);
            if (komento == null) {
                komento = ohje;
            }
            
            jatketaan = komento.suorita();
        }
    }

    private void luoKomennot(Muistio muistio) {
        luoKomento(new LisaaNumero(lukija, muistio, "1", "lisää numero"));
        luoKomento(new HaeNumerot(lukija, muistio, "2", "hae numerot"));
        luoKomento(new HaeNimi(lukija, muistio, "3", "hae puhelinnumeroa vastaava henkilö"));
        luoKomento(new LisaaOsoite(lukija, muistio, "4", "lisää osoite"));
        luoKomento(new HaeHenkilo(lukija, muistio, "5", "hae henkilön tiedot"));
        luoKomento(new PoistaTiedot(lukija, muistio, "6", "poista henkilön tiedot"));
        luoKomento(new Listaus(lukija, muistio, "7", "filtteröity listaus"));
        luoKomento(new Lopetus(lukija, muistio, "x", "lopeta"));
    }

    private void luoKomento(Komento k) {
        komennot.put(k.getNimi(), k);
    }
}
