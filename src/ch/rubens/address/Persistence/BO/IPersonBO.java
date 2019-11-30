package ch.rubens.address.Persistence.BO;

import ch.rubens.address.model.DTO.Person;
import ch.rubens.address.Persistence.Exceptions.InvalidDataInserted;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public interface IPersonBO extends IBO<Person> {

    public Person getPerson(int personId);
    
    public Person remove(int personId);
    public Person update(int personId, Person newPersonData) throws InvalidDataInserted;
    public boolean isRegistered(int personId);
    
    public String getPersonFirstName(int personId);
    public String getPersonLastName(int personId);
    public LocalDate getPersonBirthdate(int personId);
    
    public ArrayList<Person> loadAllPersonsData();
    
}
