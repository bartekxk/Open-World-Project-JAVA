package com.company;

import java.util.Random;

public class Zwierze extends Organizm {
    @Override
    public   void akcja()
    {
        int ruchx,ruchy;
        Random losowanie=new Random();
        do {
            ruchx=losowanie.nextInt(3)-1;
            ruchy=losowanie.nextInt(3)-1;
        }while(ruchx==0 && ruchy==0);
        if(this.s.CheckField(this.x+ruchx,this.y+ruchy)==false)
            return;
        if(this.s.GetField(this.x+ruchx,this.y+ruchy)==null)
        {
            this.s.setField(this.x+ruchx,this.y+ruchy,this);
            this.s.setField(this.x,this.y,null);
            this.x+=ruchx;
            this.y+=ruchy;
            this.s.l.add("Organizm " + this.getGatunek()+ " porusza sie na pole ("+ Integer.toString(this.getX())+","+ Integer.toString(this.getY())+")");
        }else
        {
            Organizm pom= this.s.GetField(this.x + ruchx, this.y + ruchy);
            this.s.l.add("Organizm " + this.getGatunek() + "kolizja z " + this.s.GetField(this.x + ruchx, this.y + ruchy).getGatunek() + "(" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");
            this.kolizja(this.s.GetField(this.x+ruchx,this.y+ruchy));
            pom.reakcja(this);
        }
    }
    @Override
    public  void kolizja(Organizm a)
    {
        if(a.getGatunek().equals(this.getGatunek()))
        {
            boolean czyznaleziono=false;
            for(int i=-1;i<=1;i++)
            {
                for(int j=-1;j<=1;j++)
                {
                    if(a.getworld().CheckField(a.getX()+i,a.getY()+j)==true && a.getworld().GetField(a.getX()+i,a.getY()+j)==null)
                    {
                        czyznaleziono=true;
                        Organizm c=a.rozmnoz(a.getX()+i,a.getY()+j);
                        this.getworld().setField(a.getX()+i,a.getY()+j,c);
                        this.s.queue.add(this.s.GetField(a.getX()+i,a.getY()+j));
                        this.s.l.add("Organizm " + this.getGatunek() + " rozmnaza sie (" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");
                        return;
                    }
                }

            }
            if(czyznaleziono==false)
            {
                for (int i = -1; i <= 1; i++)
                {
                    for (int j = -1; j < 1; j++)
                    {
                        if (this.getworld().CheckField(this.x + i, this.getY() + j) == true && this.getworld().GetField(this.getX() + i, this.getY() + j) == null)
                        {
                            czyznaleziono = true;
                            Organizm c = a.rozmnoz(a.getX() + i, a.getY() + j);
                            this.s.setField(this.getX() + i, this.getY() + j, c);
                            this.s.queue.add(this.s.GetField(a.getX() + i, a.getY() + j));
                            this.s.l.add("Organizm " + this.getGatunek() + " rozmnaza sie (" + Integer.toString(this.getX()) + "," + Integer.toString(this.getY()) + ")");
                            return;
                        }
                    }
                }
            }
        }
        else
        {
            if (this.getSila() < a.getSila())
            {
                this.s.queue.del(this.getX(), this.getY());
                this.s.setField(this.getX(), this.getY(), null);
                this.s.dozabicia.q.add(this);
                this.s.l.add("Organizm " + this.getGatunek() + " zostaje zabity przez " + a.getGatunek() + " (" + Integer.toString(this.getX()) + ", " + Integer.toString(this.getY()) + ")");
                return;
            }
		else
            {
                this.s.queue.del(a.getX(), a.getY());
                a.s.setField(a.getX(), a.getY(), this);
                this.s.setField(this.getX(), this.getY(), null);
                this.x = a.getX();
                this.y = a.getY();
                this.s.dozabicia.q.add(a);
                this.s.l.add("Organizm " + this.getGatunek() + " zabija " + a.getGatunek()+ "(" + Integer.toString(this.getX()) + ", " + Integer.toString(this.getY()) + ")");
            }
        }
    }
@Override
public void reakcja(Organizm a)
{
    return;
}
    @Override
  public  Organizm rozmnoz(int x,int y)
    {
        return null;

    }
}
