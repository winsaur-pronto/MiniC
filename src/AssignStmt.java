public class AssignStmt extends Statement {
    public Vname V;
    public Expression E;


    public AssignStmt (Vname V,Expression E) {
        this.V = V;
        this.E = E;
        
    }
    
    public void Print(){
        System.out.println ("Assign to: "+V.I.spelling);
        E.Print();
    }
    
    public Object visit(Visitor v, Object arg) {
        return v.visitAssignStmt(this, arg);
    }
}