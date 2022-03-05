/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthy_nutrition_simulation;

import static healthy_nutrition_simulation.Healthy_nutrition_simulation.Healthy_Image;
import static healthy_nutrition_simulation.Healthy_nutrition_simulation.UnHealthy_Image;
import static healthy_nutrition_simulation.Healthy_nutrition_simulation.interval;
import static healthy_nutrition_simulation.Healthy_nutrition_simulation.counter;
import static healthy_nutrition_simulation.Healthy_nutrition_simulation.tab_y;
import static healthy_nutrition_simulation.Healthy_nutrition_simulation.tab_x;
import static healthy_nutrition_simulation.Healthy_nutrition_simulation.tab_y2;
import static healthy_nutrition_simulation.Healthy_nutrition_simulation.tab_x2;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import static java.lang.Integer.max;
import static java.lang.String.valueOf;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * klasa abstrakcyjna do losowania nowego elementu
 * @author Kacper Kluge
 */
public class check_time {

    private int interval;
    
    Random rand = new Random();
    
    /**
     * Konstruktor pobierajacy aktualny czas
     * @param interval aktualny czas
     */
    public check_time(int interval) {
        this.interval = interval;
        
        
        if(interval%10==0 && interval>15)
        {
            
            if(interval%20==0)
            {
            int rand_x, rand_y;
            Healthy_Image[counter].setVisible(true);
            
                do
                {
                    rand_x = rand.nextInt(7);
                    rand_y = rand.nextInt(5);
                }
                while(tab_x2[rand_x] != 0 && tab_y2[rand_y] != 0);
                tab_x2[rand_x] = 1;
                tab_y2[rand_y] = 1;
                Healthy_Image[counter].setLocation(tab_x[rand_x],tab_y[rand_y]);
                counter++;
            }
            else
            {
                int rand_x, rand_y;
                UnHealthy_Image[counter].setVisible(true);

                do
                {
                    rand_x = rand.nextInt(7);
                    rand_y = rand.nextInt(5);
                }
                while(tab_x2[rand_x] != 0 && tab_y2[rand_y] != 0);
                tab_x2[rand_x] = 1;
                tab_y2[rand_y] = 1;
                UnHealthy_Image[counter].setLocation(tab_x[rand_x],tab_y[rand_y]);
                counter++;
            }
        }
        
        if(interval<70 && interval%5==0)
        {
            int randomowo = rand.nextInt(4);
            UnHealthy_Image[randomowo].setLocation(0,0);
            UnHealthy_Image[randomowo].setVisible(false);
        }
    }
    
    
}
