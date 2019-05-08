package ch.rubens.address.model.concreate;

import ch.rubens.address.model.abstracts.IPersonList;
import ch.rubens.address.model.abstracts.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rubens
 */
public class PersonListSingleton implements IPersonList<ObservableList> {

    private static ObservableList<Person> personList;
    private static PersonListSingleton instance;
    
    private PersonListSingleton() {
        
        personList = FXCollections.observableArrayList();
        
    }
    
    public static PersonListSingleton getPersonListSingleInstace() {
        
        if (instance == null)
            instance = new PersonListSingleton();
        
        return instance;
        
    }
    
    @Override
    public void addPerson(Person person) {
        
        personList.add(person);
        
    }

    @Override
    public void removePerson(Person person) {
        
        personList.remove(person);
        
    }

    @Override
    public Person getPerson(Integer i) {
        
        return personList.get(i);
        
    }

    @Override
    // Aplicar o padrão itarator ao invés de pegar a lista
    public ObservableList getList() {
        
        return personList;
        
    }
    
}
