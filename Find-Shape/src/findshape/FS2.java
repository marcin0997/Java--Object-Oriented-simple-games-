/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findshape;

import static findshape.FindShape.ksztalty;
import static findshape.FindShape.liczba;
import java.util.Random;
import javax.swing.JButton;

/**
 * Klasa poboczna jako abstrakcyjna
 */
public class FS2 {
    //int liczba=0;
    
    Random rand = new Random();
    
    /**
     * Kontruktor klasy losujacy liczby
     */
    public FS2()
    {
        liczba = rand.nextInt(10);
    }
    
    /**
     * Losowanie miejsca pojawienia sie przycisku
     * @param ksztalty Przycisk
     */
    public void losuj_przycisk(JButton ksztalty)
    {
        
        
        int y,x;
        y = rand.nextInt(550);
        x = rand.nextInt(900);
        
        ksztalty.setLocation(x, y);
        ksztalty.setVisible(true);
    }
    
    /**
     * Powolne spadanie przyciskow
     * @param przycisk ksztalt
     */
    public void spadanie(JButton przycisk)
    {
        int y,x;
        y = przycisk.getLocation().y;
        x = przycisk.getLocation().x;
        y+=1;
        przycisk.setLocation(x,y); 
        //przycisk.setRolloverEnabled(true);
    }
    
}
