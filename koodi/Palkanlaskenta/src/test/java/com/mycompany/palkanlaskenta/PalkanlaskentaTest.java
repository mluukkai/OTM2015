
package com.mycompany.palkanlaskenta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PalkanlaskentaTest {

    Palkanlaskenta p;
    
    @Before
    public void setUp() {
        p = new Palkanlaskenta();
    }
    
    @Test
    public void alussaEiMaksujaEikaTuontekijoita(){
        assertTrue(p.maksuhistoria("csv").isEmpty());
        assertTrue(p.tyontekijat("csv").isEmpty());
    }
    
    @Test
    public void yksiLisattyTyontekijaIlmanTunteja(){
        boolean ok = p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345",10);
        assertTrue(ok);
        
        assertEquals(1, p.tyontekijat("csv").size());
        List<String> odotettu = new ArrayList<>();
        odotettu.add(toCSV("1","Arto", "123 456", "10"));
        assertContent(odotettu,p.tyontekijat("csv"));
        
        assertEquals(0, p.maksuhistoria("csv").size());
    }   

    @Test
    public void samaIdEiVoiOllaKahdellaTyontekijalla(){
        boolean ok = p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345", 10);
        assertTrue(ok);
        ok = p.lisaaTyontekija(1,"Pekka", "123 457", "hämeentie","pek@ka.fi","040-44345",20);
        assertFalse(ok);
        assertEquals(1, p.tyontekijat("csv").size());
        List<String> odotettu = new ArrayList<>();
        odotettu.add(toCSV("1","Arto", "123 456", "10"));
        assertContent(odotettu,p.tyontekijat("csv"));
    } 
    
    @Test
    public void tuntienLisaaminenTyontekijalle(){
        p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345",10);
   
        p.lisaaTunnit(1, 5);
        assertEquals(0, p.maksuhistoria("csv").size());
        
        p.maksaPalkat();
        assertEquals(1, p.maksuhistoria("csv").size()); 
        
        List<String> odotettu = new ArrayList<>();
        odotettu.add(toCSV("Arto", "123 456", "50"));
        assertContent(odotettu,p.maksuhistoria("csv"));        
    }   
    
    @Test
    public void tuntienLisaaminenTyontekijalleMontaKertaa(){
        p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345",10);
   
        p.lisaaTunnit(1, 5);
        p.lisaaTunnit(1, 20);
        assertEquals(0, p.maksuhistoria("csv").size());
        
        p.maksaPalkat();
        assertEquals(1, p.maksuhistoria("csv").size()); 
        
        List<String> odotettu = new ArrayList<>();
        odotettu.add(toCSV("Arto", "123 456", "250"));
        assertContent(odotettu,p.maksuhistoria("csv"));                 
    }  
    
    @Test
    public void tuntejaEiVoiLisataTuntemattomalleTyontekijalle(){
        p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345",10);
   
        boolean ok = p.lisaaTunnit(2, 5);
        assertFalse(ok);
    }     
   
    @Test
    public void montaTyontekijaa(){
        p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345",10);
        p.lisaaTyontekija(2,"Liisa", "123 457", "hämeentie","lii@sa.fi","040-44345",20);
        
        assertEquals(2, p.tyontekijat("csv").size());
        List<String> odotettu = new ArrayList<>();
        odotettu.add(toCSV("1","Arto", "123 456", "10"));
        odotettu.add(toCSV("2","Liisa", "123 457", "20"));        
        assertContent(odotettu,p.tyontekijat("csv"));        
    }  
    
    @Test
    public void tuntienLisaaminenMonelleTyontekijalle(){
        p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345",10);
        p.lisaaTyontekija(2,"Liisa", "123 457", "hämeentie","lii@sa.fi","040-44345",20);
        
        p.lisaaTunnit(1, 5);
        p.lisaaTunnit(1, 5);
        p.lisaaTunnit(2, 20);
        p.lisaaTunnit(2, 5);
        p.lisaaTunnit(1, 10);
        
        assertEquals(0, p.maksuhistoria("csv").size());
        
        p.maksaPalkat();
        assertEquals(2, p.maksuhistoria("csv").size()); 
        
        List<String> odotettu = new ArrayList<>();
        odotettu.add(toCSV("Arto", "123 456", "200"));
        odotettu.add(toCSV("Liisa", "123 457", "500"));
        assertContent(odotettu,p.maksuhistoria("csv"));        
    }      
    
    @Test
    public void tuntienLisaaminenJaToinenPalkanmaksu(){
        p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345",10);
   
        p.lisaaTunnit(1, 5);
        assertEquals(0, p.maksuhistoria("csv").size());
        
        p.maksaPalkat();
        assertEquals(1, p.maksuhistoria("csv").size()); 
        
        List<String> odotettu = new ArrayList<>();
        odotettu.add(toCSV("Arto", "123 456", "50"));
        assertContent(odotettu,p.maksuhistoria("csv"));   
        
        p.lisaaTunnit(1, 15);
        p.lisaaTunnit(1, 20);

        assertEquals(1, p.maksuhistoria("csv").size());        
        
        p.maksaPalkat();
        assertEquals(2, p.maksuhistoria("csv").size()); 
        odotettu = new ArrayList<>();
        odotettu.add(toCSV("Arto", "123 456", "50"));
        odotettu.add(toCSV("Arto", "123 456", "350"));
        assertContent(odotettu,p.maksuhistoria("csv")); 
    }       
    
    @Test(expected=IllegalArgumentException.class)
    public void vainCVSMuotoTuoettuMaksuhistoriassa(){
        p.maksuhistoria("json");
    }

    @Test(expected=IllegalArgumentException.class)
    public void vainCVSMuotoTuoettuTyontekijalistalla(){
        assertTrue(p.tyontekijat("json").isEmpty());
    }
 
    //@Test
    public void palkanMuuttuminenHuomioidaanMaksussa(){
        p.lisaaTyontekija(1,"Arto", "123 456", "mannerheimintie","ar@to.fi","040-12345", 10);
  
        p.lisaaTunnit(1, 10);

        p.uusiTuntipalkka(1, 20);
        p.lisaaTunnit(1, 10);
        
        p.maksaPalkat();
        assertEquals(1, p.maksuhistoria("csv").size()); 
        
        List<String> odotettu = new ArrayList<>();
        
        // maksetun palkan tulee olla nyt 300 euroa, sillä tunteja on kirjattu
        //  10h tuntipalkalla 10 e
        //  10h tuntipalkalla 20 e
        odotettu.add(toCSV("Arto", "123 456", "300"));
        assertContent(odotettu,p.maksuhistoria("csv"));        
    }       
    
    private void assertContent(List<String> odotettu, List<String> tulos){
        assertEquals(odotettu.size(), tulos.size());
        
        for (String tulosRivi : tulos) {
            assertTrue("palautettu "+tulosRivi+" ei odotettujen "+odotettu+ " joukossa",odotettu.contains(tulosRivi));
        }
    }
            
    private String toCSV(String... osat){
        String csv = "";
        for (int i = 0; i < osat.length-1; i++) {
            csv += osat[i]+ ";";
        }
        csv += osat[osat.length-1];
        
        return csv;
    }

}
