package week_5;
import headacheRemoval.createWindow;

import javax.swing.*;
import java.util.*;

public class gui {
    private static final createWindow window = new createWindow("Gene finder", 650, 700);
    private static final JTextArea entry1 = new JTextArea();
    private static final JTextArea entry2 = new JTextArea();
    private static final JTextArea entry3 = new JTextArea();
    private static JButton calculate;
    private static JComboBox dropdown;
    private static final JLabel result = new JLabel();
    private static final HashMap<String, String[]> seqs = new HashMap<>();


    public static void main(String[] args) {
        build();
    }

    private static void build(){
        window.add(entry1, 10, 20, 200, 500);
        window.add(entry2, 220, 20, 200, 500);
        window.add(entry3, 430, 20, 200, 500);
        calcbuilder();
        dropdownbuilder();
        window.add(result, 10, 600, 200, 20);
        window.show();
    }

    private static void calcbuilder() {
        calculate = new JButton("Calculate");
        calculate.addActionListener(e -> {
            if(dropdown.getSelectedItem().equals("all sequences")){
                calculate(true, true, true);
            }
            else if(dropdown.getSelectedItem().equals("seq1 & seq2")){
                calculate(true, true, false);
            }
            else if(dropdown.getSelectedItem().equals("seq1 & seq3")){
                calculate(true, false, true);
            }
            else if(dropdown.getSelectedItem().equals("seq2 & seq3")){
                calculate(false, true, true);
            }
            else{
                throw new RuntimeException("Invalid dropdown selection");
            }
        });
        window.add(calculate, 10, 530, 100, 20);
    }

    private static void dropdownbuilder() {
        String[] options = {"all sequences", "seq1 & seq2", "seq1 & seq3", "seq2 & seq3"};
        dropdown = new JComboBox(options);
        window.add(dropdown, 10, 560, 100, 20);
    }

    private static void calculate(Boolean one, Boolean two, Boolean three) {
        String[] seq1 = entry1.getText().split("\n");
        String[] seq2 = entry2.getText().split("\n");
        String[] seq3 = entry3.getText().split("\n");
        seqs.put("seq1", seq1);
        seqs.put("seq2", seq2);
        seqs.put("seq3", seq3);
        Set<String> duplicates = findDuplicates(one, two, three);
        result.setText("Found " + duplicates.size() + " duplicates: " + duplicates);
    }

    private static Set<String> findDuplicates(Boolean one, Boolean two, Boolean three){
        Set<String> duplicates = new HashSet<>();
        int counter = 0;
        if(one){duplicates.addAll(Arrays.asList(seqs.get("seq1"))); }
        if(two){ duplicates.addAll(Arrays.asList(seqs.get("seq2")));}
        if(three){ duplicates.addAll(Arrays.asList(seqs.get("seq3")));}
        System.out.println(duplicates);
        if(one){duplicates.retainAll(Arrays.asList(seqs.get("seq1")));}
        if(two){ duplicates.retainAll(Arrays.asList(seqs.get("seq2")));}
        if(three){ duplicates.retainAll(Arrays.asList(seqs.get("seq3")));}
        System.out.println(duplicates);
        return duplicates;
    }

}


