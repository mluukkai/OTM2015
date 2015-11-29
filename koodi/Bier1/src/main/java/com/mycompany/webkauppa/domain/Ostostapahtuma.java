package com.mycompany.webkauppa.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Ostostapahtuma {
    @Id
    private ObjectId id;
    
    private String maksu;
    private String toimitus;

    public Ostostapahtuma(String maksu, String toimitus) {
        this.maksu = maksu;
        this.toimitus = toimitus;
    }

    public Ostostapahtuma() {
    }
    
    public String getMaksu() {
        return maksu;
    }

    public String getToimitus() {
        return toimitus;
    }
    
}
