local json_encode

local escape_char_map = {
  [ "\\" ] = "\\\\",
  [ "\"" ] = "\\\"",
  [ "\b" ] = "\\b",
  [ "\f" ] = "\\f",
  [ "\n" ] = "\\n",
  [ "\r" ] = "\\r",
  [ "\t" ] = "\\t",
}

local escape_char_map_inv = { [ "\\/" ] = "/" }
for k, v in pairs(escape_char_map) do
  escape_char_map_inv[v] = k
end

local function escape_char(c)
  return escape_char_map[c] or string.format("\\u%04x", c:byte())
end

local function encode_nil(val)
  return "null"
end

local function encode_table(val, stack)
  local res = {}
  stack = stack or {}
  if stack[val] then error("circular reference") end
  stack[val] = true
  if val[1] ~= nil or next(val) == nil then
    -- Treat as array -- check keys are valid and it is not sparse
    local n = 0
    for k in pairs(val) do
      if type(k) ~= "number" then
        error("invalid table: mixed or invalid key types")
      end
      n = n + 1
    end
    if n ~= #val then
      error("invalid table: sparse array")
    end
    -- Encode
    for i, v in ipairs(val) do
      table.insert(res, json_encode(v, stack))
    end
    stack[val] = nil
    return "[" .. table.concat(res, ",") .. "]"

  else
    -- Treat as an object
    for k, v in pairs(val) do
      if type(k) ~= "string" then
        error("invalid table: mixed or invalid key types")
      end
      table.insert(res, json_encode(k, stack) .. ":" .. json_encode(v, stack))
    end
    stack[val] = nil
    return "{" .. table.concat(res, ",") .. "}"
  end
end

local function encode_string(val)
  return '"' .. val:gsub('[%z\1-\31\\"]', escape_char) .. '"'
end

local function encode_number(val)
  return string.format("%.14g", val)
end

local type_func_map = {
  [ "nil"     ] = encode_nil,
  [ "table"   ] = encode_table,
  [ "string"  ] = encode_string,
  [ "number"  ] = encode_number,
  [ "boolean" ] = tostring,
}

function json_encode(val, stack)
  local t = type(val)
  local f = type_func_map[t]
  if f then
    return f(val, stack)
  end
  error("unexpected type '" .. t .. "'")
end

local cs = {}
local translation_data = {}

function i18n(plugin, key, ...)
    if type(plugin) == "number" then
        ExecuteWebJS(plugin, "window.i18n = (plugin, key, ...args) => { let s = ("..json_encode(translation_data)..")[plugin][key]; for(let i=0; i<args.length; i++){s=s.replace('{'+(i+1)+'}',args[i]);} return s; }")
        return
    end
    local args = {...}
    local s = translation_data[plugin][key]
    for i=1,#args do
        s = s:gsub("{"..i.."}", args[i])
    end
    return s
end

AddRemoteEvent("__java_i18n__", function(plugin, data)
    translation_data[plugin] = {}
    for _,d in ipairs(data) do
        for k,v in pairs(d) do
            translation_data[plugin][k] = v
        end
    end
    CallEvent("TranslationReady:"..plugin)
end)

CallRemoteEvent("RequestTranslation")

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