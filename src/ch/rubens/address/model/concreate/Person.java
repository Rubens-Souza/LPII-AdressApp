package ch.rubens.address.model.concreate;

import ch.rubens.address.util.concreate.LocalDateAdapter;
import ch.rubens.address.model.abstracts.IPerson;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
    private LocalDate birthday; 
    private String street;
    private String city;
    private Integer postalCode;
    
    private ArrayList<Address> addressList;
    
    public Person() { this(null, null); }
    
    public Person(Integer id) {
        setId(id);
        addressList = new ArrayList<Address>();
    }
    
    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
    
    public Integer getId() {
        return id;
    }
    
    public ArrayList<Address> getAddressList() {
        return addressList;
    }
    
    public Address getAddress(int index) {
        return addressList.get(index);
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
    
    public void setAddressList(ArrayList<Address> addressList) {
        this.addressList = addressList;
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
    
    public void addAddress(Address address) {
        addressList.add(address);
    }
    
    public void addAllAddress(Collection<? extends Address> addressList) {
        this.addressList.addAll(addressList);
    }
    
    public void removeAddress(Address address) {
        addressList.remove(address);
    }
    
    public void removeAddress(int index) {
        addressList.remove(index);
    }
    
    public void removeAllAddress() {
        addressList.clear();
    }
    
    public boolean haveAddress(Address address) {
        return addressList.contains(address);
    }
    
    public int countAddress() {
        return addressList.size();
    }
    
}
