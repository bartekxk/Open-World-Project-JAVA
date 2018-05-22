package zwierzeta;

import com.company.*;

public class Owca extends Zwierze {
    public Owca(Swiat s,int x,int y)
{
    this.s = s;
    this.sila = 4;
    this.inicjatywa = 4;
    this.x = x;
    this.y = y;
    this.dlugosczycia = 0;
    this.s.setField(this.x, this.y, this);
}

    @Override
    public Organizm rozmnoz(int x,int y)
    {
        Owca v=new Owca(this.s,x,y);
        return v;
    }
    @Override
    public String getGatunek()
    {
        return "Owca";
    }
}
