package puhelinmuistio.komennot;

import java.util.ArrayList;
import java.util.Collections;
import puhelinmuistio.domain.Henkilo;
import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class Listaus extends Komento {

    public Listaus(Lukija lukija, Muistio muistio, String nimi, String selite) {
        super(lukija, muistio, nimi, selite);
    }

    @Override
    public boolean suorita() {
        String hakus = lukija.lueMerkkijono("hakusana (jos tyhjä, listataan kaikki): ");

        ArrayList<Henkilo> tulos = new ArrayList<Henkilo>();

        for (Henkilo henkilo : muistio.henkilotNimenPerusteella.values()) {
            if (match(henkilo, hakus)) {
                tulos.add(henkilo);
            }
        }

        if (tulos.isEmpty()) {
            System.out.println("ei löytynyt");
            return true;
        }

        Collections.sort(tulos);

        for (Henkilo h : tulos) {
            System.out.println(h.getNimi());
            h.tulostaPuhelinJaOsoite();
        }
        
        return  true;
    }
    
    private boolean match(Henkilo henkilo, String hakus) {
        if (henkilo.getNimi().contains(hakus)) {
            return true;
        }

        if (henkilo.osoite() != null && henkilo.osoite().contains(hakus)) {
            return true;
        }

        return false;
    }
    
    
}
