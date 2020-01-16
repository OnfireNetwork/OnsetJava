package net.onfirenetwork.onsetjava.i18n;

import java.util.List;

public interface I18NPlugin {
    String translate(String key, Object... args);
    String raw(String key);
    List<String> keys();
}
