package Libraries;

public class listNode {
    private Integer val;
    private listNode next;

    public listNode (Integer val, listNode next) {
        this.val = val;
        this.next = next;
    }

    public void setNext (listNode next) {
        this.next = next;
    }

    public void append(listNode app) {
        if(this.next == null)
            this.next = app;
        else
            this.next.append(app);
    }

    public Integer getVal () {
        return this.val;
    }

    public listNode getNext () {
        return this.next;
    }

    public void printList() {
        System.out.print(this.val + " :: ");
        if(this.next == null)
            System.out.println("null");
        else
            this.next.printList();
    }

    public listNode copy() {
        listNode toReturn = new listNode(this.val, null);
        toReturn.setNext(this.next.copy());

        return toReturn;
    }
}
