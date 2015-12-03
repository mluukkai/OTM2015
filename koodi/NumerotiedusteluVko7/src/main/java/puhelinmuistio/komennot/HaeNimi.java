package puhelinmuistio.komennot;

import puhelinmuistio.domain.Henkilo;
import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class HaeNimi extends Komento {

    public HaeNimi(Lukija lukija, Muistio muistio, String nimi, String selite) {
        super(lukija, muistio, nimi, selite);
    }
    
    @Override
    public boolean suorita() {
        String nro = lukija.lueMerkkijono("numero: ");
        Henkilo hlo = muistio.haeHenkiloNumeronPerusteella(nro);
        System.out.println(" " + hlo.getNimi());
        return true;
    }

}
