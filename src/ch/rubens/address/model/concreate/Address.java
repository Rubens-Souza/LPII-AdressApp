package ch.rubens.address.model.concreate;

/**
 *
 * Cria uma classe DTO para guardar o endereço das pessoas. Isso permite que elas
 * possuam mais de um edereço.
 * 
 * @author Aluno
 */
public class Address {

    private Integer postalCode;
    private String street;
    private String city;
    
    public Address() {
    
        this(null, null, null);
        
    }
    
    public Address(Integer postalCode) {
        
        this(postalCode, null, null);
        
    }
    
    public Address(Integer postalCode, String street, String city) {
        
        setPostalCode(postalCode);
        setStreet(street);
        setCity(city);
        
    }

    public Integer getPostalCode() {
        return postalCode;
    }
    
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
    
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
}
