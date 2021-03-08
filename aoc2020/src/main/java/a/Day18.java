package a;

import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

abstract class Node {
}

@Data
class Constant extends Node {
    Long n;

    public Constant(Long n) {
        this.n = n;
    }
}

class Op extends Node {
    String s;

    public Op(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}

@Data
class Expr extends Node {
    List<Node> nodes = new ArrayList<>();

    public void addNode(Node n) {
        nodes.add(n);
    }
}

public class Day18 {
    public long eval(String s) {
        System.out.println(s);
        return eval(parseExpr(s));
    }

    private long eval(Node n) {
        if (n instanceof Constant) {
            return ((Constant) n).getN();
        } else if (n instanceof Expr) {
            return eval((Expr) n);
        } else {
            throw new RuntimeException("Couldn't evaluate: " + n);
        }
    }

    private long eval(Node x, Op o, Node y) {
        if (o.toString().equals("+")) {
            return eval(x) + eval(y);
        } else if (o.toString().equals("*")) {
            return eval(x) * eval(y);
        } else {
            throw new RuntimeException("Invalid operation: " + o);
        }
    }

    private long eval(Expr e) {
        System.out.println(e);
        Constant x = new Constant(eval(e.getNodes().get(0)));
        int i = 1;
        while (i < e.getNodes().size()) {
            long n = eval(x, (Op) e.getNodes().get(i), e.getNodes().get(i + 1));
            x = new Constant(n);
            i += 2;
        }
        return x.getN();
    }

    public Expr parseExpr(String s) {
        Expr expr = new Expr();
        List<Expr> subExprs = new ArrayList<>();
        Expr currExpr = expr;
        for (char c : s.toCharArray()) {
            switch (c) {
                case ' ':
                    continue;
                case '(':
                    Expr e = new Expr();
                    currExpr.addNode(e);
                    subExprs.add(e);
                    currExpr = e;
                    break;
                case ')':
                    subExprs.remove(currExpr);
                    if (subExprs.size() > 0) {
                        currExpr = subExprs.get(subExprs.size() - 1);
                    } else {
                        currExpr = expr;
                    }
                    break;
                case '+':
                case '*':
                    currExpr.addNode(new Op(String.valueOf(c)));
                    break;
                default:
                    long x = Integer.parseInt(String.valueOf(c));
                    currExpr.addNode(new Constant(x));
            }
        }
        return expr;
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("inputs/18.txt"));
        Day18 d = new Day18();
        long sum = 0;
        for (String l : lines) {
            sum += d.eval(l);
        }
        System.out.println(lines.size());
        System.out.println(sum);
    }
}
