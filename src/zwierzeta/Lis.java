package zwierzeta;

import com.company.*;

import java.util.Random;

public class Lis extends Zwierze {
    public Lis(Swiat s,int x,int y)
{
    this.s = s;
    this.sila = 3;
    this.inicjatywa = 7;
    this.x = x;
    this.y = y;
    this.dlugosczycia = 0;
    this.s.setField(this.x, this.y, this);
}
@Override
public void akcja()
{
    Random losowanie=new Random();
    int ruchx, ruchy;
    do
    {
        ruchx = losowanie.nextInt( 3) - 1;
        ruchy = losowanie.nextInt(  3) - 1;
    } while (ruchx == 0 && ruchy == 0);
    if(this.s.CheckField(this.x + ruchx, this.y + ruchy) == false)return;
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
        if (this.s.GetField(this.x + ruchx, this.y + ruchy).getSila() > this.getSila())return;
        this.s.l.add("Organizm " + this.getGatunek() + "kolizja z " + this.s.GetField(this.x + ruchx, this.y + ruchy).getGatunek() + "(" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");
        Organizm pom = this.s.GetField(this.x + ruchx, this.y + ruchy);
        this.kolizja(this.s.GetField(this.x + ruchx, this.y + ruchy));
        pom.reakcja(this);

    }
}

    @Override
    public Organizm rozmnoz(int x,int y)
    {
        Lis v=new Lis(this.s,x,y);
        return v;
    }
    @Override
    public String getGatunek()
    {
        return "Lis";
    }
}
