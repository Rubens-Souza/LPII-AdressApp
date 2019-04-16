package ch.rubens.address.model.concreate;

import ch.rubens.address.model.abstracts.Person;
import ch.rubens.address.util.DateUtil;
import ch.rubens.address.util.LocalDateAdapter;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Esta implementação consiste em utilizar os atributos de uma Person como Property
 * para que eventos sejam detectados na interface gráfica. Segue (SRP) (OCP) (DIP)
 * @author rubens
 */
public class PersonProperty implements Person {

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty street;
    private StringProperty city;
    private IntegerProperty postalCode;
    private ObjectProperty<LocalDate> birthday;  
    
    public PersonProperty() { this(null, null); }
    
    public PersonProperty(String firstName, String lastName) {
        
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.street = new SimpleStringProperty("a");
        this.city = new SimpleStringProperty("a");
        this.postalCode = new SimpleIntegerProperty(1);
        this.birthday = new SimpleObjectProperty(DateUtil.parse("02/03/2001"));
        
    }
    
    public StringProperty getFirstNameProperty() {
        return firstName;
    }
    
    public StringProperty getLastNameProperty() {
        return lastName;
    }
    
    public StringProperty getStreetProperty() {
        return street;
    }
    
    public StringProperty getCityProperty() {
        return city;
    }
    
    public IntegerProperty getPostalCodeProperty() {
        return postalCode;
    }
    
    public ObjectProperty<LocalDate> getBirthdayProperty() {
        return birthday;
    }
    
    @Override
    public String getFirstName() {
        return firstName.get();
    }

    @Override
    public String getLastName() {
        return lastName.get();
    }

    @Override
    public String getStreet() {
        return street.get();
    }

    @Override
    public String getCity() {
        return city.get();
    }

    @Override
    public Integer getPostalCode() {
        return postalCode.get();
    }

    @Override
    @XmlJavaTypeAdapter(LocalDateAdapter.class) // Anotação para salvar no XML
    public LocalDate getBirthday() {
        return birthday.get();
    }

    @Override
    public void setFirstName(String name) {
        firstName.set(name);
    }

    @Override
    public void setLastName(String name) {
        lastName.set(name);
    }

    @Override
    public void setStreet(String street) {
        this.street.set(street);
    }

    @Override
    public void setCity(String city) {
        this.city.set(city);
    }

    @Override
    public void setPostalCode(Integer postalCode) {
        this.postalCode.set(postalCode);
    }

    @Override
    public void setBirthday(LocalDate birthdayDate) {
        this.birthday.set(birthdayDate);
    }
    
}
