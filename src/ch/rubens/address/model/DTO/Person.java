package ch.rubens.address.model.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Esta implementação consiste em apenas abstrair uma pessoa, de modo que possa
 * ser usada para apenas representá-la no código. Segue (SRP) (OCP) (DIP)
 * 
 * @author rubens
 */
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthday; 
    
    private ArrayList<Address> addressList;
    
    public Person() { 
        
        addressList = new ArrayList<Address>();
        
    }
    
    public Person(Integer id) {
        
        setId(id);
        addressList = new ArrayList<Address>();
        
    }
    
    public Integer getId() {
        return id;
    }
    
    public ArrayList<Address> getAddressList() {
        return addressList;
    }
    
    public Address getAddress(int index) {
        
        if (addressList.size() <= 0) {
            
            return null;
            
        }
        
        return addressList.get(index);
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setAddressList(ArrayList<Address> addressList) {
        this.addressList = addressList;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

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
