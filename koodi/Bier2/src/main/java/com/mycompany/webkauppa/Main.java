package com.mycompany.webkauppa;

import com.mycompany.webkauppa.db.OstostapahtumaDAOMongo;
import com.mycompany.webkauppa.db.TuoteDAO;
import com.mycompany.webkauppa.db.TuoteDAOMongo;
import com.mycompany.webkauppa.rajapinnat.PankkiRajapinta;
import com.mycompany.webkauppa.rajapinnat.ToimitusRajapinta;
import com.mycompany.webkauppa.domain.Ostoskori;
import com.mycompany.webkauppa.domain.Ostostapahtumat;
import com.mycompany.webkauppa.domain.Tuote;
import com.mycompany.webkauppa.domain.Varasto;
import com.mycompany.webkauppa.ohjaus.OstoksenLisaysKoriin;
import com.mycompany.webkauppa.ohjaus.OstoksenPoistoKorista;
import com.mycompany.webkauppa.ohjaus.OstoksenSuoritus;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import spark.ModelAndView;
import spark.Request;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

// mvn exec:java -Dexec.mainClass=com.mycompany.webkauppa.Main

public class Main {
    public static void main(String[] args) {   
        port(getHerokuAssignedPort());
        Map<String, Object> viewParams = new HashMap<>();  
        
        Varasto varasto = Varasto.create(new TuoteDAOMongo());
        Ostostapahtumat ostostapahtumat = Ostostapahtumat.create(new OstostapahtumaDAOMongo());
        PankkiRajapinta pankki = PankkiRajapinta.getInstance();
        ToimitusRajapinta toimitus = ToimitusRajapinta.getInstance();      
        
        get("/home", (req, res) ->  {           
            return view("index");
            
        }, renderer());

        get("/yhteystiedot", (req, res) ->  { 
            return view("yhteystiedot");            
    
        }, renderer());        

        get("/tyhjenna", (req, res) ->  { 
            req.session().removeAttribute("ostoskori");             
            res.redirect("/tuotteet");
            return "";        
        });     

        get("/kassa", (req, res) ->  { 
            Ostoskori kori = getOstoskoriFrom(req);
            if (kori==null) {
                kori = new Ostoskori();
            }
            viewParams.put("kori", kori);            
            return Main.view("kassa", viewParams);    
        
        }, renderer());              
        
        // ostosten poisto korista
        post("/poista", (req, res) ->  { 

            OstoksenPoistoKorista komento = new OstoksenPoistoKorista(
                getOstoskoriFrom(req), 
                new ObjectId(req.queryParams("id"))
            );          
            komento.suorita();            

            res.redirect("/kassa");
            return ""; 
        });         
        
        // tuotteiden maksu
        post("/kassa", (req, res) ->  { 
            Ostoskori kori = getOstoskoriFrom(req); 
            int hinta = kori.hinta();
            
            OstoksenSuoritus komento = new OstoksenSuoritus(
                req.queryParams("nimi"), 
                req.queryParams("osoite"),
                req.queryParams("luottokorttinumero"), 
                kori
            );
            
            if ( komento.suorita() ) {
                req.session(true).attribute("osoite", req.queryParams("osoite"));
                req.session(true).attribute("hinta", ""+hinta);
                res.redirect("/onnistunut_maksu");                 
            } else {
                res.redirect("/epaonnistunut_maksu");    
            }
                        
            return ""; 
        }); 
        
        get("/onnistunut_maksu", (req, res) ->  {  
            String osoite = (String)req.session().attribute("osoite");
            req.session().removeAttribute("osoite");
            
            String hinta = (String)req.session().attribute("hinta");
            req.session().removeAttribute("hinta");

            viewParams.put("osoite", osoite);    
            viewParams.put("hinta", hinta);   
            return Main.view("ostokset", viewParams);    
        
        }, renderer()); 
        
        get("/epaonnistunut_maksu", (req, res) ->  {           
            return view("virhe");    
        
        }, renderer()); 
        
        get("/tuotteet", (req, res) ->  {
            viewParams.put("tuotteet", varasto.tuotteidenLista());
            
            Ostoskori kori = getOstoskoriFrom(req);
            if ( kori==null ) {
                kori = new Ostoskori();
                req.session(true).attribute("ostoskori", kori);
            }                   

            viewParams.put("kori", kori);
                        
            return view("tuotteet", viewParams);    
        
        }, renderer());      

        // ostoksen lisäys koriin
        post("/tuotteet", (req, res) ->  { 
            OstoksenLisaysKoriin komento = new OstoksenLisaysKoriin(
                getOstoskoriFrom(req), 
                new ObjectId(req.queryParams("id"))
            );
            
            komento.suorita();
            
            res.redirect("/tuotteet");
            return "";
        }); 
        
        post("/hallinta", (req, res) ->  {
            String nimi = req.queryParams("nimi");
            int hinta = Integer.parseInt(req.queryParams("hinta"));
            int saldo = Integer.parseInt(req.queryParams("saldo"));
            
            varasto.lisaaTuote(nimi, hinta, saldo);
            res.redirect("/hallinta");
            return "";
        }); 
                
        get("/hallinta", (req, res) ->  {      
            viewParams.put("tuotteet", varasto.tuotteidenLista());
            viewParams.put("maksut", ostostapahtumat.maksut());
            viewParams.put("toimitukset", ostostapahtumat.toimitukset());
            
            return Main.view("hallinta", viewParams);       
        }, renderer());       
        
        get( "*", (req, res) ->  {          
            return view("index");           
        }, renderer());    
    }
    
    static Ostoskori getOstoskoriFrom(Request req){
        return (Ostoskori)req.session(true).attribute("ostoskori");
    }
    
    static ThymeleafTemplateEngine renderer() {
        return new ThymeleafTemplateEngine();
    }
 
    static ModelAndView view(String template){
        return new ModelAndView(new HashMap<>(), template);
    }
       
    static ModelAndView view(String template, Map<String, Object> map){
        return new ModelAndView(map, template);
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }   
}