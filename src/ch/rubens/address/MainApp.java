package ch.rubens.address;

import ch.rubens.address.model.abstracts.IPersonListSingleton;
import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.windows.concreate.PrimaryStageSingleton;
import ch.rubens.persistence.XML.ContactsFileXMLSingleton;
import ch.rubens.persistence.XML.DAO.PersonXMLDAO;
import ch.rubens.persistence.exceptions.NotRegisteredDataException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ContactsFileXMLSingleton contactsFile;
    
    public MainApp() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        
        personsList = PersonListSingleton.getInstance();
        
        try {
            
            contactsFile = ContactsFileXMLSingleton.openXMLFile("Contacts_XML_Saved_Files\\contacts.xml");
            
        }
        catch (ParserConfigurationException | SAXException | IOException ex) {
            
            System.out.println("Erro when opening file: " + ex);
            
        }
        
        Person p1 = new Person(1);
        Address ad = new Address(31545040);
        Address ad2 = new Address(31545000);
        
        ad.setCity("Belo Horizonte");
        ad.setStreet("Rua Onofre Camillo Campos");
        ad2.setCity("El Dorado");
        ad2.setStreet("r. 31");
        
        p1.setFirstName("Raine");
        p1.setLastName("Souza");
        p1.setBirthday(LocalDate.of(2001, Month.MARCH, 2));
        p1.addAddress(ad);
        p1.addAddress(ad2);
        
        Person p2 = new Person(2);
        Address adP2 = new Address(30545040);
        
        adP2.setCity("El Dorado");
        adP2.setStreet("Guillock");
        
        p2.setFirstName("Reizer");
        p2.setLastName("Zuriel");
        p2.setBirthday(LocalDate.of(2001, Month.MARCH, 2));
        p2.addAddress(adP2);
        
        Person p3 = new Person(3);
        
        p3.setFirstName("Zuriel");
        p3.setLastName("Reizer");
        p3.setBirthday(LocalDate.of(2001, Month.MARCH, 2));
        p3.addAddress(adP2);
        
        
        // Testing
        PersonXMLDAO pDAO = new PersonXMLDAO();
        
        pDAO.add(p1);
        pDAO.add(p3);
        pDAO.add(p2);
        
        contactsFile.saveFileXML();
        
        System.out.println(pDAO.isRegistered(1)); // True
        System.out.println(pDAO.isRegistered(0)); // False
        
        for (int i = 1; i <= 3; i++) {
            
            Person p = pDAO.getPerson(i);
            
            System.out.println(p.getFirstName());
            System.out.println(p.getLastName());
            System.out.println(p.getAddress(0).getStreet());            
            
        }
        
        Person p4 = new Person(4);
        p4.setFirstName("Mae");
        p4.setLastName("Borowski");
        p4.setBirthday(LocalDate.of(1996, Month.DECEMBER, 1));
        
        Address ad4 = new Address(4);
        ad4.setCity("Possum Springs");
        ad4.setStreet("Line street");
        p4.addAddress(ad4);
        
        pDAO.update(p1, p4);
        
        contactsFile.saveFileXML();
        
        Person removedPerson = pDAO.remove(p4);
        
        contactsFile.saveFileXML();
        
        System.out.println(removedPerson.getFirstName());
        
        pDAO.add(p4);
        pDAO.add(p1);
        
        contactsFile.saveFileXML();
        
        List<Person> list = pDAO.listAll();
        
        for (Person p : list) {
            
            System.out.println(p.getFirstName());
            System.out.println(p.getLastName());
            System.out.println(p.getAddress(0).getStreet());            
            
        }

    }
    
    @Override
    public void start(Stage primaryStage) {

        PrimaryStageSingleton.getInstance(primaryStage);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           
        launch(args);
        
    }
    
}
