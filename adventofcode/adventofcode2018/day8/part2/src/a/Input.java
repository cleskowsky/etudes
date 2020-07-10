package a;

import java.util.ArrayList;
import java.util.List;

class Input {
    public static Node parse(String s) {
        List<Integer> data = new ArrayList<>();
        for (String n : s.split(" ")) {
            data.add(new Integer(n));
        }
        Input i = new Input();
        return i.readNode(data);
    }

    private Node readNode(List<Integer> in) {
        // a node looks like k, n, (k kids), (n metadata items)
        int k = in.remove(0);
        int n = in.remove(0);

        List<Node> kids = new ArrayList<>();
        List<Integer> metas = new ArrayList<>();
        Node root = new Node(kids, metas);

        // read kids
        for (int i = 0; i < k; i++) {
            kids.add(readNode(in));
        }

        // read metas
        for (int i = 0; i < n; i++) {
            metas.add(in.remove(0));
        }

        return root;
    }
}
