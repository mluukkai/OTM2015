package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.domain.Ostoskori;
import com.mycompany.webkauppa.domain.Ostostapahtumat;
import com.mycompany.webkauppa.domain.Varasto;
import com.mycompany.webkauppa.rajapinnat.PankkiRajapinta;
import com.mycompany.webkauppa.rajapinnat.ToimitusRajapinta;


public class OstoksenSuoritus {

    private PankkiRajapinta pankki;
    private ToimitusRajapinta toimitusjarjestelma;
    private Ostostapahtumat ostostapahtumat;
    private String asiakkaanNimi;
    private String postitusosoite;
    private String luottokortti;
    private Ostoskori ostoskori;
    private Varasto varasto;

    public OstoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori) {
        this.varasto = Varasto.getInstance();
        this.pankki = PankkiRajapinta.getInstance();
        this.toimitusjarjestelma = ToimitusRajapinta.getInstance();
        this.ostostapahtumat = Ostostapahtumat.getInstance();
        this.asiakkaanNimi = nimi;
        this.postitusosoite = osoite;
        this.luottokortti = luottokorttinumero;
        this.ostoskori = kori;
    }

    public boolean suorita() {
        if ( asiakkaanNimi.length()==0 || postitusosoite.length()==0 || ostoskori.tuotteitaKorissa()==0 )
            return false;
        
        if (!pankki.maksa(asiakkaanNimi, luottokortti, ostoskori.hinta())) {
            return false;
        }

        ostostapahtumat.lisaaOstostapahtuma(ostoskori, asiakkaanNimi, luottokortti, postitusosoite);
        
        toimitusjarjestelma.kirjaatoimitus(asiakkaanNimi, postitusosoite, ostoskori.ostokset());
        ostoskori.tyhjenna();
        
        return true;
    }
       
}
