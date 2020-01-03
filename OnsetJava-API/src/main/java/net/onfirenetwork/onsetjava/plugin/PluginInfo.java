package net.onfirenetwork.onsetjava.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PluginInfo {
    String name();
    String author() default "Unknown";
    String version() default "1.0";
    String[] depend() default {};
}
