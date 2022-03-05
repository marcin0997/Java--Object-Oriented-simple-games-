/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findshape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Klasa glowna
 */
public class FindShape  extends JFrame
{
    public static int liczba=0;
    Random rand = new Random();

     public static JLabel zgadnij; 
    
    private static JLabel punkty_Txt;
    private static JLabel poziom_Txt;
    private static JLabel czas_Txt;
    /**
     * Licznik
     */
    private static JLabel time_counter;
    /**
     * Glowny panel gry
     */
    public JPanel okno_gry;
            
    private static int score=0;
    
    
    public static JButton[] ksztalty = new JButton[10]; 
    public static JButton[] ksztalty2 = new JButton[20]; 
    public static JButton[] ksztalty3 = new JButton[40];
    public final static Icon[] ikony = new Icon[4];
    public static int[] tabela = {0,0,0,0,0,0,0,0,0,0};
    public static int[] powtorka = {0,0,0,0,0,0,0,0,0,0};
    public static int[] powtorka2 = new int[30];
    public static int[] powtorka3 = new int[40];
    
    public static int[] tabela2;
    public static int[] tabela3;
    
    private int licznik = 0;
    public static int wylosowana = 0;
    
    public static FS2 spadanie;
    
    /**
     * Konstruktor klasy glownej
     */
    public FindShape()
    {
        this.setTitle("Find Shape");
        this.setVisible(true);
        
        okno_gry = new JPanel();
        this.add(okno_gry);
        this.setContentPane(okno_gry);
        this.getContentPane().setBackground(Color.MAGENTA);
        this.getContentPane().setPreferredSize(new Dimension(1024,768));
        okno_gry.setLayout(null);

        wylosowana = rand.nextInt(4);
        this.ikony[0] = new ImageIcon("img/a.png");
        this.ikony[1] = new ImageIcon("img/b.png");
        this.ikony[2] = new ImageIcon("img/c.png");
        this.ikony[3] = new ImageIcon("img/d.png");
        
        this.zgadnij = new JLabel("");
        zgadnij.setSize(100,100);
        zgadnij.setLocation(500,650);
        //zgadnij.setLocation(970,550);
        zgadnij.setIcon(ikony[wylosowana]);
        this.getContentPane().add(zgadnij);
        
        this.punkty_Txt = new JLabel("Punkty: 0");
        punkty_Txt.setFont(new Font("Serif", Font.BOLD, 24));
        punkty_Txt.setSize(150,50);
        punkty_Txt.setLocation(800,20);
        this.getContentPane().add(punkty_Txt);
        
        this.czas_Txt = new JLabel("Czas: 00:00:00");
        czas_Txt.setFont(new Font("Serif", Font.BOLD, 24));
        czas_Txt.setSize(150,50);
        czas_Txt.setLocation(800,50);
        this.getContentPane().add(czas_Txt);
        
        this.poziom_Txt = new JLabel("Poziom: 1");
        poziom_Txt.setFont(new Font("Serif", Font.BOLD, 24));
        poziom_Txt.setSize(150,50);
        poziom_Txt.setLocation(800,80);
        this.getContentPane().add(poziom_Txt);
        

        Icon icon = new ImageIcon("img/a.png");
    
        
        for(int i=0;i<10;i++)
        {
            if(i==0 || i==9)
            {
                this.ksztalty[i] = new JButton(ikony[wylosowana]);
            }
            else
                this.ksztalty[i] = new JButton(ikony[rand.nextInt(4)]);
            ksztalty[i].setBorder(BorderFactory.createEmptyBorder());
            ksztalty[i].setContentAreaFilled(false);
            ksztalty[i].setVisible(false);
            ksztalty[i].setLocation(0,700);
            //ksztalty[i].setLocation(0+(40*i),100+(50*i));
            //ksztalty[i].setIcon(new ImageIcon(Class.class.getResource("img/a.png")));
            ksztalty[i].setSize(150,50);
            
            this.getContentPane().add(ksztalty[i]);
        }
        
        for(int i=0;i<20;i++)
        {
            if(i==0 || i==9)
            {
                this.ksztalty2[i] = new JButton(ikony[wylosowana]);
            }
            else
                this.ksztalty2[i] = new JButton(ikony[rand.nextInt(4)]);
            ksztalty2[i].setBorder(BorderFactory.createEmptyBorder());
            ksztalty2[i].setContentAreaFilled(false);
            ksztalty2[i].setVisible(false);
            ksztalty2[i].setLocation(0,700);
            //ksztalty[i].setLocation(0+(40*i),100+(50*i));
            //ksztalty[i].setIcon(new ImageIcon(Class.class.getResource("img/a.png")));
            ksztalty2[i].setSize(150,50);
            
            this.getContentPane().add(ksztalty2[i]);
        }
        
        for(int i=0;i<40;i++)
        {
            if(i==0 || i==9)
            {
                this.ksztalty3[i] = new JButton(ikony[wylosowana]);
            }
            else
                this.ksztalty3[i] = new JButton(ikony[rand.nextInt(4)]);
            ksztalty3[i].setBorder(BorderFactory.createEmptyBorder());
            ksztalty3[i].setContentAreaFilled(false);
            ksztalty3[i].setVisible(false);
            ksztalty3[i].setLocation(0,700);
            //ksztalty[i].setLocation(0+(40*i),100+(50*i));
            //ksztalty[i].setIcon(new ImageIcon(Class.class.getResource("img/a.png")));
            ksztalty3[i].setSize(150,50);
            
            this.getContentPane().add(ksztalty3[i]);
        }
     
        JLabel oddziel = new JLabel("");
        oddziel.setSize(1034,200);
        oddziel.setLocation(0,630);
        oddziel.setBackground(Color.WHITE);
        oddziel.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        oddziel.setBorder(border);
        this.getContentPane().add(oddziel);
            
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        tabela2 = new int[20];
        for(int i=0;i<20;i++){
            tabela2[i]=0;
            powtorka2[i]=0;
        }
        tabela3 = new int[40];
        for(int i=0;i<40;i++){
            tabela3[i]=0;
            powtorka3[i]=0;
        }
  
    }
    
    
    /**
     * Poziom 1
     */
    public static void level1()
    {
        int i=0;
        for(i=0;i<10;i++)
        {
            Icon pasuje = ksztalty[i].getIcon();
            JButton przycisk = ksztalty[i];
            ksztalty[i].addActionListener(e->{
            if(pasuje == zgadnij.getIcon())
            {
                przycisk.setVisible(false);
                score++;
            }
            else
            {
                przycisk.setVisible(false);
                score--;
            }
            punkty_Txt.setText("Punkty: "+score);
            });  
        }
        
        spadanie = new FS2();
        for(int j=0;j<4;j++)
        {
            do{
                new FS2();
            }while(tabela[liczba]!=0);
            tabela[liczba]=1;

            ksztalty[liczba].setVisible(true);
            spadanie.losuj_przycisk(ksztalty[liczba]);
        }
        
        Timer timer = new Timer();
        
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 20;

            public void run() {
                System.out.println(i);
                i--;
                
                if(i%3==0 && i>10)
                {
                    do{
                        new FS2();
                    }while(tabela[liczba]!=0);
                    tabela[liczba]=1;
                    System.out.print(liczba);

                    ksztalty[liczba].setVisible(true);
                    spadanie.losuj_przycisk(ksztalty[liczba]);

                }

                if (i < 0) {
                   timer.cancel();
                   punkty_Txt.setText("Koniec");
                   
                }
            }
        }, 0, 1000);
        
        
        Timer timer2 = new Timer();
        
        timer2.scheduleAtFixedRate(new TimerTask() {
            int j = 20;

            public void run() {
                //System.out.println(i);
                j--;
                
                for(int k=0;k<10;k++)
                {
                    if(ksztalty[k].isVisible())
                    {
                        spadanie.spadanie(ksztalty[k]);
                    }
                    
                    if(ksztalty[k].getLocation().y >550 && ksztalty[k].isVisible())
                    {
                        //int asdf = spadanie.rand.nextInt(4);
                        //ksztalty[k].setIcon(ikony[asdf]);
                        spadanie.losuj_przycisk(ksztalty[k]);
                        powtorka[k]++;
                        if(powtorka[k]>2)
                            ksztalty[k].setVisible(false);
                        if(ksztalty[k].getIcon() == zgadnij.getIcon())
                        {
                            //score--;
                            punkty_Txt.setText("Punkty: "+score);
                        }
                    }
                    
                }

                if (j < 0) {
                   //timer2.cancel();
                   
                }
                if(punkty_Txt.getText().equals("Koniec"))
                {
                    timer2.cancel();
                    for(int z=0;z<10;z++)
                    {
                        ksztalty[z].setVisible(false);
                        ksztalty[z].setLocation(0,700);
                    }
                    zgadnij.setIcon(ikony[2]);
                    Timer timer3 = new Timer();
        
                    timer3.scheduleAtFixedRate(new TimerTask() {
                        int p = 2;

                        public void run() {
                            p--;
                            if (p < 0) {
                                timer3.cancel();
                                punkty_Txt.setText("Punkty: "+String.valueOf(score));
                                level2();
                            }
                        }
                        }, 0, 1000);
                    
                }
            }
        }, 0, 20);
    }
    
    
    /**
     * Poziom 2
     */
    public static void level2()
    {
        int i=0;
        for(i=0;i<20;i++)
        {
            Icon pasuje = ksztalty2[i].getIcon();
            JButton przycisk = ksztalty2[i];
            ksztalty2[i].addActionListener(e->{
            if(pasuje == zgadnij.getIcon())
            {
                //System.out.print("out");
                
                przycisk.setVisible(false);
                score++;
            }
            else
            {
                przycisk.setVisible(false);
                score--;
            }
            punkty_Txt.setText("Punkty: "+score);
            });  
        }
        
        spadanie = new FS2();
        for(int j=0;j<2;j++)
        {
            do{
                new FS2();
            }while(tabela2[liczba]!=0);
            tabela2[liczba]=1;
            //System.out.print(liczba);

            

            ksztalty2[liczba].setVisible(true);
            spadanie.losuj_przycisk(ksztalty2[liczba]);
        }
        
        Timer timer0 = new Timer();
        
        timer0.scheduleAtFixedRate(new TimerTask() {
            int i = 30;

            public void run() {
                System.out.println(i);
                i--;
                
                if(i%3==0 && i>7)
                {
                    do{
                        new FS2();
                    }while(tabela2[liczba]!=0);
                    tabela2[liczba]=1;
                    //System.out.print(liczba);

                    ksztalty2[liczba].setVisible(true);
                    spadanie.losuj_przycisk(ksztalty2[liczba]);

                }

                if (i < 0) {
                   timer0.cancel();
                   punkty_Txt.setText("Koniec");
                   
                }
            }
        }, 0, 1000);
        
        
        Timer timer2 = new Timer();
        
        timer2.scheduleAtFixedRate(new TimerTask() {
            int j = 20;

            public void run() {
                //System.out.println(i);
                j--;
                
                for(int k=0;k<20;k++)
                {
                    if(ksztalty2[k].isVisible())
                    {
                        spadanie.spadanie(ksztalty2[k]);
                    }
                    
                    if(ksztalty2[k].getLocation().y >550 && ksztalty2[k].isVisible())
                    {
                        //int asdf = spadanie.rand.nextInt(4);
                        //ksztalty[k].setIcon(ikony[asdf]);
                        spadanie.losuj_przycisk(ksztalty2[k]);
                        powtorka2[k]++;
                        if(powtorka2[k]>2)
                            ksztalty2[k].setVisible(false);
                        if(ksztalty2[k].getIcon() == zgadnij.getIcon())
                        {
                            //score--;
                            punkty_Txt.setText("Punkty: "+score);
                        }
                    }
                    
                }

                if (j < 0) {
                   //timer2.cancel();
                   
                }
                 if(punkty_Txt.getText().equals("Koniec"))
                {
                    timer2.cancel();
                    for(int z=0;z<10;z++)
                    {
                        ksztalty2[z].setVisible(false);
                        ksztalty2[z].setLocation(0,700);
                    }
                    zgadnij.setIcon(ikony[3]);
                    Timer timer3 = new Timer();
        
                    timer3.scheduleAtFixedRate(new TimerTask() {
                        int p = 5;

                        public void run() {
                            p--;
                            if (p < 0) {
                                timer3.cancel();
                                level3();
                            }
                        }
                        }, 0, 1000);
                    
                }
            }
        }, 0, 20);
    }
    
    /**
     * Poziom 3
     */
    public static void level3()
    {
        int i=0;
        for(i=0;i<20;i++)
        {
            Icon pasuje = ksztalty3[i].getIcon();
            JButton przycisk = ksztalty3[i];
            ksztalty3[i].addActionListener(e->{
            if(pasuje == zgadnij.getIcon())
            {
                //System.out.print("out");
                
                przycisk.setVisible(false);
                score++;
            }
            else
            {
                przycisk.setVisible(false);
                score--;
            }
            punkty_Txt.setText("Punkty: "+score);
            });  
        }
        
        spadanie = new FS2();
        for(int j=0;j<2;j++)
        {
            do{
                new FS2();
            }while(tabela3[liczba]!=0);
            tabela3[liczba]=1;
            //System.out.print(liczba);

            ksztalty3[liczba].setVisible(true);
            spadanie.losuj_przycisk(ksztalty3[liczba]);
        }
        
        Timer timer0 = new Timer();
        
        timer0.scheduleAtFixedRate(new TimerTask() {
            int i = 30;

            public void run() {
                System.out.println(i);
                i--;
                
                if(i%2==0 && i>7)
                {
                    do{
                        new FS2();
                    }while(tabela2[liczba]!=0);
                    tabela2[liczba]=1;
                    //System.out.print(liczba);

                    ksztalty3[liczba].setVisible(true);
                    spadanie.losuj_przycisk(ksztalty3[liczba]);

                }

                if (i < 0) {
                   timer0.cancel();
                   poziom_Txt.setText("Koniec");
                   
                }
            }
        }, 0, 1000);
        
        
        Timer timer20 = new Timer();
        
        timer20.scheduleAtFixedRate(new TimerTask() {
            int j = 20;

            public void run() {
                //System.out.println(i);
                j--;
                
                for(int k=0;k<10;k++)
                {
                    if(ksztalty3[k].isVisible())
                    {
                        spadanie.spadanie(ksztalty3[k]);
                    }
                    
                    if(ksztalty3[k].getLocation().y >550 && ksztalty3[k].isVisible())
                    {
                        //int asdf = spadanie.rand.nextInt(4);
                        //ksztalty[k].setIcon(ikony[asdf]);
                        spadanie.losuj_przycisk(ksztalty3[k]);
                        powtorka3[k]++;
                        if(powtorka3[k]>2)
                            ksztalty3[k].setVisible(false);
                        if(ksztalty3[k].getIcon() == zgadnij.getIcon())
                        {
                            //score--;
                            punkty_Txt.setText("Punkty: "+score);
                        }
                    }
                    
                }

                if (j < 0) {
                   //timer2.cancel();
                   
                }
                 if(poziom_Txt.getText().equals("Koniec"))
                {
                    timer20.cancel();
                    for(int z=0;z<10;z++)
                    {
                        ksztalty3[z].setVisible(false);
                        ksztalty3[z].setLocation(0,700);
                    }
                    zgadnij.setIcon(ikony[2]);
                    Timer timer30 = new Timer();
        
                    timer30.scheduleAtFixedRate(new TimerTask() {
                        int p = 5;

                        public void run() {
                            p--;
                            if (p < 0) {
                                timer30.cancel();
                                czas_Txt.setText("Koniec");
                                
                                //level3();
                            }
                        }
                        }, 0, 1000);
                    
                }
            }
        }, 0, 20);
    }
    
    /**
     * Glowna metoda
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new FindShape();
        
        level1();
        
        Timer timer10 = new Timer();
        timer10.scheduleAtFixedRate(new TimerTask() {
            int j = 0;

            public void run() {
                //System.out.println(i);
                j++;
                
                czas_Txt.setText("Czas: 00:"+j+":00");
                
                if (j < 0) {
                   //timer2.cancel();
                   
                }
                
                if(czas_Txt.getText().equals("Koniec"))
                {
                    timer10.cancel();
                }
            }
        }, 0, 1000);
    }
    
    
    
}
