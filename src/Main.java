import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author asus
 */
public class Main {
// Main method of the scanner tester
    private static String fName = "";
    private static BufferedWriter out;
    
    public static void main(String args[]) throws FileNotFoundException, IOException {
        if(args.length > 0){
            fName = args[0];
            run();
            
        }
        else{
            fName = "ex002.minic";
            run();
        }
    }
    
    private static void run()throws FileNotFoundException, IOException {
        Parser parser;
        scanner scanner;
        String stmt = "";

        BufferedReader inReader;
        BufferedReader content;
        
        String currentPath= System.getProperty("user.dir");
        //print("currentPath: "+currentPath);
        String filename = currentPath+"/minic/"+fName;
        // print("filename: "+filename);
        //inReader = new BufferedReader(new FileReader(filename));
            
        File file = new File(filename);
        if ( file.exists() ){
            
            // Output *.tree
            //String tFile = fName.replace(".minic", ".tree");
            //tFile = currentPath+"/tree/"+tFile;
            
            
            // Output *.tm
            String oFile = fName.replace(".minic", ".tm");
            oFile = currentPath+"/object/"+oFile;
            
            /*Generate Tree
             * 
             *
            File f = new File(oFile);
            if(!f.exists())
            {
                try {
                           f.createNewFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

            try {
                    FileOutputStream fos = new FileOutputStream(f);
                    PrintStream ps = new PrintStream(fos);
                    System.setOut(ps);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
                 
            System.out.println("Syntactic Analyzer: \n");
            parser = new Parser(filename);
            Program prog = parser.parse();
            System.out.println("");
            System.out.println("Contextual Analyzer:\n");
            Checker check = new Checker();
            check.check(prog);
            System.out.println("");
            System.out.println("Code Generator:\n");
            //Encoder encoder = new Encoder();
            //encoder.encode(prog);
            CodeGenerator theEncoder = new CodeGenerator();
            theEncoder.visitProgram(prog, (short)0);
        }
        else{
            System.out.println("File not found: "+fName);
        }
    }
    
    public static void print(Object x){
        System.out.println(x.toString());
    }
}
