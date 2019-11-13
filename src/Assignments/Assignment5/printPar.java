package Assignments.Assignment5;

public class printPar {
    public static int extraSpace(String[] words, int max, int i, int j) {
        if (words == null) {
            return -1;
        }
        if (i > j) {
            return max + 1;
        }

        return extraSpace(words, max - words[i].length() - 1, i + 1, j);
    }

    public static int badnessLine (String[] words, int max, int i, int j) {
        return -1;
    }

    public static void main(String[] args) {
        String foo = "The people have nothing to lose but their chains";
        int max = 40;

        String[] data = foo.split(" ");
        System.out.print(extraSpace(data, max, 0, 6));

    }
}
