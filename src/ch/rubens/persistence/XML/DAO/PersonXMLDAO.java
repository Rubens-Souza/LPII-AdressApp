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
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author rubens
 */
public class PersonXMLDAO implements IPersonDAO {
    
    private ContactsFileXMLSingleton contactsFile;
    
    private final String PERSON_ID_PREFIX = "personId_";
    private final String ADDRESS_ID_PREFIX = "postalCode_";
    
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
        
        Element person = contacts.getElementById(PERSON_ID_PREFIX + Integer.toString(personId));
        
        return createPersonFromElement(person);
        
    }

    @Override
    public Person remove(int personId) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Element rootElement = (Element) contacts.getElementsByTagName("persons").item(0);
        Element personElement = contacts.getElementById(PERSON_ID_PREFIX + personId);
        
        Person personData = createPersonFromElement(personElement);
        
        rootElement.removeChild(personElement);
        
        return personData;
        
    }

    @Override
    public Person update(int personId, Person newPersonData) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Element rootElement = (Element) contacts.getElementsByTagName("persons").item(0);
        Element oldPersonElement = contacts.getElementById(PERSON_ID_PREFIX + personId);
        
        Person oldPersonData = createPersonFromElement(oldPersonElement);
        
        Node newPersonNode = createNode(newPersonData);
        
        rootElement.replaceChild(newPersonNode, oldPersonElement);
        
        return oldPersonData;
        
    }
    
    @Override
    public boolean isRegistered(Person data) {
        
        return isRegistered(data.getId());
        
    }

    @Override
    public boolean isRegistered(int personId) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Element person = contacts.getElementById(PERSON_ID_PREFIX + Integer.toString(personId));
        
        return (person != null);
        
    }

    @Override
    public String getPersonFirstName(int personId) {
        
        Person personData = getPerson(personId);
        
        return personData.getFirstName();
        
    }

    @Override
    public String getPersonLastName(int personId) {
                
        Person personData = getPerson(personId);
        
        return personData.getLastName();
        
    }

    @Override
    public LocalDate getPersonBirthday(int personId) {
                
        Person personData = getPerson(personId);
        
        return personData.getBirthday();
        
    }

    @Override
    public int getPersonPostalCode(int personId) {
        
        Person personData = getPerson(personId);
        
        return personData.getPostalCode();
                
    }

    @Override
    public List<Person> listAll() {
        
        Document contacts = contactsFile.getContactsAsDOM();
        List<Person> personList = new ArrayList<Person>();
        
        Element rootElement = (Element) contacts.getElementsByTagName("persons").item(0);
        
        NodeList personNodes = rootElement.getElementsByTagName("person");
        
        for (int i = 0; i < personNodes.getLength(); i++) {
            
            Node person = personNodes.item(i);
            
            if (person.getNodeType() == Node.ELEMENT_NODE) {
                
                Element personElement = (Element) person;
                
                Person personData = createPersonFromElement(personElement);
                personList.add(personData);
                
            }
            
        }
        
        return personList;
        
    }

    @Override
    public boolean add(Person data) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Node rootNode = contacts.getElementsByTagName("persons").item(0);
        
        if (rootNode == null) {
            
            rootNode = contacts.createElement("persons");
            contacts.appendChild(rootNode);
            
        }
        
        Node newPersonNode = createNode(data);
        
        Node nodeAdded = rootNode.appendChild(newPersonNode);
        
        return (nodeAdded != null);
        
    }

    @Override
    public Person remove(Person data) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Node rootElement = contacts.getElementsByTagName("persons").item(0);
        
        Node personNodeToRemove = contacts.getElementById(PERSON_ID_PREFIX + data.getId());
        
        rootElement.removeChild(personNodeToRemove);
        
        return createPersonFromElement((Element) personNodeToRemove);
        
    }

    @Override
    public boolean update(Person oldData, Person newData) {
        
        Document contacts = contactsFile.getContactsAsDOM();
        
        Node persons = contacts.getElementsByTagName("persons").item(0);
        
        Node oldPersonNode = contacts.getElementById(PERSON_ID_PREFIX + oldData.getId());
        
        Node newPersonNode = createNode(newData);
        
        Node replacedNode = persons.replaceChild(newPersonNode, oldPersonNode);
        
        return (replacedNode != null);
        
    }
    
    public Person createPersonFromElement(Element personElement) {
        
        Integer personId = Integer.parseInt(personElement.getAttributes().getNamedItem("id").getTextContent().split("_")[1]);
        
        Person person = new Person(personId);
        
        String firstName = personElement.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = personElement.getElementsByTagName("lastName").item(0).getTextContent();
        LocalDate birthday = LocalDate.parse(personElement.getElementsByTagName("birthday").item(0).getTextContent());
        
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setBirthday(birthday);
        
        NodeList personAddressNodeList = personElement.getElementsByTagName("address");
        ArrayList<Address> personAddressList = new ArrayList<Address>();
        
        for (int i = 0; i < personAddressNodeList.getLength(); i++) {
            
            Element addressNode = (Element) personAddressNodeList.item(i);
            
            if (addressNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element personAddress = (Element) addressNode;
                
                Integer postalCode = Integer.parseInt(personAddress.getAttributes().getNamedItem("postalCode").getTextContent().split("_")[1]);
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
        idAttribute.setValue(PERSON_ID_PREFIX + personData.getId().toString());
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
        
        person.appendChild(personId);
        person.appendChild(firstName);
        person.appendChild(lastName);
        person.appendChild(birthday);
        
        for (int i = 0; i < personData.countAddress(); i++) {
            
            Element address = contacts.createElement("address");
            
            Attr postalCodeAttribute = contacts.createAttribute("postalCode");
            postalCodeAttribute.setValue(ADDRESS_ID_PREFIX + personData.getAddress(i).getPostalCode().toString());
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
        
        return person;
        
    }
    
}
