public class Encoder implements Visitor {
private Instruction[] code = new Instruction[1024];
private short nextInstrAddr;

//private identificationTable idTable;
//private ErrorReporter error;

public Encoder() {
//nextAddr = Machine.CB;
//this.error = error;
//idTable = new identificationTable();
}

public void encode(Program prog){
    prog.visit(this,null);
}

private void emit(byte op, byte n, byte r, short d) {
    code[nextInstrAddr++]=new Instruction(op, n, r, d);
		/*Instruction nextInstr = new Instruction();
		if (n > 255) {
			error.add("Length of operand can not exceed 255 words.");
			n = 255; // to allow code generation to continue
		}
		nextInstr.op = op;
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
private void encodeFetch(Vname name){}
private void encodeAssign(Vname name){}
  
    @Override
    public Object visitProgram(Program prog, Object arg) {
        prog.S.visit(this, arg);
        emit(Instruction.HALT, (byte)0 , (byte)0 , (short)0);
       return null;
    }

    @Override
    public Object visitAssignStmt(AssignStmt stmt, Object arg) {
        stmt.E.visit(this, arg);
        encodeAssign(stmt.V);
        return null;
    }

    @Override
    public Object visitIfElseStmt(IfElseStmt stmt, Object arg) {
        stmt.E.visit(this, arg);
        short i = nextInstrAddr;
        //emit(op, n, r, d);
        stmt.S1.visit(this, arg);
        short j = nextInstrAddr;
        patch(i,j);
        stmt.S2.visit(this, arg);
        short k = nextInstrAddr;
        patch(i,k);
        return null;
    }

    @Override
    public Object visitIfStmt(IfStmt stmt, Object arg) {
        stmt.E.visit(this, arg);
        short i = nextInstrAddr;
        //emit(op, n, r, d);
        stmt.S1.visit(this, arg);
        short j = nextInstrAddr;
        patch(i,j);
        
        return null;
    }

    @Override
    public Object visitReadStmt(ReadStmt stmt, Object arg) {
        return null;
    }

    @Override
    public Object visitRepeatStmt(RepeatStmt stmt, Object arg) {
       return null;
    }

    @Override
    public Object visitStmtSequence(StmtSequence stmt, Object arg) {
        stmt.ST1.visit(this, arg);
        stmt.ST2.visit(this, arg);
        return null;
    }

    @Override
    public Object visitWriteStmt(WriteStmt stmt, Object arg) {
        return null;
    }

    @Override
    public Object visitOperatorExpression(OperatorExpression expr, Object arg) {
        expr.SE1.visit(this, arg);
        expr.SE2.visit(this, arg);
        //short p = address for expr.Op operation;
        //emit(op, n, r, d);
        return null;
    }

    @Override
    public Object visitOperatorSimpleExpression(OperatorSimpleExpression expr, Object arg) {
        return null;
    }

    @Override
    public Object visitFactorExpression(FactorExpression expr, Object arg) {
        return null;
    }

    @Override
    public Object visitVname(Vname vname, Object arg) {
        return null;
    }

    @Override
    public Object visitFactorNum(FactorNum num, Object arg) {
       return null;
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral integer, Object arg) {
        return null;
    }

    @Override
    public Object visitOperatorTerm(OperatorTerm term, Object arg) {
        return null;
    }

    @Override
    public Object visitIdentifier(Identifier id, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitOperator(Operator op, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
