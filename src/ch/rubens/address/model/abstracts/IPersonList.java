package ch.rubens.address.model.abstracts;

/**
 * 
 * 
 * 
 * @author rubens
 * @param <T> List type
 */
public interface IPersonList<T> {

    public void addPerson(Person person);
    public void removePerson(Person person);
    public Person getPerson(Integer i);
    
    public T getList();
    
}
