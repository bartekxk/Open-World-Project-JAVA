package com.company;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logs {
    private String logi;
    private PrintWriter out;
    private Swiat world;
    private JLabel dologow;
    private void clear()
    {
        this.logi="";
    }
    Logs(Swiat w)
    {
        this.world=w;
        logi="";
        this.dologow=new JLabel("");
        dologow.setBounds(this.world.getN()*10+120,30,600,60);
        this.world.getOkno().add(dologow);
        try {
            out=new PrintWriter("logi.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void add(String x)
    {
        if(this.logi.equals(""))
        {
            this.logi=x;
        }else
        {
            this.logi+=" | ";
            this.logi+=x;
        }
    }
    public void log()
    {
        this.dologow.setText(this.logi);
        this.dologow.repaint();
        this.out.println(this.logi);
        this.clear();
        this.out.close();
    }
}
