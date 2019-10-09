package Assignments.Assignment0;
import Libraries.*;

public class asmt {
    public static void main(String[] args) {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;

        listNode fwd = new listNode(i1, new listNode(i2, new listNode(i3, null)));
        listNode bwd = new listNode(i3, new listNode(i2, new listNode(i1, null)));

        listNode coins = new listNode(100, new listNode(50, new listNode(25, null)));
        coins.append(new listNode(10, new listNode(5, new listNode(1, null))));

        System.out.println("\n\t====<Testing listPrint>=====");
        System.out.print("fwd = ");
        fwd.printList();
        System.out.print("bwd = ");
        bwd.printList();
        System.out.print("coins = ");
        coins.printList();

        System.out.println("\n\t=====<Testing reversal>=====");
        System.out.print("r([1, 2, 3]) = ");
        r(fwd).printList();
        System.out.print("r([3, 2, 1]) = ");
        r(bwd).printList();
        System.out.print("r(coins) = ");
        r(coins).printList();

        System.out.println("\n\t=====<Testing Product>======");
        System.out.println("prod(0, 4) = " + prod(0, 4));
        System.out.println("prod(4, 0) = " + prod(4, 0));
        System.out.println("prod(7, 6) = " + prod(7, 6));
        System.out.println("prod(-7, 6) = " + prod(-7, 6));

        System.out.println("\n\t=====<Testing fastPow>======");
        System.out.println("fastPow(2, 0) = " + fastPow(2, 0));
        System.out.println("fastPow(2, 4) = " + fastPow(2, 4));
        System.out.println("fastPow(2, 10) = " + fastPow(2, 10));

        System.out.println("\n\t====<Testing prodAccum>=====");
        System.out.println("prodAccum(0, 4) = " + prodAccum(0, 4, 0));
        System.out.println("prodAccum(4, 0) = " + prodAccum(4, 0, 0));
        System.out.println("prodAccum(7, 6) = " + prodAccum(7, 6, 0));
        System.out.println("prodAccum(-7, 6) = " + prodAccum(-7, 6, 0));

        System.out.println("\n\t====<Testing minChange>=====");
        System.out.println("minChange(100, coins) = " + minChange(100, coins));
        System.out.println("minChange(49, coins) = " + minChange(49, coins));
        System.out.println("greedyMinChange(100, coins) = " + greedyMinChange(100, coins));
        System.out.println("greedyMinChange(49, coins) = " + greedyMinChange(49, coins));

        System.out.println("\n\t======<Testing powIt>======");
        System.out.println("powIt(2, 0) = " + powIt(2, 0));
        System.out.println("powIt(2, 4) = " + powIt(2, 4));
        System.out.println("powIt(2, 10) = " + powIt(2, 10));
        System.out.println("powIt(2, 16) = " + powIt(2, 16));

        System.out.println("\n\t======<Testing sSort>=======");
        System.out.print("sSort([3, 2, 1]) = ");
        sSort(fwd).printList();
        System.out.print("sSort([1, 2, 3]) = ");
        sSort(bwd).printList();
        System.out.print("sSort(coins) = ");
        sSort(coins).printList();

    }

    public static listNode r(listNode root) {
        if (root == null)
            return null;

        listNode x = root.copy();
        listNode xs = x.getNext();

        x.setNext(null);
        xs = r(xs);

        if (xs == null)
            return x;
        else {
            xs.append(x);
            return xs;
        }
    }

    public static Integer prod(Integer m, Integer n) {
        if (m == 0)
            return 0;
        else if (m < 0)
            return prod(m + 1, n) - n;
        else
            return prod(m - 1, n) + n;
    }

    public static Integer fastPow(Integer b, Integer k) {
        if (k <= 0)
            return 1;
        else if (k % 2 == 0)
            return fastPow(b * b, k / 2);
        else
            return fastPow(b * b, k / 2) * b;
    }

    public static Integer prodAccum(Integer m, Integer n, Integer a) {
        if (m == 0)
            return a;
        else if (m > 0)
            return prodAccum(m - 1, n, n + a);
        else
            return prodAccum(m + 1, n, a - n);
    }

    public static Integer minChange(Integer a, listNode d) {
        if (a == 0)
            return 0;
        if (d == null)
            return null;

        listNode ds = d.getNext();
        if (d.getVal() > a)
            return minChange(a, ds);
        else
            return min(fPlus(1, minChange(a - d.getVal(), d)), minChange(a, ds));
    }

    public static Integer greedyMinChange(Integer a, listNode d) {
        if (a == 0)
            return 0;
        if (d == null)
            return null;

        listNode ds = d.getNext();

        if (d.getVal() > a)
            return greedyMinChange(a, ds);
        else
            return fPlus(quotient(a, d.getVal()), greedyMinChange(remainder(a, d.getVal()), ds));
    }

    public static Integer powIt (Integer b, Integer n) {
        Integer a = 1;
        for(int i = n; i >= 1; i--) {
            a = b*a;
        }
        return a;
    }

    //====================<Regular Problems>====================//
    public static listNode sSort(listNode x) {
        x = x.copy();
        if(x == null)
            return null;

        Integer minVal = fMin(x.getNext(), x.getVal());
        return new listNode (minVal, sSort(rVal(x, minVal)));
    }

    public static Integer fMin (listNode x, Integer m) {
        if(x == null)
            return m;

        listNode xs = x.getNext();
        if (m <= x.getVal())
            return fMin(xs, m);
        else
            return fMin(xs, x.getVal());
    }

    public static listNode rVal (listNode x, Integer v) {
        if (x == null)
            return null;

        listNode xs = x.getNext();
        if(x.getVal() == v)
            return xs;
        else {
            x.setNext(rVal(xs, v));
            return x;
        }

    }

    //====================<Helper Functions>====================//
    public static Integer min(Integer l, Integer r) {
        if (l == null)
            return r;
        else if (r == null)
            return l;
        else if (l <= r)
            return l;
        else
            return r;
    }

    public static Integer fPlus(Integer a, Integer b) {
        if (a == null || b == null)
            return null;
        else
            return a + b;
    }

    public static Integer quotient(Integer a, Integer b) {
        if (a == null || b == null || b == 0)
            return null;
        else
            return a / b;
    }

    public static Integer remainder(Integer a, Integer b) {
        if (a == null || b == null || b == 0)
            return null;
        else
            return a - b * quotient(a, b);
    }
}


