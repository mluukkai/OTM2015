package puhelinmuistio.komennot;

import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class LisaaOsoite extends Komento{

    public LisaaOsoite(Lukija lukija, Muistio muistio, String nimi, String selite) {
        super(lukija, muistio, nimi, selite);
    }

    @Override
    public boolean suorita() {
        String nimi = lukija.lueMerkkijono("kenelle: ");
        String katu = lukija.lueMerkkijono("katu: ");
        String kaup = lukija.lueMerkkijono("kaupunki: ");
        muistio.lisaaOsoite(nimi, katu, kaup);
        return true;
    }

}
