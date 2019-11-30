package ch.rubens.address;

import ch.rubens.address.model.DTO.Address;
import ch.rubens.address.model.DTO.Person;
import ch.rubens.address.model.RuntimeData.PersonsListsSingleton;
import ch.rubens.address.util.UserInput.Chooser;
import ch.rubens.address.util.UserInput.FolderChooser;
import ch.rubens.address.view.Windows.PrimaryStageSingleton;
import ch.rubens.address.Persistence.BO.IPersonBO;
import ch.rubens.address.Persistence.XML.PersonXMLBO;
import ch.rubens.address.Persistence.DAO.IDAO;
import ch.rubens.address.Persistence.DAO.IPersonDAO;
import ch.rubens.address.Persistence.JDBC.PersonJDBCDAO;
import ch.rubens.address.Persistence.XML.ContactsFileXMLSingleton;
import ch.rubens.address.Persistence.XML.PersonXMLDAO;
import ch.rubens.address.Persistence.Exceptions.XML.NoneFileOpenedException;
import ch.rubens.address.Persistence.Exceptions.InvalidDataInserted;
import ch.rubens.address.Persistence.Exceptions.NotRegisteredDataException;
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
    
    private PersonsListsSingleton personsList;
    private ContactsFileXMLSingleton contactsFile;
    
    public MainApp() throws ParserConfigurationException, SAXException, IOException, TransformerException, NoneFileOpenedException, InvalidDataInserted {
        
        personsList = PersonsListsSingleton.getInstance();
        
        try {
            
            contactsFile = ContactsFileXMLSingleton.openXMLFile("Contacts_XML_Saved_Files\\contacts.xml");
            
        }
        catch (ParserConfigurationException | SAXException | IOException ex) {
            
            System.out.println("Erro when opening file: " + ex);
            
        }
       
        IPersonDAO personJDBCDAO = new PersonJDBCDAO();
        
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
        
        IPersonBO pBO = new PersonXMLBO();
        
        pBO.add(p1);
        pBO.add(p2);
        pBO.add(p3);
        
        contactsFile.saveFileXML();
        
        Person pg = pBO.getPerson(p1.getId());
        
        System.out.println(pg.getAddress(0).getCity());

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
