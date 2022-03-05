/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package falling_letters;

import static falling_letters.Falling_letters.Koniec;
import static falling_letters.Falling_letters.Start;
import static falling_letters.Falling_letters.okno_gry;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Klasa Menu gry
 * @author Marcin Kubiak
 */
public class Menu extends JFrame {
    /**
     * Przycisk startu
     */
    public static JButton Start;
    /**
     * Przycisk wyjscia z gry
     */
    public static JButton Koniec;
    
    /**
     * Konstruktor Menu gry
     */
    public Menu()
    {
        Start = new JButton("START");
        Start.setSize(150,50);
        Start.setLocation(350,200);
        this.getContentPane().add(Start);
        
        Koniec = new JButton("KONIEC");
        Koniec.setSize(150,50);
        Koniec.setLocation(350,500);
        this.getContentPane().add(Koniec);
        
    }
}
