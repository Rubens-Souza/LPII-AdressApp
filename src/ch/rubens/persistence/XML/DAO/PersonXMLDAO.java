package ch.rubens.persistence.XML.DAO;

import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.persistence.DAO.abstracts.IPersonDAO;
import ch.rubens.persistence.XML.ContactsFileXMLSingleton;
import ch.rubens.persistence.XML.exceptions.NoneFileOpenedException;
import ch.rubens.persistence.exceptions.NotRegisteredDataException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author rubens
 */
public class PersonXMLDAO implements IPersonDAO {
    
    private ContactsFileXMLSingleton contactsFile;
    
    public PersonXMLDAO() {
        
        try {
            
            contactsFile = ContactsFileXMLSingleton.getContactsFile();
            
        }
        catch (NoneFileOpenedException ex) {
            
            try {
                
                contactsFile = ContactsFileXMLSingleton.openXMLFile("Contacts_XML_Saved_Files\\contacts.xml");
                
            } 
            catch (ParserConfigurationException | SAXException | IOException ex1 ) {
                
                System.out.println("Error when opening the contacts XML file: " + ex1);
                
            }
            
        }
        
    }
    
    @Override
    public Person getPerson(int personId) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Element person = contacts.getElementById(Integer.toString(personId));
        
        return createPersonFromElement(person);
        
        /*Node rootNode = contacts.getElementsByTagName("persons").item(0);
        
        if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
            
            Element personsList = (Element) rootNode;
            NodeList persons = personsList.getElementsByTagName("person");
            
            for (int i = 0; i < persons.getLength(); i++) {
                
                Node personNode = persons.item(i);
                Integer personSavedId = Integer.parseInt(personNode.getAttributes().getNamedItem("id").getNodeValue()); 
                
                if (personNode.getNodeType() == Node.ELEMENT_NODE && personSavedId == personId) {
                    
                    Element personElement = (Element) personNode;
                    
                    String firstName = personElement.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = personElement.getElementsByTagName("lastName").item(0).getTextContent();
                    LocalDate birthday = LocalDate.parse(personElement.getElementsByTagName("birthday").item(0).getTextContent());
                    
                    NodeList addressNodes = personElement.getElementsByTagName("address");
                    ArrayList<Address> personAdresses = new ArrayList<Address>();
                    
                    for (int j = 0; j < addressNodes.getLength(); j++) {
                        
                        Element addressElement = (Element) addressNodes.item(j);
                        
                        Integer postalCode = Integer.parseInt(addressElement.getElementsByTagName("postalCode").item(0).getTextContent());
                        String city = addressElement.getElementsByTagName("city").item(0).getTextContent();
                        String street = addressElement.getElementsByTagName("street").item(0).getTextContent();
                        
                        Address address = new Address(postalCode);
                        address.setCity(city);
                        address.setStreet(street);
                        
                        personAdresses.add(address);
                        
                    }
                    
                    Person person = new Person(personSavedId);
                    person.setFirstName(firstName);
                    person.setLastName(lastName);
                    person.setBirthday(birthday);
                    person.setAddressList(personAdresses);
                    
                    return person;
                    
                }
                
            }
            
        }
        
        return null;*/
        
    }

    @Override
    public Person remove(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person update(int personId, Person newPersonData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isRegistered(Person data) {
        
        return isRegistered(data.getId());
        
    }

    @Override
    public boolean isRegistered(int personId) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Element person = contacts.getElementById(Integer.toString(personId));
        
        return (person != null);
        
        /*Node rootNode = contacts.getElementsByTagName("persons").item(0);
        
        if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
            
            Element personsElement = (Element) rootNode;
            NodeList personNodes = personsElement.getElementsByTagName("persons");
            
            for (int i = 0; i < personNodes.getLength(); i++) {
                
                Node personElement = personNodes.item(i);
                Integer personSavedId = Integer.parseInt(personElement.getAttributes().getNamedItem("id").getNodeValue());
                
                if (personSavedId == personId) {
                    
                    return true;
                    
                }
                
            }
            
        }
        
        return false;*/
        
    }

    @Override
    public String getPersonFirstName(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPersonLastName(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalDate getPersonBirthdate(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPersonPostalCode(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person add(Person data) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Node rootNode = contacts.getElementsByTagName("persons").item(0);
        
        Node newPersonNode = createNode(data);
        
        rootNode.appendChild(newPersonNode);
        
        return null;
        
    }

    @Override
    public Person remove(Person data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person update(Person oldData, Person newData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Person createPersonFromElement(Element personElement) {
        
        Integer personId = Integer.parseInt(personElement.getAttributes().getNamedItem("id").getTextContent()) ;
        
        Person person = new Person(personId);
        
        String firstName = personElement.getAttributes().getNamedItem("firstName").getTextContent();
        String lastName = personElement.getAttributes().getNamedItem("lastName").getTextContent();
        LocalDate birthday = LocalDate.parse(personElement.getAttributes().getNamedItem("birthday").getTextContent());
        
        person.setFirstName(lastName);
        person.setLastName(lastName);
        person.setBirthday(birthday);
        
        NodeList personAddressNodeList = personElement.getElementsByTagName("address");
        ArrayList<Address> personAddressList = new ArrayList<Address>();
        
        for (int i = 0; i < personAddressNodeList.getLength(); i++) {
            
            Element addressNode = (Element) personAddressNodeList.item(i);
            
            if (addressNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element personAddress = (Element) addressNode;
                
                Integer postalCode = Integer.parseInt(personAddress.getAttributes().getNamedItem("postalCode").getTextContent());
                String city = personAddress.getElementsByTagName("city").item(0).getTextContent();
                String street = personAddress.getElementsByTagName("street").item(0).getTextContent();
                
                Address address = new Address(postalCode);
                address.setCity(city);
                address.setStreet(street);
                
                personAddressList.add(address);
                
            }
            
        }
        
        person.setAddressList(personAddressList);
        
        return person;
        
    }
   
    public Node createNode(Person personData){

        Document contacts = contactsFile.getContactsAsDOM();
        
        Element person = contacts.createElement("person");

        Attr idAttribute = contacts.createAttribute("id");
        idAttribute.setValue(personData.getId().toString());
        person.setAttributeNode(idAttribute);
        person.setIdAttributeNode(idAttribute, true);
        
        Element personId = contacts.createElement("id");
        personId.appendChild(contacts.createTextNode(personData.getId().toString()));
        
        Element firstName = contacts.createElement("firstName");
        firstName.appendChild(contacts.createTextNode(personData.getFirstName()));
        
        Element lastName = contacts.createElement("lastName");
        lastName.appendChild(contacts.createTextNode(personData.getLastName()));
        
        Element birthday = contacts.createElement("birthday");
        birthday.appendChild(contacts.createTextNode(personData.getBirthday().toString()));
        
        for (int i = 0; i < personData.countAddress(); i++) {
            
            Element address = contacts.createElement("address");
            
            Attr postalCodeAttribute = contacts.createAttribute("postalCode");
            postalCodeAttribute.setValue(personData.getAddress(i).getPostalCode().toString());
            address.setAttributeNode(postalCodeAttribute);
            address.setIdAttributeNode(postalCodeAttribute, true);
            person.appendChild(address);

            Element postalCode = contacts.createElement("postalCode");
            postalCode.appendChild(contacts.createTextNode(personData.getAddress(i).getPostalCode().toString()));
            address.appendChild(postalCode);

            Element city = contacts.createElement("city");
            city.appendChild(contacts.createTextNode(personData.getAddress(i).getCity()));
            address.appendChild(city);

            Element street = contacts.createElement("street");
            street.appendChild(contacts.createTextNode(personData.getAddress(i).getStreet()));
            address.appendChild(street);
            
        }
        
        person.appendChild(personId);
        person.appendChild(firstName);
        person.appendChild(lastName);
        person.appendChild(birthday);
        
        return person;
        
    }
    
}
