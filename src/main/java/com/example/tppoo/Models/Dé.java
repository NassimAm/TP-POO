package com.example.tppoo.Models;
import java.util.Random;

public class Dé{

    public final int max = 6;

    public int GenererCoup() {
        Random rand = new Random();
        return rand.nextInt(max) + 1;
    }

}
