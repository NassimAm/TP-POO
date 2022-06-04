package com.example.tppoo.Models;
import java.io.Serializable;
import java.util.Random;

public class Dé implements Serializable {

    private final int max = 6;
    private int current = 1;

    public int GenererCoup() {
        Random rand = new Random();
        this.current = rand.nextInt(max) + 1;
        return current;
    }

    public int getValeur()
    {
        return current;
    }

}
