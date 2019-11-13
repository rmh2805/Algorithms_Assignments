package Assignments.Assignment4;

import javax.naming.PartialResultException;
import javax.swing.text.FlowView;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class asmt {
    //========================================<Problem 1>=========================================//
    public static float max2(float a, float b) {
        float dif = a - b;
        if (abs(dif) == dif) {
            return a;
        }
        else {
            return b;
        }
    }


    //========================================<Problem 2>=========================================//
    public static Integer fSelect(List<Integer> list, int index) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }

        List<List<Integer>> lets = divLst(list);
        List<Integer> l = lets.get(0);
        List<Integer> s = lets.get(1);
        List<Integer> g = lets.get(2);

        if (index < l.size()) {
            return fSelect(l, index);
        }
        else if (index < l.size() + s.size()) {
            return s.get(0);
        }
        else {
            return fSelect(g, index - l.size() - s.size());
        }
    }

    private static List<List<Integer>> divLst(List<Integer> list) {
        List<Integer> l = new LinkedList<>();
        List<Integer> s = new LinkedList<>();
        List<Integer> g = new LinkedList<>();

        Integer x = list.get(0);
        for (Integer y : list) {
            if (y < x)
                l.add(y);
            else if (y.equals(x))
                s.add(y);
            else
                g.add(y);
        }

        List<List<Integer>> toReturn = new LinkedList<>();
        toReturn.add(l);
        toReturn.add(s);
        toReturn.add(g);

        return toReturn;
    }

    //========================================<Problem 3>=========================================//
    private static boolean checkEmptyList (List list) {
        if(list == null)
            return true;

        return list.size() == 0;
    }

    private static int partition(List<Integer> list, int lowBound, int highBound) {
        int pivot = list.get(highBound);
        int slowMark = lowBound - 1;

        for (int fastMark = lowBound; fastMark < highBound; fastMark++) {
            if (list.get(fastMark) <= pivot) {
                slowMark++;
                swap(list, slowMark, fastMark);
            }
        }

        swap(list, slowMark + 1, highBound);
        return slowMark + 1;
    }

    private static void quickSort(List<Integer> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<Integer> list, int lowBound, int highBound) {
        if (lowBound < highBound) {
            int pivot = partition(list, lowBound, highBound);
            quickSort(list, lowBound, pivot - 1);
            quickSort(list, pivot + 1, highBound);
        }
    }

    public static Integer iSelect(List<Integer> lst, int i) {
        if(checkEmptyList(lst))
            return null;

        int lowBound = 0;
        int highBound = lst.size() - 1;

        while (true) {
            int pivotIdx = iSelectHelper(lst, lowBound, highBound);
            int lCard = pivotIdx - lowBound;
            int gCard = highBound - pivotIdx;

            Integer pivot = lst.get(pivotIdx);

            if(i < lCard) {
                highBound = pivotIdx - 1;
            } else if (i == lCard) {
                return pivot;
            } else {
                lowBound = pivotIdx + 1;
            }
        }
    }

    public static int iSelectHelper(List<Integer> list, int lowBound, int highBound) {
        return partition(list, lowBound, highBound);
    }

    public static void swap(List<Integer> list, int a, int b) {
        Integer temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }




    //========================================<Test Cases>========================================//
    public static void testAll(List<Integer> lst) {
        for (int i = 0; i < lst.size(); i++) {
            testCase(lst, i);
        }
    }

    public static void testCase(List<Integer> lst, int i) {
        System.out.println("Select( lst, " + i + "):");
        System.out.println("\t" + fSelect(lst, i));
        System.out.println("\t" + iSelect(lst, i));
    }

    public static void main(String[] args) {
        List<Integer> lst = new LinkedList<>(Arrays.asList(2, 3, 17, 42, 55, 2, -54, -54, 42, 666, 1080));

        testAll(lst);

    }
}
