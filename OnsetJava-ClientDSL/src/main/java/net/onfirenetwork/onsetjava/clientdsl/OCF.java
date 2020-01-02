package net.onfirenetwork.onsetjava.clientdsl;

public class OCF extends LF {

    public OCF(String... params){
        super(params);
    }

    public Val GetPlayerName(Object player){
        return call("GetPlayerName", player);
    }

    public Val GetPlayerId(){
        return call("GetPlayerId");
    }

    public Val ImportPackage(Object name){
        return call("ImportPackage", name);
    }

    public void AddFunctionExport(Object name, Object function){
        callVarArgs("AddFunctionExport", 0, name, function);
    }

    public void AddPlayerChat(Object... text){
        callVarArgs("AddPlayerChat", 0, concat(text));
    }

    public void SetPostEffect(Object category, Object effect, Object value){
        callVarArgs("SetPostEffect", 0, category, effect, value);
    }

    public void SetPostEffect(Object category, Object effect, Object r, Object g, Object b, Object a){
        callVarArgs("SetPostEffect", 0, category, effect, r, g, b, a);
    }

    public void AddEvent(Object name, LF callback){
        addLine("AddEvent("+makeObjectSafe(name).getVarName()+", "+callback.toHeaderCode());
        callback.toBodyLines().forEach(line -> addLine("\t"+line));
        addLine("end)");
    }

    public void AddRemoteEvent(Object name, LF callback){
        addLine("AddRemoteEvent("+makeObjectSafe(name).getVarName()+", "+callback.toHeaderCode());
        callback.toBodyLines().forEach(line -> addLine("\t"+line));
        addLine("end)");
    }

    public Val CreateWebUI(){
        return call("CreateWebUI", 0, 0, 0, 0);
    }

    public Val CreateWebUI(Object zOrder, Object frameRate){
        return call("CreateWebUI", 0, 0, 0, 0, zOrder, frameRate);
    }

    public Val CreateWebUI3D(Object x, Object y, Object z, Object rx, Object ry, Object rz, Object width, Object height){
        return call("CreateWebUI3D", x, y, z, rx, ry, rz, width, height);
    }

    public Val CreateWebUI3D(Object x, Object y, Object z, Object rx, Object ry, Object rz, Object width, Object height, Object frameRate){
        return call("CreateWebUI3D", x, y, z, rx, ry, rz, width, height, frameRate);
    }

    public Val CreateRemoteWebUI(){
        return call("CreateRemoteWebUI", 0, 0, 0, 0);
    }

    public Val CreateRemoteWebUI(Object zOrder, Object frameRate){
        return call("CreateRemoteWebUI", 0, 0, 0, 0, zOrder, frameRate);
    }

    public Val CreateRemoteWebUI3D(Object x, Object y, Object z, Object rx, Object ry, Object rz, Object width, Object height){
        return call("CreateRemoteWebUI3D", x, y, z, rx, ry, rz, width, height);
    }

    public Val CreateRemoteWebUI3D(Object x, Object y, Object z, Object rx, Object ry, Object rz, Object width, Object height, Object frameRate){
        return call("CreateRemoteWebUI3D", x, y, z, rx, ry, rz, width, height, frameRate);
    }

    public void ExecuteWebJS(Object web, Object js){
        callVarArgs("ExecuteWebJS", 0, web, js);
    }

    public void SetWebAnchors(Object web, Object x1, Object y1, Object x2, Object y2){
        callVarArgs("SetWebAnchors", 0, web, x1, y1, x2, y2);
    }

    public void SetWebAlignment(Object web, Object x, Object y){
        callVarArgs("SetWebAlignment", 0, web, x, y);
    }

    public Val GetWebUICount(){
        return call("GetWebUICount");
    }

    public Val GetAllWebUI(){
        return call("GetAllWebUI");
    }

    public void DestroyWebUI(Object web){
        callVarArgs("DestroyWebUI", 0, web);
    }

    public void SetWebVisibility(Object web, Object visibility){
        callVarArgs("SetWebVisibility", 0, web, visibility);
    }

    public Val GetWebVisibility(Object web){
        return call("GetWebVisibility", web);
    }

    public void SetWebURL(Object web, Object url){
        callVarArgs("SetWebURL", 0, web, url);
    }

    public void LoadWebFile(Object web, Object file){
        callVarArgs("LoadWebFile", 0, web, file);
    }

    public void SetWebSize(Object web, Object width, Object height){
        callVarArgs("SetWebSize", 0, web, width, height);
    }

    public void SetWebLocation(Object web, Object x, Object y, Object z){
        callVarArgs("SetWebLocation", 0, web, x, y, z);
    }

    public void SetWebRotation(Object web, Object rx, Object ry, Object rz){
        callVarArgs("SetWebRotation", 0, web, rx, ry, rz);
    }

    public void SetIgnoreMoveInput(Object ignore){
        callVarArgs("SetIgnoreMoveInput", 0, ignore);
    }

    public void SetIgnoreLookInput(Object ignore){
        callVarArgs("SetIgnoreLookInput", 0, ignore);
    }

    public void SetControlRotation(Object pitch, Object yaw, Object roll){
        callVarArgs("SetControlRotation", 0, pitch, yaw, roll);
    }

    public Val IsCtrlPressed(){
        return call("IsCtrlPressed");
    }

    public Val IsCmdPressed(){
        return call("IsCmdPressed");
    }

    public Val IsAltPressed(){
        return call("IsAltPressed");
    }

    public Val IsShiftPressed(){
        return call("IsShiftPressed");
    }

    public void CallEvent(Object... params){
        callVarArgs("CallEvent", 0, params);
    }

    public void CallRemoteEvent(Object... params){
        callVarArgs("CallRemoteEvent", 0, params);
    }

    public void Delay(Object delay, LF callback){
        addLine("Delay("+makeObjectSafe(delay).getVarName()+", "+callback.toHeaderCode());
        callback.toBodyLines().forEach(line -> addLine("\t"+line));
        addLine("end)");
    }

    public Val CreateTimer(LF callback, Object delay){
        Val val = new Val();
        addLine("local "+val.getVarName()+" = CreateTimer("+callback.toHeaderCode());
        callback.toBodyLines().forEach(line -> addLine("\t"+line));
        addLine("end, "+makeObjectSafe(delay).getVarName()+")");
        return val;
    }

    public Val CreateCountTimer(LF callback, Object delay, Object count){
        Val val = new Val();
        addLine("local "+val.getVarName()+" = CreateCountTimer("+callback.toHeaderCode());
        callback.toBodyLines().forEach(line -> addLine("\t"+line));
        addLine("end, "+makeObjectSafe(delay).getVarName()+", "+makeObjectSafe(count).getVarName()+")");
        return val;
    }

    public Val GetTimerCount(){
        return call("GetTimerCount");
    }

    public Val GetAllTimers(){
        return call("GetAllTimers");
    }

    public void DestroyTimer(Object timer){
        callVarArgs("DestroyTimer", 0, timer);
    }

    public void PauseTimer(Object timer){
        callVarArgs("PauseTimer", 0, timer);
    }

    public void UnpauseTimer(Object timer){
        callVarArgs("UnpauseTimer", 0, timer);
    }

    public Val IsValidTimer(Object timer){
        return call("IsValidTimer", timer);
    }

    public Val GetTimerRemainingTime(Object timer){
        return call("GetTimerRemainingTime", timer);
    }

    public Val[] GetPlayerLocation(){
        return callVarArgs("GetPlayerLocation", 3);
    }

    public Val[] GetPlayerLocation(Object player){
        return callVarArgs("GetPlayerLocation", 3, player);
    }

    public void CopyToClipboard(Object... text){
        callVarArgs("CopyToClipboard", 0, concat(text));
    }

}
