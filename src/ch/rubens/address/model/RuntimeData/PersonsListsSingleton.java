package ch.rubens.address.model.RuntimeData;

import ch.rubens.address.model.Adapters.PersonPropertyAdapter;
import ch.rubens.address.model.DTO.Person;
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
public class PersonsListsSingleton {

    private static PersonsListsSingleton instance = null;
    
    private ArrayList<Person> personList;
    private ObservableList<PersonPropertyAdapter> personObservableList;
    
    private PersonsListsSingleton() {
    
        personList = new ArrayList<Person>();
        personObservableList = FXCollections.observableArrayList();
        
    }

    public static PersonsListsSingleton getInstance() {
        
        if (instance == null) {
            
            instance = new PersonsListsSingleton();
            
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
        
        personObservableList.remove(index);
        return personList.remove(index);
        
    }
    
    public Person removePerson(Person person) {
        
        PersonPropertyAdapter personAdapter = getPersonPropertyAdapter(person);
        
        personObservableList.remove(personAdapter);
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
    
    public PersonPropertyAdapter getPersonPropertyAdapter(int index) {
        
        return personObservableList.get(index);
        
    }
    
    public PersonPropertyAdapter getPersonPropertyAdapter(Integer personId) {
        
        Person selectedPerson = getPerson(personId);
        
        return getPersonPropertyAdapter(selectedPerson);
        
    }
    
    public PersonPropertyAdapter getPersonPropertyAdapter(Person person) {
        
        PersonPropertyAdapter selectedPersonAdapter = null;
        
        for (PersonPropertyAdapter personAdapter : personObservableList) {
            
            if (personAdapter.getPerson().equals(person)) {
                
                selectedPersonAdapter = personAdapter;
                break;
                
            }
            
        }
        
        return selectedPersonAdapter;
        
    }

    public void clear() {
        
        personList.clear();
        personObservableList.clear();
        
    }
    
    public ArrayList<Person> getPersonsList() {
        
        return personList;
        
    }

    public ObservableList<PersonPropertyAdapter> getPersonsObservableList() {
        
        return personObservableList;
        
    }
    
}
