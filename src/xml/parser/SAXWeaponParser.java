package xml.parser;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import xml.generated.Weapon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleh on 26.05.14.
 */
public class SAXWeaponParser implements XmlParser {

    private List<Weapon> weapons = new ArrayList<Weapon>();

    @Override
    public void parse(String path) throws SAXException, IOException {

        weapons = new ArrayList<Weapon>();

        XMLReader reader = XMLReaderFactory.createXMLReader();
        SAXWeaponHandler handler = new SAXWeaponHandler();
        reader.setContentHandler(handler);
        reader.parse(path);
        weapons = handler.getWeapons();
    }

    @Override
    public List<Weapon> getWeapons() {
        return weapons;
    }
}
