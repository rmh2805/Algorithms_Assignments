package Assignments.Assignment5;

import java.util.LinkedList;
import java.util.List;

public class printPar {
    public static int extraSpace(String[] words, int max, int i, int j) {

        if (i >= j)
            return -1;

        int len = -1;
        for (int k = i; k < j; k++) {
            len += 1 + words[k].length();
        }
        return max - len;
    }


    public static int badnessLine(String[] words, int max, int i, int j) {
        int k = extraSpace(words, max, i, j);
        if (k < 0)
            return max + 1;
        else
            return k;
    }


    public static int minBad(String[] S, int M, int i) {
        if (S == null)
            return M + 1;


        int nWords = S.length;

        if (badnessLine(S, M, i, nWords) < M) {
            return 0;
        }

        int mB = M + 1;
        for (int k = i + 1; k < nWords; k++) {
            mB = Math.min(mB, Math.max(badnessLine(S, M, i, k), minBad(S, M, k)));
        }

        return mB;
    }

    public static int minBadDynamic(String[] S, int M) {
        int inf = M + 1;    //Since we can't have a negative length, you can't have a badness greater than

        if (S == null)
            return inf;

        int nWords = S.length;
        int[][] lines = new int[nWords][nWords + 1];

        for (int i = 0; i < nWords; i++) {
            for (int k = 0; k <= nWords; k++) {
                if (badnessLine(S, M, i, nWords) < M) {
                    //lines[i][k] = 0;
                } else if (k < i) {
                    lines[i][k] = -1;
                }
                else {
                    lines[i][k] = inf;
                }
            }


        }

        for (int i = nWords - 1; i >= 0; i--) {
            for (int k = i + 1; k <= nWords; k++) {
                if (lines[i][k] == 0)
                    continue;

                int lhs = badnessLine(S, M, i, k);
                if (lhs > M) {
                    lines[i][k] = inf;
                }
                else if (k >= nWords) { //Legal to the end of the line
                    lines[i][k] = 0;
                }
                else {
                    int rhs = inf;
                    for (int j = k + 1; j < nWords; j++) {
                        rhs = Math.min(rhs, lines[k][j]);
                    }
                    lines[i][k] = Math.max(lhs, rhs);
                }
            }
        }


        /*
        for (int[] row : lines) {
            for (int cell : row) {
                if (cell < 0) {
                    System.out.print("###");
                }
                else if (cell > M) {
                    System.out.print("-o-");
                }
                else {
                    System.out.printf("%2d ", cell);
                }
                System.out.print('\t');
            }
            System.out.println();
        }

         */

        int min = inf;
        for(int k = 0; k <= nWords; k++) {
            min = Math.min(min, lines[0][k]);
        }

        return min;
    }

    public static List<Integer> minBadDynamicChoice (String[] S, int M) {
        int inf = M + 1;    //Since we can't have a negative length, you can't have a badness greater than

        if (S == null)
            return null;

        int nWords = S.length;
        int[][] lines = new int[nWords][nWords + 1];
        int[][] lineEnds = new int[nWords][nWords + 1];

        for (int i = 0; i < nWords; i++) {
            for (int k = 0; k <= nWords; k++) {
                if (badnessLine(S, M, i, nWords) < M) {
                    //lines[i][k] = 0;
                } else if (k < i) {
                    lines[i][k] = -1;
                    lineEnds[i][k] = -1;
                }
                else {
                    lines[i][k] = inf;
                    lineEnds[i][k] = inf;
                }
            }


        }

        for (int i = nWords - 1; i >= 0; i--) {
            for (int k = i + 1; k <= nWords; k++) {
                if (lines[i][k] == 0)
                    continue;

                int lhs = badnessLine(S, M, i, k);
                if (lhs > M) {
                    lines[i][k] = inf;
                }
                else if (k >= nWords) { //Legal to the end of the line
                    lines[i][k] = 0;
                    lineEnds[i][k] = -1;
                }
                else {
                    int rhs = inf;
                    for (int j = k + 1; j < nWords; j++) {
                        if(rhs > lines[k][j]) {
                            rhs = lines[k][j];
                            lineEnds[i][k] = j;
                        }
                    }
                    lines[i][k] = Math.max(lhs, rhs);
                }
            }
        }


        /*
        for (int[] row : lineEnds) {
            for (int cell : row) {
                if (cell < 0) {
                    System.out.print("###");
                }
                else if (cell >= nWords) {
                    System.out.print("-o-");
                }
                else {
                    System.out.printf("%2d ", cell);
                }
                System.out.print('\t');
            }
            System.out.println();
        }
         */

        List<Integer> toReturn = new LinkedList<>();
        int min = inf;
        int lineEnd = -1;
        for(int k = 0; k <= nWords; k++) {
            if(min > lines[0][k]) {
                min = lines[0][k];
                lineEnd = k;
            }
        }

        toReturn.add(min);

        int lineStart = 0;
        while (lines[lineStart][lineEnd] > 0) {
            toReturn.add(lineEnd);
            int temp = lineEnds[lineStart][lineEnd];
            lineStart = lineEnd;
            lineEnd = temp;
        }


        return toReturn;
    }

    public static int printParagraph(String[] S, int M) {
        List<Integer> cuts = minBadDynamicChoice(S, M);
        int badness = cuts.remove(0);

        int lineStart = 0;
        for(int cut: cuts) {
            for(int i = lineStart; i < cut; i++) {
                System.out.print(S[i]);
                System.out.print(' ');
            }
            System.out.println();
            lineStart = cut;
        }

        return badness;
    }

    public static void main(String[] args) {
        String foo = "the end, enumeration will be somewhat faster, because while both " +
                "permutation and iteration cover all possible permutations, recursion requires the additional time " +
                "and space overhead as any function call,while permutation enumeration can be done iteratively.";
        String bar = "foo bar, baz biz bing bang bong";

        String modernity = "I am the very model of a modern Major-General\n" +
                "I've information vegetable, animal, and mineral\n" +
                "I know the kings of England, and I quote the fights\n" +
                "historical\n" +
                "From Marathon to Waterloo, in order categorical\n" +
                "I'm very well acquainted, too, with matters\n" +
                "mathematical\n" +
                "I understand equations, both the simple and quadratical\n" +
                "About binomial theorem I'm teeming with a lot o' news\n" +
                "With many cheerful facts about the square of the\n" +
                "hypotenuse\n" +
                "With many cheerful facts about the square of the\n" +
                "hypotenuse\n" +
                "With many cheerful facts about the square of the\n" +
                "hypotenuse\n" +
                "With many cheerful facts about the square of the\n" +
                "hypotepotenuse\n" +
                "I'm very good at integral and differential calculus\n" +
                "I know the scientific names of beings animalculous\n" +
                "In short, in matters vegetable, animal, and mineral\n" +
                "I am the very model of a modern Major-General\n" +
                "In short, in matters vegetable, animal, and mineral\n" +
                "He is the very model of a modern Major-General\n" +
                "I know our mythic history, King Arthur's and Sir\n" +
                "Caradoc's\n" +
                "I answer hard acrostics, I've a pretty taste for\n" +
                "paradox\n" +
                "I quote in elegiacs all the crimes of Heliogabalus\n" +
                "In conics I can floor peculiarities parabolous\n" +
                "I can tell undoubted Raphaels from Gerard Dows and\n" +
                "Zoffanies\n" +
                "I know the croaking chorus from the Frogs of\n" +
                "Aristophanes\n" +
                "Then I can hum a fugue of which I've heard the music's\n" +
                "din afore\n" +
                "And whistle all the airs from that infernal nonsense\n" +
                "Pinafore\n" +
                "And whistle all the airs from that infernal nonsense\n" +
                "Pinafore\n" +
                "And whistle all the airs from that infernal nonsense\n" +
                "Pinafore\n" +
                "And whistle all the airs from that infernal nonsense\n" +
                "Pinapinafore\n" +
                "Then I can write a washing bill in Babylonic cuneiform\n" +
                "And tell you ev'ry detail of Caractacus's uniform\n" +
                "In short, in matters vegetable, animal, and mineral\n" +
                "I am the very model of a modern Major-General\n" +
                "In short, in matters vegetable, animal, and mineral\n" +
                "He is the very model of a modern Major-General\n" +
                "In fact, when I know what is meant by \"mamelon\" and\n" +
                "\"ravelin\"\n" +
                "When I can tell at sight a Mauser rifle from a javelin\n" +
                "When such affairs as sorties and surprises I'm more\n" +
                "wary at\n" +
                "And when I know precisely what is meant by\n" +
                "\"commissariat\"\n" +
                "When I have learnt what progress has been made in\n" +
                "modern gunnery\n" +
                "When I know more of tactics than a novice in a nunnery\n" +
                "In short, when I've a smattering of elemental strategy\n" +
                "You'll say a better Major-General had never sat a gee\n" +
                "You'll say a better Major-General had never sat a gee\n" +
                "You'll say a better Major-General had never sat a gee\n" +
                "You'll say a better Major-General had never sat a sat a\n" +
                "gee\n" +
                "For my military knowledge, though I'm plucky and\n" +
                "adventury\n" +
                "Has only been brought down to the beginning of the\n" +
                "century\n" +
                "But still, in matters vegetable, animal, and mineral\n" +
                "I am the very model of a modern Major-General\n" +
                "But still, in matters vegetable, animal, and mineral\n" +
                "He is the very model of a modern Major-General";

        int max = 80;

        String[] data = modernity.strip().split("\\s");

        System.out.println(printParagraph(data, max));

    }
}

/*
the end, enumeration will be somewhat faster, because while both permutation
and iteration cover all possible permutations, recursion requires the additional
time and space overhead as any function call,while permutation enumeration can
 */
