public class Token {
    static String[] tokenTable;
    byte kind;		
    public String spelling;
    
    public Token (byte kind,String spelling) {
        this.kind = kind;
        this.spelling = spelling;
    }
        
    public static final byte  // token codes
        IDENTIFIER = 0,
        INTLITERAL = 1,
        OPERATOR   = 2,
        WRITE = 3,  // write
        IF = 4,     // if
        THEN = 5,   // then
        ELSE = 6,   // else
        END = 7,    // end
        REPEAT = 8, // repeat
        UNTIL = 9,  // until
        READ = 10,  // read
        SEMICOLON = 11, // ;
        LCOMMENT = 12,  // {
        RCOMMENT = 13,  // }
        BECOME = 14,    // :=
        COLON = 15,     // :
        EOT = 16,       //EOT
        LPAREN = 17,    // (
        RPAREN = 18,    // )
        EQUAL = 19;     // =
        
        
    private final static String[] spellings = {
        "<identifier>", "<integer-literal>", "<operator>", "write", "if",
        "then","else","end","repeat","until","read",";","{","}",":=",":","EOT",
        "(",")","="
    };    
    
    public String getSpelling(byte a)
    {
        return spellings[a];
    }
}
