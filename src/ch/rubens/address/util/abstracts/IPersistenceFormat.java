package ch.rubens.address.util.abstracts;

import java.util.List;

/**
 *
 * @author rubens
 */
public interface IPersistenceFormat<T> {

    public void save(T data);
    
    public List load(T data);
    
}
