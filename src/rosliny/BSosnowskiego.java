package rosliny;


import com.company.Organizm;
import com.company.Roslina;
import com.company.Swiat;

import java.util.Random;

public class BSosnowskiego extends Roslina {
public BSosnowskiego(Swiat s, int x, int y)
{
    this.s=s;
    this.sila=10;
    this.inicjatywa=0;
    this.x=x;
    this.y=y;
    this.dlugosczycia=0;
    this.s.setField(this.x,this.y,this);

}
@Override
public Organizm rozmnoz(int x, int y)
{
  BSosnowskiego v=new BSosnowskiego(this.s,x,y);
  return v;
}
@Override
public void reakcja(Organizm a)
{
    this.s.l.add("Organizm " + this.getGatunek() + " zabija " + a.getGatunek() + "(" + Integer.toString(this.getX()) + ", " + Integer.toString(this.getY()) + ")");
    this.s.setField(this.getX(),this.getY(),null);
    this.s.dozabicia.q.add(this);
}
@Override
public void akcja()
{
    Random losowanie=new Random();
    int czyrozprzestrzenic=losowanie.nextInt(6);
    if(czyrozprzestrzenic==0)
    {
        int rx=losowanie.nextInt(3)-1;
        int ry=losowanie.nextInt(3)-1;
        if(this.s.CheckField(this.getX()+rx,this.getY()+ry)==true && this.s.GetField(this.getX()+rx,this.getY()+ry)==null)
        {
            this.s.l.add("Organizm " + this.getGatunek() + " rozmnaza sie (" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");
            Organizm c = this.rozmnoz(this.getX() + rx, this.getY() + ry);
            this.s.setField(this.getX() + rx, this.getY() + ry, c);
            this.s.queue.add(this.s.GetField(this.getX() + rx, this.getY() + ry));
        }
    }
    for (int i = -1; i <= 1; i++)
    {
        for (int j = -1; j <= 1; j++)
        {

            if (this.s.CheckField(this.getX() + i, this.getY() + j) == true && this.s.GetField(this.getX() + i, this.getY() + j) != null && (this.s.GetField(this.getX() + i, this.getY() + j)).getGatunek() != this.getGatunek())
            {
                this.s.l.add("Organizm " + this.getGatunek() + " zabija " + this.s.GetField(this.getX() + i, this.getY() + j).getGatunek() + "(" + Integer.toString(this.getX()) + ", " + Integer.toString(this.getY()) + ")");
                this.s.dozabicia.q.add(this.s.GetField(this.getX() + i, this.getY() + j));
                this.s.setField(this.getX() + i, this.getY() + j, null);
                this.s.queue.del(this.getX() + i, this.getY() + j);
            }
        }
    }
}
@Override
 public String getGatunek()
{
    return "BSosnowskiego";
}
}
