public class ReadStmt extends Statement {
    public Vname V;

    public ReadStmt (Vname V) {
        this.V = V;
        
    }
    
    public void Print(){
        System.out.println("Read: "+V.I.spelling);
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitReadStmt(this, arg);
    }
}