/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package falling_letters;

import java.awt.Font;
import javax.swing.JLabel;
import static falling_letters.Falling_letters.okno_gry;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Klasa podrzedna odpowiadajaca za wlasciwosci spadajacych liter
 * @author Marcin Kubiak
 */
public class Losuj_literke extends JFrame {
    Random random = new Random();
    
    Losuj_literke()
    {
        
    }
    /**
     * Konstruktor metody odpowiedzialny za ustawienie czcionki i rozmiaru kazdej z liter oraz dodanie do okna gry
     * @param znak Litera
     * @param x polozenie na planszy
     * @param y polozenie na planszy
     * @return Zwroc litere po zmianach
     */
    public JLabel los(char znak, int x, int y)
    {
        
        JLabel litera = new JLabel(String.valueOf(znak));
        litera.setSize(50,50);
        litera.setLocation(x,y);
        Font czcionka = new Font("Serif", Font.BOLD, 48);
        litera.setFont(czcionka);
        okno_gry.add(litera);
        return litera;
    }
    
    /**
     * Metoda odpowiadajaca za losowanie pozycji pojawiajacej sie litery na planszy
     * @param litera Dana litera
     */
    public void losuj_pozycje(JLabel litera)
    {
        
        int pozycja = random.nextInt(700)+20;
        litera.setLocation(pozycja, 80);
    }
    
    /**
     * Metoda kontrolujaca spadanie litery
     * @param litera Dana litera
     */
    public void spadnij_literke(JLabel litera)
    {
        int y = litera.getLocation().y;
        int x = litera.getLocation().x;
        y=y+2;
        litera.setLocation(x,y);
    }
    
    /**
     * Metoda odpowiedzialna za wylosowanie id litery, ktora ma sie pojawic na planszy
     * @return Wylosowane id litery
     */
    public int losuj_liczbe()
    {
        Random rand = new Random();
        return rand.nextInt(26);
    }
    
    /**
     * Metoda uzupelniajaca tablice o litery nie wchodzace w sklad slowa glownego
     * @return Tablica bez liter ze slowa w ramce
     */
    public int[] losuj_zle()
    {
        int[] tab = new int[26];
        int b;
        for(int i=0;i<26;i++)
        {
            b = random.nextInt(26);
            tab[i] = b;
            
        }
        return tab;
    }
}
