package com.mycompany.palkanlaskenta;

public class Main {
    public static void main(String[] args) {
        Palkanlaskenta p = new Palkanlaskenta();
        p.lisaaTyontekija(1,"Arto", "123 456", "Mannerheimintie 10","arto@arto.fi", "040-12345",10);
   
        p.lisaaTunnit(1, 5);
   
        System.out.println(p.tyontekijat("csv"));
        System.out.println(p.maksuhistoria("csv"));
        
        p.maksaPalkat();

        System.out.println(p.maksuhistoria("csv"));
    }
}
