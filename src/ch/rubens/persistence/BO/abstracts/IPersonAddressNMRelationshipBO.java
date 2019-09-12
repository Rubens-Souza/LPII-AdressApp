package ch.rubens.persistence.BO.abstracts;

import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.model.concreate.PersonAddressNMRelationship;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface IPersonAddressNMRelationshipBO extends IBO<PersonAddressNMRelationship> {
    
    public PersonAddressNMRelationship getRelationship(int postalCode, int personId);
    public List<PersonAddressNMRelationship> getAllPersonsInAddress(int postalCode);
    public List<PersonAddressNMRelationship> getAllAddressOfPerson(int personId);
    
    public PersonAddressNMRelationship remove(int postalCode, int personId);
    public List<PersonAddressNMRelationship> removeAllOfPerson(Person person);
    public List<PersonAddressNMRelationship> removeAllOfPerson(int personId);
    public List<PersonAddressNMRelationship> removeAllOfAddress(Address address);
    public List<PersonAddressNMRelationship> removeAllOfAddress(int postalCode);
    
    public PersonAddressNMRelationship update(int postalCode, int personId, PersonAddressNMRelationship newRelationshipData);
    public PersonAddressNMRelationship updatePersonRelationships(int personId, Person newPersonData);
    public PersonAddressNMRelationship updateAddressRelationships(int postalCode, Address newAddressData);
    
    public boolean isRegistered(int postalCode, int personId);
    
    public String getAddressType(PersonAddressNMRelationship relationship);
    public String getAddressType(int postalCode, int personId);
    
}
