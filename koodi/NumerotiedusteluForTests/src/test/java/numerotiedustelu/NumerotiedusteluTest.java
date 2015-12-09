package numerotiedustelu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NumerotiedusteluTest {

    ByteArrayOutputStream tulosvirta;

    @Before
    public void setUp() {
        tulosvirta = new ByteArrayOutputStream();
        System.setOut(new PrintStream(tulosvirta));
    }

    @Test
    public void menuTulostuu() {
	String syote = muodosta("x");
        
	Numerotiedustelu tiedustelu = new Numerotiedustelu(new Scanner(syote));
        tiedustelu.kaynnista();

        String tulos = tulosvirta.toString();
        
        assertTrue(tulos.contains("1 lisää numero"));
        assertTrue(tulos.contains("2 hae numerot"));
        assertTrue(tulos.contains("3 hae puhelinnumeroa vastaava henkilö"));
    }

    @Test
    public void tuntemattomanNumeroaEiLoydy() {
	String syote = muodosta("2","arto","x");
        
	Numerotiedustelu tiedustelu = new Numerotiedustelu(new Scanner(syote));
        tiedustelu.kaynnista();
        
        String tulos = tulosvirta.toString();
        
        assertTrue("ohjelman tulostus oli: "+tulos, tulos.contains("ei löytynyt"));
    }    
    
    private String muodosta(String... lines) {
        String linesWithEnter = "";
        for (String line : lines) {
            linesWithEnter += line + "\n";
        }
        return linesWithEnter;
    }      
    
}
