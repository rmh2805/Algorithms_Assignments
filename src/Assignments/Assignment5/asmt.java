package Assignments.Assignment5;


public class asmt {

    private static class pair{
        public float w;
        public float v;

        public pair(float w, float v) {
            this.w = w;
            this.v = v;
        }
    }

    private static class pairNode{
        private pair val;
        private pairNode next;

        public pairNode(float w, float v) {
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
            if(this.next == null)
                this.next = next;
            else
                this.next.append(next);

        }

        public int length () {
            if(next == null)
                return 1;
            else
                return 1 + next.length();
        }
    }

    public static int fibDyn(int n) {
        if(n <= 0)
            return -1;
        if(n == 1)
            return 0;

        int[] arr = new int[n+1];
        arr[1] = 0;
        arr[2] = 1;
        for(int i = 3; i <= n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }

        return arr[n];
    }

    public static float knapsackR(pairNode pairs, float W) {
        if(pairs == null)
            return 0;

        float v = pairs.getVal().v;
        float w = pairs.getVal().w;
        pairs = pairs.getNext();

        if(W - w < 0)
            return knapsackR(pairs, W);
        else
            return Math.max(knapsackR(pairs, W), knapsackR(pairs, W - w) + v);

    }

    public static float knapsack(pairNode pairs, float W) {


        return -1;
    }

    public static void main(String[] args) {
        pairNode pairs = new pairNode(10, 60);
        pairs.append(new pairNode(20, 100));
        pairs.append(new pairNode(30, 120));

        System.out.println(knapsackR(pairs, 45));
    }
}
