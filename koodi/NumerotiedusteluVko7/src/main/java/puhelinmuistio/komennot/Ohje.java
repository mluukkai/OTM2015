package puhelinmuistio.komennot;

import java.util.Collection;
import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public class Ohje extends Komento {
    private Collection<Komento> komennot;
    
    public Ohje(Lukija lukija, Muistio muistio, String nimi, String selite, Collection<Komento> komennot) {
        super(lukija, muistio, nimi, selite);
        this.komennot = komennot;
    }
    

    @Override
    public boolean suorita() {
        System.out.println("numerotiedustelu");
        System.out.println("käytettävissä olevat komennot:");
        
        for (Komento komento : komennot) {
            System.out.println(komento.getNimi()+" "+komento.getSelite() );
        }
        
        return true;
    }

}
