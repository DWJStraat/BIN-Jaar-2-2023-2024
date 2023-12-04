package week_3.exercise_2;

import headacheRemoval.openFile;

import java.text.Collator;
import java.util.ArrayList;
import java.util.LinkedList;

public class parser {
    private final openFile file;
    private final boolean arrayList;

    private final ArrayList<gene> geneArrayList = new ArrayList<>();
    private final LinkedList<gene> geneLinkedList = new LinkedList<>();
    private String[] contents;

    public parser() {
        file = new openFile();
        arrayList = false;
        readFile();
    }

    public parser(String type) {
        file = new openFile();
        arrayList = type.equals("Arraylist");
        readFile();
    }

    private void readFile() {
        contents = file.readFileToArray();
        if (arrayList) {
            readToArrayList();
        } else {
            readToLinkedList();
        }
    }

    private void readToLinkedList() {
        for (String content:contents) {
            geneLinkedList.add(new gene(content));
        }
        geneLinkedList.sort((o1, o2) -> Collator.getInstance().compare(o1, o2));
    }

    private void readToArrayList() {
        for (String content : contents) {
            geneArrayList.add(new gene(content));
        }
        geneArrayList.sort((o1, o2) -> Collator.getInstance().compare(o1,o2));
    }
}