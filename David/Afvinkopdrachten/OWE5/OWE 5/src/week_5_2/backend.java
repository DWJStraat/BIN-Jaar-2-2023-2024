package week_5_2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class backend {
    static String input;
    static String lang;
    static ArrayList<String> sentences;

    static ArrayList<String> splitInput;
    static String output;

    public static void main(String[] args) {
        input = args[0];
        lang = args[1];
        loadCsv("sentences.csv");
        splitInput();
        for (int i = 0; i < splitInput.size(); i++) {
            filter(i);
        }
        output = output();
    }

    public static void loadCsv(String path) {
        sentences = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String langFound = line.split("\t")[1];
                String sentence = line.split("\t")[2];
                if (langFound.equals(lang))
                    sentences.add(sentence);
            }
        } catch (FileNotFoundException e) {
            try{
                loadCsv("./src/week_5_2/sentences.csv");
            } catch (Exception ex) {
                System.out.println("File not found");
            }
        }
    }

    public static void splitInput () {
        splitInput = new ArrayList<>(List.of(input.split(" ")));
    }

    public static void filter(int i) {
        String word = splitInput.get(i);
        sentences.removeIf(sentence -> !sentence.contains(word));
    }

    public static void big_filter() {
        sentences.removeIf(sentence -> !sentence.contains(input));
    }

    public static String output() {
        java.util.Random random = new java.util.Random();
        if (sentences.size() == 0) {
            throw new RuntimeException("NoMatchException");
        } else {
            int sentencePicked = random.nextInt(sentences.size());
            String output = sentences.get(sentencePicked);
            System.out.println(output);
            return output;
        }
    }
}