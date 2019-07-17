package ch.rubens.address.util.concreate;

import ch.rubens.address.MainApp;
import ch.rubens.address.model.abstracts.IListWrapper;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.model.concreate.PersonProperty;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import ch.rubens.address.util.abstracts.IPersistenceFormat;

/**
 *
 * @author rubens
 */
public class PersistDataXML implements IPersistenceFormat<File> {

    private Unmarshaller unmarshaller;
    private Marshaller marshaller;
    private Preferences appPreferences;
    private JAXBContext context;
    private IListWrapper saveWrapper;
    private MainApp main;
    
    public PersistDataXML(Class context, IListWrapper wrapper, MainApp main) throws JAXBException {
        
        setContext(JAXBContext.newInstance(context));
        setWrapper(wrapper);
        
        unmarshaller = this.context.createUnmarshaller();
        
        marshaller = this.context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        appPreferences = Preferences.userNodeForPackage(MainApp.class);

    }
    
    @Override
    public void save(File data) {
        
        ArrayList<PersonProperty> personPropertyList = new ArrayList();
            
        for(IPerson p : PersonListSingleton.getInstance().getObservableList())
            personPropertyList.add((PersonProperty) p);
            
        try {
            
            saveWrapper.setList(personPropertyList);
            
            marshaller.marshal(saveWrapper, data);
            
            setFilePath(data);
            
        } catch (JAXBException ex) {
            
            System.out.println("Não foi possível salvar dados do arquivo:\n" 
                               + data.getPath() + "\n" + ex);
            
        }
            
    }

    @Override
    public List load(File data) {

        IListWrapper loadWrapper = null;
        
        try {
            
            loadWrapper = (IListWrapper) unmarshaller.unmarshal(data);
            
        } catch (JAXBException ex) {
            
            System.out.println("Não foi possível carregar dados do arquivo\n"
                               + data.getPath() + "\n" + ex);
            
        }
        
        if (loadWrapper == null)
            return null;
        
        return loadWrapper.getList();
        
    }
    
    public void setFilePath(File file) {
        
        if (file != null) {
            appPreferences.put("filePath", file.getPath());
            main.getPrimaryStage().setTitle("App de Endereços - " + file.getName());
        }
        else {
            appPreferences.remove("filePath");
            main.getPrimaryStage().setTitle("App de Endereços");
        }
        
    }
    
    public File getFilePath() {
        
        String filePath = appPreferences.get("filePath", null);
        
        if (filePath != null) {
            return new File(filePath);
        }
        else {
            return null;
        }
        
    }
    
    public void setContext(JAXBContext context) {
        
        this.context = context;
        
    }
    
    public void setWrapper(IListWrapper wrapper) {
        
        this.saveWrapper = wrapper;
        
    }
    
    public JAXBContext getContext() { 
        
        return context;
        
    }
    
    public IListWrapper getIListWrapper() { 
        
        return saveWrapper; 
    
    }
    
}
