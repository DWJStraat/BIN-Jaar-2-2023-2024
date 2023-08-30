public class Week_1_2_a {
    public static void run(String fname) {
        System.out.println("Hello " + fname + "!");
    }

    public static void main(String[] args) {
        try {
            run(args[0]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a name as an argument.");
        }
    }
}