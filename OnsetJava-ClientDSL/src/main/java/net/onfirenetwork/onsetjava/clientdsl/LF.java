package net.onfirenetwork.onsetjava.clientdsl;

import java.util.ArrayList;
import java.util.List;

public class LF {

    private String[] params;
    private List<String> lines = new ArrayList<>();
    protected TableHelper table = new TableHelper();
    protected MathHelper math = new MathHelper();
    protected static Val nil = new Val("nil");

    public LF(String... params){
        this.params = params;
    }

    public Val constant(String value){
        return new Val("\""+value+"\"");
    }

    public Val constant(int value){
        return new Val(String.valueOf(value));
    }

    public Val constant(float value){
        return new Val(String.valueOf(value));
    }

    public String[] getParamNames(){
        return params;
    }

    protected void addLine(String line){
        lines.add(line);
    }

    public Val table(Object... values){
        Val[] luaValues = makeObjectsSafe(values);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i=0; i<luaValues.length; i++){
            if(i>0)
                sb.append(",");
            sb.append(luaValues[i].getVarName());
        }
        sb.append("}");
        return new Val(sb.toString());
    }

    protected Val makeObjectSafe(Object object){
        if(object == null)
            return nil;
        if(object instanceof Val)
            return (Val) object;
        if(object instanceof String)
            return constant((String) object);
        if(object.getClass().equals(Integer.class) || object.getClass().equals(int.class))
            return constant((int) object);
        if(object.getClass().equals(Float.class) || object.getClass().equals(float.class))
            return constant((float) object);
        return nil;
    }

    protected Val[] makeObjectsSafe(Object... objects){
        Val[] result = new Val[objects.length];
        for(int i=0; i<objects.length; i++){
            result[i] = makeObjectSafe(objects[i]);
        }
        return result;
    }

    public void local(String variable){
        lines.add("local "+variable);
    }

    public void local(String variable, Object value){
        lines.add("local "+variable+" = "+makeObjectSafe(value).getVarName());
    }

    public void var(String variable, Object value){
        lines.add(variable+" = "+makeObjectSafe(value).getVarName());
    }

    public Val var(String variable){
        return new Val(variable);
    }

    public Val concat(Object... values){
        if(values.length == 0){
            return constant("");
        }
        if(values.length == 1){
            return makeObjectSafe(values[0]);
        }
        Val[] luaValues = makeObjectsSafe(values);
        StringBuilder line = new StringBuilder("(");
        for(int i=0; i<luaValues.length; i++){
            if(i>0)
                line.append("..");
            line.append(luaValues[i].getVarName());
        }
        line.append(")");
        return new Val(line.toString());
    }

    public Val equal(Object a, Object b){
        return new Val("("+makeObjectSafe(a).getVarName()+" == "+makeObjectSafe(b).getVarName()+")");
    }

    public Val notEqual(Object a, Object b){
        return new Val("("+makeObjectSafe(a).getVarName()+" ~= "+makeObjectSafe(b).getVarName()+")");
    }

    public Val less(Object a, Object b){
        return new Val("("+makeObjectSafe(a).getVarName()+" < "+makeObjectSafe(b).getVarName()+")");
    }

    public Val lessOrEqual(Object a, Object b){
        return new Val("("+makeObjectSafe(a).getVarName()+" <= "+makeObjectSafe(b).getVarName()+")");
    }

    public Val greater(Object a, Object b){
        return new Val("("+makeObjectSafe(a).getVarName()+" > "+makeObjectSafe(b).getVarName()+")");
    }

    public Val greaterOrEqual(Object a, Object b){
        return new Val("("+makeObjectSafe(a).getVarName()+" >= "+makeObjectSafe(b).getVarName()+")");
    }

    public Val not(Object value){
        return new Val("(not "+makeObjectSafe(value).getVarName()+")");
    }

    protected String varargsToString(Object... values){
        Val[] luaValues = makeObjectsSafe(values);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<luaValues.length; i++){
            if(i>0)
                sb.append(", ");
            sb.append(luaValues[i].getVarName());
        }
        return sb.toString();
    }

    protected Val call(String name, Object... params){
        StringBuilder line = new StringBuilder(name);
        line.append("(");
        line.append(varargsToString(params));
        line.append(")");
        return new Val(line.toString());
    }

    public Val[] callVarArgs(String name, int returnAmount, Object... params){
        StringBuilder line = new StringBuilder();
        Val[] returns = new Val[returnAmount];
        if(returnAmount > 0) {
            line.append("local ");
            for(int i=0; i<returnAmount; i++){
                returns[i] = new Val();
                if(i>0)
                    line.append(", ");
                line.append(returns[i].getVarName());
            }
            line.append(" = ");
        }
        line.append(call(name, params).getVarName());
        lines.add(line.toString());
        return returns;
    }

    public void If(Object condition, LF thenCallback, LF elseCallback){
        lines.add("if "+makeObjectSafe(condition).getVarName()+" then");
        thenCallback.toBodyLines().forEach(line -> lines.add("\t"+line));
        if(elseCallback != null){
            lines.add("else");
            elseCallback.toBodyLines().forEach(line -> lines.add("\t"+line));
        }
        lines.add("end");
    }

    public void If(Object condition, LF thenCallback){
        If(condition, thenCallback, null);
    }

    public void While(Object condition, LF doCallback){
        lines.add("while "+makeObjectSafe(condition).getVarName()+" do");
        doCallback.toBodyLines().forEach(line -> lines.add("\t"+line));
        lines.add("end");
    }

    public void RepeatUntil(Object condition, LF doCallback){
        lines.add("repeat");
        doCallback.toBodyLines().forEach(line -> lines.add("\t"+line));
        lines.add("until("+makeObjectSafe(condition).getVarName()+")");
    }

    public void For(Object start, Object end, LF loopCallback){
        if(loopCallback.getParamNames().length > 0 && loopCallback.getParamNames()[0] != null){
            lines.add("for "+loopCallback.getParamNames()[0]+"="+makeObjectSafe(start).getVarName()+","+makeObjectSafe(end)+" do");
        }else{
            lines.add("for _="+makeObjectSafe(start).getVarName()+","+makeObjectSafe(end)+" do");
        }
        loopCallback.toBodyLines().forEach(line -> lines.add("\t"+line));
        lines.add("end");
    }

    public void Times(Object times, LF callback){
        For(1, times, callback);
    }

    public void ForEach(Object table, LF loopCallback){
        if(loopCallback.getParamNames().length == 1){
            lines.add("for _,"+loopCallback.getParamNames()[0]+" in ipairs("+makeObjectSafe(table).getVarName()+") do");
        }
        if(loopCallback.getParamNames().length == 2){
            lines.add("for "+loopCallback.getParamNames()[0]+","+loopCallback.getParamNames()[1]+" in ipairs("+makeObjectSafe(table).getVarName()+") do");
        }
        loopCallback.toBodyLines().forEach(line -> lines.add("\t"+line));
        lines.add("end");
    }

    public void ForMap(Object table, LF loopCallback){
        if(loopCallback.getParamNames().length == 1){
            lines.add("for "+loopCallback.getParamNames()[0]+",_ in pairs("+makeObjectSafe(table).getVarName()+") do");
        }
        if(loopCallback.getParamNames().length == 2){
            lines.add("for "+loopCallback.getParamNames()[0]+","+loopCallback.getParamNames()[1]+" in pairs("+makeObjectSafe(table).getVarName()+") do");
        }
        loopCallback.toBodyLines().forEach(line -> lines.add("\t"+line));
        lines.add("end");
    }

    public void print(Object text){
        callVarArgs("print", 0, text);
    }

    public List<String> toBodyLines(){
        return lines;
    }

    public String toBodyCode(boolean newLines, boolean tabs){
        if(newLines){
            if(tabs){
                List<String> tabbed = new ArrayList<>();
                lines.forEach(line -> tabbed.add("\t"+line));
                return String.join("\n", tabbed);
            }
            return String.join("\n", lines);
        }
        return String.join(" ", lines);
    }

    public String toBodyCode(boolean newLines){
        return toBodyCode(newLines, false);
    }

    public String toBodyCode(){
        return toBodyCode(true);
    }

    public String toHeaderCode(){
        StringBuilder sb = new StringBuilder("function(");
        for(int i=0; i<params.length; i++){
            if(i>0)
                sb.append(",");
            sb.append(params[i]);
        }
        sb.append(")");
        return sb.toString();
    }

    public String toCode(boolean newLines){
        if(newLines){
            return toHeaderCode()+"\n"+toBodyCode(true, true)+"\nend";
        }
        return toHeaderCode()+" "+toBodyCode(false)+" end";
    }

    public String toCode(){
        return toCode(true);
    }

    public String toString() {
        return toCode(true);
    }

    public class TableHelper {
        public void insert(Object table, Object element){
            callVarArgs("table.insert", 0, table, element);
        }
        public void remove(Object table, Object index){
            callVarArgs("table.remove", 0, table, index);
        }
    }

    public class MathHelper {
        public Val sqrt(Object value){
            return call("math.sqrt", value);
        }
        public Val sin(Object value){
            return call("math.sin", value);
        }
        public Val asin(Object value){
            return call("math.asin", value);
        }
        public Val cos(Object value){
            return call("math.cos", value);
        }
        public Val acos(Object value){
            return call("math.acos", value);
        }
        public Val tan(Object value){
            return call("math.tan", value);
        }
        public Val atan(Object value){
            return call("math.atan", value);
        }
        public Val deg(Object value){
            return call("math.deg", value);
        }
        public Val rad(Object value){
            return call("math.rad", value);
        }
        public Val floor(Object value){
            return call("math.floor", value);
        }
        public Val ceil(Object value){
            return call("math.ceil", value);
        }
        public Val min(Object a, Object b){
            return call("math.min", a, b);
        }
        public Val max(Object a, Object b){
            return call("math.max", a, b);
        }
    }

}
