package week_1;

import java.util.ArrayList;
import java.util.Collections;

public class week_1 {
    private static int counter = 1;
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Scuffsort");
        long startTime = System.nanoTime();
        for(int j = 0; j < 1000; j++){
            test();
            System.out.print(counter+"\r");
        }
        long endTime = System.nanoTime();
        System.out.println("---------");
        System.out.println((endTime - startTime)/100000 + " miliseconds ," + (counter-1)+ " iterations");
        counter = 0;
        startTime = System.nanoTime();
        System.out.println("Bubble Sort");
        for(int j = 0; j < 1000; j = j * 10){
            heapSort();
            System.out.print(counter+"\r");
        }
        endTime = System.nanoTime();
        System.out.println((endTime - startTime)/100000 + " seconds," + (counter-1) + "iterations");
    }
    private static void test(){
        counter++;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < counter; i++){
            list.add((int)(Math.random() * 100000));
        }
        ScuffSort sort = new ScuffSort(list);
//        Collections.sort(list);
//        System.out.println(list.equals(sort.getSortedList()));
    }

    private static void heapSort(){
        counter++;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < counter; i++){
            list.add((int)(Math.random() * 100000));
        }
        ScuffSort sort = new ScuffSort(list);
//        Collections.sort(list);
//        System.out.println(list.equals(sort.getSortedList()));
    }
}
