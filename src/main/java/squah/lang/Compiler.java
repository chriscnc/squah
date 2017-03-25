package squah.lang;

import java_cup.runtime.Symbol;

public class Compiler {

    interface Expr {
        public int interp();
    }

    static class AddExpr implements Expr {
        public final Expr l;
        public final Expr r;

        public AddExpr(Expr l, Expr r) {
            this.l = l;
            this.r = r;
        }

        public int interp() {
            return l.interp() + r.interp();
        }
    }

    static class IntExpr implements Expr {
        public final int n;

        public IntExpr(int n) {
            this.n = n;
        }

        public int interp() {
            return n;
        }
    }

    public static void main(String[] args) throws Exception {
        Parser p = new Parser(new Scanner(args[0]));
        Symbol s = p.parse();
        Expr ast = (Expr)s.value;
        System.out.println(ast.interp());
    }
}




