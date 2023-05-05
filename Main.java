package com.company;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Bigramm bigramm = new Bigramm(System.getProperty("user.dir")+"\\src\\com\\company\\names.txt");
        bigramm.printProbability();
        for (int i = 0; i < 1000; i++) {
            System.out.println(bigramm.generateName());
        }
    }
}
