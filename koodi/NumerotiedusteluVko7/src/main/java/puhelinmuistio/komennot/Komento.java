package puhelinmuistio.komennot;

import puhelinmuistio.domain.Muistio;
import puhelinmuistio.util.Lukija;

public abstract class Komento {
    protected Lukija lukija;
    private String nimi;
    private String selite;
    protected Muistio muistio;

    public Komento(Lukija lukija, Muistio muistio, String nimi, String selite) {
        this.lukija = lukija;
        this.nimi = nimi;
        this.selite = selite;
        this.muistio = muistio;
    }   

    public String getNimi() {
        return nimi;
    }

    public String getSelite() {
        return selite;
    }       
    
    public abstract boolean suorita();
    
}
