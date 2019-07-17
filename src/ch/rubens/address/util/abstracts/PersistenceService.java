package ch.rubens.address.util.abstracts;

/**
 *
 * @author rubens
 */
public abstract class PersistenceService<T, G> {

    private IPersistenceFormat persistenceFormat;
    
    public abstract void save(T data);
    public abstract void load(G data);
    
    protected abstract IPersistenceFormat createPersistenceData();
    
    public void setPersistenceFormat(IPersistenceFormat persistenceFormat) {
        
        this.persistenceFormat = persistenceFormat;
        
    }
    
    public IPersistenceFormat getPersistenceFormat() {
        
        return persistenceFormat;
        
    }
    
}
