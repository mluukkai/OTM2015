package numerotiedustelu;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
 
        Numerotiedustelu numerotiedustelu = new Numerotiedustelu(lukija);
        numerotiedustelu.kaynnista();
    }
}
