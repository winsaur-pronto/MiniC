import java.io.*;
import java.util.Scanner;

public class scanner{
	
    private char currentChar;
    private byte currentKind;
    private StringBuffer currentSpelling;
    private Scanner in;
    public static int line;
    public static boolean status;
    private String path;

    public scanner(String path) throws FileNotFoundException {
        try{
            this.path = path;
            in = new Scanner(new File(path));
            in.useDelimiter("");
            currentChar = in.next().charAt(0);
            currentSpelling = new StringBuffer();
            line = 1;
            status = true;
        }
        catch(FileNotFoundException e){
            System.out.println(String.valueOf(path)+" is not found.");
            status = false;
        }
    }

    private void take(char expectedChar){
        if(currentChar == expectedChar){
            currentSpelling.append(currentChar);
            currentChar = in.next().charAt(0);
            //System.out.println("Check: "+currentSpelling);
        } else{
            System.out.println(path+":"+line+"->"+currentChar+" is not a "+expectedChar);
            System.exit(0);
        }
    }

    private void takeIt(){
        currentSpelling.append(currentChar);
        if(in.hasNext()){
            currentChar = in.next().charAt(0);
            //System.out.println("Check: "+currentSpelling);
        }
        else
            currentChar = '\0';
    }

    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    private boolean isLetter(char c){
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }
    
    public Token scan() throws IOException{
        while(currentChar == '\t' || currentChar == ' ' || currentChar == '\n' || currentChar == '{' || currentChar == '\r') 
            scanSeperator();
        currentSpelling = new StringBuffer("");
        currentKind = scanToken();
        return new Token(currentKind, currentSpelling.toString());
    }

    private byte scanToken() throws IOException{
        switch (currentChar) {
            case 'a': case 'b':case 'c': case 'd':case 'e': case 'f':case 'g': case 'h':
            case 'i': case 'j':case 'k': case 'l':case 'm': case 'n':case 'o': case 'p':
            case 'q': case 'r':case 's': case 't':case 'u': case 'v':case 'w': case 'x':
            case 'y': case 'z':
            case 'A': case 'B':case 'C': case 'D':case 'E': case 'F':case 'G': case 'H':
            case 'I': case 'J':case 'K': case 'L':case 'M': case 'N': case 'O': case 'P':
            case 'Q': case 'R':case 'S': case 'T':case 'U': case 'V':case 'W': case 'X':
            case 'Y': case 'Z':
           //     read names, keywords
            {
                takeIt();
                while (isLetter(currentChar)) {
                    takeIt();
                }
                return checkWords();
            }
            // read numbers
            case '0': case '1': case '2':case '3':case '4':case '5':case '6':
            case '7':case '8': case '9':
            {
                takeIt();
                while (isDigit(currentChar))
                    takeIt();
                return Token.INTLITERAL;
            }
            case '+': case '-': case '*': case '/': case '<': case '=':
            {
                takeIt();
                return Token.OPERATOR;
            }
                
            case ':':
            {
                takeIt();
                if(currentChar == '=')
                {
                    takeIt();
                    return Token.BECOME;
                }
                else {
                    return Token.COLON;
                }
            }
                
            case ';':  
            {
                takeIt();
                return Token.SEMICOLON;
            }
            
            case '{':  
            {
                takeIt();
                while (currentChar != '}') {
                    takeIt();
                }
                takeIt();
                return Token.LCOMMENT;
            }
                
            case '(' :
            {
                takeIt();
                return Token.LPAREN; // LPAREN
            }
                
            case ')' :
            {
                takeIt();
                return Token.RPAREN; // RPAREN
            }
            
            
            case '\0':
            {
                takeIt();
                return Token.EOT;
            }
            
            default:
            {
                System.out.println("Lexical Rule is error!");
                return 99;
            }
        }
    }

    private void scanSeperator(){
        if(currentChar == '\t' || currentChar == '\r' || currentChar == ' '){
            takeIt();
        }
        else if(currentChar == '\n'){
            line++;
            takeIt();
        }
        else if(currentChar == '{'){
            takeIt();
            while(currentChar != '}' && currentChar != '{' && currentChar != 0){
                takeIt();
                if(currentChar == '\n')
                    line++;
            }
            if(currentChar == '{'){
                System.out.println(path+":"+line+" error: Comment cannot be nested.");
                System.exit(0);
            }
            if(currentChar != 0)
                takeIt();
        }
        
    }
    private byte checkWords() throws IOException
    {
        if (currentSpelling.toString().toUpperCase().equals("ELSE"))
            return Token.ELSE;
        if (currentSpelling.toString().toUpperCase().equals("END"))
            return Token.END;
        if (currentSpelling.toString().toUpperCase().equals("IF"))
            return Token.IF;
        if (currentSpelling.toString().toUpperCase().equals("READ"))
            return Token.READ;
        if (currentSpelling.toString().toUpperCase().equals("REPEAT"))
            return Token.REPEAT;
        if (currentSpelling.toString().toUpperCase().equals("THEN"))
            return Token.THEN;
        if (currentSpelling.toString().toUpperCase().equals("UNTIL"))
            return Token.UNTIL;
        if (currentSpelling.toString().toUpperCase().equals("WRITE"))
            return Token.WRITE;
        if (currentSpelling.toString().toUpperCase().equals("EOT"))
            return Token.EOT;
        else return Token.IDENTIFIER;
    }
}