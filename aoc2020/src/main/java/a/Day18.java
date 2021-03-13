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

    private long eval(Node expr) {
        System.out.println(expr);
        if (expr instanceof Constant) {
            return ((Constant) expr).getN();
        } else if (expr instanceof Expr) {
            Expr e = (Expr) expr;
            Constant result = new Constant(eval(e.getNodes().get(0)));
            int i = 1;
            while (i < e.getNodes().size()) {
                Node x = result;
                Op op = (Op) e.getNodes().get(i);
                Node y = e.getNodes().get(i + 1);
                long n = 0;
                if (op.toString().equals("+")) {
                    n = eval(x) + eval(y);
                } else if (op.toString().equals("*")) {
                    n = eval(x) * eval(y);
                }
                result = new Constant(n);
                i += 2;
            }
            return result.getN();
        } else {
            throw new RuntimeException("Couldn't evaluate: " + expr);
        }
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
