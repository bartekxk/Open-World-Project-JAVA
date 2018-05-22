package rosliny;

import com.company.*;

public class Trawa extends Roslina {
    public Trawa(Swiat s,int x,int y)
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
    public Organizm rozmnoz(int x,int y)
{
    Trawa v=new Trawa(this.s,x,y);
    return v;
}
@Override
    public String getGatunek()
{
    return "Trawa";
}
}
