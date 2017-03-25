package squah.lang;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.Symbol;

public class Scanner {
    /* single lookahead character */
    protected static int nextChar;
    protected static InputStream in;

    public Scanner(String filename) throws Exception {
        in = new FileInputStream(filename);
    }

    /* we use a SymbolFactory to generate Symbols */
    private SymbolFactory sf = new DefaultSymbolFactory();

    /* advance input by one character */
    protected static void advance() throws IOException {
        nextChar = in.read(); 
    }

    /* initialzie the scanner */
    public static void init() throws Exception {
        advance();
    }

    /* recongnize and return the next complete token */
    public Symbol nextToken() throws IOException {
        for (;;)
            switch (nextChar) {
                case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                    int iVal = 0;
                    do {
                        iVal = iVal * 10 + (nextChar - '0');
                        advance();
                    } while (nextChar >= '0' && nextChar <= '9');
                    return sf.newSymbol("INT", ParserSym.INT, new Integer(iVal));

                case -1: 
                    return sf.newSymbol("EOF", ParserSym.EOF);

                default:
                    /* in this simple scanner we just ignore everyting else */
                    advance();
                    break;
            }
    }
}


