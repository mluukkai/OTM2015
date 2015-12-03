
public class Main {

    public static void main(String[] args) {
        Seuranta s = new Seuranta();
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
    
        System.out.println("pohjoisimmassa kävi:");
        System.out.println(s.pohjoisinVierailu());
     
        System.out.println("\neteläisimmässä kävi:");
        System.out.println(s.etelaisinVierailu());
    
        System.out.println("\npisimmän matkan teki:");
        System.out.println(s.pisimmanMatkanLiikkunut());
    }
    
}
