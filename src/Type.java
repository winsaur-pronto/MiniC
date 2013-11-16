public class Type {
    private byte kind; //either BOOL or INT
    public final byte BOOL = 0, INT = 1;
    
    public Type (byte kind) {
        this.kind = kind;
    }
    
    public boolean equals (Object other) {
        Type otherType = (Type) other;
        return (this.kind == otherType.kind);
    }
}
