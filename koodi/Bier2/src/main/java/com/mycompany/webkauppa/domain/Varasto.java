package com.mycompany.webkauppa.domain;

import com.mycompany.webkauppa.db.TuoteDAO;
import java.util.*;
import org.bson.types.ObjectId;

public class Varasto {

    private TuoteDAO tuoteDAO;
    private static Varasto instance;

    public static Varasto create(TuoteDAO tuoteDAO) {
        instance = new Varasto(tuoteDAO);
        return instance;
    }
    
    public static Varasto getInstance(){
        return instance;
    }
    
    private Varasto(TuoteDAO tuoteDAO) {
        this.tuoteDAO = tuoteDAO;
    }

    public List<Tuote> tuotteidenLista() {
        return tuoteDAO.findAll();
    }

    public Tuote etsiTuote(ObjectId id) {        
        return tuoteDAO.find(id);
    }

    public Tuote otaVarastosta(ObjectId id) {
        Tuote tuote = etsiTuote(id);
        if (tuote.getSaldo() == 0) {
            return null;
        }
        tuote.setSaldo(tuote.getSaldo() - 1);
        tuoteDAO.save(tuote);
        return tuote;
    }

    public void palautaVarastoon(ObjectId id) {
        Tuote tuote = etsiTuote(id);
        tuote.setSaldo(tuote.getSaldo() + 1);
        tuoteDAO.save(tuote);
    }

    public void palautaVarastoon(Ostos ostos) {
        Tuote tuote = etsiTuote(ostos.tuotteenId());
        tuote.setSaldo(tuote.getSaldo() + ostos.lukumaara());
        tuoteDAO.save(tuote);
    }

    public void paivitaTuotteenTiedot(ObjectId tuoteId, int uusiHinta, int uusiSaldo) {
        Tuote tuote = etsiTuote(tuoteId);
        tuote.setSaldo(uusiSaldo);
        tuote.setHinta(uusiHinta);
        tuoteDAO.save(tuote);
    }

    public boolean lisaaTuote(String nimi, int hinta, int saldo) {
        for (Tuote tuote : tuoteDAO.findAll()) {
            if ( tuote.getNimi().equals(nimi) ) return false;
        }
        
        Tuote tuote = new Tuote(nimi, hinta);
        tuote.setSaldo(saldo);
        tuoteDAO.save(tuote);
        return true;
    }
}
