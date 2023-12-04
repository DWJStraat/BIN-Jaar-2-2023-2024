package week_1;

public class BogoSort {
    private int[] list;
    void bogoSort(int[] input){
        list = input;
        while(!sorted(list)){
            shuffle(list);
        }
    }

    private boolean sorted(int[] input) {
        for(int i = 1; i<input.length; i++){
            if(input[i] < input[i-1]){
                return false;
            }
        }
        return true;
    }

    private void shuffle(int[] input){
        for(int i = 0; i<input.length; i++){
            swap(i, (int)(Math.random() * i));
        }
    }

    private void swap(int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public int[] getList(){
        return list;
    }
}
