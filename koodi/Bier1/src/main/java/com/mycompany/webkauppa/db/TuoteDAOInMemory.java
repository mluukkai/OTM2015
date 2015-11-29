
package com.mycompany.webkauppa.db;

import com.mycompany.webkauppa.domain.Tuote;
import java.util.*;
import org.bson.types.ObjectId;

public class TuoteDAOInMemory implements TuoteDAO {
 
    private ArrayList<Tuote> tuotteet;
    private int id;
    
    public TuoteDAOInMemory() {
        tuotteet = new ArrayList<>();
        
        tuotteet.add( new Tuote(new ObjectId(new Date(), id++), "Fink Bräu", 1) );     
        tuotteet.add( new Tuote(new ObjectId(new Date(), id++), "Karjala", 2) );
        tuotteet.add( new Tuote(new ObjectId(new Date(), id++), "Huvila Pale Ale", 4) );
        tuotteet.add( new Tuote(new ObjectId(new Date(), id++), "Nögne IPA", 7) );
        tuotteet.add( new Tuote(new ObjectId(new Date(), id++), "Weihenstephaner", 4) );
        for (Tuote tuote : tuotteet) {
            tuote.setSaldo(100);
        }

    }        
    
    @Override
    public List<Tuote> findAll() {
        return tuotteet;
    }

    @Override
    public void save(Tuote tuote) { 
        if ( tuote.getId()==null ) {
            tuote.setId(new ObjectId(new Date(), id++));
            System.out.println( "saved "+tuote.getNimi() );
        }
    }

    @Override
    public Tuote find(ObjectId id) {
        for (Tuote tuote : tuotteet) {
            if ( tuote.getId().equals(id)) {
                return tuote;
            }
         }
        
        return null;    
    }

}
