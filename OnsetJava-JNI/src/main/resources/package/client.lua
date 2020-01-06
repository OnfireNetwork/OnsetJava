local cs = {}

AddRemoteEvent("__java_rcs__", function(id, size)
    cs[id] = {
        script = "",
        size = size
    }
end)

AddRemoteEvent("__java_rcs_data__", function(id, script)
    if cs[id] == nil then
        return
    end
    cs[id].script = cs[id].script..script
    if cs[id].script:len() == cs[id].size then
        loadstring(cs[id].script)
        cs[id] = nil
    end
end)