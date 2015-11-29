package com.mycompany.webkauppa.db;

import com.mycompany.webkauppa.domain.Tuote;
import java.util.List;
import org.bson.types.ObjectId;

public interface TuoteDAO {

    List<Tuote> findAll();

    Tuote find(ObjectId id);
    
    void save(Tuote tuote);

}
