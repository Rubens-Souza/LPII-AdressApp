package ch.rubens.persistence.BO.abstracts;

import java.util.List;

/**
 *
 * @author Aluno
 */
public interface IBO<T> {
    
    public List<T> listAll();
    public T add(T data);
    public T remove(T data);
    public T update(T oldData, T newData);
    public boolean isRegistered(T data);
    
}
