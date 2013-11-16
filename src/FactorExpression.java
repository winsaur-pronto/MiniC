public class FactorExpression extends Factor {
    public Expression E;
    
    public FactorExpression(Expression E){
        this.E = E;
    }
    
    public void Print(){
        E.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitFactorExpression(this, arg);
    }
}