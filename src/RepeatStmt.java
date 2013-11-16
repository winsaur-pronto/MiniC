public class RepeatStmt extends Statement {
    public Statement S;
    public Expression E;


    public RepeatStmt (Statement S,Expression E) {
        this.S = S;
        this.E = E;
        
    }
    
    public void Print(){
        System.out.println("Repeat");
        S.Print();
        E.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitRepeatStmt(this, arg);
    }
}