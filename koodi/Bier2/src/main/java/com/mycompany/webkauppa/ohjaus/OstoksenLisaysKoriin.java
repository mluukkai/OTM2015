package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.domain.Ostoskori;
import com.mycompany.webkauppa.domain.Tuote;
import com.mycompany.webkauppa.domain.Varasto;
import org.bson.types.ObjectId;

public class OstoksenLisaysKoriin {

    private Ostoskori ostoskori;
    private ObjectId tuoteId;
    private Varasto varasto;

    public OstoksenLisaysKoriin(Ostoskori ostoskori, ObjectId tuoteId) {
        this.ostoskori = ostoskori;
        this.tuoteId = tuoteId;
        this.varasto = Varasto.getInstance();
    }

    public void suorita() {
        Tuote tuote = varasto.otaVarastosta(tuoteId);
        
        if (tuote==null) {
            return;
        }
                           
        ostoskori.lisaaTuote(tuote);                

    }
}
