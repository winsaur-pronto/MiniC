 public class IfStmt extends Statement {
    public Expression E;
    public Statement S1;

    public IfStmt (Expression E,Statement S1) {
        this.E = E;
        this.S1 = S1;
    }
    
    public void Print(){
        System.out.println("If");
        E.Print();
        S1.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitIfStmt(this, arg);
    }
}