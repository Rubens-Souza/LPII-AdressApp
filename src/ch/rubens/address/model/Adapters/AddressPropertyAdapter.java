package ch.rubens.address.model.Adapters;

import ch.rubens.address.model.DTO.Address;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author rubens
 */
public class AddressPropertyAdapter {
    
    private Address address;
    
    private StringProperty street;
    private StringProperty city;
    private IntegerProperty postalCode;
    
    public AddressPropertyAdapter(Address address) {
        
        setAddress(address);
        
    }
    
    private void setAddress(Address address) {
        
        this.address = address;
        
        postalCode = new SimpleIntegerProperty(this.address.getPostalCode());
        street = new SimpleStringProperty(this.address.getStreet());
        city = new SimpleStringProperty(this.address.getStreet());
        
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
    
    public String getStreet() {
        
        return street.get();
        
    }
    
    public String getCity() {
        
        return city.get();
        
    }
    
    public Integer getPostalCode() {
        
        return postalCode.get();
        
    }
    
    public Address getAddress() {
        
        return address;
        
    }
    
}
