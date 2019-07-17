
package ch.rubens.address.util.concreate;

import ch.rubens.address.MainApp;
import ch.rubens.address.model.abstracts.IListWrapper;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.util.abstracts.IPersistenceFormat;
import ch.rubens.address.util.abstracts.PersistenceService;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author rubens
 */
public class PersistenceXML extends PersistenceService<File, File> {
    
    private Class context;
    private IListWrapper wrapper;
    private MainApp main;

    public PersistenceXML(Class context, IListWrapper wrapper, MainApp main) {
        
        this.context = context;
        this.wrapper = wrapper;
        this.main = main;
        
        setPersistenceFormat(createPersistenceData());
        
    }
    
    @Override
    public void save(File data) {
        
        getPersistenceFormat().save(data);
        
    }

    @Override
    public void load(File data) {
        
        List personsSavedList = getPersistenceFormat().load(data);
        
        PersonListSingleton.getInstance().clear();
        
        if (personsSavedList != null)
            PersonListSingleton.getInstance().addAll(personsSavedList);
        
    }

    @Override
    protected IPersistenceFormat createPersistenceData() {
        
        try {
            
            return new PersistDataXML(context, wrapper, main);
            
        } catch (JAXBException ex) {
            
            System.out.println("Erro ao definir a forma de salvar os dados do sistema: " + ex);
            
        }
        
        return null;
        
    }
  
    public File getFilePath() {
        
        PersistDataXML persistFormat = (PersistDataXML) getPersistenceFormat();
        
        return persistFormat.getFilePath();
        
    }
    
    public void resetFilePath() {
        
        PersistDataXML persistFormat = (PersistDataXML) getPersistenceFormat();
        persistFormat.setFilePath(null);
        
    }
    
}
