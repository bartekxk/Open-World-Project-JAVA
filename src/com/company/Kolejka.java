package com.company;

import java.util.LinkedList;

public class Kolejka
{
    private LinkedList<Organizm> lista;
    Kolejka(){
    lista=new LinkedList();
    }
public void add(Organizm c)
{
    if(lista.size()==0)
    {
        this.lista.add(c);
        return;
    }
    for(int i=this.lista.size()-1;i>=0;i--)
    {
        if(this.lista.get(i).getIni()>c.getIni())
        {
            this.lista.add(i+1,c);
            return;

        }
    }
    this.lista.add(0,c);

}
Organizm at(int index)
{
    return this.lista.get(index);
}
void erase(int index)
{
    this.lista.remove(index);
}
int length()
{
    return this.lista.size();
}
public void del(int x,int y) {
    for (int i = 0; i < this.lista.size();i++)
    {
        if(this.lista.get(i).getX()==x && this.lista.get(i).getY()==y)
        {
            this.erase(i);
            return;
        }
    }
}
}
