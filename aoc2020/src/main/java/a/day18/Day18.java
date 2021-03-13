package a.day18;

public class Day18 {
    public long eval(String s) {
        System.out.println(s);
        return eval(ExprParser.parseExpr(s));
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
}
