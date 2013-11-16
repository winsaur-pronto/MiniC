import java.io.*;

public class Program extends AST {
    public Statement S;

    public Program (Statement S) {
        this.S = S;
    }
    
    public void Print(){
        S.Print();
    }

    public Object visit(Visitor v, Object arg) {
        return v.visitProgram(this, arg);
    }
    
}