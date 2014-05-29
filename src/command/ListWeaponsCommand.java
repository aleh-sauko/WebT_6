package command;

import manager.ConfigurationManager;
import org.xml.sax.SAXException;
import xml.generated.Weapon;
import xml.parser.DOMWeaponParser;
import xml.parser.SAXWeaponParser;
import xml.parser.StAXWeaponParser;
import xml.parser.XmlParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

/**
 * Created by aleh on 26.05.14.
 */
public class ListWeaponsCommand implements Command {

    private static final String PARSER_TYPE = "parser";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        XmlParser parser = null;
        String parserType = request.getParameter(PARSER_TYPE);

        if (parserType.equals("SAX")) {
            parser = new SAXWeaponParser();
        } else if (parserType.equals("StAX")) {
            parser = new StAXWeaponParser();
        } else {
            parser = new DOMWeaponParser();
        }

        try {
            parser.parse(ConfigurationManager.PATH_TO_XML);
            List<Weapon> weapons = parser.getWeapons();
            request.setAttribute("weapons", weapons);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.WEAPONS_PAGE_PATH);
    }
}
