package com.mycompany.webkauppa.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Tuote {
    @Id
    private ObjectId id;

    private String nimi;
    private int hinta;
    private int saldo;

    public Tuote() {
    }

    public Tuote(String nimi, int hinta) {
        this.nimi = nimi;
        this.hinta = hinta;
    }

    public Tuote(ObjectId id, String nimi, int hinta) {
        this.id = id;
        this.nimi = nimi;
        this.hinta = hinta;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setHinta(int hinta) {
        this.hinta = hinta;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getHinta() {
        return hinta;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return nimi + " " + hinta + " euroa";
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
        
}
