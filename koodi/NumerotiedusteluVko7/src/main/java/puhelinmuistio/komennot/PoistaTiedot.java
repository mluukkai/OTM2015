package puhelinmuistio.komennot;

import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class PoistaTiedot extends Komento{

    public PoistaTiedot(Lukija lukija, Muistio muistio, String nimi, String selite) {
        super(lukija, muistio, nimi, selite);
    }

    @Override
    public boolean suorita() {
        String nimi = lukija.lueMerkkijono("kenet: ");
        muistio.posta(nimi);
        return true;
    }

}
