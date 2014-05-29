package xml.parser;

import org.xml.sax.SAXException;
import xml.generated.Weapon;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

/**
 * Created by aleh on 26.05.14.
 */
public interface XmlParser {

    public void parse(String path) throws SAXException, IOException, XMLStreamException, ParserConfigurationException;
    public List<Weapon> getWeapons();
}
