package ch.rubens.address.model.concreate;

/**
 *
 * Cria uma classe DTO para representar o relacionamento N:M  entre Person e 
 * Address.
 * 
 * @author Aluno
 */
public class PersonAddressNMRelationship {
    
    private int personId;
    private int postalCode;
    private String addressType;

    public PersonAddressNMRelationship(int personId, int postalCode) {
        
        setPersonId(personId);
        setPostalCode(postalCode);
        
    }
    
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
    
}
