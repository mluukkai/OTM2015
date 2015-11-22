package numerotiedustelu;

public class Osoite {
    private String katu;
    private String kaupunki;

    public Osoite(String katu, String kaupunki) {
        this.katu = katu;
        this.kaupunki = kaupunki;
    }

    public Osoite() {
    }
     
    public String getKatu() {
        return katu;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    @Override
    public String toString() {
        return katu+ " "+kaupunki;
    }

}