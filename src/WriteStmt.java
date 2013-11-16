public class WriteStmt extends Statement {
    public Expression E;


    public WriteStmt (Expression E) {
        this.E = E;
    }
    
    public void Print(){
        System.out.println("Write");
        E.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitWriteStmt(this, arg);
    }
} 