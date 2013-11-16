public class Identifier extends Terminal {
    public String spelling;

    public Identifier (String spelling) {
        this.spelling = spelling;
        //System.out.println("\tId: " + spelling+"      (print in class Identifier)");
    }
    
    public void Print(){
        
        System.out.println("\tId: " + spelling);
    }
    
    public Object visit(Visitor v, Object arg) {
        return v.visitIdentifier(this, arg);
    }
}