package com.company;

import java.util.Random;

public class Roslina extends Organizm{
    @Override
    public void akcja()
    {
        Random losowanie=new Random();
        int czyrozprzestrzenic=losowanie.nextInt(3);
        if(czyrozprzestrzenic==0)
        {
            int rx=losowanie.nextInt(3)-1;
            int ry=losowanie.nextInt(3)-1;
            if (this.getworld().CheckField(this.getX() + rx, this.getY() + ry) == true && this.s.GetField(this.getX() + rx, this.getY() + ry) == null)
            {
                Organizm c= this.rozmnoz(this.getX() + rx, this.getY() + ry);
                this.s.setField(this.getX() + rx, this.getY() + ry, c);
                this.s.queue.add(this.s.GetField(this.getX() + rx, this.getY() + ry));
                this.s.l.add("Organizm " + this.getGatunek() + " rozprzestrzenia sie (" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");
            }
        }
    }
    @Override
    public  void kolizja(Organizm a)
    {
        return;
    }
    @Override
  public  void reakcja(Organizm a)
    {
        return;
    }
    @Override
    public Organizm rozmnoz(int x,int y)
    {
        return null;

    }
}
