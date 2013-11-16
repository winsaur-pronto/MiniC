public class OperatorSimpleExpression extends SimpleExpression {
    public Operator Op;
    public SimpleExpression SE;
    public Term T;
    
    public OperatorSimpleExpression(SimpleExpression SE, Operator Op, Term T){
        this.Op = Op;
        this.SE = SE;
        this.T = T;
    }
    
    public void Print(){
        Op.Print();
        SE.Print();
        T.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitOperatorSimpleExpression(this, arg);
    }
}