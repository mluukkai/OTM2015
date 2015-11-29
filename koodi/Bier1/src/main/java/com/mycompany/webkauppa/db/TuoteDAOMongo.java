package com.mycompany.webkauppa.db;

import com.mongodb.MongoClient;
import com.mycompany.webkauppa.domain.Tuote;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

public class TuoteDAOMongo implements TuoteDAO {
    private Datastore store;
    
    public TuoteDAOMongo() {
        Morphia morphia = new Morphia();
        MongoClient mc = new MongoClient("80.69.172.211:27017");
        store = morphia.createDatastore(mc, "otm6");    
    }
    
    private Query<Tuote> tuotteet() {
        return store.createQuery(Tuote.class);
    }
    
    @Override
    public List<Tuote> findAll() {
        return tuotteet().asList();
    }

    @Override
    public Tuote find(ObjectId id) {
        return tuotteet().field("id").equal(id).get();
    }

    @Override
    public void save(Tuote tuote) {
        store.save(tuote);
    }

}
