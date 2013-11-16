public class Instruction 
{
	public byte op;
	public byte r; 
	public byte s;
	public short d; 
	public static final byte //op-code
            HALT =0,    IN=1,   OUT=2,  ADD=3,  SUB=4,  MUL=5, DIV=6,
            LD=7,       LDA=8,  LDC=9,  ST=10,  JLT=11, JLE=12, JGE=13,
            JGT=14,     JEQ=15, JNE=16;
        
	public Instruction() 
	{
	    op = 0;
	    r = 0;
	    s = 0;
	    d = 0;
	}
	
	public Instruction(byte op, byte r, byte s, short d) 
	{
		this.op = op;
		this.r = r;
		this.s = s;
		this.d = d;
                print(op, r, s, d);
	}
        
        public void print(byte op, byte r, byte s, short d){
            switch(op){
                case 0: System.out.println("HALT"+" "+r+","+s+","+d); break;
                case 1: System.out.println("IN"+" "+r+","+s+","+d); break;
                case 2: System.out.println("OUT"+" "+r+","+s+","+d); break;
                case 3: System.out.println("ADD"+" "+r+","+s+","+d); break;
                case 4: System.out.println("SUB"+" "+r+","+s+","+d); break;
                case 5: System.out.println("MUL"+" "+r+","+s+","+d); break;
                case 6: System.out.println("DIV"+" "+r+","+s+","+d); break;
                case 7: System.out.println("LD"+" "+r+","+s+"("+d+")"); break;
                case 8: System.out.println("LDA"+" "+r+","+s+"("+d+")"); break;
                case 9: System.out.println("LDC"+" "+r+","+s+"("+d+")"); break;
                case 10: System.out.println("ST"+" "+r+","+s+"("+d+")"); break;
                case 11: System.out.println("JLT"+" "+r+","+s+"("+d+")"); break;
                case 12: System.out.println("JLE"+" "+r+","+s+"("+d+")"); break;
                case 13: System.out.println("JGE"+" "+r+","+s+"("+d+")"); break;
                case 14: System.out.println("JGT"+" "+r+","+s+"("+d+")"); break;
                case 15: System.out.println("JEQ"+" "+r+","+s+"("+d+")"); break;
                case 16: System.out.println("JNE"+" "+r+","+s+"("+d+")"); break;
                default:
                    System.out.println(op+" "+r+","+d+"("+s+")");
                    break;
            }
        }
}
