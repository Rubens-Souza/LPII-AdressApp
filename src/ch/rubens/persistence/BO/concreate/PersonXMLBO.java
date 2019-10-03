package ch.rubens.persistence.BO.concreate;

import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.util.abstracts.IAddressValidation;
import ch.rubens.address.util.abstracts.IDateValidation;
import ch.rubens.address.util.abstracts.INameValidation;
import ch.rubens.address.util.abstracts.INumberValidation;
import ch.rubens.address.util.concreate.AddressValidation;
import ch.rubens.address.util.concreate.BrazilDateValidation;
import ch.rubens.address.util.concreate.NameValidation;
import ch.rubens.address.util.concreate.NumberValidation;
import ch.rubens.persistence.BO.abstracts.IPersonBO;
import ch.rubens.persistence.DAO.abstracts.IPersonDAO;
import ch.rubens.persistence.XML.DAO.PersonXMLDAO;
import ch.rubens.persistence.exceptions.InvalidDataInserted;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class PersonXMLBO implements IPersonBO {

    IPersonDAO personDAO;
    INameValidation nameValidator;
    INumberValidation numberValidator;
    IAddressValidation addressValidator;
    IDateValidation dateValidator;
    
    public PersonXMLBO() {
        
        personDAO = new PersonXMLDAO();
        nameValidator = new NameValidation();
        numberValidator = new NumberValidation();
        addressValidator = new AddressValidation();
        dateValidator = new BrazilDateValidation();
        
    }
    
    @Override
    public Person getPerson(int personId) {
        
        Person serchedPerson = personDAO.getPerson(personId);
        return serchedPerson;
        
    }

    @Override
    public Person remove(int personId) {
        
        Person removedPerson = personDAO.remove(personId);
        return removedPerson;
        
    }

    @Override
    public Person update(int personId, Person newPersonData) throws InvalidDataInserted {
        
        if (!isPersonValid(newPersonData)) {
            
            throw new InvalidDataInserted();
            
        }
        
        Person updatedPerson = personDAO.update(personId, newPersonData);
        return updatedPerson;
        
    }

    @Override
    public boolean isRegistered(int personId) {
        
        boolean isRegistered = personDAO.isRegistered(personId);
        return isRegistered;
        
    }

    @Override
    public String getPersonFirstName(int personId) {
        
        String firstName = personDAO.getPersonFirstName(personId);
        return firstName;
        
    }

    @Override
    public String getPersonLastName(int personId) {
        
        String lastName = personDAO.getPersonFirstName(personId);
        return lastName;
        
    }

    @Override
    public LocalDate getPersonBirthdate(int personId) {
        
        LocalDate birthday = personDAO.getPersonBirthday(personId);
        return birthday;
        
    }

    @Override
    public List<Person> listAll() {
        
        List<Person> personsList = personDAO.listAll();
        return personsList;
        
    }

    @Override
    public boolean add(Person data) throws InvalidDataInserted {
        
        if (!isPersonValid(data)) {
            
            throw new InvalidDataInserted();
            
        }
        
        boolean wasPersonIserted = personDAO.add(data);
        return wasPersonIserted;
        
    }

    @Override
    public Person remove(Person data) {
        
        Person removedPerson = personDAO.remove(data);
        return removedPerson;
        
    }

    @Override
    public boolean update(Person oldData, Person newData) throws InvalidDataInserted {
        
        if (!isPersonValid(newData)) {
            
            throw new InvalidDataInserted();
            
        }
        
        boolean wasPersonUpdated = personDAO.update(oldData, newData);
        return wasPersonUpdated;
        
    }

    @Override
    public boolean isRegistered(Person data) {
        
        boolean isRegistered = personDAO.isRegistered(data);
        return isRegistered;
        
    }
    
    private boolean isPersonValid(Person person) {
        
        boolean nameValidation = isNameValid(person);
        boolean addressValidation = isAddressValid(person);
        boolean birthdayValidation = isBirthdayValid(person);
        
        return (nameValidation && addressValidation && birthdayValidation);
        
    }
    
    private boolean isNameValid(Person person) {
        
        boolean firstNameValidation = nameValidator.isFisrtNameValid(person.getFirstName());
        boolean lastNameValidation = nameValidator.isLastNameValid(person.getLastName());
        
        return (firstNameValidation && lastNameValidation);
        
    }
    
    private boolean isAddressValid(Person person) {
        
        for (Address address : person.getAddressList()) {
            
            boolean cityValidation = addressValidator.isCityValid(address.getCity());
            boolean postalCodeValidation = addressValidator.isPostalCodeValid(address.getPostalCode().toString());
            boolean streetValidation = addressValidator.isStreetValid(address.getStreet());
            
            if (!cityValidation || !postalCodeValidation || !streetValidation) {
                
                return false;
                
            }
            
        }
        
        return true;
        
    }
    
    private boolean isBirthdayValid(Person person) {
        
        boolean birthdayValidation = dateValidator.isDateValid(person.getBirthday().toString());
        
        return birthdayValidation;
        
    }
    
}
