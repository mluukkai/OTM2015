package com.mycompany.palkanlaskenta;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

public class MaksupalveluRajapinta {
    String url;

    public MaksupalveluRajapinta() {
        this.url = "http://otmpaymentinterface.herokuapp.com/payments";
    }
    
    public void suoritaTilisiirto(String nimi, String tili, int summa){
        Payment maksu = new Payment(nimi, tili, summa);
        
        try {
            HttpResponse httpResponse = Request.Post(url)
                    .bodyString(new Gson().toJson(maksu), ContentType.APPLICATION_JSON)        
                    .execute().returnResponse();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    class Payment{
        String name;
        String account;
        int amount;

        public Payment(String name, String account, int amount) {
            this.name = name;
            this.account = account;
            this.amount = amount;
        }
        
    }
}
