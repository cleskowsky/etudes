package a;

import java.util.List;

class Node {
    private List<Node> kids;
    private List<Integer> metas;

    public Node(List<Node> kids, List<Integer> metas) {
        this.kids = kids;
        this.metas = metas;
    }

    /**
     * Returns the number of nodes in subtree rooted at node
     *
     * @return Count of subtree nodes
     */
    public Integer count() {
        int cnt = 0;
        // count kids
        for (Node k : kids) {
            cnt += k.count();
        }
        // count myself
        return cnt + 1;
    }

    public Integer sum() {
        // Local variables are slightly different; the compiler never assigns a default value to an uninitialized local variable. If you cannot initialize your local variable where it is declared, make sure to assign it a value before you attempt to use it. Accessing an uninitialized local variable will result in a compile-time error.
        // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
        int sum = 0;
        // get kids sum
        for (Node k : kids) {
            sum += k.sum();
        }
        // add my sum
        for (Integer i : metas) {
            sum += i;
        }
        return sum;
    }

    public Integer value() {
        int val = 0;

        if (kids.isEmpty()) {
            for (Integer n : metas) {
                val += n;
            }
        } else {
            for (Integer n : metas) {
                try {
                    val += kids.get(n-1).value();
                } catch (IndexOutOfBoundsException ex) {
                    // don't touch val
                }
            }
        }

        return val;
    }
}
