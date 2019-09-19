package ch.rubens.persistence.DAO.abstracts;

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
