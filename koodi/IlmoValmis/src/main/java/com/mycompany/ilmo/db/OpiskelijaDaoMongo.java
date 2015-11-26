package com.mycompany.ilmo.db;

import com.mongodb.MongoClient;
import com.mycompany.ilmo.Opiskelija;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

public class OpiskelijaDaoMongo {

    private Datastore store;

    public OpiskelijaDaoMongo(String palvelin, String tietokanta) {
        Morphia morphia = new Morphia();
        MongoClient mc = new MongoClient(palvelin);
        store = morphia.createDatastore(mc, tietokanta);
    }

    private Query<Opiskelija> query() {
        return store.createQuery(Opiskelija.class);
    }

    public List<Opiskelija> kaikki() {
        return query().asList();
    }

    public List<Opiskelija> ilmottautujat(String kurssi) {
        return query().field("ilmoittautumiset.kurssi").equal(kurssi).asList();
    }

    public Opiskelija haeNimellä(String nimi) {
        return query().field("nimi").equal(nimi).get();
    }
}
