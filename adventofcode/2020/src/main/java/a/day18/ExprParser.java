package a.day18;

import java.util.ArrayList;
import java.util.List;

public class ExprParser {
    public static Expr parseExpr(String s) {
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

}
