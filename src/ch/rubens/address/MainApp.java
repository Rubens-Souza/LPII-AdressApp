package ch.rubens.address;

import ch.rubens.address.model.abstracts.IPersonListSingleton;
import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.windows.concreate.PrimaryStageSingleton;
import ch.rubens.persistence.XML.ContactsFileXMLSingleton;
import ch.rubens.persistence.XML.DAO.PersonXMLDAO;
import ch.rubens.persistence.exceptions.NotRegisteredDataException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * 
 * A main tem a unica responsabilidade de criar o primaryStage e entrega-lo ao
 * PrimaryStageSingleton
 * 
 * @author rubens
 */
public class MainApp extends Application {
    
    private IPersonListSingleton personsList;
    
    public MainApp() {
        
        personsList = PersonListSingleton.getInstance();

    }
    
    @Override
    public void start(Stage primaryStage) {

        PrimaryStageSingleton.getInstance(primaryStage);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException, NotRegisteredDataException {
        
        Person p1 = new Person(1);
        Address ad = new Address(31545040);
        
        ad.setCity("Belo Horizonte");
        ad.setStreet("Rua Onofre Camillo Campos");
        
        p1.setFirstName("Raine");
        p1.setLastName("Souza");
        p1.setBirthday(LocalDate.of(2001, Month.MARCH, 2));
        p1.addAddress(ad);
        
        ContactsFileXMLSingleton contacts = ContactsFileXMLSingleton.openXMLFile("Contacts_XML_Saved_Files\\contacts.xml");
        
        Document dc = contacts.getContactsAsDOM();

        /*Element persons = dc.createElement("persons");
        dc.appendChild(persons);
        
        Element el = dc.createElement("person");
        Attr idAttribute = dc.createAttribute("id");
        idAttribute.setValue(p1.getId().toString());
        el.setAttributeNode(idAttribute);
        persons.appendChild(el);      
        
        Element personId = dc.createElement("id");
        personId.appendChild(dc.createTextNode(p1.getId().toString()));
        el.appendChild(personId);
        
        Element firstName = dc.createElement("firstName");
        firstName.appendChild(dc.createTextNode(p1.getFirstName()));
        el.appendChild(firstName);
        
        Element lastName = dc.createElement("lastName");
        lastName.appendChild(dc.createTextNode(p1.getLastName()));
        el.appendChild(lastName);
        
        Element birthday = dc.createElement("birthday");
        birthday.appendChild(dc.createTextNode(p1.getBirthday().toString()));
        el.appendChild(birthday);
        
        Element address = dc.createElement("address");
        Attr postalCodeAttribute = dc.createAttribute("postalCode");
        postalCodeAttribute.setValue(p1.getAddress(0).getPostalCode().toString());
        address.setAttributeNode(postalCodeAttribute);
        el.appendChild(address);
        
        Element postalCode = dc.createElement("postalCode");
        postalCode.appendChild(dc.createTextNode(p1.getAddress(0).getPostalCode().toString()));
        address.appendChild(postalCode);
        
        Element city = dc.createElement("city");
        city.appendChild(dc.createTextNode(p1.getAddress(0).getCity()));
        address.appendChild(city);
        
        Element street = dc.createElement("street");
        street.appendChild(dc.createTextNode(p1.getAddress(0).getStreet()));
        address.appendChild(street);*/
        
        contacts.saveFileXML();
        
        PersonXMLDAO pDAO = new PersonXMLDAO();
        Person p = pDAO.getPerson(1);
        
        System.out.println(pDAO.isRegistered(1));
        System.out.println(pDAO.isRegistered(0));
        
        launch(args);
        
    }
    
}
