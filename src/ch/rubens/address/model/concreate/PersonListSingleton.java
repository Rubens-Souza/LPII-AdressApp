package ch.rubens.address.model.concreate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Esta classe é a implementação da interface IPersonListSingleton de forma que
 * ela tenha apenas uma única instância.
 * 
 * @author rubens
 */
public class PersonListSingleton {

    private static PersonListSingleton instance = null;
    
    private ArrayList<Person> personList;
    private ObservableList<PersonPropertyAdapter> personObservableList;
    
    private PersonListSingleton() {
    
        personList = new ArrayList<Person>();
        personObservableList = FXCollections.observableArrayList();
        
    }

    public static PersonListSingleton getInstance() {
        
        if (instance == null) {
            
            instance = new PersonListSingleton();
            
        }
        
        return instance;
        
    }
    
    public void addPerson(Person person) {
        
        personList.add(person);
        
        PersonPropertyAdapter personProperty = new PersonPropertyAdapter(person);
        personObservableList.add(personProperty);
        
    }
    
    public void addAll(Collection<Person> list) {
        
        for (Person person : list) {
            
            addPerson(person);
            
        }
        
    }

    public Person removePerson(int index) {
        
        return personList.remove(index);
        
    }
    
    public Person removePerson(Person person) {
        
        personList.remove(person);
        
        return person;
        
    }

    public Person getPerson(int index) {
        
        return personList.get(index);
        
    }

    public Person getPerson(Integer personId) {
        
        Person selectedPerson = null;
        
        for (Person person : personList) {
            
            if (Objects.equals(person.getId(), personId)) {
                
                selectedPerson = person;
                break;
                
            }
            
        }
        
        return selectedPerson;
        
    }

    public void clear() {
        
        personList.clear();
        
    }
    
    public ArrayList<Person> getPersonsList() {
        
        return personList;
        
    }

    public ObservableList<PersonPropertyAdapter> getPersonsObservableList() {
        
        return personObservableList;
        
    }
    
}
