
import java.util.ArrayList;
import java.util.List;

public class Seuranta {
    List<Lintu> linnut;

    public Seuranta() {
        this.linnut = new ArrayList<>();
    }
    
    public void lisaaLintu(int tunnus, String nimi, String laji, String lajiLatinaksi, String lajinTiedot){
        for (Lintu lintu : linnut) {
            if ( lintu.getTunnus()==tunnus) {
                throw new IllegalArgumentException();
            }
        }
        Lintu lintu = new Lintu(tunnus, nimi, laji, lajinTiedot, lajiLatinaksi);
        linnut.add(lintu);
    }
    
    public void lisaaPisteet(int linnunTunnus, int[] xKordinaatit, int[] yKordinaatit){
        for (Lintu lintu : linnut) {
            if ( lintu.getTunnus()==linnunTunnus) {
                lintu.lisaaPisteet(xKordinaatit, yKordinaatit);
            }
        }
    }

    public void lisaaPiste(int linnunTunnus, int x, int y){
        for (Lintu lintu : linnut) {
            if ( lintu.getTunnus()==linnunTunnus) {
                lintu.lisaaPiste(x, y);
            }
        }        
    }
    
    public Lintu etelaisinVierailu() {
        int tunnus = -1;
        int etelaisinKordinaatti = Integer.MAX_VALUE;
        for (Lintu lintu : linnut) {
            if ( lintu.etelaisinPiste()[1]<etelaisinKordinaatti ) {
                etelaisinKordinaatti = lintu.etelaisinPiste()[1];
                tunnus = lintu.getTunnus();
            }
        }

        Lintu etsitty = null;
        for (Lintu lintu : linnut) {
            if ( lintu.getTunnus()==tunnus) {
                etsitty = lintu;
            }
        }         
        return etsitty;
    }
         
    public Lintu pohjoisinVierailu() {
        int tunnus = -1;
        int pohjoisinKordinaatti = Integer.MIN_VALUE;
        for (Lintu lintu : linnut) {
            if ( lintu.pohjoisinPiste()[1]>pohjoisinKordinaatti) {
                pohjoisinKordinaatti = lintu.pohjoisinPiste()[1];
                tunnus = lintu.getTunnus();
            }
        }
                
        Lintu etsitty = null;
        for (Lintu lintu : linnut) {
            if ( lintu.getTunnus()==tunnus) {
                etsitty = lintu;
            }
        }         
        return etsitty;
    }
          
    
    public Lintu pisimmanMatkanLiikkunut() {
        int tunnus = -1;
        double pisinMatka = -1;
        for (Lintu lintu : linnut) {
            if ( lintu.liikuttuMatka()>pisinMatka) {
                pisinMatka = lintu.liikuttuMatka();
                tunnus = lintu.getTunnus();
            }
        }        
        Lintu etsitty = null;
        for (Lintu lintu : linnut) {
            if ( lintu.getTunnus()==tunnus) {
                etsitty = lintu;
            }
        }         
        return etsitty;
    } 
    
}
