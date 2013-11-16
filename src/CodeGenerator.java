public class CodeGenerator implements Visitor{
    
private Instruction[] code = new Instruction[1024];
private short nextInstrAddr;

private void emit(byte op, byte n, byte r, short d) {
    System.out.print(nextInstrAddr+":");
    code[nextInstrAddr++]=new Instruction(op, n, r, d);
    //Instruction nextInstr = new Instruction();
    if (n > 1024) {
            //error.add("Length of operand can not exceed 255 words.");
            //n = 255; // to allow code generation to continue
    }
    /*
     * nextInstr.op = op;
    nextInstr.n = n;
    nextInstr.r = r;
    nextInstr.d = d;
    if (nextAddr == Machine.PB) {
            error.add("There are too many instructions for code segment.");
    } else {
            Machine.code[nextAddr] = nextInstr;
            nextAddr = nextAddr + 1;
    }*/
}

    
private void patch(short addr, short d) {
    code[addr].d = d;
}

public short valuation(String s){
    return Short.parseShort(s);
}

public void encodeFetch(Vname vname, short s){
    RuntimeEntity entity = null;// vname.I.decl.entity
    if(entity instanceof KnownValue){
        short v = ((KnownValue) entity).value;
        //emit(Instruction.un1, (byte)0, (byte)0, v);
    }
    else{
        short d = (entity instanceof KnownValue) ?
                ((UnknownValue) entity).address :
                ((KnownAddress) entity).address;
        //emit(Instruction.un1, (byte)0, Instruction.un2, d);
    }
}
private void encodeAssign(Vname vname, short s){
    emit(Instruction.ST, (byte)0 , (byte)0 , (short)0);
    emit(Instruction.LD, (byte)0 , (byte)0 , (short)0);
    /*
    RuntimeEntity entity = (RuntimeEntity) vname.visit(this, s);
    if(entity instanceof KnownValue){
        short v = ((KnownValue) entity).value;
        //emit(Instruction.un1, (byte)0, (byte)0, v);
    }
    else{
        short d = (entity instanceof UnknownValue) ?
                ((UnknownValue) entity).address :
                ((KnownAddress) entity).address;
        //emit(Instruction.un1, (byte)0, Instruction.un2, d);
    }*/
    /*RuntimeEntity entity = (RuntimeEntity) vname.visit(this, s);
    if(entity instanceof KnownValue){
        short v = ((KnownValue) entity).value;
        //emit(Instruction.un1, (byte)0, (byte)0, v);
    }
    else{
        short d = (entity instanceof UnknownValue) ?
                ((UnknownValue) entity).address :
                ((KnownAddress) entity).address;
        //emit(Instruction.un1, (byte)0, Instruction.un2, d);
    }*/
}

    private static short shortValueOf(Object obj){
        return (short) obj;
    }
    @Override
    public Object visitProgram(Program prog, Object arg) {
        short gs = 6;//shortValueOf(arg);
        emit(Instruction.LD, (byte)gs , (byte)0 , (short)0);
        emit(Instruction.ST, (byte)0 , (byte)0 , (short)0);
        //System.out.print("Started Program");
        //System.out.println("---------------");
        prog.S.visit(this, arg);
        //System.out.print("---------------");
        //System.out.println("Finished generated code");
        emit(Instruction.HALT, (byte)0 , (byte)0 , (short)0);
        return null;
    }

    @Override
    public Object visitAssignStmt(AssignStmt stmt, Object arg) {
        short gs = shortValueOf(arg);
        System.out.println(">>visitAssignStmt>>start--");
        //emit(Instruction.LDC, (byte)0, (byte)valuation(integer.spelling), (byte)0 );
        stmt.E.visit(this, arg);
        System.out.println(">>visitAssignStmt>>next---");
        emit(Instruction.ST, (byte)0 , (byte)0 , (short)0);
        stmt.V.visit(this, arg);
        emit(Instruction.LD, (byte)0 , (byte)0 , (short)0);
        System.out.println(">>visitAssignStmt>>end----");
        return null;
    }

    @Override
    public Object visitIfElseStmt(IfElseStmt stmt, Object arg) {
        short gs = shortValueOf(arg);
        System.out.println(">>visitIfElseStmt");
        /*stmt.E.visit(this, arg);
        stmt.S1.visit(this, arg);
        stmt.S2.visit(this, arg);
        */
        System.out.println(">>visitIfElseStmt--end");
        return null;
    }

    @Override
    public Object visitIfStmt(IfStmt stmt, Object arg) {
        short gs = shortValueOf(arg);
        /*stmt.E.visit(this, arg);
        short i = nextInstrAddr;
        System.out.print(nextInstrAddr+" visitIfStmt: ");
        emit(Instruction.un1, (byte)0 , (byte)0 , (short)0);
        stmt.S1.visit(this, arg);
        short g = nextInstrAddr;
        //patch(i,g);
        //code as specified*/
        
        System.out.println(">>visitIfStmt");
        /*
        stmt.E.visit(this, arg);
        stmt.S1.visit(this, arg);
        */ 
        System.out.println(">>visitIfStmt--end");
        return null;
    }

    @Override
    public Object visitReadStmt(ReadStmt stmt, Object arg) {
        short gs = shortValueOf(arg);
        //System.out.println(">>visitReadStmt");
        emit(Instruction.ST, (byte)0 , (byte)0  ,(short)0 );
        emit(Instruction.IN, (byte)0 , (byte)0  ,(short)0 );
        //System.out.println(">>visitReadStmt--end");
        return null;
    }

    @Override
    public Object visitRepeatStmt(RepeatStmt stmt, Object arg) {
        short gs = shortValueOf(arg);
        System.out.println(">>visitRepeatStmt>>start----");
        stmt.S.visit(this, arg);
        stmt.E.visit(this, arg);
        System.out.println(">>visitRepeatStmt>>next----");
        System.out.println(">>visitRepeatStmt>>end----");
        return null;
    }

    @Override
    public Object visitStmtSequence(StmtSequence stmt, Object arg) {
        short gs = shortValueOf(arg);
        //System.out.println(">>visitStmtSequence>>start--");
        stmt.ST1.visit(this, arg);
        //System.out.println(">>visitStmtSequence>>next---");
        stmt.ST2.visit(this, arg);
        //System.out.println(">>visitStmtSequence>>end----");
        return null;
    }

    @Override
    public Object visitWriteStmt(WriteStmt stmt, Object arg) {
        short gs = shortValueOf(arg);
        System.out.println(">>visitWriteStmt");
        stmt.E.visit(this, arg);
        emit(Instruction.OUT, (byte)0 , (byte)0 , (short)0);
        //System.out.println(">>visitWriteStmt>>end");
        return null;
    }

    @Override
    public Object visitOperatorExpression(OperatorExpression expr, Object arg) {
        short gs = shortValueOf(arg);
        System.out.println(">>>>visitOperatorExpression");
        emit(Instruction.ST, (byte)0 , (byte)0 , (short)0);
        expr.SE1.visit(this, arg);
        expr.SE2.visit(this, arg);
        emit(Instruction.LD, (byte)0 , (byte)0 , (short)0);
        emit(Instruction.SUB, (byte)0 , (byte)0 , (short)0);
        emit(Instruction.JEQ, (byte)0 , (byte)0 , (short)0);
        emit(Instruction.LDC, (byte)0 , (byte)0 , (short)0);
        emit(Instruction.LDA, (byte)0 , (byte)0 , (short)0);
        emit(Instruction.LDC, (byte)0 , (byte)0 , (short)0);
        expr.Op.visit(this, arg);
        System.out.println(">>>>visitOperatorExpression--end");
        
        return null;
    }

    @Override
    public Object visitOperatorSimpleExpression(OperatorSimpleExpression expr, Object arg) {
        short gs = shortValueOf(arg);
        //code as specified
        System.out.println(">>>>visitOperatorSimpleExpression");
        emit(Instruction.LD, (byte)0 , (byte)0 , (short)0);
        emit(Instruction.ST, (byte)0 , (byte)0 , (short)0);
        expr.SE.visit(this, arg);
        expr.T.visit(this, arg);
        emit(Instruction.LD, (byte)0 , (byte)0 , (short)0);
        expr.Op.visit(this, arg);
        System.out.println(">>>>visitOperatorSimpleExpression--end");
        return null;
    }

    @Override
    public Object visitFactorExpression(FactorExpression expr, Object arg) {
        short gs = shortValueOf(arg);
        //code as specified
        System.out.println(">>>>visitFactorExpression");
        expr.E.visit(this, arg);
        System.out.println(">>>>visitFactorExpression--end");
        return null;
    }

    @Override
    public Object visitVname(Vname vname, Object arg) {
        short gs = shortValueOf(arg);
        //System.out.println(">>>>visitVname");
        vname.I.visit(this, arg);
        return null;
    }

    @Override
    public Object visitFactorNum(FactorNum num, Object arg) {
        short gs = shortValueOf(arg);
        System.out.println(">>>>visitFactorNum");
        num.N.visit(this, arg);
        return null;
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral integer, Object arg) {
        short gs = shortValueOf(arg);
        System.out.println(">>>>visitIntegerLiteral");
        emit(Instruction.LDC, (byte)0, (byte)valuation(integer.spelling), (byte)0 );
        return null;
    }

    @Override
    public Object visitOperatorTerm(OperatorTerm term, Object arg) {
        short gs = shortValueOf(arg);
        /*term.F.visit(this, arg);
        //code as specified*/
        System.out.println(">>>>visitOperatorTerm");
        
        term.T.visit(this, arg);
        term.F.visit(this, arg);
        term.Op.visit(this, arg);
        
        return null;
    }

    @Override
    public Object visitIdentifier(Identifier id, Object arg) {
        short gs = shortValueOf(arg);
        //code as specified
        System.out.println(">>>>visitIdentifier");
        //emit(Instruction.LD, (byte)0, (byte)0 , (byte)0 );
        return null;
    }

    @Override
    public Object visitOperator(Operator op, Object arg) {
        short gs = shortValueOf(arg);
        //code as specified
        System.out.println(">>>>visitOperator");
        //emit(Instruction.LD, (byte)0, (byte)0 , (byte)0 );
        switch(op.spelling){
            case "+": 
                emit(Instruction.ADD, (byte)0, (byte)0 , (byte)0 );
                break;
            case "-": 
                emit(Instruction.SUB, (byte)0, (byte)0 , (byte)0 );
                break;
            case "*": 
                emit(Instruction.MUL, (byte)0, (byte)0 , (byte)0 );
                break;
            case "/":
                emit(Instruction.DIV, (byte)0, (byte)0 , (byte)0 );
                break;
            case "=":
            case "==":    
                emit(Instruction.JEQ, (byte)0, (byte)0 , (byte)0 );
                break;    
            case "<":
                emit(Instruction.JLT, (byte)0, (byte)0 , (byte)0 );
                break; 
            case "<=":
                emit(Instruction.JLE, (byte)0, (byte)0 , (byte)0 );
                break; 
            case ">":
                emit(Instruction.JGT, (byte)0, (byte)0 , (byte)0 );
                break;     
            case ">=":
                emit(Instruction.JGE, (byte)0, (byte)0 , (byte)0 );
                break;   
            case "!=":
                emit(Instruction.JNE, (byte)0, (byte)0 , (byte)0 );
                break;         
        }
      
        return null;
    }
}
