public class OperatorTerm extends Term {
    public Operator Op;
    public Term T;
    public Factor F;
    
    public OperatorTerm(Term T, Operator Op, Factor F){
        this.Op = Op;
        this.T = T;
        this.F = F;
    }
    
    public void Print(){
        Op.Print();
        T.Print();
        F.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitOperatorTerm(this, arg);
    }
}