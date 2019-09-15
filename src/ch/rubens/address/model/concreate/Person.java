package ch.rubens.address.model.concreate;

import ch.rubens.address.util.concreate.LocalDateAdapter;
import ch.rubens.address.model.abstracts.IPerson;
import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Esta implementação consiste em apenas abstrair uma pessoa, de modo que possa
 * ser usada para apenas representá-la no código. Segue (SRP) (OCP) (DIP)
 * 
 * @author rubens
 */
public class Person implements IPerson {

    private Integer id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private Integer postalCode;
    private LocalDate birthday; 
    
    private Address address;
    
    public Person() { this(null, null); }
    
    public Person(Integer id) {
        setId(id);
    }
    
    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
    
    public Integer getId() {
        return id;
    }
    
    public Address getAddress() {
        return address;
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
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setAddress(Address address) {
        this.address = address;
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
