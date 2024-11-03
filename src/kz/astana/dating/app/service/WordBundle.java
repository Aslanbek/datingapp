package kz.astana.dating.app.service;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class WordBundle {
    private final ResourceBundle resourceBundle;

    public WordBundle(String lang) {
        Locale locale = Locale.of("en");
        if ("kz".equals(lang)) {
            locale = Locale.of("kz");
        } else if ("ru".equals(lang)) {
            locale = Locale.of("ru");
        }

        this.resourceBundle = ResourceBundle.getBundle("words", locale);
    }

    public String getWord(String key) {
        String result;
        try {
            result = resourceBundle.getString(key.toLowerCase());
        } catch (MissingResourceException | ClassCastException e) {
            result = key;
        } catch (NullPointerException e) {
            result = "*empty*";
        }

        return result;
    }
}
