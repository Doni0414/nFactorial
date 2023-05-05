package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Bigram {
    Map<Character,Map<Character,Integer>> map;
    int size;
    public Bigram(String fileName) throws IOException {
        map = new HashMap<>();
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext()){
            String name = scanner.next();
            compute(name);
        }
    }
    public void compute(String name){
        put('^',name.charAt(0));
        put(name.charAt(name.length()-1),'$');
        for (int i = 0; i < name.length()-1; i++)
            put(name.charAt(i),name.charAt(i+1));
    }
    public void print(){
        for (Map.Entry<Character,Map<Character,Integer>> entry: map.entrySet()){
            for (Map.Entry<Character,Integer> counter: entry.getValue().entrySet()){
                System.out.println(entry.getKey()+""+counter.getKey()+": "+counter.getValue());
            }
        }
    }
    public void generate(String name){
        String s = generateName();
        int count = 0;
        while(!s.equals(name)){
            s = generateName();
            count++;
        }
        System.out.println(name+" "+count);
    }
    public String generateName(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        char c = nextChar('^');
        while(c!='$'){
            sb.append(c);
            c = nextChar(c);
        }
        return sb.toString();
    }
    public Character nextChar(char c){
        if (!map.containsKey(c)) return '$';
        double sum = 0;
        for (Map.Entry<Character,Integer> entry: map.get(c).entrySet()){
            sum+=getProbability(c,entry.getKey());
        }
        Random random = new Random();
        sum = random.nextDouble(sum);
        for (Map.Entry<Character,Integer> entry: map.get(c).entrySet()){
            sum-=getProbability(c,entry.getKey());
            if (sum<0) return entry.getKey();
        }
        return '$';
    }
    public void printProbability(){
        for (Map.Entry<Character, Map<Character, Integer>> entry: map.entrySet()){
            for (Map.Entry<Character,Integer> bigram: map.get(entry.getKey()).entrySet()){
                System.out.printf("%s%s: %.4f\n",entry.getKey(),bigram.getKey(),getProbability(entry.getKey(), bigram.getKey()));
            }
        }
    }
    public void put(char c1, char c2){
        char lc1 = Character.toLowerCase(c1);
        char lc2 = Character.toLowerCase(c2);
        Map<Character,Integer> counter = map.getOrDefault(lc1,new HashMap<>());
        counter.put(lc2,counter.getOrDefault(lc2,0)+1);
        map.put(lc1,counter);
        size++;
    }
    public double getProbability(char c1, char c2){
        return (map.get(c1).get(c2)/(double)size);
    }
    public void addName(String name){
        compute(name);
    }
    public void removeName(String name){
        removeNameUtil('^',name.charAt(0));
        removeNameUtil(name.charAt(name.length()-1),'$');
        for (int i = 0; i < name.length()-1; i++) {
            removeNameUtil(name.charAt(i),name.charAt(i+1));
        }
    }
    public void removeNameUtil(char c1, char c2) {
        if(map.containsKey(c1) && map.get(c1).getOrDefault(c2,0)>0){
            c1 = Character.toLowerCase(c1);
            c2 = Character.toLowerCase(c2);
            map.get(c1).put(c2,map.get(c1).get(c2)-1);
            size--;
        }
    }
}
