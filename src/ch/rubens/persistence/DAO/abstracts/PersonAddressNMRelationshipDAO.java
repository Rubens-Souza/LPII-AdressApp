package ch.rubens.persistence.DAO.abstracts;

import ch.rubens.address.model.concreate.PersonAddressNMRelationship;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface PersonAddressNMRelationshipDAO extends DAO<PersonAddressNMRelationship> {
    
    public List<Integer> getAllPersonsInAddress(int postalcode);
    public List<Integer> getAllAddressOfPerson(int personId);
    public PersonAddressNMRelationship update(int postalCode, int personId, PersonAddressNMRelationship newRelationship);
    
    public String getAddressType(PersonAddressNMRelationship relationship);
    
}
