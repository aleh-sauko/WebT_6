package util;

import command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {

    private static RequestHelper instance = null;

    private HashMap<String, Command> commands = new HashMap<String, Command>();

    private RequestHelper() {
        commands.put(null, new NoCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("listWeapons", new ListWeaponsCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        return commands.get(action);
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}