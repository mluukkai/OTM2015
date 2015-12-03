
import java.util.ArrayList;
import java.util.List;

public class Lintu {
    private int tunnus;
    private String nimi;
    private String laji;
    private String lajinTiedot;
    private String lajiLatinaksi;
    private List<Integer> xKordinaatit;
    private List<Integer> yKordinaatit;
    
    public Lintu(int tunnus, String nimi, String laji, String lajinTiedot, String lajiLatinaksi) {
        this.tunnus = tunnus;
        this.nimi = nimi;
        this.laji = laji;
        this.lajinTiedot = lajinTiedot;
        this.lajiLatinaksi = lajiLatinaksi;
        this.xKordinaatit = new ArrayList<>();
        this.yKordinaatit = new ArrayList<>();
    }
 
    /*
    linnun yhden hetken sijainti ilmoitetaan (x,y)-kordinaattina
    x kasvaa itään päin ja y kasvaa pohjoiseen
     */
    public int getTunnus() {
        return tunnus;
    }
    
    public void lisaaPiste(int x, int y){
        xKordinaatit.add(x);
        yKordinaatit.add(y);
    }
    
    public void lisaaPisteet(int[] xt, int[] yt){
        for (int i = 0; i < xt.length; i++) {
        xKordinaatit.add(xt[i]);
        yKordinaatit.add(yt[i]);                       
        }
    }
    
    public double liikuttuMatka() {
        int matka = 0;
        for (int i = 0; i < xKordinaatit.size()-1; i++) {
            int dX = xKordinaatit.get(i)-xKordinaatit.get(i+1);
            int dY = yKordinaatit.get(i)-yKordinaatit.get(i+1);
            matka += Math.sqrt(dX*dX+dY*dY);
        }
        
        return matka;
    }    
    
    public int[] etelaisinPiste(){
        int etelaisin = Integer.MAX_VALUE;
        int indeksi = 0;
        for (int i = 0; i < yKordinaatit.size(); i++) {
            if ( yKordinaatit.get(i)<etelaisin ) {
                indeksi = i;
                etelaisin = yKordinaatit.get(i);
            }
        }
        
        int[] piste = new int[2];
        piste[0] = xKordinaatit.get(indeksi);
        piste[1] = yKordinaatit.get(indeksi); 
        return piste;
    } 
    
    public int[] pohjoisinPiste(){
        int pohjoisin = Integer.MIN_VALUE;
        int indeksi = 0;
        for (int i = 0; i < yKordinaatit.size(); i++) {
            if ( yKordinaatit.get(i)>pohjoisin ) {
                indeksi = i;
                pohjoisin = yKordinaatit.get(i);
            }
        }       
        
        int[] piste = new int[2];
        piste[0] = xKordinaatit.get(indeksi);
        piste[1] = yKordinaatit.get(indeksi);        
        return piste;
    } 

    @Override
    public String toString() {
        return nimi+ " ("+laji+", "+lajiLatinaksi+") havaintopisteitä "+xKordinaatit.size()+ " kpl";
    }
    
    
}
