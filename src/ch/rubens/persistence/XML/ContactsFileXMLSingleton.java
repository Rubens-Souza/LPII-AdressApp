package ch.rubens.persistence.XML;

import ch.rubens.persistence.XML.exceptions.NoneFileOpenedException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author rubens
 */
public class ContactsFileXMLSingleton extends File {
    
    private static ContactsFileXMLSingleton contactsXMLFile;
    private Document contactsAsDOM;
    
    private ContactsFileXMLSingleton(String path) throws ParserConfigurationException, SAXException, IOException {
        
        super(path);
        
    }
    
    public static ContactsFileXMLSingleton openXMLFile(String path) throws ParserConfigurationException, SAXException, IOException {
        
        if (contactsXMLFile == null) {
            
            contactsXMLFile = new ContactsFileXMLSingleton(path);
            
        }
        
        if (!contactsXMLFile.exists()) {
            
            String directoryPath = contactsXMLFile.getParentFile().getAbsolutePath();
            
            new File(directoryPath).mkdir();
            
        }
        
        return contactsXMLFile;
        
    }
    
    public static ContactsFileXMLSingleton getContactsFile() throws NoneFileOpenedException {
        
        if (contactsXMLFile == null) {
            
            throw new NoneFileOpenedException();
            
        }
        
        return contactsXMLFile;
        
    }
    
    public void saveFileXML(String saveLocationPath) throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException, TransformerException {
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();        
        DOMSource contactsDOMSource = new DOMSource(getContactsAsDOM());
        
        StreamResult streamResult = new StreamResult(saveLocationPath);
        
        transformer.transform(contactsDOMSource, streamResult);
        
    }
    
    public void saveFileXML() throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException, TransformerException {
        
        saveFileXML(getPath());
        
    }
    
    public Document getContactsAsDOM() {
        
        if (contactsAsDOM == null) {
            
            try {
                
                createContactsDOM();
                
            }
            catch (ParserConfigurationException | SAXException | IOException ex) {
                
                System.out.println("Error when tring to open the contacts file as DOM");
                
            }
            
        }
        
        return contactsAsDOM;
        
    }

    private void createContactsDOM() throws ParserConfigurationException, SAXException, IOException {
        
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
        System.out.println(contactsXMLFile.exists());
        if (contactsXMLFile.exists()) {
            
            contactsAsDOM = documentBuilder.parse((File) contactsXMLFile);
            
        }
        else {
            
            contactsAsDOM = documentBuilder.newDocument();
            
        }
            
        contactsAsDOM.normalize();
        
    }
    
}
