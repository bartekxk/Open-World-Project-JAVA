package com.company;
public class Organizm {
    protected int inicjatywa;
    protected int sila;
    protected int x;
    protected int y;
    protected int dlugosczycia;
    protected Swiat s;
    public void kolizja(Organizm a)
    {

    }
    public void reakcja(Organizm a)
    {
//todo
    }
    public String getGatunek()
    {
        return "empty";
    }
   public void akcja()
    {

    }
   public Organizm rozmnoz(int x,int y)
    {
        return null;

    }
    public int getDZ()
    {
        return this.dlugosczycia;
    }
    public void setSila(int s)
    {
        this.sila=s;
    }
    public void setIni(int ini)
    {
        this.inicjatywa=ini;
    }
    public void setDZ(int dz)
    {
        this.dlugosczycia=dz;
    }
    void grow()
    {
        this.dlugosczycia++;
    }
    public  int getSila()
    {
        return this.sila;
    }
    public int getIni()
    {
        return this.inicjatywa;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public Swiat getworld()
    {
        return this.s;
    }
}
