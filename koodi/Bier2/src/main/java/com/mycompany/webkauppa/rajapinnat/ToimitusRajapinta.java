package com.mycompany.webkauppa.rajapinnat;

import com.mycompany.webkauppa.domain.Ostos;
import java.util.*;

public class ToimitusRajapinta {
    private static ToimitusRajapinta instance;
    
    public static ToimitusRajapinta getInstance() {
        if (instance==null) {
            instance = new ToimitusRajapinta();
        }
        return instance;
    }

    private ToimitusRajapinta() {
    }
  
    public void kirjaatoimitus(String nimi, String osoite, List<Ostos> ostokset){
        // lähetä toimituksen tiedot postitustietojärjestelmään
    }
}
