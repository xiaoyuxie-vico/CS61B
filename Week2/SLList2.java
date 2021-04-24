import edu.princeton.cs.introcs.In;

/*
Using sentinel as the first
 */

public class SLList2 {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public SLList2() {
        sentinel = new IntNode(63, null);
        size = 1;
    }

    public SLList2(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Add the first item in the list */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Returns the first item in the list */
    public int getFirst() {
        return sentinel.next.item;
    }

    /**
     * Returns the last item in the list
     * @return the last item
     */
    public int getLast() {
        if (sentinel.next == null) {
            return sentinel.item;
        }
        sentinel = sentinel.next;
        return getLast();
    }

    /**
     * Add an item to a list
     * @param args int x
     */
    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;

        /* Advance p to the end of the list. */
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

//    /** Returns the size of the list starting at IntNode p. */
//    private static int size(IntNode p) {
//        if (p.next == null) {
//            return 1;
//        }
//
//        return 1 + size(p.next);
//    }
//
//    public int size() {
//        return size(first);
//    }

    public int size() {
        return size;
    }

//    public static int deleteFirst() {
//
//    }

    public static void main(String[] args) {
        /** Create a list of one integer, namely 10 */
        SLList2 L = new SLList2(10);
        L.addFirst(10);
        System.out.println(L.getFirst());
        L.addFirst(5);
//        L.deleteFirst();
        System.out.println(L.getFirst());
        L.addLast(100);
        System.out.println(L.getLast());
        System.out.println(L.size());
    }
}
