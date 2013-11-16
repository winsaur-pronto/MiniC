public class StmtSequence extends Statement { 
    public Statement ST1;
    public Statement ST2;

    
    public StmtSequence (Statement ST1,Statement ST2) {
        this.ST1 = ST1;
        this.ST2 = ST2;
    }
    
    public void Print(){
        ST1.Print();
        ST2.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitStmtSequence(this, arg);
    }
    
}