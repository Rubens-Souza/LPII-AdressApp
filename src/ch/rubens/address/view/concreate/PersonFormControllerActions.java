package ch.rubens.address.view.concreate;

import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.util.Validations.AddressValidation;
import ch.rubens.address.util.Validations.BrazilDateValidation;
import ch.rubens.address.util.Validations.IAddressValidation;
import ch.rubens.address.util.Validations.IDateValidation;
import ch.rubens.address.util.Validations.INameValidation;
import ch.rubens.address.util.Validations.NameValidation;
import ch.rubens.address.util.abstracts.IFastAlert;
import ch.rubens.address.util.concreate.FastAlertWarning;
import ch.rubens.address.view.Controllers.PersonFormController;
import java.time.LocalDate;
import javafx.scene.control.TextField;

/**
 *
 * @author rubens
 */
public class PersonFormControllerActions {
    
    private PersonFormController controller;
    
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField birthdayField;
    private TextField postalCodeField;
    private TextField streetField;
    private TextField cityField;
    
    public PersonFormControllerActions(PersonFormController controller) {
        
        this.controller = controller;
        
        firstNameField = controller.getFirstNameField();
        lastNameField = controller.getLastNameField();
        birthdayField = controller.getBirthdayField();
        postalCodeField = controller.getPostalCodeField();
        streetField = controller.getStreetField();
        cityField = controller.getCityField();
        
    }
    
    public void setInputValuesIntoPerson(Person person) {
        
        person.setFirstName(firstNameField.getText());
        person.setLastName(lastNameField.getText());
        person.setBirthday(LocalDate.parse(birthdayField.getText()));
        
        Address personAddress = person.getAddress(0);
        
        personAddress.setPostalCode(Integer.parseInt(postalCodeField.getText()));
        personAddress.setStreet(streetField.getText());
        personAddress.setCity(cityField.getText());
        
    }
    
    public void showEditPersonContent(Person person) {
        
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        birthdayField.setText(person.getBirthday().toString());
        
        Address personAddress = person.getAddress(0);
        
        postalCodeField.setText(personAddress.getPostalCode().toString());
        streetField.setText(personAddress.getStreet());
        cityField.setText(personAddress.getCity());
        
    }
    
    public void showErrorMassage(String errorText) {
        
        IFastAlert alert = new FastAlertWarning("Invalid Entries",
                    "Please, Fix the following errors", errorText);
        alert.openAlert();
        
    }
    
    public boolean validateFirstNameInput() {
        
        INameValidation nameValidator = new NameValidation();
        
        return nameValidator.isFisrtNameValid(firstNameField.getText());
        
    }
    
    public boolean validateLastNameInput() {
        
        INameValidation nameValidator = new NameValidation();
        
        return nameValidator.isLastNameValid(lastNameField.getText());
        
    }
    
    public boolean validateBirthdayInput() {
        
        IDateValidation brazilDateValidator = new BrazilDateValidation();
        
        boolean isFieldEmpty = (birthdayField.getText() == null || birthdayField.getText().length() == 0);
        boolean isBirthdayValid = brazilDateValidator.isDateValid(birthdayField.getText());
        
        return (isBirthdayValid && !isFieldEmpty);
        
    }
    
    public boolean validatePostalCodeInput() {
        
        IAddressValidation addressValidator = new AddressValidation();
        
        return addressValidator.isPostalCodeValid(postalCodeField.getText());
        
    }
    
    public boolean validateStreetInput() {
        
        IAddressValidation addressValidator = new AddressValidation();
        
        return addressValidator.isStreetValid(streetField.getText());
        
    }
    
    public boolean validateCityInput() {
        
        IAddressValidation addressValidator = new AddressValidation();
        
        return addressValidator.isCityValid(cityField.getText());
        
    }
    
    public PersonFormController getController() {
        
        return controller;
        
    }

    public void setController(PersonFormController controller) {
        
        this.controller = controller;
        
    }

}
