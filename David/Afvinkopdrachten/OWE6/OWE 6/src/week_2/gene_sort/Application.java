package week_2.gene_sort;

import headacheRemoval.openFile;

import java.util.ArrayList;
import java.util.Collections;

public class Application {
    private static ArrayList<Gene> sortedList;
    private static openFile file;
    private static ArrayList<Gene> list = new ArrayList<Gene>();
    private static String[] contents;
    public static void main(String[] args){
        if (args.length == 0){
            file = new openFile();
        }
        else{
            file = new openFile(args[0]);
        }
        readFile();
    }

    public static void readFile(){
        contents = file.readFileToArray();
        for(int i = 1; i < contents.length; i++){
            list.add(new Gene(contents[i]));
        }
        sortedList = list;
        Collections.sort(sortedList);
    }


    public static ArrayList<Gene> getSortedList() {
        return sortedList;
    }

    public static void setSortedList(ArrayList<Gene> sortedList) {
        Application.sortedList = sortedList;
    }

    public static openFile getFile() {
        return file;
    }

    public static void setFile(openFile file) {
        Application.file = file;
    }

    public static ArrayList<Gene> getList() {
        return list;
    }

    public static void setList(ArrayList<Gene> list) {
        Application.list = list;
    }

    public static String[] getContents() {
        return contents;
    }

    public static void setContents(String[] contents) {
        Application.contents = contents;
    }
}
