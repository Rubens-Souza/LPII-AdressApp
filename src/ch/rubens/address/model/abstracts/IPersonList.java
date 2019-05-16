package ch.rubens.address.model.abstracts;

/**
 *
 * @author Aluno
 */
public interface IPersonList<T> {

    public void addPerson(Person person);
    public void removePerson(Person person);
    public void removePerson(Integer index);
    public void clear();
    public Person getPerson(Integer index);
    
    public T getList();
    
}
