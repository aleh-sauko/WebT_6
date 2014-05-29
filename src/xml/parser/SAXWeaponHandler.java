package xml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xml.generated.Holder;
import xml.generated.Kind;
import xml.generated.Weapon;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleh on 26.05.14.
 */
public class SAXWeaponHandler extends DefaultHandler {

    private List<Weapon> weapons = new ArrayList<Weapon>();
    private Weapon weapon;

    private WeaponElement currElement;

    public List<Weapon> getWeapons() {
        return weapons;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("weapon")) {
            weapon = new Weapon();
            weapon.setSerialId(atts.getValue("serialId"));
            weapon.setKind(Kind.fromValue(atts.getValue("kind")));
        }
        if (qName.equals("holder")) {
            weapon.setHolder(new Holder());
        }

        if (!"weapon".equals(qName) && !"weap:Weapons".equals(qName) &&
                !"holder".equals(qName)) {
            currElement = WeaponElement.valueOf(qName.toUpperCase());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (currElement == null) return;
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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("weapon")) {
            weapons.add(weapon);
        }
        currElement = null;
    }
}
