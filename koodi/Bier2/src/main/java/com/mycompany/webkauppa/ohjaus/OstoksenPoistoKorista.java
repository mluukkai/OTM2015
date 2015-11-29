
package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.domain.Ostoskori;
import com.mycompany.webkauppa.domain.Tuote;
import com.mycompany.webkauppa.domain.Varasto;
import org.bson.types.ObjectId;


public class OstoksenPoistoKorista {
    private Ostoskori ostoskori;
    private ObjectId tuoteId;
    private Varasto varasto;
    
    public OstoksenPoistoKorista(Ostoskori ostoskori, ObjectId tuoteId) {
        this.ostoskori = ostoskori;
        this.tuoteId = tuoteId;
        this.varasto = Varasto.getInstance();
    }    
    
    public void suorita() {
        varasto.palautaVarastoon( tuoteId );         
        Tuote poistettava = varasto.etsiTuote( tuoteId );              
        ostoskori.poista(poistettava);  
    }          
}
