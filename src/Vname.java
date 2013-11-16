public class Vname extends Factor {
    public Identifier I;
    
    public Vname(Identifier I){
        this.I = I;
    }
    
    public void Print(){
        I.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitVname(this, arg);
    }
    
}
