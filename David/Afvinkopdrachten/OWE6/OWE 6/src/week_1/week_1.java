package week_1;

import java.util.ArrayList;
import java.util.Collections;

public class week_1 {
    private static int counter = 1;
    public static void main(String[] args) {
        System.out.println("Hello World!");

        for(int j = 0; j < 1000; j++){
            long startTime = System.nanoTime();
            test();
            long endTime = System.nanoTime();
            System.out.println((endTime - startTime)/10000 + "," + counter);
        }
    }
    private static void test(){
        counter++;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < counter; i++){
            list.add((int)(Math.random() * 100000));
        }
        ScuffSort sort = new ScuffSort(list);
        Collections.sort(list);
//        System.out.println(list.equals(sort.getSortedList()));
    }
}
