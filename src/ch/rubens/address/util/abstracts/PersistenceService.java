package ch.rubens.address.util.abstracts;

/**
 *
 * @author rubens
 */
public abstract class PersistenceService {

    private IPersistenceFormat persistenceFormat;
    
    public abstract void save();
    public abstract void load();
    
    protected abstract IPersistenceFormat createPersistenceData();
            
    public PersistenceService() {
        
        setPersistenceFormat(createPersistenceData());
        
    }
    
    public void setPersistenceFormat(IPersistenceFormat persistenceFormat) {
        
        this.persistenceFormat = persistenceFormat;
        
    }
    
    public IPersistenceFormat getPersistenceFormat() {
        
        return persistenceFormat;
        
    }
    
}
