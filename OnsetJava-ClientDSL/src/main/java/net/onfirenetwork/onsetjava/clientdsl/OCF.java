package net.onfirenetwork.onsetjava.clientdsl;

public class OCF extends LF {

    public static Val WEB_VISIBLE = new Val("WEB_VISIBLE");
    public static Val WEB_HITINVISIBLE = new Val("WEB_HITINVISIBLE");
    public static Val WEB_HIDDEN = new Val("WEB_HIDDEN");
    public static Val HIT_AIR = new Val("HIT_AIR");
    public static Val HIT_PLAYER = new Val("HIT_PLAYER");
    public static Val HIT_VEHICLE = new Val("HIT_VEHICLE");
    public static Val HIT_NPC = new Val("HIT_NPC");
    public static Val HIT_OBJECT = new Val("HIT_OBJECT");
    public static Val HIT_LANDSCAPE = new Val("HIT_LANDSCAPE");
    public static Val HIT_WATER = new Val("HIT_WATER");
    public static Val INPUT_GAME = new Val("INPUT_GAME");
    public static Val INPUT_GAMEANDUI = new Val("INPUT_GAMEANDUI");
    public static Val INPUT_UI = new Val("INPUT_UI");
    public static Val EDIT_NONE = new Val("EDIT_NONE");
    public static Val EDIT_LOCATION = new Val("EDIT_LOCATION");
    public static Val EDIT_ROTATION = new Val("EDIT_ROTATION");
    public static Val EDIT_SCALE = new Val("EDIT_SCALE");

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

    public void EnableFirstPersonCamera(Object enable){
        callVarArgs("EnableFirstPersonCamera", 0, enable);
    }

    public Val IsFirstPersonCamera(){
        return call("IsFirstPersonCamera");
    }

    public Val GetDistance3D(Object x1, Object y1, Object z1, Object x2, Object y2, Object z2){
        return call("GetDistance3D", x1, y1, z1, x2, y2, z2);
    }

    public Val Random(Object min, Object max){
        return call("Random", min, max);
    }

    public Val RandomFloat(Object min, Object max){
        return call("RandomFloat", min, max);
    }

    public Val[] LineTrace(Object sX, Object sY, Object sZ, Object eX, Object eY, Object eZ){
        return callVarArgs("LineTrace", 9, sX, sY, sZ, eX, eY, eZ);
    }

    public Val[] LineTrace(Object sX, Object sY, Object sZ, Object eX, Object eY, Object eZ, Object complex){
        return callVarArgs("LineTrace", 9, sX, sY, sZ, eX, eY, eZ, complex);
    }

    public void ConnectToServer(Object address, Object port){
        callVarArgs("ConnectToServer", 0, address, port);
    }

    public void ConnectToServer(Object address, Object port, Object password){
        callVarArgs("ConnectToServer", 0, address, port, password);
    }

    public Val[] ScreenToWorld(Object x, Object y){
        return callVarArgs("ScreenToWorld", 7, x, y);
    }

    public void ShowMouseCursor(Object show){
        callVarArgs("ShowMouseCursor", 0, show);
    }

    public void SetInputMode(Object mode){
        callVarArgs("SetInputMode", 0, mode);
    }

    public void SetMouseLocation(Object x, Object y){
        callVarArgs("SetMouseLocation", 0, x, y);
    }

    public Val[] GetScreenSize(){
        return callVarArgs("GetScreenSize", 0);
    }

    public void SetTime(Object time){
        callVarArgs("SetTime", 0, time);
    }

    public void SetWeather(Object weather){
        callVarArgs("SetWeather", 0, weather);
    }

    public Val GetVehicleForwardSpeed(Object vehicle){
        return call("GetVehicleForwardSpeed", vehicle);
    }

    public Val CreateSound(Object file){
        return call("CreateSound", file);
    }

    public Val CreateSound3D(Object file, Object x, Object y, Object z, Object radius){
        return call("CreateSound3D", file, x, y, z, radius);
    }

    public void DestroySound(Object sound){
        callVarArgs("DestroySound", 0, sound);
    }

    public void SetSoundVolume(Object sound, Object volume){
        callVarArgs("SetSoundVolume", 0, sound, volume);
    }

    public void SetSoundPitch(Object sound, Object pitch){
        callVarArgs("SetSoundPitch", 0, sound, pitch);
    }

    public void SetObjectEditable(Object object, Object mode){
        callVarArgs("SetObjectEditable", 0, object, mode);
    }

}
