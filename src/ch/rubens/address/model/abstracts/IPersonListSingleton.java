package ch.rubens.address.model.abstracts;

import java.util.Collection;
import javafx.collections.ObservableList;

/**
 *
 * @author rubens
 */
public interface IPersonListSingleton {
    
    public void addPerson(IPerson person);
    public void addAll(Collection<IPerson> list);
    
    public IPerson removePerson(int index);
    public IPerson removePerson(IPerson person);
    
    public IPerson getPerson(int index);
    public IPerson getPerson(String firstName);
    
    public void clear();
    
    public ObservableList<IPerson> getObservableList();
    
}
