package puhelinmuistio.komennot;

import puhelinmuistio.domain.Henkilo;
import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class HaeHenkilo extends Komento {

    public HaeHenkilo(Lukija lukija, Muistio muistio, String nimi, String selite) {
        super(lukija, muistio, nimi, selite);
    }

    @Override
    public boolean suorita() {
        String nimi = lukija.lueMerkkijono("kenen: ");
        Henkilo h = muistio.haeHenkiloNimenPerusteella(nimi);

        if (h == null) {
            System.out.println(" ei löytynyt");
            return true;
        }
        h.tulostaPuhelinJaOsoite();
        
        return true;
    }


}
