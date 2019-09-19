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
        ad2.setCity("Valfeno");
        ad2.setStreet("Riverdell");
        
        p1.setFirstName("Raine");
        p1.setLastName("Souza");
        p1.setBirthday(LocalDate.of(2001, Month.MARCH, 2));
        p1.addAddress(ad);
        p1.addAddress(ad2);
        
        PersonXMLDAO pDAO = new PersonXMLDAO();
        
        pDAO.add(p1);
        
        contactsFile.saveFileXML();
        
        Person p = pDAO.getPerson(p1.getId());
        System.out.println(p.getFirstName());
        System.out.println(p.getLastName());
        System.out.println(p.getAddress(0).getStreet());
        
        System.out.println(pDAO.isRegistered(1));
        System.out.println(pDAO.isRegistered(0));

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
