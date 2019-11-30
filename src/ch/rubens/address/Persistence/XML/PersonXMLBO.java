package ch.rubens.address.Persistence.XML;

import ch.rubens.address.model.DTO.Address;
import ch.rubens.address.model.DTO.Person;
import ch.rubens.address.util.Validations.AddressValidation.IAddressValidation;
import ch.rubens.address.util.Validations.DateValidation.IDateValidation;
import ch.rubens.address.util.Validations.NameValidation.INameValidation;
import ch.rubens.address.util.Validations.TypeValidation.INumberValidation;
import ch.rubens.address.util.Validations.AddressValidation.AddressValidation;
import ch.rubens.address.util.Validations.DateValidation.BrazilDateValidation;
import ch.rubens.address.util.Validations.NameValidation.NameValidation;
import ch.rubens.address.util.Validations.TypeValidation.NumberValidation;
import ch.rubens.address.Persistence.BO.IPersonBO;
import ch.rubens.address.Persistence.DAO.IPersonDAO;
import ch.rubens.address.Persistence.Exceptions.InvalidDataInserted;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Essa classe BO permite modificações no arquivo XML Contacts
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
    
    @Override
    public ArrayList<Person> loadAllPersonsData() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
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
