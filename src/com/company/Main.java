package com.company;


import rosliny.BSosnowskiego;
import zwierzeta.*;
import rosliny.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int N,M;
        JFrame frame=new JFrame();
        JTextField wymiarN=new JTextField();
        JTextField wymiarM=new JTextField();
        JTextField org=new JTextField();
        JTextField dowczytania=new JTextField("Nazwa pliku");
        JLabel labelcreate=new JLabel("Stworz nowy swiat o wymiarach");
        JLabel labelN=new JLabel("N:");
        JLabel labelM=new JLabel("M:");
        JLabel labelOrg=new JLabel("Początkowa ilość organizmów na gatunek:");
        JButton buttoncreate=new JButton("Stwórz świat");
        JButton buttonload=new JButton("Wczytaj świat");
        JLabel dane=new JLabel("Bartłomiej Kocot indeks 171557");
        labelN.setBounds(20,50,30,30);
        labelM.setBounds(80,50,30,30);
        wymiarN.setBounds(50,50,30,30);
        org.setBounds(20,110,30,30);
        wymiarM.setBounds(110,50,30,30);
        labelcreate.setBounds(20,20,210,30);
        labelOrg.setBounds(20,80,300,30);
        buttoncreate.setBounds(20,160,110,50);
        buttonload.setBounds(310,50,150,50);
        dowczytania.setBounds(310,120,150,30);
        dane.setBounds(20,220,250,30);
        frame.add(dane);
        frame.add(dowczytania);
        frame.add(wymiarN);
        frame.add(wymiarM);
        frame.add(labelM);
        frame.add(labelN);
        frame.add(labelcreate);
        frame.add(labelOrg);
        frame.add(org);
        frame.add(buttoncreate);
        frame.add(buttonload);
        frame.setSize(500,300);
        frame.setLayout(null);
        frame.setVisible(true);
        buttoncreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x, y, n, m, iloscorg;
                Organizm o;
                Random losowanie = new Random();
                if (wymiarN.getText().equals("") || wymiarM.getText().equals("")) {
                    n = 0;
                    m = 0;
                } else {
                    n = Integer.parseInt(wymiarN.getText());
                    m = Integer.parseInt(wymiarM.getText());
                }
                if (org.getText().equals(""))
                    iloscorg = 2;
                else
                    iloscorg = Integer.parseInt(org.getText());
                Swiat world;
                if (n == 0 || m == 0)
                    world = new Swiat();
                else
                    world = new Swiat(n, m);
                for (int i = 0; i < iloscorg; i++) {
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new BSosnowskiego(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new Guarana(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new Mlecz(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new Trawa(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new WilczeJagody(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new Antylopa(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new Lis(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new Owca(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new Wilk(world, x, y);
                        world.queue.add(o);
                    }
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        o = new Zolw(world, x, y);
                        world.queue.add(o);
                    }
                }
                for (; ; ) {
                    x = losowanie.nextInt(world.getN());
                    y = losowanie.nextInt(world.getM());
                    if (world.GetField(x, y) == null) {
                        Czlowiek v = new Czlowiek(world, x, y);
                        world.queue.add(v);
                        world.c=v;
                        break;
                    }
                }
                world.wykonajTure();


            }
        });
        buttonload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Swiat g=null;
                LineNumberReader lineNumberReader = null;
                try {
                    lineNumberReader = new LineNumberReader(new FileReader(dowczytania.getText()));

                String line = null;
                Organizm pom=null;
                int dlugosckolejki,n,m,x,y,sila,ini,dz;
                String nazwa,gatunek;
                line=lineNumberReader.readLine();
                n=Integer.parseInt(line);
                line=lineNumberReader.readLine();
                m=Integer.parseInt(line);
                line=lineNumberReader.readLine();
                dlugosckolejki=Integer.parseInt(line);
                g=new Swiat(n,m);
                for(int i=0;i<dlugosckolejki;i++)
                {
                    gatunek=lineNumberReader.readLine();
                    x=Integer.parseInt(lineNumberReader.readLine());
                    y=Integer.parseInt(lineNumberReader.readLine());
                    sila=Integer.parseInt(lineNumberReader.readLine());
                    ini=Integer.parseInt(lineNumberReader.readLine());
                    dz=Integer.parseInt(lineNumberReader.readLine());
                    if (gatunek.equals("BSosnowskiego"))
                    pom = new BSosnowskiego(g, x, y);
                    if (gatunek.equals("Guarana"))
                    pom = new Guarana(g, x, y);
                    if (gatunek.equals("Mlecz"))
                    pom = new Mlecz(g, x, y);
                    if (gatunek.equals("Trawa"))
                    pom = new Trawa(g, x, y);
                    if (gatunek.equals("WilczeJagody"))
                    pom = new WilczeJagody(g, x, y);
                    if (gatunek.equals("Antylopa"))
                    pom = new Antylopa(g, x, y);
                    if (gatunek.equals("Lis"))
                    pom = new Lis(g, x, y);
                    if (gatunek.equals("Owca"))
                    pom = new Owca(g, x, y);
                    if (gatunek.equals("Wilk"))
                    pom = new Wilk(g, x, y);
                    if (gatunek.equals("Zolw"))
                    pom = new Zolw(g, x, y);
                    if (gatunek.equals("Czlowiek")) {
                        Czlowiek v = new Czlowiek(g, x, y);
                        v.setSila(sila);
                        v.setIni(ini);
                        v.setDZ(dz);
                        g.queue.add(v);
                        g.c=v;
                        continue;
                    }
                    pom.setSila(sila);
                    pom.setIni(ini);
                    pom.setDZ(dz);
                    g.queue.add(pom);
                }
            }  catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                    e1.printStackTrace();
                }
            finally
            {
                g.wykonajTure();
            }
            }
        });

    }
}
