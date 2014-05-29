package xml.parser;

import xml.generated.Holder;
import xml.generated.Kind;
import xml.generated.Weapon;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleh on 26.05.14.
 */
public class StAXWeaponParser implements XmlParser {

    private List<Weapon> weapons = new ArrayList<Weapon>();
    private Weapon weapon;

    private WeaponElement currElement;

    @Override
    public void parse(String path) throws IOException, XMLStreamException {

        weapons = new ArrayList<Weapon>();

        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(path));

        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String tag = reader.getLocalName();
                    if (tag.equals("weapon")) {
                        weapon = new Weapon();
                        weapon.setSerialId(reader.getAttributeValue(null, "serialId"));
                        weapon.setKind(Kind.fromValue(reader.getAttributeValue(null, "kind")));
                    }
                    if (tag.equals("holder")) {
                        weapon.setHolder(new Holder());
                    }

                    if (!"weapon".equals(tag) && !"Weapons".equals(tag) &&
                            !"holder".equals(tag)) {
                        currElement = WeaponElement.valueOf(tag.toUpperCase());
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String value = reader.getText();
                    if (currElement == null) break;
                    switch (currElement) {
                        case NAME:
                            weapon.setName(value);
                            break;
                        case DAMAGE:
                            weapon.setDamage(BigDecimal.valueOf(Double.valueOf(value)));
                            break;
                        case CAPACITY:
                            weapon.getHolder().setCapacity(BigInteger.valueOf(Long.valueOf(value)));
                            break;
                        case CALIBER:
                            weapon.getHolder().setCaliber(Float.valueOf(value));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (reader.getLocalName().equals("weapon")) {
                        weapons.add(weapon);
                    }
                    currElement = null;
                    break;
            }
        }
    }

    @Override
    public List<Weapon> getWeapons() {
        return weapons;
    }
}
