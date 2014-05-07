package manager;

import util.UTF8Control;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    private static MessageManager instance;

    private static final String BUNDLE_NAME = "resources.messages";

    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String REGISTRATION_ERROR_MESSAGE = "REGISTRATION_ERROR_MESSAGE";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
    public static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE";

    private ResourceBundle resourceBundle_ru;
    private ResourceBundle resourceBundle_en;

    private MessageManager() {
        resourceBundle_ru = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("ru"), new UTF8Control());
        resourceBundle_en = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH, new UTF8Control());
    }

    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return (Locale.getDefault().equals(Locale.ENGLISH)) ? resourceBundle_en.getString(key) :
                resourceBundle_ru.getString(key);
    }

}