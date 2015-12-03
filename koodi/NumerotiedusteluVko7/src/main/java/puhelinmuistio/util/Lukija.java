package puhelinmuistio.util;

import java.util.Scanner;
 
public class Lukija {
    private Scanner lukija;
     
    public Lukija() {
        lukija = new Scanner(System.in);
    }   
     
    public String lueMerkkijono() {
        return lukija.nextLine();
    }
    
    public String lueMerkkijono(String kysymys) {
        System.out.print(kysymys);
        return lukija.nextLine();
    }
    
    public int lueKokonaisluku() {
        return Integer.parseInt( lukija.nextLine() );
    }
}
