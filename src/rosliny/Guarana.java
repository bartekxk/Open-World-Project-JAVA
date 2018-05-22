package rosliny;

import com.company.*;

public class Guarana extends Roslina{
    public  Guarana(Swiat s,int x,int y)
    {
        this.s=s;
        this.sila=0;
        this.inicjatywa=0;
        this.x=x;
        this.y=y;
        this.dlugosczycia=0;
        this.s.setField(this.x,this.y,this);
    }

    @Override
    public Organizm rozmnoz(int x,int y)
    {
        Guarana v=new Guarana(this.s,x,y);
        return v;
    }
    @Override
    public void reakcja(Organizm a)
    {
        this.s.l.add("Organizm " + this.getGatunek() + " dodaje 3 do sily dla " + a.getGatunek());
        a.setSila(a.getSila()+3);
        return;
    }
    @Override
    public String getGatunek()
    {
        return "Guarana";
    }
}
