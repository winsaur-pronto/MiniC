public abstract class AST {
    
    public AST(){
        
    }
    
    public abstract void Print();
    
    public abstract Object visit(Visitor v, Object arg);
}