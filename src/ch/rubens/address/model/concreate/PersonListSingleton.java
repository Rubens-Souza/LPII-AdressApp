package ch.rubens.address.model.concreate;

import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.abstracts.IPersonListSingleton;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Esta classe é a implementação da interface IPersonListSingleton de forma que
 * ela tenha apenas uma única instância.
 * 
 * @author rubens
 */
public class PersonListSingleton implements IPersonListSingleton {

    private ObservableList<IPerson> personList = FXCollections.observableArrayList();
    private static PersonListSingleton instance = null;
    
    private PersonListSingleton() { }

    public static IPersonListSingleton getInstance() {
        
        if (instance == null)
            instance = new PersonListSingleton();
        
        return instance;
        
    }
    
    @Override
    public void addPerson(IPerson person) {
        
        personList.add(person);
        
    }
    
    @Override
    public void addAll(Collection list) {
        
        personList.addAll(list);
        
    }

    @Override
    public IPerson removePerson(int index) {
        
        return personList.remove(index);
        
    }
    
    @Override
    public IPerson removePerson(IPerson person) {
        
        personList.remove(person);
        
        return person;
        
    }

    @Override
    public IPerson getPerson(int index) {
        
        return personList.get(index);
        
    }

    @Override
    public IPerson getPerson(String firstName) {
        
        IPerson selectedPerson = null;
        
        for (IPerson person : personList) {
            
            if (person.getFirstName() == firstName) {
                selectedPerson = person;
                break;
            }
            
        }
        
        return selectedPerson;
        
    }

    @Override
    public void clear() {
        
        personList.clear();
        
    }

    @Override
    public ObservableList<IPerson> getObservableList() {
        
        return personList;
        
    }
    
}
