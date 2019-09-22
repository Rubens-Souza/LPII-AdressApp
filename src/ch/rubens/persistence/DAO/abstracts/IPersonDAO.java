package ch.rubens.persistence.DAO.abstracts;

import ch.rubens.address.model.concreate.Person;
import java.time.LocalDate;

/**
 *
 * @author Aluno
 */
public interface IPersonDAO extends IDAO<Person> {
    
    public Person getPerson(int personId);
    public Person remove(int personId);
    public Person update(int personId, Person newPersonData);
    public boolean isRegistered(int personId);
    
    public String getPersonFirstName(int personId);
    public String getPersonLastName(int personId);
    public LocalDate getPersonBirthday(int personId);
    public int getPersonPostalCode(int personId);
    
}
