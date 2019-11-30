package ch.rubens.address.model.concreate;

import ch.rubens.address.util.concreate.LocalDateAdapter;
import ch.rubens.address.model.abstracts.IPersonProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Esta implementação consiste em utilizar os atributos de uma Person como Property
 * para que eventos sejam detectados na interface gráfica. Segue (SRP) (OCP) (DIP)
 * 
 * @author rubens
 */
public class PersonPropertyAdapter {

    private Person person;
    
    private StringProperty firstName;
    private StringProperty lastName;
    private ObjectProperty<LocalDate> birthday;  
    
    private ArrayList<AddressPropertyAdapter> addressProperties;
    
    public PersonPropertyAdapter(Person person) { 
        
        addressProperties = new ArrayList<AddressPropertyAdapter>();
        
        setPerson(person);
        
    }
    
    public void setPerson(Person person) {
        
        this.person = person;
        
        firstName = new SimpleStringProperty(this.person.getFirstName());
        lastName = new SimpleStringProperty(this.person.getLastName());
        birthday = new SimpleObjectProperty(this.person.getBirthday());
        
        addressProperties.clear();
        
        for (Address address : person.getAddressList()) {
            
            AddressPropertyAdapter addressProperty = new AddressPropertyAdapter(address);
            addressProperties.add(addressProperty);
            
        }
        
    }
    
    public StringProperty getFirstNameProperty() {
        
        return firstName;
        
    }
    
    public StringProperty getLastNameProperty() {
        
        return lastName;
        
    }
    
    public ObjectProperty<LocalDate> getBirthdayProperty() {
        
        return birthday;
        
    }
    
    public String getFirstName() {
        
        return firstName.get();
        
    }

    public String getLastName() {
        
        return lastName.get();
        
    }

    public LocalDate getBirthday() {
        
        return birthday.get();
        
    }
    
    public Person getPerson() {
        
        return person;
        
    }

    public AddressPropertyAdapter getAddressProperty(int index) {
        
        return addressProperties.get(index);
        
    }
    
    public ArrayList<AddressPropertyAdapter> getAddressPropertiesList() {
        
        return addressProperties;
        
    }

}
