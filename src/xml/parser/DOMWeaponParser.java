package xml.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.generated.Holder;
import xml.generated.Kind;
import xml.generated.Weapon;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleh on 26.05.14.
 */
public class DOMWeaponParser implements XmlParser {

    private List<Weapon> weapons;

    @Override
    public void parse(String path) throws SAXException, IOException, XMLStreamException, ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = documentBuilder.parse(path);

        Element root = doc.getDocumentElement();
        weapons = listBuilder(root);
    }

    @Override
    public List<Weapon> getWeapons() {
        return weapons;
    }

    private List<Weapon> listBuilder(Element root) {
        List<Weapon> weapons = new ArrayList<Weapon>();

        NodeList weaponNodes = root.getElementsByTagName("weapon");
        Weapon weapon;

        for (int i=0; i < weaponNodes.getLength(); i++) {
            weapon = new Weapon();
            Element weaponElement = (Element) weaponNodes.item(i);

            weapon.setSerialId(weaponElement.getAttribute("serialId"));
            weapon.setKind(Kind.fromValue(weaponElement.getAttribute("kind")));
            weapon.setName(getBabyValue(weaponElement, "name"));
            weapon.setDamage(BigDecimal.valueOf(Double.valueOf(getBabyValue(weaponElement, "damage"))));

            Holder holder = new Holder();
            Element holderElement = getBaby(weaponElement, "holder");

            holder.setCapacity(BigInteger.valueOf(Long.valueOf(getBabyValue(holderElement, "capacity"))));
            holder.setCaliber(Float.valueOf(getBabyValue(holderElement, "caliber")));

            weapon.setHolder(holder);

            weapons.add(weapon);
        }

        return weapons;
    }

    private static Element getBaby(Element parent, String childName) {
        return (Element) parent.getElementsByTagName(childName).item(0);
    }

    private static String getBabyValue(Element parent, String childName) {
        return getBaby(parent, childName).getFirstChild().getNodeValue();
    }
}
