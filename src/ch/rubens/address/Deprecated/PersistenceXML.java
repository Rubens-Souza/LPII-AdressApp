
package ch.rubens.address.Deprecated;

import ch.rubens.address.view.Windows.PrimaryStageExceptions.PrimaryStageInstanceException;
import ch.rubens.address.view.Windows.PrimaryStageExceptions.PrimaryStageSingletonInstanceException;
import java.io.File;
import javax.xml.bind.JAXBException;

/**
 *
 * Esta classe funciona como um proxy para as operações feitas pelo PersistenceFormatXML
 * 
 * @author rubens
 */
public class PersistenceXML extends PersistenceService<File, File> {
    
    private Class context;
    private IListWrapper wrapper;

    public PersistenceXML(Class context, IListWrapper wrapper) {
        
        this.context = context;
        this.wrapper = wrapper;
        
        setPersistenceFormat(createPersistenceData());
        
    }
    
    @Override
    public void save(File data) {
        
        getPersistenceFormat().save(data);
        
    }

    @Override
    public void load(File data) {
        
        /*List personsSavedList = getPersistenceFormat().load(data);
        
        PersonsListsSingleton.getInstance().clear();
        
        if (personsSavedList != null)
            PersonsListsSingleton.getInstance().addAll(personsSavedList);*/
        
    }

    @Override
    protected IPersistenceFormat createPersistenceData() {
        
        try {
            
            return new PersistenceFormatXML(context, wrapper);
            
        } catch (JAXBException ex) {
            
            System.out.println("Erro ao definir a forma de salvar os dados do sistema: " + ex);
            
        }
        
        return null;
        
    }
  
    public File getFilePath() {
        
        PersistenceFormatXML persistFormat = (PersistenceFormatXML) getPersistenceFormat();
        
        return persistFormat.getFilePath();
        
    }
    
    public void resetFilePath() {
        
        PersistenceFormatXML persistFormat = (PersistenceFormatXML) getPersistenceFormat();
        
        try {
            
            persistFormat.setFilePath(null);
            
        } catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
            System.out.println("Não foi possível redefiinir o caminho do arquivo. Erro: " + ex);
            
        }
        
    }
    
}
