import java.io.FileNotFoundException;
import java.io.IOException;

public class Parser {
    private Token currentToken;
    private scanner scanner;
    public boolean status;
    private String filePath;

    public Parser(String filePath) throws FileNotFoundException{
        this.filePath = filePath;
        scanner = new scanner(filePath);
        status = true;
        if(!scanner.status){
            status = false;
        }
    }

    private void accept(byte expectedKind) throws IOException{
        if(currentToken.kind == expectedKind)
            currentToken = scanner.scan();
        else{
            System.exit(0);
        }
    }
    private void acceptIt() throws IOException{
        currentToken = scanner.scan();
    }

    public Program parse() throws IOException{
        currentToken = scanner.scan();
        Program pgAST = parseProgram();
        if(currentToken.kind!=Token.EOT){
            System.out.println(filePath+":"+scanner.line+" error: End of file is missing.");
            System.exit(0);
        }
        pgAST.Print();
        return pgAST;
    }
    private Program parseProgram() throws IOException {
        Program pgAST = new Program(parseStatementSeq());
        return pgAST;
    }
    private Statement parseStatementSeq() throws IOException {
        Statement stmt1AST = parseStatement();
        while(currentToken.kind==Token.SEMICOLON){
            acceptIt();
            Statement stmt2AST = parseStatement();
            stmt1AST = new StmtSequence(stmt1AST,stmt2AST);
        }
        return stmt1AST;
    }
    private Statement parseStatement() throws IOException {
        Statement stmtAST = null;
        Expression eAST;
        Vname vAST;
        switch(currentToken.kind){
            case Token.IF:
                acceptIt();
                eAST = parseExpression();
                accept(Token.THEN);
                Statement stmt1AST = parseStatementSeq();
                stmtAST = new IfStmt(eAST,stmt1AST);
                if(currentToken.kind==Token.ELSE){
                    acceptIt();
                    Statement stmt2AST = parseStatementSeq();
                    stmtAST = new IfElseStmt(eAST,stmt1AST,stmt2AST);
                }
                accept(Token.END);
                break;
            case Token.REPEAT:
                acceptIt();
                Statement sAST = parseStatementSeq();
                accept(Token.UNTIL);
                eAST = parseExpression();
                stmtAST = new RepeatStmt(sAST,eAST);
                break;
            case Token.IDENTIFIER:
                vAST = parseVname();
                accept(Token.BECOME);
                eAST = parseExpression();
                stmtAST = new AssignStmt(vAST,eAST);
                break;
            case Token.READ:
                acceptIt();
                vAST = parseVname();
                stmtAST = new ReadStmt(vAST);
                break;
            case Token.WRITE:
                acceptIt();
                eAST = parseExpression();
                stmtAST = new WriteStmt(eAST);
                break;
            default:
                //System.out.println(filePath+":"+scanner.line+"error: Unexpected "+Token.token[currentToken.kind]+" \'"+currentToken.spelling+"\'");
                System.exit(0);
            }
            return stmtAST;
        
    }
    private Expression parseExpression() throws IOException{
        Expression eAST;
        SimpleExpression se1AST = parseSimpleExpression();
        eAST = se1AST;
        while(currentToken.kind==Token.OPERATOR && (currentToken.spelling.compareTo("<")==0 || currentToken.spelling.compareTo("=")==0)){
            Operator opAST = new Operator(currentToken.spelling);
            acceptIt();
            SimpleExpression se2AST = parseSimpleExpression();
            eAST = new OperatorExpression(se1AST,opAST,se2AST);
        }
        return eAST;
    }
    private SimpleExpression parseSimpleExpression() throws IOException{
        SimpleExpression seAST = parseTerm();
        while(currentToken.kind==Token.OPERATOR && (currentToken.spelling.compareTo("+")==0 || currentToken.spelling.compareTo("-")==0)){
            Operator opAST = new Operator(currentToken.spelling);
            acceptIt();
            Term tAST = parseTerm();
            seAST = new OperatorSimpleExpression(seAST,opAST,tAST);
        }
        return seAST;
    }
    private Term parseTerm() throws IOException{
        Term tAST = parseFactor();
        while(currentToken.kind==Token.OPERATOR && (currentToken.spelling.compareTo("*")==0 || currentToken.spelling.compareTo("/")==0)){
            Operator opAST = new Operator(currentToken.spelling);
            acceptIt();
            Factor fAST = parseFactor();
            tAST = new OperatorTerm(tAST,opAST,fAST);
        }
        return tAST;
    }
    private Factor parseFactor() throws IOException{
        Factor fAST = null;
        switch(currentToken.kind){
            case Token.LPAREN:
                acceptIt();
                Expression eAST = parseExpression();
                fAST = new FactorExpression(eAST);
                accept(Token.RPAREN);
                break;
            case Token.IDENTIFIER:
                fAST = parseVname();
                break;
            case Token.INTLITERAL:
                IntegerLiteral nAST = new IntegerLiteral(currentToken.spelling);
                acceptIt();
                fAST = new FactorNum(nAST);
                break;
            default:
                //System.out.println(filePath+":"+scanner.line+"error: Factor is expected. Not "+Token.token[currentToken.kind]+" \'"+currentToken.spelling+"\'");
                System.exit(0);
            }
            return fAST;
    }
    private Vname parseVname() throws IOException {
        Vname vAST = null;
        if(currentToken.kind==Token.IDENTIFIER){
            Identifier iAST = new Identifier(currentToken.spelling);
            acceptIt();
            vAST = new Vname(iAST);
        }
        return vAST;
    }
}