package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Bigramm {
    Map<Character,Map<Character,Integer>> map;
//    Map<Character,Queue<Character>> sorter;
    int size;
    public Bigramm(String fileName) throws IOException {
        map = new HashMap<>();
//        sorter = new HashMap<>();
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext()){
            String name = scanner.next();
            put('^',name.charAt(0));
            put(name.charAt(name.length()-1),'$');
            for (int i = 0; i < name.length()-1; i++)
                put(name.charAt(i),name.charAt(i+1));
        }
        for (char key: map.keySet()){
            System.out.println(key+" "+map.get(key));
        }
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
        char c = nextChar((char)(random.nextInt(26)+'a'));
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
//    public String generateRandomName(){
//        StringBuilder sb = new StringBuilder();
//        char c = getRandom();
//        while (c!='$'){
//            if (sb.length()>20) break;
//            if (c!='^') sb.append(c);
//            c = getRandomNext(c);
////            System.out.println(c);
//        }
//        return sb.toString();
//    }
//    public Character getNext(char c){
//        Queue<Character> heap = sorter.getOrDefault(c,new PriorityQueue<>((i,j)->map.get(c).get(j)-map.get(c).get(i)));
//        return heap.peek();
//    }
//    public void printSortedProbability(char c){
//        Queue<Character> heap = new PriorityQueue<>(sorter.getOrDefault(c,new PriorityQueue<>((i,j)->map.get(c).get(j)-map.get(c).get(i))));
//        while (!heap.isEmpty()){
//            char next = heap.poll();
//            System.out.printf("%s%s: %.4f\n",c,next,getProbability(c,next));
//        }
//    }
    public void printProbability(){
        for (Map.Entry<Character, Map<Character, Integer>> entry: map.entrySet()){
            for (Map.Entry<Character,Integer> bigram: map.get(entry.getKey()).entrySet()){
                System.out.printf("%s%s: %.4f\n",entry.getKey(),bigram.getKey(),getProbability(entry.getKey(), bigram.getKey()));
            }
        }
    }
//    public char getRandomNext(char c){
//        ArrayList<Character> list = new ArrayList<>(sorter.get(c));
//        Collections.shuffle(list);
//        return list.get(0);
//    }
    public char getRandom(){
        ArrayList<Character> list = new ArrayList<>(map.keySet());
        Collections.shuffle(list);
        return list.get(0)=='^' ? list.get(1) : list.get(0);
    }
    public void put(char c1, char c2){
        char lc1 = Character.toLowerCase(c1);
        char lc2 = Character.toLowerCase(c2);
        Map<Character,Integer> counter = map.getOrDefault(lc1,new HashMap<>());
        counter.put(lc2,counter.getOrDefault(lc2,0)+1);
        map.put(lc1,counter);

//        Queue<Character> heap = sorter.getOrDefault(lc1, new PriorityQueue<>((i,j)->map.get(lc1).get(j)-map.get(lc1).get(i)));
//        heap.remove(lc2);
//        heap.add(lc2);
//        sorter.put(lc1,heap);
        size++;
    }
    public double getProbability(char c1, char c2){
        return (map.get(c1).get(c2)/(double)size);
    }
}
