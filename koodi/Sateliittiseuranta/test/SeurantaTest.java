import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SeurantaTest {
    
    Seuranta s;
    
    @Before
    public void setUp() {
        s = new Seuranta();
        s.lisaaLintu(1, "Heikki", "Lokki", "Laridae", "Rantalintujen eli kahlaajalintujen lahkoon (Charadriformes) kuuluva heimo, josta Suomessa tavataan hyvin yleisesti 8 lajia kolmesta eri suvusta. Maailmanlaajuisesti lokkien heimoon kuuluu lähes 70 lajia 15 eri suvusta.");
        s.lisaaLintu(2, "Liisa", "Lokki", "Laridae", "Rantalintujen eli kahlaajalintujen lahkoon (Charadriformes) kuuluva heimo, josta Suomessa tavataan hyvin yleisesti 8 lajia kolmesta eri suvusta. Maailmanlaajuisesti lokkien heimoon kuuluu lähes 70 lajia 15 eri suvusta.");
        s.lisaaLintu(3, "Olli", "Haukka", "Accipitridae", "Päiväpetolintujen lahkoon (Accipitriformes) kuuluva heimo. (Toinen lahkon Suomessa tavattavista heimoista on jalohaukat.) Suomessa haukkojen heimoon kuuluvat molemmat maassamme tavattavat kotkat, useat haukat sekä sääksi ja piekana.");
    
        int[] x1 = {0, 3, -1, -3, 3};
        int[] y1 = {0, 2, 1, 5, 8};
        s.lisaaPisteet(1, x1, y1);
        
        int[] x2 = {0, 2, 24};
        int[] y2 = {0, -7, 4};        
        s.lisaaPisteet(2, x2, y2);  

        s.lisaaPiste(3, 1, 1); 
        s.lisaaPiste(3, 2, 1); 
        s.lisaaPiste(3, 1, 2); 
        s.lisaaPiste(3, 2, -1); 
        s.lisaaPiste(3, 1, -1); 
        s.lisaaPiste(3, -3, 1);         
    }
    
    @Test
    public void pohjoisin(){
        Lintu lintu = s.pohjoisinVierailu();
        assertEquals(1, lintu.getTunnus());
    }
    
    @Test
    public void etelaisin(){
        Lintu lintu = s.etelaisinVierailu();
        assertEquals(2, lintu.getTunnus());
    }
    
    @Test
    public void pisinMatka(){
        Lintu lintu = s.pisimmanMatkanLiikkunut();
        assertEquals(2, lintu.getTunnus());
    }    
}
