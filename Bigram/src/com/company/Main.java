package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose file name: ");
        String fileName = scanner.next();
        System.out.println();
        Bigram bigram = new Bigram(System.getProperty("user.dir")+"\\src\\com\\company\\"+fileName);
        while (true){
            System.out.println("--------------------------");
            System.out.println("Choose options: ");
            System.out.println("1. Print probability.");
            System.out.println("2. Generate name.");
            System.out.println("3. Generate names.");
            System.out.println("4. Add new name to bigrams.");
            System.out.println("5. Remove name from the bigrams.");
            System.out.println("6. Exit.");
            System.out.print("Choose one: ");
            int a = scanner.nextInt();
            System.out.println();
            if (a==1){
                bigram.printProbability();
            }else if(a==2){
                System.out.println(bigram.generateName());
            }else if(a==3){
                System.out.println("Input the n, how many names should be generated.");
                System.out.print("n: ");
                int n = scanner.nextInt();
                System.out.println();
                for (int i = 0; i < n; i++) {
                    System.out.println(bigram.generateName());
                }
            }else if(a==4){
                System.out.print("Enter a new name: ");
                String name = scanner.next();
                System.out.println();
                bigram.addName(name);
            }else if(a==5){
                System.out.print("Enter a name to remove: ");
                String name = scanner.next();
                System.out.println();
                bigram.removeName(name);
            }else if(a==6){
                break;
            }
        }
    }
}
