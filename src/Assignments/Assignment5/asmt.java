package Assignments.Assignment5;


import Libraries.listNode;

import java.util.LinkedList;
import java.util.List;

public class asmt {

    private static class pair {
        public int w;
        public float v;

        public pair(int w, float v) {
            this.w = w;
            this.v = v;
        }
    }

    private static class pairNode {
        private pair val;
        private pairNode next;

        public pairNode(int w, float v) {
            this(new pair(w, v));
        }

        public pairNode(pair val) {
            this.val = val;
            this.next = null;
        }

        public pair getVal() {
            return val;
        }

        public pairNode getNext() {
            return next;
        }

        public void setNext(pairNode next) {
            this.next = next;
        }

        public void append(pairNode next) {
            if (this.next == null)
                this.next = next;
            else
                this.next.append(next);

        }

        public int length() {
            if (next == null)
                return 1;
            else
                return 1 + next.length();
        }
    }

    public static int fibDyn(int n) {
        if (n <= 0)
            return -1;
        if (n == 1)
            return 0;

        int[] arr = new int[n + 1];
        arr[1] = 0;
        arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }

    public static float knapsackR(pairNode pairs, int W) {
        if (pairs == null)
            return 0;

        float v = pairs.getVal().v;
        float w = pairs.getVal().w;
        pairs = pairs.getNext();

        if (W - w < 0)
            return knapsackR(pairs, W);
        else
            return Math.max(knapsackR(pairs, W), knapsackR(pairs, (int) (W - w)) + v);

    }


    public static float knapsack(pairNode pairs, int W) {
        if (pairs == null || W == 0)
            return 0;

        int nPairs = pairs.length();
        float[][] values = new float[nPairs + 1][W + 1];

        for (int cap = 0; cap <= W; cap++)
            values[0][cap] = 0;

        for (int i = 1; i <= nPairs; i++) {
            float v = pairs.getVal().v;
            int w = pairs.getVal().w;
            pairs = pairs.getNext();

            for (int cap = 0; cap <= W; cap++) {
                if (w > cap) {
                    values[i][cap] = values[i - 1][cap];
                }
                else {
                    values[i][cap] = Math.max(values[i - 1][cap], values[i - 1][cap - w] + v);
                }
            }
        }
        return values[nPairs][W];
    }

    public static List<Integer> knapsackContents(pairNode pairs, int W) {
        if (pairs == null || W == 0)
            return null;

        int nPairs = pairs.length();
        float[][] values = new float[nPairs + 1][W + 1];
        int[][][] chain = new int[nPairs + 1][W + 1][3];

        for (int cap = 0; cap <= W; cap++) {
            values[0][cap] = 0;
            chain[0][cap] = null;
        }

        for (int i = 1; i <= nPairs; i++) {
            float v = pairs.getVal().v;
            int w = pairs.getVal().w;
            pairs = pairs.getNext();

            for (int cap = 0; cap <= W; cap++) {
                if (w > cap) {
                    values[i][cap] = values[i - 1][cap];
                    chain[i][cap] = chain[i - 1][cap];
                }
                else {
                    float value = values[i - 1][cap - w] + v;
                    if (value < values[i - 1][cap]) {
                        values[i][cap] = values[i - 1][cap];
                        chain[i][cap] = chain[i - 1][cap];
                    }
                    else {
                        values[i][cap] = value;
                        int lastRow = i - 1;
                        int lastCol = cap - w;
                        chain[i][cap] = new int[]{i, lastRow, lastCol};
                    }

                    values[i][cap] = Math.max(values[i - 1][cap], values[i - 1][cap - w] + v);
                }
            }
        }

        /*
        for (int[][] row : chain) {
            for (int[] cell : row) {
                if (cell != null) {
                    System.out.printf("( %5d, %5d, %5d)\t", cell[0], cell[1], cell[2]);
                }
                else {
                    System.out.print("( -----, -----, -----)\t");
                }
            }
            System.out.println();
        }

         */

        List<Integer> toReturn = new LinkedList<>();
        int[] cell = chain[nPairs][W];
        while (cell != null) {
            toReturn.add(cell[0]);
            cell = chain[cell[1]][cell[2]];
        }

        return toReturn;
    }

    public static void main(String[] args) {
        pairNode pairs = new pairNode(1, 60);
        pairs.append(new pairNode(2, 100));
        pairs.append(new pairNode(3, 120));

        pairNode wiki = new pairNode(23, 505);
        wiki.append(new pairNode(26, 352));
        wiki.append(new pairNode(20, 458));
        wiki.append(new pairNode(18, 220));
        wiki.append(new pairNode(32, 354));
        wiki.append(new pairNode(27, 414));
        wiki.append(new pairNode(29, 498));
        wiki.append(new pairNode(26, 545));
        wiki.append(new pairNode(30, 473));
        wiki.append(new pairNode(27, 543));

        System.out.println(knapsackContents(wiki, 67));


    }
}
