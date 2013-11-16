public class IntegerLiteral extends Terminal {
    public String spelling;

    public IntegerLiteral (String spelling) {
        this.spelling = spelling;
        
        //System.out.println("\tConst "+this.spelling+"      (print in class IntegerLiteral)");
    }
    
    public void Print(){
        System.out.println("\tConst "+this.spelling);
    }
    
    public Object visit(Visitor v, Object arg) {
        return v.visitIntegerLiteral(this, arg);
    }
}