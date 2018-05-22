package com.company;

import rosliny.*;
import zwierzeta.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class Swiat {
    private int N, M,tura;
    private Organizm[][] plansza;
    private JLabel[][] obrazki;
    private JLabel obrazeklegendy;
    public Logs l;
    public Zabij dozabicia;
    private JTextField dozapisu;
    public Czlowiek c;
    private boolean przycisk;
    public Kolejka queue;
    private JFrame okno;
    private JLabel legendao;
    private String aktualnygatunek;
    boolean wyjsciezeswiata;
    Swiat() {
        this.tura=0;
        this.przycisk=false;
        this.N = 20;
        this.M = 20;
        this.okno=new JFrame();
        this.okno.setSize(900,450);
        this.wyjsciezeswiata=false;
        this.l=new Logs(this);
        this.aktualnygatunek="Antylopa";
        this.queue=new Kolejka();
        this.dozabicia=new Zabij();
        this.plansza=new Organizm[20][20];
        this.obrazki=new JLabel[20][20];
        this.obrazeklegendy=new JLabel();
        this.obrazeklegendy.setBounds(this.N+640,110,10,10);
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
                this.plansza[i][j]=null;
        }
        JButton zapisz=new JButton("Zapisz");
        JButton wyjscie=new JButton("Wyjście");
        JButton zmianag=new JButton("Zmień");
        JButton dalej=new JButton("Dalej");
        zmianag.setBounds(this.N+660,100,100,30);
        this.dozapisu=new JTextField("Nazwa pliku");
        zapisz.setBounds(this.N*10+120,100,100,60);
        this.dozapisu.setBounds(this.N*10+120,170,100,20);
        this.legendao=new JLabel("Do dodania: " +this.aktualnygatunek);
        legendao.setBounds(this.N*10+240,100,300,30);
        this.okno.add(legendao);
        wyjscie.setBounds(this.N*10+120,200,100,60);
        JLabel opis=new JLabel("Poruszanie strzałkami, X aktywacja specjalnej umiejętności");
        dalej.setBounds(this.N*10+120,320,80,50);
        dalej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przycisk=false;
                wykonajTure();
            }
        });
        opis.setBounds(this.N*10+120,280,620,30);
        wyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wyjsciezeswiata=true;
                okno.removeAll();
                wykonajTure();
                System.gc();

            }
        });
        zapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Save();
            }
        });
        zmianag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gatunki[]gat=gatunki.values();
                for(int i=0;;i++)
                {
                    if(i==gat.length)
                        i=0;
                    if(String.valueOf(gat[i]).equals(aktualnygatunek))
                    {
                        i++;
                        if(i==gat.length)
                            i=0;
                        aktualnygatunek= String.valueOf(gat[i]);
                        try {
                            rysujSwiat();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });
        this.okno.add(this.obrazeklegendy);
        this.okno.add(this.dozapisu);
        this.okno.add(opis);
        this.okno.add(wyjscie);
        this.okno.add(zmianag);
        this.okno.add(zapisz);
        this.okno.add(dalej);
        for(int i=0;i<this.N;i++)
        {
            for(int j=0;j<this.M;j++)
            {
                this.obrazki[i][j]=new JLabel();
                this.obrazki[i][j].setBounds(1+i*10,1+j*10,10,10);
                int finalI = i;
                int finalJ = j;
                this.obrazki[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        dodajgatunek(finalI, finalJ);
                    }
                });
                this.okno.add(this.obrazki[i][j]);
            }
        }
        this.okno.setLayout(null);
        this.okno.setVisible(true);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                new KeyEventDispatcher() {
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getKeyChar() == 'n' && e.getKeyCode()==78)
                        {
                            e.setKeyChar('d');
                            c.setkey(0);
                            wykonajTure();
                        }
                        if(e.getKeyCode()==37 || e.getKeyCode()==38 || e.getKeyCode()==39 || e.getKeyCode()==40 || e.getKeyCode()==88)
                        {

                          c.setkey(e.getKeyCode());
                          wykonajTure();
                        }
                        return false;
                    }
                });

    }

    Swiat(int n, int m) {
        this.tura=0;
        this.N = n;
        this.M = m;
        this.przycisk=true;
        this.okno=new JFrame();
        okno.setSize(N*10+700,M*10+450);
        this.l=new Logs(this);
        this.queue=new Kolejka();
        this.aktualnygatunek="Antylopa";
        this.dozabicia=new Zabij();
        this.obrazeklegendy=new JLabel();
        this.obrazeklegendy.setBounds(this.N+600,110,10,10);
        this.wyjsciezeswiata=false;
        this.plansza=new Organizm[this.N][this.M];
        this.obrazki=new JLabel[this.N][this.M];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
                this.plansza[i][j]=null;
        }
        JButton zapisz=new JButton("Zapisz");
        JButton wyjscie=new JButton("Wyjście");
        JButton zmianag=new JButton("Zmień");
        JButton dalej=new JButton("Dalej");
        zmianag.setBounds(this.N+350,100,100,30);
        zapisz.setBounds(this.N*10+120,100,100,60);
        this.legendao=new JLabel("Do dodania: " +this.aktualnygatunek);
        this.legendao.setBounds(this.N*10+360,100,200,30);
        this.okno.add(legendao);
        this.dozapisu=new JTextField("Nazwa pliku");
        this.dozapisu.setBounds(this.N*10+120,170,100,20);
        wyjscie.setBounds(this.N*10+120,200,100,60);
        JLabel opis=new JLabel("Poruszanie strzałkami, X aktywacja specjalnej umiejętności");
        opis.setBounds(this.N*10+120,280,620,30);
        dalej.setBounds(this.N*10+120,320,80,50);
        dalej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przycisk=false;
                wykonajTure();
            }
        });
        wyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wyjsciezeswiata=true;
                okno.removeAll();
                wykonajTure();
                System.gc();
            }
        });

        zapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Save();
            }
        });
        zmianag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gatunki[]gat=gatunki.values();
                for(int i=0;;i++)
                {
                    if(i==gat.length)
                        i=0;
                    if(String.valueOf(gat[i]).equals(aktualnygatunek))
                    {
                        i++;
                        if(i==gat.length)
                            i=0;
                        aktualnygatunek= String.valueOf(gat[i]);
                        try {
                            rysujSwiat();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });
        this.okno.add(zmianag);
        this.okno.add(dalej);
        this.okno.add(this.obrazeklegendy);
        this.okno.add(opis);
        this.okno.add(this.dozapisu);
        this.okno.add(wyjscie);
        this.okno.add(zapisz);
        for(int i=0;i<this.N;i++)
        {
            for(int j=0;j<this.M;j++)
            {
                this.obrazki[i][j]=new JLabel();
                this.obrazki[i][j].setBounds(1+i*10,1+j*10,10,10);
                int finalI = i;
                int finalJ = j;
                this.obrazki[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        dodajgatunek(finalI, finalJ);
                    }
                });
                this.okno.add(this.obrazki[i][j]);
            }
        }
        this.okno.setLayout(null);
        this.okno.setVisible(true);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                new KeyEventDispatcher() {
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getKeyChar() == 'n' && e.getKeyCode()==78)
                        {
                            e.setKeyChar('d');
                            if(c!=null)
                                c.setkey(0);
                            wykonajTure();
                        }
                        if(e.getKeyCode()==37 || e.getKeyCode()==38 || e.getKeyCode()==39 || e.getKeyCode()==40 || e.getKeyCode()==88)
                        {
                            if(c!=null)
                                c.setkey(e.getKeyCode());
                            wykonajTure();
                        }
                        return false;
                    }
                });


    }

    public JFrame getOkno()
    {
        return this.okno;
    }
    public void dodajgatunek(int x,int y)
    {
        Organizm pom=null;
        String gatunek=String.valueOf(this.aktualnygatunek);
        if (gatunek.equals("BSosnowskiego"))
            pom = new BSosnowskiego(this, x, y);
        if (gatunek.equals("Guarana"))
            pom = new Guarana(this, x, y);
        if (gatunek.equals("Mlecz"))
            pom = new Mlecz(this, x, y);
        if (gatunek.equals("Trawa"))
            pom = new Trawa(this, x, y);
        if (gatunek.equals("WilczeJagody"))
            pom = new WilczeJagody(this, x, y);
        if (gatunek.equals("Antylopa"))
            pom = new Antylopa(this, x, y);
        if (gatunek.equals("Lis"))
            pom = new Lis(this, x, y);
        if (gatunek.equals("Owca"))
            pom = new Owca(this, x, y);
        if (gatunek.equals("Wilk"))
            pom = new Wilk(this, x, y);
        if (gatunek.equals("Zolw"))
            pom = new Zolw(this, x, y);
        this.queue.add(pom);
        try {
            this.rysujSwiat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  public void setField(int x, int y, Organizm c)
    {
        this.plansza[x][y]=c;
    }

    public Organizm GetField(int x, int y)
    {
        return this.plansza[x][y];
    }
    int getN()
    {
        return this.N;
    }
    int getM()
    {
        return this.M;
    }
    void rysujSwiat() throws IOException {

        BufferedImage myPicture = null;
        String src="L:\\Users\\Bartek\\IdeaProjects\\OpenWorldProject\\src\\";
        JLabel picLabel;
        this.legendao.setText("Do dodania: " +this.aktualnygatunek);
        myPicture=ImageIO.read(new File (src+this.aktualnygatunek+".bmp"));
        this.obrazeklegendy.setIcon(new ImageIcon(myPicture));
        this.obrazeklegendy.repaint();
        for(int i=0;i<this.getN();i++)
        {
            for(int j=0;j<this.getM();j++)
            {
                try {
                    if(this.GetField(i,j)==null)
                        myPicture = ImageIO.read(new File(src+"pusty.bmp"));
                    else {
                     String h=this.GetField(i,j).getGatunek();
                        myPicture = ImageIO.read(new File(src + h + ".bmp"));
                    }
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                this.obrazki[i][j].setIcon(new ImageIcon(myPicture));
               // this.obrazki[i][j].setBounds(1+i*10,1+j*10,10,10);
                this.obrazki[i][j].repaint();
            }
        }


    }
    private  void obsulgatury()
    {
        if(this.tura>=this.queue.length()-1)
           this.tura=0;
        else
            this.tura++;
    }
    void wykonajTure() {
            Organizm pom;
            if(this.przycisk==true)
            {
                this.przycisk=false;
                return;
            }
            if (this.wyjsciezeswiata == true) {
                this.przycisk=false;
                this.getOkno().setVisible(false);
                this.getOkno().dispose();
                return;
            }
            pom=this.queue.at(this.tura);
            if(pom.getGatunek().equals("Czlowiek"))
            {
                this.l.add("Ruch czlowieka");
                this.l.log();
                if(this.c.getkey()==88) {
                    c.ablity();
                    return;
                }
                if(this.c.getkey()==37 ||this.c.getkey()==38 ||this.c.getkey()==39 ||this.c.getkey()==40 )
                        c.akcja();
                else
                    return;
            }
            else
            pom.akcja();
            this.l.log();
        try {
            this.rysujSwiat();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.obsulgatury();
         this.przycisk=true;

    }

   public boolean CheckField(int x, int y)
    {
        if(x<this.N && x>=0 && y<this.M && y>=0)
            return true;
        return false;
    }

    private void Save() {

        try {
            PrintWriter out = new PrintWriter(this.dozapisu.getText());

            out.println(this.N);
            out.println(this.M);
            out.println(this.queue.length());
            for (int i = 0; i < this.queue.length(); i++)
            {
                out.println(this.queue.at(i).getGatunek());
                out.println(this.queue.at(i).getX());
                out.println(this.queue.at(i).getY());
                out.println(this.queue.at(i).getSila());
                out.println(this.queue.at(i).getIni() );
                out.println(this.queue.at(i).getDZ());
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}