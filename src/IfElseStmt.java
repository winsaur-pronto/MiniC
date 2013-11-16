public class IfElseStmt extends Statement {
   public Expression E;
   public Statement S1;
   public Statement S2;

   public IfElseStmt (Expression E,Statement S1,Statement S2) {
       this.E = E;
       this.S1 = S1;
       this.S2 = S2;
   }
   
   public void Print(){
       System.out.print("If");
       E.Print();
       S1.Print();
       S2.Print();
   }

    public Object visit(Visitor v, Object arg) {
        return v.visitIfElseStmt(this, arg);
    }
}