package rosliny;

import com.company.*;

import java.util.Random;

public class Mlecz extends Roslina {
    public Mlecz(Swiat s,int x,int y)
{
    this.s = s;
    this.sila = 0;
    this.inicjatywa = 0;
    this.x = x;
    this.y = y;
    this.dlugosczycia = 0;
    this.s.setField(this.x, this.y, this);
}

@Override
  public  Organizm rozmnoz(int x,int y)
{
    Mlecz v=new Mlecz(this.s,x,y);
    return v;
}
@Override
public void akcja()
{
    Random losowanie=new Random();
    for (int i = 0; i < 3; i++)
    {
        int czyrozprzestrenic = losowanie.nextInt(3);
        if (czyrozprzestrenic == 0)
        {
            int rx = losowanie.nextInt(3) - 1;
            int ry = losowanie.nextInt(3) - 1;
            if (this.s.CheckField(this.getX() + rx, this.getY() + ry)==true && this.s.GetField(this.getX() + rx, this.getY() + ry) == null)
            {
                Organizm c = this.rozmnoz(this.getX() + rx, this.getY() + ry);
                this.s.setField(this.getX() + rx, this.getY() + ry, c);
                this.s.queue.add(this.s.GetField(this.getX() + rx, this.getY() + ry));
                this.s.l.add("Organizm " + this.getGatunek() + " rozmnaza sie (" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");
            }
        }
    }
}
@Override
    public String getGatunek()
    {
        return "Mlecz";
    }
}
