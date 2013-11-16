public class FactorNum extends Factor {
    public IntegerLiteral N;
            
    public FactorNum(IntegerLiteral N){
        this.N = N;
    }
    
    public void Print(){
        N.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitFactorNum(this, arg);
    }
}