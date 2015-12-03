package puhelinmuistio.komennot;

import puhelinmuistio.domain.Henkilo;
import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class HaeNumerot extends Komento {

    public HaeNumerot(Lukija lukija, Muistio muistio, String nimi, String selite) {
        super(lukija, muistio, nimi, selite);
    }

    @Override
    public boolean suorita() {
        String nimi = lukija.lueMerkkijono("kenen: ");

        Henkilo henkilo = muistio.haeHenkiloNimenPerusteella(nimi);
        if (henkilo == null) {
            System.out.println("ei löytynyt");

            return true;
        }

        for (String n : henkilo.numerot()) {
            System.out.println(n);
        }
        
        return true;
    }   
    
}
