package com.company;

public class Zabij {
    public Kolejka q;
    Zabij()
    {
        q=new Kolejka();
    }
    void kill()
    {
        for(int i=0;i<q.length();i++)
        {
            q.erase(0);
        }
    }
}
