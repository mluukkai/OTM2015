package puhelinmuistio.komennot;

import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class Lopetus extends Komento{

    public Lopetus(Lukija lukija, Muistio muistio, String nimi, String selite) {
        super(lukija, muistio, nimi, selite);
    }

    @Override
    public boolean suorita() {
        return false;
    }

}
