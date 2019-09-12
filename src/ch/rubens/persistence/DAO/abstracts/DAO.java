package ch.rubens.persistence.DAO.abstracts;

import java.util.List;

/**
 *
 * @author Aluno
 */
public interface DAO<T> {
    
    public List<T> listAll();
    public T add(T data);
    public T remove(T data);
    public T update(T oldData, T newData);
    public boolean isRegistered(T data);
    
}
