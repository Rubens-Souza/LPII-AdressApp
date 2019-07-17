package ch.rubens.address.util.abstracts;

/**
 *
 * Esta interface serve para estabelecer uma padrão de como as classes (Proxy) que irão
 * prestar o serviço de carregar e salvar informações.
 * 
 * @author rubens
 * @param <T> Forma que o dado será salvo
 * @param <G> Forma que o dado será carregado
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
