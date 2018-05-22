package zwierzeta;

import com.company.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Czlowiek extends Zwierze {
    private int czasumiejetnosci;
    private int Key;
    public  Czlowiek(Swiat s,int x,int y)
    {
        this.czasumiejetnosci = 0;
        this.s = s;
        this.sila = 5;
        this.inicjatywa = 4;
        this.x = x;
        this.y = y;
        this.dlugosczycia = 0;
        this.s.setField(this.x, this.y, this);
    }
    public void setkey(int key)
    {
        this.Key=key;
    }
    public int getkey()
    {
        return this.Key;
    }
    public void akcja()
    {
        Random losowanie=new Random();
        int ruch = this.Key;
        int ruchx=0, ruchy=0;
        if (ruch ==88)
        {
            this.ablity();
        }

            if (ruch == 37)ruchx = -1;
            if (ruch == 39)ruchx = 1;
            if (ruch == 38)ruchy = -1;
            if (ruch == 40)ruchy = 1;
            if (this.s.CheckField(this.x + ruchx, this.y + ruchy) == false)return;
            if (this.czasumiejetnosci > 2)
            {
                ruchx *= 2;
                ruchy *= 2;
            }
            if (this.czasumiejetnosci == 1 || this.czasumiejetnosci == 2)
            {
                int losuj = losowanie.nextInt(2);
                if (losuj == 1)
                {
                    ruchx *= 2;
                    ruchy *= 2;
                }
            }

        if (this.s.GetField(this.x + ruchx, this.y + ruchy) == null)
        {
            this.s.setField(this.x + ruchx, this.y + ruchy, this);
            this.s.setField(this.x, this.y, null);
            this.x = x + ruchx;
            this.y = y + ruchy;
            this.s.l.add("Organizm " + this.getGatunek() + " porusza sie na pole (" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");

        }
        else
        {
            Organizm pom = this.s.GetField(this.x + ruchx, this.y + ruchy);
            this.s.l.add("Organizm " + this.getGatunek() + "kolizja z " + this.s.GetField(this.x + ruchx, this.y + ruchy).getGatunek() + "(" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");
            this.kolizja(this.s.GetField(this.x + ruchx, this.y + ruchy));
            pom.reakcja(this);
        }
        this.Key=0;
        if (this.czasumiejetnosci == 1)
        {
            this.czasumiejetnosci = -5;
            return;
        }
        if (this.czasumiejetnosci < 0)
        {
            this.czasumiejetnosci++;
            return;
        }
        if (this.czasumiejetnosci > 0)
        {
            this.czasumiejetnosci--;
            return;
        }
    }
    public void ablity()
    {
        if (this.czasumiejetnosci != 0)
        {
            this.s.l.add( "Umiejętność zostala juz aktywowana");
            this.s.l.log();
            return;
        }
        this.czasumiejetnosci = 5;
        this.s.l.add("Umiejetnosc zostala aktywowana");
        this.s.l.log();
    }

    @Override
    public Organizm rozmnoz(int x,int y)
    {
        Czlowiek v=new Czlowiek(this.s,x,y);
        return v;
    }
    @Override
    public String getGatunek()
    {
        return "Czlowiek";
    }
}
