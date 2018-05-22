package zwierzeta;

import com.company.*;

public class Wilk extends Zwierze {
    public Wilk(Swiat s,int x,int y)
{
    this.s = s;
    this.sila = 9;
    this.inicjatywa = 5;
    this.x = x;
    this.y = y;
    this.dlugosczycia = 0;
    this.s.setField(this.x, this.y, this);
}

    @Override
    public Organizm rozmnoz(int x,int y)
    {
        Wilk v=new Wilk(this.s,x,y);
        return v;
    }
    @Override
    public String getGatunek()
    {
        return "Wilk";
    }
}
