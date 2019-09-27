package ch.rubens.persistence.XML.DAO;

import ch.rubens.address.model.concreate.Address;
import ch.rubens.persistence.DAO.abstracts.IAddressDAO;
import ch.rubens.persistence.XML.ContactsFileXMLSingleton;
import ch.rubens.persistence.XML.exceptions.NoneFileOpenedException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author rubens
 */
public class AddressXMLDAO implements IAddressDAO {

    private ContactsFileXMLSingleton contactsFile;
    
    private final String PERSON_ID_PREFIX = "personId_";
    private final String ADDRESS_ID_PREFIX = "postalCode_";
    
    public AddressXMLDAO() {
        
        try {
            
            contactsFile = ContactsFileXMLSingleton.getContactsFile();
            
        } catch (NoneFileOpenedException ex) {
            
            try {
                
                contactsFile = ContactsFileXMLSingleton.openXMLFile("Contacts_XML_Saved_Files\\contacts.xml");
                
            } catch (ParserConfigurationException | SAXException | IOException ex1) {
                
                System.out.println("Error when opening the contacts XML file: " + ex1);
                
            }
            
        }
        
    }
    
    @Override
    public Address getAddress(int postalCode) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Element addressElement = contacts.getElementById(ADDRESS_ID_PREFIX + postalCode);
        
        return createAddressFromElement(addressElement);
        
    }

    @Override
    public Address remove(int postalCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address update(int postalCode, Address newAddressData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRegistered(int postalCode) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Element addressElement = contacts.getElementById(ADDRESS_ID_PREFIX + postalCode);
        
        return (addressElement != null);
        
    }

    @Override
    public String getStreet(int postalCode) {
        
        Address address = getAddress(postalCode);
        
        return address.getStreet();
        
    }

    @Override
    public String getCity(int postalCode) {
        
        Address address = getAddress(postalCode);
        
        return address.getCity();
        
    }

    @Override
    public List<Address> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Address data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address remove(Address data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Address oldData, Address newData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRegistered(Address data) {
        
        return isRegistered(data.getPostalCode());
        
    }
    
    private Address createAddressFromElement(Element addressElement) {
        
        Integer postalCode = Integer.parseInt(addressElement.getAttributes().getNamedItem("postalCode").getTextContent().split("_")[1]);
        
        Address address = new Address(postalCode);
        
        String city = addressElement.getElementsByTagName("city").item(0).getTextContent();
        String street = addressElement.getElementsByTagName("street").item(0).getTextContent();
        
        address.setCity(city);
        address.setStreet(street);
        
        return address;
        
    }
    
    private Node createNode(Address address) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Element addressElement = contacts.createElement("address");
        
        Attr postalCodeAttribute = contacts.createAttribute("postalCode");
        postalCodeAttribute.setValue(PERSON_ID_PREFIX + address.getPostalCode());
        addressElement.setAttributeNode(postalCodeAttribute);
        addressElement.setIdAttributeNode(postalCodeAttribute, true);
        
        Element postalCode = contacts.createElement("postalCode");
        postalCode.appendChild(contacts.createTextNode(address.getCity()));
        
        Element city = contacts.createElement("city");
        city.appendChild(contacts.createTextNode(address.getCity()));
        
        Element street = contacts.createElement("street");
        street.appendChild(contacts.createTextNode(address.getStreet()));
        
        addressElement.appendChild(city);
        addressElement.appendChild(street);
        
        return addressElement;
        
    }
    
}
