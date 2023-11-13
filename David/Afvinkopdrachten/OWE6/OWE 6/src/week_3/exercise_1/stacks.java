package week_3.exercise_1;

import java.util.Objects;
import java.util.Stack;

public class stacks {
    private final String code;
    private final Stack<Character> stack = new Stack<>();
    public boolean balanced = true;
    public stacks(String codeIn) {
        code = codeIn;
        iterate();
    }

    public void iterate() {
        for (int i = 0; i < code.length(); i++) {
            char character = code.charAt(i);
            if ("([{".indexOf(character) != -1) {
                start(character);
            } else if (")]}".indexOf(character) != -1) {
                if (!stop(String.valueOf(character))) {
                    balanced = false;
                    break;
                }
            }
        }
        if(!stack.isEmpty()){
            balanced = false;
        }
    }

    public void start(char character) {
        stack.add(character);
    }

    public boolean stop(String character) {
        String popped = String.valueOf(stack.pop());
        return switch (character) {
            case "}" -> Objects.equals(popped, "{");
            case "]" -> Objects.equals(popped, "[");
            case ")" -> Objects.equals(popped, "(");
            default -> false;
        };
    }
}