package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by aleh on 07.05.14.
 */
public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale.setDefault(Locale.getDefault().equals(Locale.ENGLISH) ? new Locale("ru") : Locale.ENGLISH);
        return "/";
    }
}
