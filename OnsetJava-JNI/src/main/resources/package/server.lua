local jvm
local nextCS = 1

function GetPlayerSteamIdString(player)
    return tostring(GetPlayerSteamId(player))
end

function RegisterJavaCommand(name)
	AddCommand(name, function(...)
		CallJavaStaticMethod(jvm, 'net/onfirenetwork/onsetjava/jni/LuaAdapter', 'luaCommand', '(Ljava/lang/String;Ljava/util/Map;)V', name, {...})
	end)
end

function RegisterJavaEvent(name)
	AddEvent(name, function(...)
		return CallJavaStaticMethod(jvm, 'net/onfirenetwork/onsetjava/jni/LuaAdapter', 'luaEvent', '(Ljava/lang/String;Ljava/util/Map;)Ljava/lang/Boolean;', name, {...})
	end)
end

function RegisterJavaRemoteEvent(name)
	AddRemoteEvent(name, function(...)
		CallJavaStaticMethod(jvm, 'net/onfirenetwork/onsetjava/jni/LuaAdapter', 'luaRemoteEvent', '(Ljava/lang/String;Ljava/util/Map;)V', name, {...})
	end)
end

function ImportPackageToJava(name, functions)
	local pack = ImportPackage(name)
	local tab = {}
	for _,k in pairs(functions) do
		tab[k] = function(...) pack[k](...) end
	end
	return tab
end

function AddJavaFunctionExport(name)
	AddFunctionExport(name, function(...)
		return CallJavaStaticMethod(jvm, 'net/onfirenetwork/onsetjava/jni/LuaAdapter', 'callExportFunction', '(Ljava/lang/String;Ljava/util/Map;)Ljava/lang/Object;', name, {...})
	end)
end

function RunClientScript(player, script)
	local cs = nextCS
	nextCS = nextCS + 1
	CallRemoteEvent(player, "__java_rcs__", cs, script:len())
	local processed = 0
	while processed < script:len() do
		local rem = script:len()-processed
		if rem > 1000 then
			rem = 1000
		end
		CallRemoteEvent(player, "__java_rcs_data__", cs, script:sub(processed+1, processed+rem))
		processed = processed + rem
	end
end

function DelayJava(millis, id)
    Delay(millis, function()
        CallJavaStaticMethod(jvm, 'net/onfirenetwork/onsetjava/jni/LuaAdapter', 'callDelay', '(Ljava/lang/Integer;)V', id)
    end)
end

function TimerJava(millis, id)
	local timer = CreateTimer(function()
		CallJavaStaticMethod(jvm, 'net/onfirenetwork/onsetjava/jni/LuaAdapter', 'callTimer', '(Ljava/lang/Integer;)V', id)
	end, millis)
	return timer
end

AddEvent("OnPackageStart", function()
	jvm = CreateJava()
	LinkJavaAdapter(jvm, "net/onfirenetwork/onsetjava/jni/LuaAdapter")
	CallJavaStaticMethod(jvm, 'net/onfirenetwork/onsetjava/jni/LuaAdapter', 'init', '(Ljava/lang/String;)V', GetPackageName())
end)

AddEvent("OnPackageStop", function()
	DestroyJava(jvm)
end)