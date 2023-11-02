package week_1;

import java.util.ArrayList;

public class ScuffSort {
    private ArrayList<Integer> list;
    private ArrayList<Integer> sortedList = new ArrayList<Integer>();
    public ScuffSort(ArrayList<Integer> list) {
        this.list = list;
        sort();
    }

    public ArrayList<Integer> getSortedList(){
        return sortedList;
    }

    public ArrayList<Integer> getList(){
        return list;
    }

    private void sort(){
        sortedList.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            int number = list.get(i);
            if(number > sortedList.get(sortedList.size()-1)){
                sortedList.add(number);
            }
            else {
                for (int j = 0; j < sortedList.size(); j++) {
                    int sortedNumber = sortedList.get(j);
                    if (number < sortedNumber || number == sortedNumber) {
                        sortedList.add(j, number);
                        break;
                    }
                }
            }
        }
    }
}
