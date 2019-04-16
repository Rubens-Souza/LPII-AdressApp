package ch.rubens.address.model.concreate;

import ch.rubens.address.model.abstracts.Person;
import ch.rubens.address.util.LocalDateAdapter;
import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Esta implementação consiste em apenas abstrair uma pessoa, de modo que possa
 * ser usada para apenas representá-la no código. Segue (SRP) (OCP) (DIP)
 * @author rubens
 */
public class ConcreatePerson implements Person {

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private Integer postalCode;
    private LocalDate birthday; 
    
    public ConcreatePerson() { this(null, null); }
    
    public ConcreatePerson(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
    
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public Integer getPostalCode() {
        return postalCode;
    }

    @Override
    @XmlJavaTypeAdapter(LocalDateAdapter.class) // Anotação para salvar no XML
    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public void setBirthday(LocalDate birthdayDate) {
        this.birthday = birthdayDate;
    }
    
}
