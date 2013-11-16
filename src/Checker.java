import java.util.ArrayList;

public class Checker implements Visitor {
    
    private identificationTable idTable;
    
    public void check(Program prog){
        idTable = new identificationTable();
        prog.visit(this, null);
        
        System.out.println("");
        System.out.println("Indentification Table");
        idTable.getDec();
    }

    @Override
    public Object visitProgram(Program prog, Object arg) {
        prog.S.visit(this, arg);
        return null;
    }

    @Override
    public Object visitAssignStmt(AssignStmt stmt, Object arg) {
        Type vType = (Type) stmt.V.visit(this, arg);
        Type eType = (Type) stmt.E.visit(this, arg);
        return null;
    }

    @Override
    public Object visitIfElseStmt(IfElseStmt stmt, Object arg) {
        Type eType = (Type) stmt.E.visit(this, arg);
        stmt.S1.visit(this, arg);
        stmt.S2.visit(this, arg);
        return null;
    }

    @Override
    public Object visitIfStmt(IfStmt stmt, Object arg) {
        Type eType = (Type) stmt.E.visit(this, arg);
        stmt.S1.visit(this, arg);
        return null;
    }

    @Override
    public Object visitReadStmt(ReadStmt stmt, Object arg) {
        idTable.enter(stmt.V);
        return null;
    }

    @Override
    public Object visitRepeatStmt(RepeatStmt stmt, Object arg) {
        stmt.E.visit(this, arg);
        stmt.S.visit(this, arg);
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
        stmt.E.visit(this, arg);
        return null;
    }

    @Override
    public Object visitOperatorExpression(OperatorExpression expr, Object arg) {
        expr.Op.visit(this, arg);
        expr.SE1.visit(this, arg);
        expr.SE2.visit(this, arg);
        return null;
    }

    @Override
    public Object visitOperatorSimpleExpression(OperatorSimpleExpression expr, Object arg) {
        expr.SE.visit(this, arg);
        expr.T.visit(this, arg);
        return null;
    }

    @Override
    public Object visitFactorExpression(FactorExpression expr, Object arg) {
        expr.E.visit(this, arg);
        return null;
    }

    @Override
    public Object visitVname(Vname vname, Object arg) {
        vname.I.visit(this, arg);
        return null;
    }

    @Override
    public Object visitFactorNum(FactorNum num, Object arg) {
        num.N.visit(this, arg);
        return null;
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral integer, Object arg) {
        //idTable.enter(integer);
        return null;
    }

    @Override
    public Object visitOperatorTerm(OperatorTerm term, Object arg) {
        term.F.visit(this, arg);
        term.Op.visit(this, arg);
        term.T.visit(this, arg);
        return null;
    }

    @Override
    public Object visitIdentifier(Identifier id, Object arg) {
        idTable.enter(id);
        return null;
    }

    @Override
    public Object visitOperator(Operator op, Object arg) {
        return null;
    }
}
