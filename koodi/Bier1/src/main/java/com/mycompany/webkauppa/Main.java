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
        
        Varasto varasto = new Varasto(new TuoteDAOMongo());
        Ostostapahtumat ostostapahtumat = new Ostostapahtumat(new OstostapahtumaDAOMongo());
        PankkiRajapinta pankki = new PankkiRajapinta();
        ToimitusRajapinta toimitus = new ToimitusRajapinta();      
        
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
        
        post("/poista", (req, res) ->  { 
            String tuoteId = req.queryParams("id");
            Ostoskori kori = getOstoskoriFrom(req);
            Tuote poistettavaTuote = varasto.etsiTuote(new ObjectId(tuoteId));
            kori.poista(poistettavaTuote);
            
            res.redirect("/kassa");
            return ""; 
        });         
        
        post("/kassa", (req, res) ->  { 
            Ostoskori kori = getOstoskoriFrom(req);
            
            String nimi = req.queryParams("nimi");
            String osoite = req.queryParams("osoite");
            String kortti = req.queryParams("luottokorttinumero");
                        
            if ( pankki.maksa(nimi, kortti, kori.hinta()) ) {
                toimitus.kirjaatoimitus(nimi, osoite, kori.ostokset());
                ostostapahtumat.lisaaOstostapahtuma(kori, nimi, kortti, osoite);
                req.session().removeAttribute("ostoskori");
                req.session(true).attribute("osoite", osoite);
                req.session(true).attribute("hinta", ""+kori.hinta());
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

        post("/tuotteet", (req, res) ->  { 
            String tuoteId = req.queryParams("id");

            Ostoskori kori = getOstoskoriFrom(req);    
    
            Tuote tuote = varasto.otaVarastosta(new ObjectId(tuoteId));
        
            if (tuote!=null) {
                kori.lisaaTuote(tuote);
            }
            
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