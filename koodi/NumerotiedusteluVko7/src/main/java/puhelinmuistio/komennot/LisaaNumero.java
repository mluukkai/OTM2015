package puhelinmuistio.komennot;

import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class LisaaNumero extends Komento{

    public LisaaNumero(Lukija lukija, Muistio muistio, String nimi, String selite) {
        super(lukija, muistio, nimi, selite);
    }

    @Override
    public boolean suorita() {
        String nimi = lukija.lueMerkkijono("kenelle: ");
        String numero = lukija.lueMerkkijono("nro: ");
               
        muistio.lisaaHenkilolleNumero(nimi, numero);
        return true;
    }

   
}
