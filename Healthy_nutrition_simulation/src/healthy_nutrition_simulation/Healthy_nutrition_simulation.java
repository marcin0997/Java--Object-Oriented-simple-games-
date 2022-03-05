/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthy_nutrition_simulation;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.Integer.max;
import static java.lang.String.valueOf;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.omg.SendingContext.RunTime;

/**
 * Glowny ekran gry
 * @author Marcin Kubiak
 */

public class Healthy_nutrition_simulation extends JFrame implements KeyListener 
{
    /**
     * Glowny gracz
     */
    private static JButton jb; 
    /**
     * Przycisk wyjscia
     */
    private final JButton exit_button;
    /**
     * Punkty
     */
    private static JLabel points;
    /**
     * Licznik
     */
    private static JLabel time_counter;
    /**
     * Glowny panel gry
     */
    public JPanel windowPanel;
            
    Random rand = new Random();
    
    private static int score=0;

    private int x; 
    private int y;
    
    private int x_hero = 68;
    private int y_hero = 100;
    private int x_window = 1024;
    private int y_window = 768;
    
    private int x_game = 800;
    private int y_game = 600;
    
    static Timer timer;
    static int interval = 75; /**ilosc czasu*/
    
    public static JLabel[] Healthy_Image; /**tablica zdrowych posilkow*/
    public static JLabel[] UnHealthy_Image; /**tablica niezdrowych posilkow*/
    
    public boolean add_point=false;
    
    private final String imgUp = "images/Healthy_Hero.png"; /**zdjecie glownego bohatera*/
    
    
    static int[] tab_x = {115,165,215,265,315,365,415,465,515,565,615,665,715};
    static int[] tab_x2 = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int[] tab_y = {50,100,150,200,250,300,350,400,450};
    static int[] tab_y2 = {0,0,0,0,0,0,0,0,0};
    int idh=0, id2h=0, idu=0, id2u=0;
    
    int new_image=2;
    public static int counter=2;
    
    /**
     * @param args the command line arguments
     */
    
    
  
    /**
     * Konstruktor gry
     */
    public Healthy_nutrition_simulation()
    {
        this.setTitle("JFrame");
        this.setVisible(true);
        this.addKeyListener(this);
        
        /**
         * Ekran całej gry
         */
        windowPanel = new JPanel();
        this.add(windowPanel);
        this.setContentPane(windowPanel);
        this.getContentPane().setBackground(Color.PINK);
        this.getContentPane().setPreferredSize(new Dimension(x_window,y_window));
        windowPanel.setLayout(null);
        
        /**
         * Tworzenie przycisku wyjście
         */
        this.exit_button = new JButton("!");
        exit_button.setSize(150,30);
        exit_button.setLocation(715,650);
        exit_button.setBackground(Color.white);
        exit_button.setText("WYJŚCIE");
        exit_button.setForeground(Color.gray);
        exit_button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.gray));
        exit_button.addActionListener(e-> System.exit(0));
        this.getContentPane().add(exit_button);
        
        
        /**
         * Tworzenie labela punktow
         */
        this.points = new JLabel("PUNKTY: ", JLabel.CENTER);
        points.setSize(200,80);
        points.setLocation(115,630);
        points.setBackground(Color.white);
        points.setOpaque(true);
        points.setForeground(Color.gray);
        points.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.gray));
        this.getContentPane().add(points);        
        
        
        /**
         * Tworzenie labela licznika czasu
         */
        time_counter = new JLabel("CZAS DO KOŃCA: ", JLabel.CENTER);
        time_counter.setSize(200,80);
        time_counter.setLocation(420,630);
        time_counter.setBackground(Color.white);
        time_counter.setOpaque(true);
        time_counter.setForeground(Color.gray);
        time_counter.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.gray));
        this.getContentPane().add(time_counter);  
        
        
        /**
         * Tworzenie gracza
         */
        this.jb = new JButton("!");
        jb.setSize(x_hero,y_hero);
        jb.setLocation(830,520);
        jb.setBackground(Color.black);
        jb.setIcon(new ImageIcon(imgUp));
        this.getContentPane().add(jb);
        
        
        
        
        
        
        int randomNum;
        Healthy_Image = new JLabel[8];
        int rand_x, rand_y;
        int[] p_xh, p_yh;
        p_xh = new int[4];
        p_yh = new int[4];
        //Tworzenie zdrowego pozywienia
        for(int i=0;i<8;i++)
        {
                if(i<2){
                do{
                    rand_x = rand.nextInt(13);
                    rand_y = rand.nextInt(9);
                }
                while(tab_x2[rand_x] != 0 && tab_y2[rand_y] != 0);
                tab_x2[rand_x] = 1;
                tab_y2[rand_y] = 1;
                System.out.println(tab_x2[rand_x]);
                
                Healthy_Image[i] = new JLabel(new ImageIcon("images/g"+(i+1)+".png"));
                Healthy_Image[i].setLocation(tab_x[rand_x],tab_y[rand_y]);
                Healthy_Image[i].setSize(100,100);
                this.getContentPane().add(Healthy_Image[i]);
                }
                else
                {

                    //Zdrowe
                    Healthy_Image[i] = new JLabel(new ImageIcon("images/g"+(i+1)+".png"));
                    Healthy_Image[i].setLocation(500, 700);
                    Healthy_Image[i].setSize(100,100);
                    this.getContentPane().add(Healthy_Image[i]);
                    Healthy_Image[i].setVisible(false);
                }
        }
        
        UnHealthy_Image = new JLabel[8];

        //Tworzenie niezdrowego pozywienia
        for(int i=0;i<8;i++)
        {
            if(i<2){
                do
                {
                    rand_x = rand.nextInt(13);
                    rand_y = rand.nextInt(9);
                }
                while(tab_x2[rand_x] != 0 && tab_y2[rand_y] != 0);
                tab_x2[rand_x] = 1;
                tab_y2[rand_y] = 1;
                
                UnHealthy_Image[i] = new JLabel(new ImageIcon("images/b"+(i+1)+".png"));
                UnHealthy_Image[i].setLocation(tab_x[rand_x],tab_y[rand_y]);
                UnHealthy_Image[i].setSize(100,100);
                this.getContentPane().add(UnHealthy_Image[i]);
            }
            else
            {
                UnHealthy_Image[i] = new JLabel(new ImageIcon("images/b"+(i+1)+".png"));
                UnHealthy_Image[i].setLocation(0, 100);
                UnHealthy_Image[i].setSize(100,100);
                UnHealthy_Image[i].setVisible(false);
                this.getContentPane().add(UnHealthy_Image[i]);
            }
        }    
        /**
         * Ekran po ktorym porusza sie gracz
         */
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.pink);
        gamePanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        gamePanel.setLocation(112, 20);
        gamePanel.setSize(new Dimension(800,600));
        this.add(gamePanel);
        gamePanel.setLayout(null);
        
        
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    
    /**
     * Sprawdzenie kolizji z obiektem niezdrowym
     * @param UnHealthy_Image Wczytanie niezdrowego obiektu z tablicy
     * @param i Wczytanie i-tego obiektu
     */
    public void if_collision(JLabel UnHealthy_Image, int i)
    {
        int x = jb.getLocation().x;
        int y = jb.getLocation().y;
        
        int img_x = UnHealthy_Image.getLocation().x;
        int img_y = UnHealthy_Image.getLocation().y;
        
        int rand_x, rand_y;
        
        if(Math.abs(x-img_x)<=85 && Math.abs(y-img_y)<=80)
        {
            
            UnHealthy_Image.setVisible(false);
            
            
            do
            {
                rand_x = rand.nextInt(13);
                rand_y = rand.nextInt(9);
            }while(tab_x2[rand_x] != 0 && tab_y2[rand_y] != 0);
            
            
            UnHealthy_Image.setLocation(tab_x[rand_x],tab_y[rand_y]);
            UnHealthy_Image.setVisible(true);
            tab_x2[rand_x] = 1;
            tab_y2[rand_y] = 1;
            
            
            for(int j=0;j<7;j++)
            {
                if(tab_x[j] == img_x)
                {
                    idu=j;
                    tab_x2[idu] = 0;
                    break;
                }
                
                System.out.println(tab_x2[j]+", ");
            }
            
            for(int k=0;k<5;k++)
            {
                if(tab_y[k] == img_y)
                {
                    id2u=k;
                    tab_y2[id2u] = 0;
                    break;
                }
            }
            
            score--;
            points.setText("PUNKTY: "+score);
            
            
        }
    }
    
    /**
     * Sprawdzenie kolizji z obiektem zdrowym
     * @param Healthy_Image Obiekt zdrowy z tablicy zdrowych obiektow
     * @param i i-ty element
     */
    public void if_collision_h(JLabel Healthy_Image, int i)
    {
        
        int x = jb.getLocation().x;
        int y = jb.getLocation().y;
        
        int img_x = Healthy_Image.getLocation().x;
        int img_y = Healthy_Image.getLocation().y;
        
        int rand_x, rand_y;
        
        if(Math.abs(x-img_x)<=85 && Math.abs(y-img_y)<=80)
        {
            
            Healthy_Image.setVisible(false);
            boolean a=false;
            
            do
            {
                rand_x = rand.nextInt(13);
                rand_y = rand.nextInt(9);
            }while(tab_x2[rand_x] != 0 && tab_y2[rand_y] != 0);
            
            Healthy_Image.setLocation(tab_x[rand_x],tab_y[rand_y]);
            Healthy_Image.setVisible(true);
            tab_x2[rand_x] = 1;
            tab_y2[rand_y] = 1;
            
            
            for(int j=0;j<7;j++)
            {
                if(tab_x[j] == img_x)
                {
                    idh=j;
                }
                
            }
            
            for(int j=0;j<5;j++)
            {
                if(tab_y[j] == img_y)
                {
                    id2h=j;
                }
            }
            tab_x2[idh] = 0;
            tab_y2[id2h] = 0;
            
            score++;
            points.setText("PUNKTY: "+score);
        }
    }
    
    /**
     * Postac porusza sie w prawo
     */
    private void moveRight()
    {
        if(jb.getX()<825)
        {
            this.x = jb.getX()+5;
            this.y = jb.getY();
            jb.setLocation(x,y);
            //jb.setIcon(new ImageIcon(imgRight));
        }
    }
    /**
     * Postac porusza sie w lewo
     */
    private void moveLeft()
    {
        if(this.jb.getX()>112+4)
        {
            this.x = jb.getX()-5;
            this.y = jb.getY();
            jb.setLocation(x,y);
        }
    }
    /**
     * Postac porusza sie w gore
     */
    private void moveUp()
    {
        if(jb.getY()>20+4)
        {
            this.x = jb.getX();
            this.y = jb.getY()-5;
            jb.setLocation(x,y);
        }    
    }
    
    /**
     * Postac porusza sie w dol
     */
    private void moveDown()
    {
        if(jb.getY()<525)
        {
            this.x = jb.getX();
            this.y = jb.getY()+5;
            jb.setLocation(x,y);
        }
    }    
    
    /**
     * Glowna funkcja uruchamiajaca gre
     * @param args Parametr
     */
    public static void main(String[] args) {
        // TODO code application logic here

    new Healthy_nutrition_simulation();
        
    int delay = 1000;
    int period = 1000;
    String secs = "75";
    timer = new Timer();
    interval = Integer.parseInt(secs);
    System.out.println(secs);
    timer.scheduleAtFixedRate(new TimerTask() {

        /**
         * Start czasu
         */
        @Override
        public void run() {
            System.out.println(setInterval());
             new check_time(interval);
            
        }
    }, delay, period);

    }

    /**
     * Metoda wykonywana po zakonczeniu czasu
     * @return 
     */
    private static final int setInterval() {
        
        time_counter.setText(String.valueOf(interval));

    if (interval == 1 || score <0){
        timer.cancel();
        time_counter.setText("Koniec czasu!! \n Uzyskany wynik:" +String.valueOf(score));
        time_counter.setLocation(200,260);
        time_counter.setSize(550,150);
        time_counter.setFont(new Font("Verdana", Font.PLAIN, 18));
        jb.addActionListener(e-> restart());
    }
    return --interval;
    }
    
    /**
     * Restart gry
     */
    public static void restart()
    {
       new Healthy_nutrition_simulation();
       System.exit(0);
    }
    
    /**
     * Wcisniecie klawisza
     * @param e Keyevent
     */
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean czy_start=false;
    /**
     * Po nacisnieciu klawisza wykonaj metody poruszajace postacia
     * @param e Klaiwsz event
     */
    @Override
    public void keyPressed(KeyEvent e) 
    {
        
        for(int i=0;i<8;i++){
            if_collision(UnHealthy_Image[i], i);
            if_collision_h(Healthy_Image[i], i);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            this.moveRight();
        }
        else
            if(e.getKeyCode()==KeyEvent.VK_LEFT)
                this.moveLeft();
            else
                if(e.getKeyCode()==KeyEvent.VK_UP)
                    this.moveUp();
                else
                    if(e.getKeyCode()==KeyEvent.VK_DOWN)
                        this.moveDown();
        this.setTitle("JFrame "+jb.getX()+" "+jb.getY());
    }

    /**
     * Puszczenie klawisza
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
