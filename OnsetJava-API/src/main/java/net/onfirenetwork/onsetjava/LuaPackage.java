package net.onfirenetwork.onsetjava;

public interface LuaPackage {
    Object[] callFunction(String name, Object... args);
    void closePackage();
}
