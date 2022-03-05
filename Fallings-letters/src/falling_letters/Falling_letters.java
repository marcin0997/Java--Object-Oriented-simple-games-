/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package falling_letters;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import static java.lang.Math.random;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * Klasa glowna
 * @author Marcin Kubiak
 */
public class Falling_letters extends JFrame implements KeyListener {

    /**
     * @param args the command line arguments
     */
    /**
     * Zmienna z literami
     */
    public static int a=2;
    /**
     * Punkty zdobyte w grze
     */
    public static int punkty=0;
    /**
     * Zmienna do wykonywania ruchu gracze
     */
    public int x; 
    /**
     * Zmienna do wykonywania ruchu gracze
     */
    public int y;
    /**
     * Poziom gracza. Gra dysponuje 4 poziomami
     */
    public static int poziom=1;
    /**
     * Menu wyswietlajace przycisk Start i Koniec
     */
    public static JButton Start;
    /**
     * Menu wyswietlajace przycisk Start i Koniec
     */
    public static JButton Koniec;
    /**
     * Gracz
     */
    public static JLabel gracz; 
    /**
     * Punkty w formie Labela
     */
    public static JLabel punkty_Txt;
    /**
     * Okno glowne gry
     */
    public static JPanel okno_gry;
    /**
     * Przycisk Menu
     */
    public static JButton Menu;
    /**
     * Label slowa wyswietlanego na ekran
     */
    public static JLabel slowo;
    /**
     * Ikona koszyka
     */
    public String ikona = "img/koszyk.png";
    /**
     * Zdjecie tla
     */
    public Image background;
    /**
     * Zmienna ustawiajaca napis w zaleznosci od dlugosci slowa (aby wpasowywal sie w ramke)
     */
    public int lokalizacja;
    /**
     * Slowa predefiniowane dostepne w grze
     */
    public static String[] slowa = {"OSA", "PLAMA", "KOSZYK", "BETONIARKA"};
    
    //public static String[] slowo_lvl1 = {"O", "S", "A"};
    /**
     * Tablica przechowujaca indeksy liter ze slowa do zebrania poziom 1
     */
    public static int[] slowo_lvl1_id = {14, 18, 0};
    //public static String[] slowo_lvl2 = {"P", "L", "A", "M"};
    /**
     * Tablica przechowujaca indeksy liter ze slowa do zebrania poziom 2
     */
    public static int[] slowo_lvl2_id = {15, 11, 0, 12};
    //public static String[] slowo_lvl3 = {"K", "O", "S", "Z", "Y", "K"};
    /**
     * Tablica przechowujaca indeksy liter ze slowa do zebrania poziom 3
     */
    public static int[] slowo_lvl3_id = {10, 14, 18, 25, 24};
    //public static String[] slowo_lvl4 = {"B", "E", "T", "O", "N", "I", "A", "R", "K"};
    /**
     * Tablica przechowujaca indeksy liter ze slowa do zebrania poziom 4
     */
    public static int[] slowo_lvl4_id = {1, 4, 19, 14, 13, 8, 0, 17, 10};
    
    /**
     * Funkcja odwolujaca sie do klasy i pobierajaca dane z metod
    */
    public static Losuj_literke litere; 
    /**
     * Tablica przechowujaca i sterujaca wszystkimi spadajacymi literami
     */
    public static JLabel[] aktualna = new JLabel[26];
    /**
     * Zmienna do okreslenia polozenia slowa w zaleznosci od dlugosci
     */
    public static int ktore_slowo=0;
    
    /**
     * Zmienna przechowujaca aktualnie odkryte slowo
     */
    public static String odkryte="";
    /**
     * Zmienna sluzaca do informacji czy uzytkownik zakonczyl pomyslnie poziom
     */
    static boolean zmiana_poziomu = false;
    
    /**
     * Tablica przechowujaca litery, ktore nie wchodza w sklad slowa
     */
    public static int[] losuj_litery_zle;
    /**
     * Funkcja losujaca pseudolosowo
     */
    Random random = new Random();
    
    /**
     * Konstruktor klasy glownej
     */
    public Falling_letters()
    {
        this.setTitle("Poziom 1");
        this.setVisible(true);
        this.addKeyListener(this);
        
        okno_gry = new JPanel();
        this.add(okno_gry);
        this.setContentPane(okno_gry);
        this.getContentPane().setBackground(Color.red);
        this.getContentPane().setPreferredSize(new Dimension(1024,768));
        okno_gry.setLayout(null);
        
        this.Menu = new JButton("MENU");
        Menu.setSize(150,50);
        Menu.setLocation(90,20);
        this.getContentPane().add(Menu);
        
        
        
        this.slowo = new JLabel(slowa[0]);
        slowo.setSize(500,80);
        ktore_slowo = 0;
        lokalizacja = 425+6*(10-slowa[ktore_slowo].length());
        slowo.setLocation(lokalizacja,10);
        slowo.setBackground(Color.CYAN);
        slowo.setFont(new Font("Serif", Font.BOLD, 24));
        this.getContentPane().add(slowo);
        
        JLabel ramka = new JLabel("");
        ramka.setSize(200,80);
        ramka.setLocation(400,10);
        ramka.setIcon(new ImageIcon("img/ramka.png"));
        this.getContentPane().add(ramka);
        
        this.punkty_Txt = new JLabel("PUNKTY: 0");
        punkty_Txt.setFont(new Font("Serif", Font.BOLD, 24));
        punkty_Txt.setSize(150,50);
        punkty_Txt.setLocation(800,20);
        this.getContentPane().add(punkty_Txt);
        
        
        this.gracz = new JLabel("");
        gracz.setSize(100,100);
        gracz.setLocation(830,700);
        //gracz.setBackground(Color.black);
        gracz.setIcon(new ImageIcon(ikona));
        this.getContentPane().add(gracz);
        
        litere = new Losuj_literke();
        char znak = 'A';
        for(int i=0;i<26;i++)
        {
            aktualna[i] = litere.los(znak, 0, 0);
            aktualna[i].setVisible(false);
            znak++;
            litere.losuj_pozycje(aktualna[i]);
        }
        
        Start = new JButton("START");
        Start.setSize(150,50);
        Start.setLocation(350,200);
        this.getContentPane().add(Start);
        Start.setVisible(false);
        
        Koniec = new JButton("KONIEC");
        Koniec.setSize(150,50);
        Koniec.setLocation(350,500);
        this.getContentPane().add(Koniec);
        Koniec.setVisible(false);
        
        JLabel panel_menu = new JLabel("");
        panel_menu.setSize(1034,100);
        panel_menu.setLocation(0,0);
        Color lightblue = new Color(168,255,255);
        panel_menu.setBackground(lightblue);
        panel_menu.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        panel_menu.setBorder(border);
        this.getContentPane().add(panel_menu);
       
        
        litere.losuj_pozycje(aktualna[0]);
        
        ImageIcon icon = new ImageIcon("img/bg.png");
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        thumb.setLocation(0,0);
        thumb.setSize(1100,800);
        this.getContentPane().add(thumb);
     
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        int b;
        losuj_litery_zle = new int[26];
        for(int i=0;i<26;i++)
        {
            b = random.nextInt(26);
            while(b==0 && b==14 && b==18)
            {
                b=random.nextInt(26);
            }
            losuj_litery_zle[i] = b;
        }
    }
    
    /**
     * Metoda statyczna pojawiajaca nowa litere na planszy
     * @param litera Litera ktora ma sie pojawic (zwykle przekazywana losowo)
     */
    public static void pojaw_litere(int litera)
    {
        litere.losuj_pozycje(aktualna[litera]);
        aktualna[litera].setVisible(true); 
    }
    
    /**
     * Metoda odpowiadajaca za pobieranie ruchu od klasy Losuj_literke i przekazujaca szybkosc spadania litery
     * @param litera Dana litera, ktora zostala wylosowana i jest widoczna na ekranie
     */
    public static void spadanie(int litera)
    {
        litere.spadnij_literke(aktualna[litera]);
    }
    
    /**
     * Metoda glowna gry
     * @param args Staly parametr
     */
    public static void main(String[] args) {
      
        new Falling_letters();  
        
        
        pojaw_litere(slowo_lvl1_id[0]);
        
        Timer timer2 = new Timer();
        
        Menu.addActionListener(e->{
            new Falling_letters(); 
            Start.setVisible(true);
            Koniec.setVisible(true);
            timer2.cancel();
        });
        
        Koniec.addActionListener(e->{
            System.exit(1);
        });
        
        Start.addActionListener(e->{
            
            Start.setVisible(false);
            Koniec.setVisible(false);
            new Falling_letters(); 
            
        });

        
        
        timer2.scheduleAtFixedRate(new TimerTask() {
            int i = 5;

            public void run() {
                System.out.println(i);
                i--;

                if (i < 0) {
                   timer2.cancel();
                   slowo.setVisible(false);
                   start_gry();
                }
            }
        }, 0, 1000);
        
        
    }
    
    

/**
 * Metoda odpowiedzialna za poziom 3
 */    
    public static void start_gry3()
    {
         pojaw_litere(slowo_lvl3_id[0]);
         Timer timer5 = new Timer();
        timer5.scheduleAtFixedRate(new TimerTask() {
            int i = 10000;

            public void run() {
                
                
                    if(i <9990 && i>9988)
                    {
                        pojaw_litere(0);
                        pojaw_litere(22);
                    }

                    if(i<9900 && aktualna[0].isVisible())
                        spadanie(0);

                    if(i<9950 && aktualna[22].isVisible())
                        spadanie(22);
                    if(i%180==0)
                    {
                        pojaw_litere(losuj_litery_zle[a]);
                        a++;
                    }

                        if(odkryte.equals("KOSZYK"))
                        {
                            timer5.cancel();
                            poziom=4;
                            odkryte="";
                            zmiana_poziomu=true;
                        }
                        
                        if(aktualna[13].getLocation().y >750)
                        {
                           pojaw_litere(losuj_litery_zle[0]);
                           aktualna[13].setLocation(0,0);
                           aktualna[13].setVisible(false);
                        }
                if(aktualna[20].getLocation().y >750)
                {
                   pojaw_litere(losuj_litery_zle[1]);
                   aktualna[20].setLocation(0,0);
                   aktualna[20].setVisible(false);
                }

                if(aktualna[slowo_lvl3_id[0]].getLocation().y>730)
                {
                    pojaw_litere(slowo_lvl3_id[0]);
                }
                if(aktualna[slowo_lvl3_id[1]].getLocation().y>730)
                {
                    pojaw_litere(slowo_lvl3_id[1]);
                }
                if((aktualna[slowo_lvl3_id[2]].getLocation().y>730) || (!aktualna[slowo_lvl3_id[2]].isVisible() && i<6800 && odkryte.equals("K O _ _ _ K")))
                {
                    pojaw_litere(slowo_lvl3_id[2]);
                }
                if((aktualna[slowo_lvl3_id[3]].getLocation().y>730) || (!aktualna[slowo_lvl3_id[3]].isVisible() && i<6700 && odkryte.equals("K O S _ _ K")))
                {
                    pojaw_litere(slowo_lvl3_id[3]);
                }
                if((aktualna[slowo_lvl3_id[4]].getLocation().y>730) || (!aktualna[slowo_lvl3_id[4]].isVisible() && i<6700 && odkryte.equals("K O S Z _ K")))
                {
                    pojaw_litere(slowo_lvl3_id[4]);
                }
                
                for(int i=0;i<26;i++)
                {
                    if(aktualna[i].getLocation().y>750)
                    {
                        aktualna[i].setVisible(false);
                    }
                    
                    
                    if(aktualna[i].isVisible())
                    {
                        spadanie(i);
                    }
                    if(aktualna[i].getLocation().y>800 && !aktualna[i].isVisible())
                        pojaw_litere(losuj_litery_zle[i]);
                }
                
                
                if(poziom==4 && zmiana_poziomu)
                    {
                        timer5.cancel();
                        Timer timer2 = new Timer();
                        
                        for(int k=0;k<26;k++)
                        {
                            aktualna[k].setLocation(0,0);
                            aktualna[k].setVisible(false);
                        }
                        
                        slowo.setText(slowa[3]);
                        slowo.setVisible(true);
                        slowo.setLocation(slowo.getLocation().x-15, slowo.getLocation().y);
                        timer2.scheduleAtFixedRate(new TimerTask() {
                        int i = 5;
                        

                        public void run() {
                            System.out.println(i);
                            i--;

                            if (i < 0) {
                               timer2.cancel();
                               zmiana_poziomu=false;
                               slowo.setVisible(false);
                               start_gry4();
                            }
                        }
                    }, 0, 1000);


                    }
                
                if(a>=24)
                   a=0;
                i--;
                sprawdz_kolizje2();
                System.out.println(i);

                if(i==10001)
                    timer5.cancel();
                if (i < 0) {
                    i=10000;
                  //  timer.cancel();
                }
                
                
            }
        }, 0, 15);
                
    }

    /**
     * Metoda odpowiedzialna za poziom 4
     */
    public static void start_gry4()
    {
         pojaw_litere(slowo_lvl4_id[0]);
         Timer timer5 = new Timer();
        timer5.scheduleAtFixedRate(new TimerTask() {
            int i = 10000;

            public void run() {
                
                
                    if(i <9990 && i>9988)
                    {
                        pojaw_litere(0);
                        pojaw_litere(22);
                    }

                    if(i<9900 && aktualna[0].isVisible())
                        spadanie(0);

                    if(i<9950 && aktualna[22].isVisible())
                        spadanie(22);
                    if(i%150==0)
                    {
                        pojaw_litere(losuj_litery_zle[a]);
                        a++;
                    }

                        if(odkryte.equals("BETONIARKA"))
                        {
                            timer5.cancel();
                            poziom=5;
                            zmiana_poziomu=true;
                        }
                        
                        if(aktualna[13].getLocation().y >750)
                        {
                           pojaw_litere(losuj_litery_zle[0]);
                           aktualna[13].setLocation(0,0);
                           aktualna[13].setVisible(false);
                        }
                if(aktualna[20].getLocation().y >750)
                {
                   pojaw_litere(losuj_litery_zle[1]);
                   aktualna[20].setLocation(0,0);
                   aktualna[20].setVisible(false);
                }

                if(aktualna[slowo_lvl4_id[0]].getLocation().y>730)
                {
                    pojaw_litere(slowo_lvl4_id[0]);
                }
                if(aktualna[slowo_lvl4_id[1]].getLocation().y>730)
                {
                    pojaw_litere(slowo_lvl4_id[1]);
                }
                if((aktualna[slowo_lvl4_id[2]].getLocation().y>730) || (!aktualna[slowo_lvl4_id[2]].isVisible() && i<9000 && odkryte.equals("BE")))
                {
                    pojaw_litere(slowo_lvl4_id[2]);
                }
                if((aktualna[slowo_lvl4_id[3]].getLocation().y>730) || (!aktualna[slowo_lvl4_id[3]].isVisible() && i<8500 && odkryte.equals("BET")))
                {
                    pojaw_litere(slowo_lvl4_id[3]);
                }
                if((aktualna[slowo_lvl4_id[4]].getLocation().y>730) || (!aktualna[slowo_lvl4_id[4]].isVisible() && i<8000 && odkryte.equals("BETO")))
                {
                    pojaw_litere(slowo_lvl4_id[4]);
                }
                if((aktualna[slowo_lvl4_id[5]].getLocation().y>730) || (!aktualna[slowo_lvl4_id[5]].isVisible() && i<8000 && odkryte.equals("BETON")))
                {
                    pojaw_litere(slowo_lvl4_id[5]);
                }
                if((aktualna[slowo_lvl4_id[6]].getLocation().y>730) || (!aktualna[slowo_lvl4_id[7]].isVisible() && i<8000 && odkryte.equals("BETONI")))
                {
                    pojaw_litere(slowo_lvl4_id[6]);
                }
                
                if(!aktualna[slowo_lvl4_id[7]].isVisible() && i<8000)
                {
                    pojaw_litere(slowo_lvl4_id[7]);
                }
                
                if((aktualna[slowo_lvl4_id[7]].getLocation().y>730) || (!aktualna[slowo_lvl4_id[7]].isVisible() && i<8000 && odkryte.equals("BETONIA _ _ A")))
                {
                    pojaw_litere(slowo_lvl4_id[7]);
                }
                
                for(int i=0;i<26;i++)
                {
                    if(aktualna[i].getLocation().y>750)
                    {
                        aktualna[i].setVisible(false);
                    }
                    
                    
                    if(aktualna[i].isVisible())
                    {
                        spadanie(i);
                    }
                    if(aktualna[i].getLocation().y>800 && !aktualna[i].isVisible())
                        pojaw_litere(losuj_litery_zle[i]);
                }
                
                
                if(poziom==5 && zmiana_poziomu)
                    {
                        timer5.cancel();
                        Timer timer2 = new Timer();
                        
                        for(int k=0;k<26;k++)
                        {
                            aktualna[k].setLocation(0,0);
                            aktualna[k].setVisible(false);
                        }
                        
                        slowo.setText(slowa[3]);
                        timer2.scheduleAtFixedRate(new TimerTask() {
                        int i = 5;
                        

                        public void run() {
                            System.out.println(i);
                            i--;

                            if (i < 0) {
                               timer2.cancel();
                               zmiana_poziomu=false;
                               slowo.setText("KONIEC GRY");
                            }
                        }
                    }, 0, 1000);


                    }
                
                if(a>=24)
                   a=0;
                i--;
                sprawdz_kolizje2();
                System.out.println(i);

                if(i==10001)
                    timer5.cancel();
                if (i < 0) {
                    i=10000;
                  //  timer.cancel();
                }
                
                
            }
        }, 0, 15);
                
    }
    
    /**
     * Metoda odpowiedzialna za poziom 2
     */
    public static void start_gry2()
    {
        pojaw_litere(15);
        Timer timer3 = new Timer();
        timer3.scheduleAtFixedRate(new TimerTask() {
            int i = 10000;

            public void run() {
                
                
                    if(i <9990 && i>9988)
                    {
                        pojaw_litere(21);
                        pojaw_litere(9);
                    }

                    if(i<9900 && aktualna[21].isVisible())
                        spadanie(20);

                    if(i<9950 && aktualna[9].isVisible())
                        spadanie(13);
                    if(i%200==0)
                    {
                        pojaw_litere(losuj_litery_zle[a]);
                        a++;
                    }
                    
                    if(odkryte.equals("PLA_A") && i<5400 && !aktualna[slowo_lvl2_id[3]].isVisible())
                    {
                        pojaw_litere(slowo_lvl2_id[3]);
                    }

                        if(odkryte.equals("PLAMA"))
                        {
                            timer3.cancel();
                            poziom=3;
                            odkryte="";
                            zmiana_poziomu=true;
                        }
                        
                        if(aktualna[13].getLocation().y >750)
                {
                   pojaw_litere(losuj_litery_zle[0]);
                   aktualna[13].setLocation(0,0);
                   aktualna[13].setVisible(false);
                }
                if(aktualna[20].getLocation().y >750)
                {
                   pojaw_litere(losuj_litery_zle[1]);
                   aktualna[20].setLocation(0,0);
                   aktualna[20].setVisible(false);
                }

                if(aktualna[slowo_lvl2_id[0]].getLocation().y>730)
                {
                    pojaw_litere(slowo_lvl2_id[0]);
                }
                if(aktualna[slowo_lvl2_id[1]].getLocation().y>730)
                {
                    pojaw_litere(slowo_lvl2_id[1]);
                }
                if(aktualna[slowo_lvl2_id[2]].getLocation().y>730)
                {
                    pojaw_litere(slowo_lvl2_id[2]);
                }
                if(aktualna[slowo_lvl2_id[3]].getLocation().y>730)
                {
                    pojaw_litere(slowo_lvl2_id[3]);
                }
                
                for(int i=0;i<26;i++)
                {
                    if(aktualna[i].getLocation().y>750)
                    {
                        aktualna[i].setVisible(false);
                    }
                    
                    
                    if(aktualna[i].isVisible())
                    {
                        spadanie(i);
                    }
                    if(aktualna[i].getLocation().y>800 && !aktualna[i].isVisible())
                        pojaw_litere(losuj_litery_zle[i]);
                }
                
                
                if(poziom==3 && zmiana_poziomu)
                    {
                        timer3.cancel();
                        Timer timer2 = new Timer();
                        
                        for(int k=0;k<26;k++)
                        {
                            aktualna[k].setLocation(0,0);
                            aktualna[k].setVisible(false);
                        }
                        
                        slowo.setText(slowa[2]);
                        slowo.setLocation(slowo.getLocation().x-15, slowo.getLocation().y);
                        timer2.scheduleAtFixedRate(new TimerTask() {
                        int i = 5;
                        

                        public void run() {
                            System.out.println(i);
                            i--;

                            if (i < 0) {
                               timer2.cancel();
                               zmiana_poziomu=false;
                               slowo.setVisible(false);
                               start_gry3();
                            }
                        }
                    }, 0, 1000);


                    }
                
                i--;
                sprawdz_kolizje2();
                System.out.println(i);
                if(a>=24)
                   a=0;

                if(i==10001)
                    timer3.cancel();
                if (i < 0) {
                    i=10000;
                  //  timer.cancel();
                }
                
                
            }
        }, 0, 15);
    }
    
    /**
     * Metoda odpowiedzialna za poziom 1
     */
    public static void start_gry()
    {
        Timer timer = new Timer();

 
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 10000;

            public void run() {

                
                if(poziom==1)
                {
                    if(i <9990 && i>9988)
                    {
                        pojaw_litere(15);
                        pojaw_litere(12);
                    }

                    if(i<9900 && aktualna[20].isVisible())
                        spadanie(20);

                    if(i<9950 && aktualna[13].isVisible())
                        spadanie(13);
                    if(i%250==0)
                    {
                        pojaw_litere(losuj_litery_zle[a]);
                        a++;
                    }

                        if(odkryte.equals("OSA"))
                        {
                            timer.cancel();
                            odkryte="";
                            poziom=2;
                            zmiana_poziomu=true;
                            
                        }
                        
                        if(aktualna[13].getLocation().y >750)
                {
                   pojaw_litere(losuj_litery_zle[0]);
                   aktualna[13].setLocation(0,0);
                   aktualna[13].setVisible(false);
                }
                if(aktualna[20].getLocation().y >750)
                {
                   pojaw_litere(losuj_litery_zle[1]);
                   aktualna[20].setLocation(0,0);
                   aktualna[20].setVisible(false);
                }

                if(aktualna[14].getLocation().y>730)
                {
                    pojaw_litere(14);
                }
                
                for(int i=0;i<26;i++)
                {
                    if(aktualna[i].getLocation().y>750)
                    {
                        aktualna[i].setVisible(false);
                    }
                    
                    
                    if(aktualna[i].isVisible())
                    {
                        spadanie(i);
                    }
                    if(aktualna[i].getLocation().y>800 && !aktualna[i].isVisible())
                        pojaw_litere(losuj_litery_zle[i]);
                }
                }
                
                
               
                    if(poziom==2 && zmiana_poziomu)
                    {
                        timer.cancel();
                        Timer timer2 = new Timer();
                        
                        for(int k=0;k<26;k++)
                        {
                            aktualna[k].setLocation(0,0);
                            aktualna[k].setVisible(false);
                        }
                        
                        slowo.setText(slowa[1]);
                        timer2.scheduleAtFixedRate(new TimerTask() {
                        int i = 3;
                        

                        public void run() {
                            System.out.println(i);
                            i--;

                            if (i < 0) {
                               timer2.cancel();
                               zmiana_poziomu=false;
                               slowo.setVisible(false);
                               start_gry2();
                            }
                        }
                    }, 0, 1000);


                    }
               
                
               
                if(a>=24)
                   a=0;
                i--;
                sprawdz_kolizje();
                System.out.println(i);

                if(i==10001)
                    timer.cancel();
                if (i < 0) {
                    i=10000;
                  //  timer.cancel();
                }
            }
        }, 0, 15);
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metoda odpowiedzialna za poruszanie postacia
     * @param e Gdy wcisnieto klawisz strzalki
     */
    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if(gracz.getX()<930)
            {
                x = gracz.getX()+10;
                y = gracz.getY();
                gracz.setLocation(x,y);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(gracz.getX()>0)
            {
                x = gracz.getX()-10;
                y = gracz.getY();
                gracz.setLocation(x,y);
            }
        }
        
        
    }

    /**
     * Dla pierwszego poziomu (1) kontrola kolizji
     */
    public static void sprawdz_kolizje()
    {
        int g_x = gracz.getLocation().x;
        int g_y = gracz.getLocation().y;
        
        for (int i=0;i<26;i++)
        {
            if(aktualna[i].isVisible() && aktualna[i].getLocation().y >= 500)
            {
                int a_x = aktualna[i].getLocation().x;
                int a_y = aktualna[i].getLocation().y;
                if(Math.abs(g_x-a_x) <= 80 && Math.abs(g_y-a_y) <=15)
                {
                    //aktualna[i].setVisible(false);
                    aktualna[i].setVisible(false);
                    aktualna[i].setLocation(0,0);
                    
                    
                    if(i==14){
                        slowo.setVisible(true);
                        odkryte="O";
                        slowo.setText(odkryte);
                        aktualna[18].setVisible(true);
                        litere.losuj_pozycje(aktualna[0]);
                        punkty++;
                    }
                    if(i==18){
                        odkryte+="S";
                        slowo.setText(odkryte);
                        aktualna[0].setVisible(true);
                        litere.losuj_pozycje(aktualna[18]);
                        punkty++;
                    }
                    if(i==0)
                    {
                        odkryte="OSA";
                        slowo.setText(odkryte);
                        //new Poziom2();
                        a=0;
                        losuj_litery_zle = litere.losuj_zle();
                        punkty+=10;
                    }
                        
                    
                    if(i!=0 && i!=14 && i!= 18)
                    {
                        pojaw_litere(losuj_litery_zle[a]);
                        a++;
                        punkty--;
                    }
                    
                    
                    
                    System.out.println("PKT: "+punkty);
                }
            }
        }
        
        
                    punkty_Txt.setText("PUNKTY: "+String.valueOf(punkty));
        
    }
    
    
    /**
     * Kontrola zlapania literki przez koszyk i odpowiednio dodanie lub odjecie punktu i uzupelnienie slowa w ramce o zlapana literke
     */
    public static void sprawdz_kolizje2()
    {
        int g_x = gracz.getLocation().x;
        int g_y = gracz.getLocation().y;
        
        for (int i=0;i<26;i++)
        {
            if(aktualna[i].isVisible() && aktualna[i].getLocation().y >= 500)
            {
                int a_x = aktualna[i].getLocation().x;
                int a_y = aktualna[i].getLocation().y;
                if(Math.abs(g_x-a_x) <= 80 && Math.abs(g_y-a_y) <=15)
                {
                    //aktualna[i].setVisible(false);
                    aktualna[i].setVisible(false);
                    aktualna[i].setLocation(0,0);
                    
                    if(poziom==2)
                    {
                        if(i==slowo_lvl2_id[0]){
                            slowo.setVisible(true);
                            if(odkryte.equals(""))
                                odkryte="P";
                            
                            slowo.setText(odkryte);
                            aktualna[slowo_lvl2_id[1]].setVisible(true);
                            litere.losuj_pozycje(aktualna[0]);
                            punkty++;
                        }
                        if(i==slowo_lvl2_id[1]){
                            if(odkryte.equals("P"))
                                odkryte+="L";
                            slowo.setText(odkryte);
                            aktualna[0].setVisible(true);
                            litere.losuj_pozycje(aktualna[18]);
                            punkty++;
                        }
                        if(i==slowo_lvl2_id[2])
                        {
                         if(odkryte.equals("PL"))   
                            odkryte+="A_A";
                            slowo.setText(odkryte);
                            punkty++;
                            //new Poziom2();
                        }
                        if(i==slowo_lvl2_id[3])
                        {
                            odkryte="PLAMA";
                            slowo.setText(odkryte);
                            a=0;
                            losuj_litery_zle = litere.losuj_zle();
                            punkty+=10;
                        }


                        if(i!=slowo_lvl2_id[0] && i!=slowo_lvl2_id[1] && i!= slowo_lvl2_id[2] && i!=slowo_lvl2_id[3])
                        {
                            pojaw_litere(losuj_litery_zle[a]);
                            a++;

                            punkty--;
                        }
                    }
                    else if(poziom==3)
                    {
                        if(i==slowo_lvl3_id[0]){
                            slowo.setVisible(true);
                            if(odkryte.equals(""))
                                odkryte="K _ _ _ _ K";
                            slowo.setText(odkryte);
                            aktualna[slowo_lvl3_id[1]].setVisible(true);
                            litere.losuj_pozycje(aktualna[0]);
                            punkty++;
                        }
                        if(i==slowo_lvl3_id[1]){
                            if(odkryte.equals("K _ _ _ _ K"))
                                odkryte="K O _ _ _ K";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl3_id[2])
                        {
                            if(odkryte.equals("K O _ _ _ K"))
                                odkryte="K O S _ _ K";
                            slowo.setText(odkryte);
                            punkty++;
                            //new Poziom2();
                        }
                        if(i==slowo_lvl3_id[3])
                        {
                            if(odkryte.equals("K O S _ _ K"))
                                odkryte="K O S Z _ K";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl3_id[4])
                        {
                            if(odkryte.equals("K O S Z _ K"))
                                odkryte="KOSZYK";
                            slowo.setText(odkryte);
                            a=0;
                            losuj_litery_zle = litere.losuj_zle();
                            punkty+=10;
                        }


                        if(i!=slowo_lvl3_id[0] && i!=slowo_lvl3_id[1] && i!= slowo_lvl3_id[2] && i!=slowo_lvl3_id[3])
                        {
                            pojaw_litere(losuj_litery_zle[a]);
                            a++;
                            punkty--;
                        }
                        
                    }
                    else if(poziom==4)
                    {
                        if(i==slowo_lvl4_id[0]){
                            slowo.setVisible(true);
                            if(odkryte.equals(""))
                                odkryte="B";
                            slowo.setText(odkryte);
                            aktualna[slowo_lvl4_id[1]].setVisible(true);
                            litere.losuj_pozycje(aktualna[0]);
                            punkty++;
                        }
                        if(i==slowo_lvl4_id[1]){
                            if(odkryte.equals("B"))
                                odkryte="BE";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl4_id[2])
                        {
                            if(odkryte.equals("BE"))
                                odkryte="BET";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl4_id[3])
                        {
                            if(odkryte.equals("BET"))
                                odkryte="BETO";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl4_id[4])
                        {
                            if(odkryte.equals("BETO"))
                                odkryte="BETON";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl4_id[5])
                        {
                            if(odkryte.equals("BETON"))
                                odkryte="BETONI";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl4_id[6])
                        {
                            if(odkryte.equals("BETONI"))
                                odkryte="BETONIA _ _ A";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl4_id[7])
                        {
                            if(odkryte.equals("BETONIA _ _ A"))
                                odkryte="BETONIAR _ A";
                            slowo.setText(odkryte);
                            punkty++;
                        }
                        if(i==slowo_lvl4_id[8])
                        {
                            odkryte="BETONIARKA";
                            slowo.setText(odkryte);
                            a=0;
                            losuj_litery_zle = litere.losuj_zle();
                            punkty+=10;
                        }


                        if(i!=slowo_lvl4_id[0] && i!=slowo_lvl4_id[1] && i!= slowo_lvl4_id[2] && i!=slowo_lvl4_id[3] && i!=slowo_lvl4_id[4] && i!=slowo_lvl4_id[5] && i!=slowo_lvl4_id[6] && i!=slowo_lvl4_id[7] && i!=slowo_lvl4_id[8])
                        {
                            pojaw_litere(losuj_litery_zle[a]);
                            a++;
                            punkty--;
                        }
                    }
                    
                    
                    
                    System.out.println("PKT: "+punkty);
                    punkty_Txt.setText("PUNKTY: "+String.valueOf(punkty));
                    
                    
                }
            }
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
