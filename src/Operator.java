public class Operator extends Terminal {
    public String spelling;

    public Operator (String spelling) {
        this.spelling = spelling;
        //System.out.println("Op: " + spelling+"      (print in class Operator)");
    }
    
    public void Print(){
        System.out.println("Op: "+spelling);
    }
    
    public Object visit(Visitor v, Object arg) {
        return v.visitOperator(this, arg);
    }
}