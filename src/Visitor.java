public interface Visitor {
    //Programs
    public Object visitProgram(Program prog,Object arg);
    
    //Commmands
    public Object visitAssignStmt(AssignStmt stmt,Object arg);
    public Object visitIfElseStmt(IfElseStmt stmt,Object arg);
    public Object visitIfStmt(IfStmt stmt,Object arg);
    public Object visitReadStmt(ReadStmt stmt,Object arg);
    public Object visitRepeatStmt(RepeatStmt stmt,Object arg);
    public Object visitStmtSequence(StmtSequence stmt,Object arg);
    public Object visitWriteStmt(WriteStmt stmt,Object arg);
    //public Object visitLetCommand(LetCommand com,Object arg);

    //Expressions
    public Object visitOperatorExpression (OperatorExpression expr,Object arg);
    public Object visitOperatorSimpleExpression (OperatorSimpleExpression expr,Object arg);
    //public Object visitUnaryExpression (UnaryExpression expr,Object arg);
    public Object visitFactorExpression (FactorExpression expr,Object arg);

    //value-or-variable names
    public Object visitVname (Vname vname,Object arg);
    public Object visitFactorNum (FactorNum num,Object arg);
    public Object visitIntegerLiteral (IntegerLiteral integer,Object arg);
    public Object visitOperatorTerm (OperatorTerm term,Object arg);
    //Declarations
    //public Object visitConstDeclaration (ConstDeclaration dec1,Object arg);
    //public Object visitVarDeclaration (VarDeclaration dec1,Object arg);
    //public Object visitSequentialDeclaration (SequentialDeclaration dec1,Object arg);

    //Type-denoters
    //public Object visitSimpletypeDenoter (SimpletypeDenoter type,Object arg);
    
    //Identifiers
    public Object visitIdentifier (Identifier id,Object arg);
    
    //Operators
    public Object visitOperator (Operator op,Object arg);
}