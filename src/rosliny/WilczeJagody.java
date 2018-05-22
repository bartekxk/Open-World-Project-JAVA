package rosliny;

import com.company.*;

public class WilczeJagody extends Roslina {
    public WilczeJagody(Swiat s,int x,int y)
{	this.s = s;
    this.sila = 99;
    this.inicjatywa = 0;
    this.x = x;
    this.y = y;
    this.dlugosczycia = 0;
    this.s.setField(this.x, this.y, this);
}

    @Override
    public Organizm rozmnoz(int x,int y)
    {
        WilczeJagody v=new WilczeJagody(this.s,x,y);
        return v;
    }
    @Override
    public void reakcja(Organizm a)
    {
        this.s.l.add("Organizm " + this.getGatunek() + " zabija " + a.getGatunek() + "(" + Integer.toString(this.getX()) + ", " + Integer.toString(this.getY()) + ")");
        this.s.setField(this.getX(), this.getY(), null);
        this.s.dozabicia.q.add(this);
        this.s.queue.del(this.getX(), this.getY());
        return;
    }
    @Override
    public String getGatunek()
    {
        return "WilczeJagody";
    }
}