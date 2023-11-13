package week_1;

import java.util.ArrayList;
import java.util.Collections;

public class heapSort {
    private ArrayList<Integer> list;



    private ArrayList<Integer> output_list = new ArrayList<>();
    public heapSort(ArrayList<Integer> input){
        list = input;
        sort();
    }

    private void sort(){
        for(int i = 0; i< list.size(); i++){
            int max = Collections.max(list);
            output_list.add(max);
        }
    }

    public ArrayList<Integer> getOutput_list() {
        return output_list;
    }
}
