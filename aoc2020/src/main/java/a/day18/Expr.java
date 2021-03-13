package a.day18;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Expr extends Node {
    List<Node> nodes = new ArrayList<>();

    public void addNode(Node n) {
        nodes.add(n);
    }
}
