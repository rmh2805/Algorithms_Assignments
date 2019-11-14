package Libraries;

public class listNode {
    private Integer val;
    private listNode next;

    public static listNode makeList(Integer[] arr) {
        listNode node = null;

        for(int i = arr.length - 1; i >= 0; i--) {
            node = new listNode(arr[i], node);
        }

        return node;
    }

    public listNode(Integer val, listNode next) {
        this.val = val;
        this.next = next;
    }

    public void setNext(listNode next) {
        this.next = next;
    }

    public void append(listNode app) {
        if (this.next == null)
            this.next = app;
        else
            this.next.append(app);
    }

    public Integer getVal() {
        return this.val;
    }

    public listNode getNext() {
        return this.next;
    }

    public static Integer car(listNode in) {
        return in.getVal();
    }

    public static listNode cdr(listNode in) {
        return in.getNext();
    }

    public String toString() {
        StringBuilder str = new StringBuilder(this.val + " :: ");

        if(this.next == null)
            str.append("null");
        else
            str.append(this.next.toString());

        return str.toString();
    }

    public void printList() {
        System.out.println(this);
    }

    public listNode copy() {
        listNode toReturn = new listNode(this.val, null);

        if(this.next != null)
            toReturn.setNext(this.next.copy());

        return toReturn;
    }
}
