import java.util.ArrayList;

public class identificationTable {
    //.. Attrbute details
    //private int level;
    private ArrayList<Declaration> decList;

    public identificationTable(){//Make an empty identification table.
        //this.level = 0;
        decList = new ArrayList<Declaration>();
    }

    public void openScope(){
        //this.level++;
    }

    public void closeScope(){
        //for(int i=decList.size()-1; i>=0; i--)
        //	if(decList.get(i).getLevel() == this.level)
        //		decList.remove(i);
        //sthis.level--;
    }
    
    public boolean enter (Identifier iden){
        boolean duplication = false;
        for(int i=0; i<decList.size(); i++)
            if(decList.get(i).getIden().equalsIgnoreCase(iden.spelling)){
                //Error DUPLICATE_DECLARATION
                duplication = true;
            }
        if(duplication == false){
            decList.add(new Declaration(iden.spelling));//value, level));
        }
        return duplication;
    }

    public boolean enter (Vname v){
        boolean duplication = false;
        for(int i=0; i<decList.size(); i++)
            if(decList.get(i).getIden().equalsIgnoreCase(v.I.spelling)){
                //Error DUPLICATE_DECLARATION
                duplication = true;
            }
        if(duplication == false){
            decList.add(new Declaration(v.I.spelling));//value, level));
        }
        return duplication;
    }

    /*
    public boolean enter(ExpressionInterface e, Type t){
            boolean duplication = false;
            Identifier i;
            if(e instanceof _false)
                    i = new Identifier("false");
            else if(e instanceof _true)
                    i = new Identifier("true");
            for(int j=0; j<decList.size(); j++)
                    if(decList.get(j).getIden().spelling.equals(j))
                            return true;
            return false;
    }
    public boolean enter(Identifier iden, Type t) {//), String value){
            boolean duplication = false;
            for(int i=0; i<decList.size(); i++)
                    if(decList.get(i).getIden().equals(iden)){
                            ;//Error DUPLICATE_DECLARATION
                            duplication = true;
                    }
            if(duplication == false){
                    decList.add(new Declaration(iden, t, level));//value, level));
            }
            return duplication;
    }

    */
        
    public int retrieve(String varName){ 
        for(int i=decList.size()-1; i>=0; i--){
            //System.out.println(decList.get(i).getIden().spelling);
            if(decList.get(i).getIden().equals(varName))
                return i;//.getValue();
        }
        return -1; //if there is no entry for id, return null
    }
        
    public void getDec(){
        System.out.println("Attr.   Ident.");
        for(int i=0; i< decList.size(); i++){
            System.out.println(" ("+(i+1)+")      "+decList.get(i).getIden());
        }
    }
}
