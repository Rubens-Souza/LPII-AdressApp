package ch.rubens.persistence.DAO.abstracts;

import ch.rubens.address.model.concreate.Person;
import java.util.Date;

/**
 *
 * @author Aluno
 */
public interface PersonDAO extends DAO<Person> {
    
    public Person getPerson(int personId);
    public Person remove(int personId);
    public Person update(int personId, Person newPersonData);
    public boolean isRegistered(int personId);
    
    public String getPersonFirstName(int personId);
    public String getPersonLastName(int personId);
    public Date getPersonBirthdate(int personId);
    public int getPersonPostalCode(int personId);
    
}
