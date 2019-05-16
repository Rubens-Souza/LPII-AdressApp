package ch.rubens.address.model.concreate;

import ch.rubens.address.model.abstracts.IPersonList;
import ch.rubens.address.model.abstracts.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Aluno
 */
public class PersonListSingleton implements IPersonList<ObservableList> {

    private ObservableList<Person> personList;
    private static PersonListSingleton instance = null;
    
    private PersonListSingleton() {
        
        personList = FXCollections.observableArrayList();
        
    }
    
    public static PersonListSingleton getInstance() {
        
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
    public void removePerson(Integer index) {
        
        personList.remove(index);
        
    }
    
    public void clear() {
        personList.clear();
    }

    @Override
    public Person getPerson(Integer index) {
        
        return personList.get(index);
        
    }

    @Override
    public ObservableList getList() {
        
        return personList;
        
    }
    
    public void setList(ObservableList personList){
        
        this.personList.addAll(personList);
        
    }
    
}
