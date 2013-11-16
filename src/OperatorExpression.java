public class OperatorExpression extends Expression {
    public Operator Op;
    public SimpleExpression SE1;
    public SimpleExpression SE2;
    
    public OperatorExpression(SimpleExpression SE1, Operator Op, SimpleExpression SE2){
        this.Op = Op;
        this.SE1 = SE1;
        this.SE2 = SE2;
        
    }
    
    public void Print(){
        Op.Print();
        SE1.Print();
        SE2.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitOperatorExpression(this, arg);
    }
}