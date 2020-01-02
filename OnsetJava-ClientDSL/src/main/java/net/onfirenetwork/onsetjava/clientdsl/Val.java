package net.onfirenetwork.onsetjava.clientdsl;

public class Val {

    private static int nextId = 0;

    private String varName;

    public Val(String varName){
        this.varName = varName;
    }

    public Val(){
        this("__val"+nextId+"__");
        nextId++;
    }

    public Val(LF function, boolean newLines){
        this(function.toCode(newLines));
    }

    public Val(LF function){
        this(function, false);
    }

    public String getVarName(){
        return varName;
    }

}
