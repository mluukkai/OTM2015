package com.mycompany.webkauppa.db;

import com.mongodb.MongoClient;
import com.mycompany.webkauppa.domain.Ostostapahtuma;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

public class OstostapahtumaDAOMongo {
    private Datastore store;
    
    public OstostapahtumaDAOMongo() {
        Morphia morphia = new Morphia();
        MongoClient mc = new MongoClient("80.69.172.211:27017");
        store = morphia.createDatastore(mc, "otm6");    
    }
    
    private Query<Ostostapahtuma> tuotteet() {
        return store.createQuery(Ostostapahtuma.class);
    }
    
    public List<Ostostapahtuma> findAll() {
        return tuotteet().asList();
    }


    public void save(Ostostapahtuma ot) {
        store.save(ot);
    }    
}
