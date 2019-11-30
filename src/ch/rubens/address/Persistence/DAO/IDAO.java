package ch.rubens.address.Persistence.DAO;

import java.util.List;

/**
 *
 * @author Aluno
 */
public interface IDAO<T> {
    
    public List<T> listAll();
    public boolean add(T data);
    public T remove(T data);
    public boolean update(T oldData, T newData);
    public boolean isRegistered(T data);
    
}
