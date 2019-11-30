package ch.rubens.address.view.Controllers;

import ch.rubens.address.model.concreate.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ch.rubens.address.view.abstracts.IController;
import ch.rubens.address.view.concreate.PersonFormControllerActions;
import ch.rubens.address.windows.abstracts.IWindow;
import ch.rubens.address.windows.concreate.PersonFormStage.OpeningMode;

/**
 * O método isInputValid() foi separado em diversas interfaces, cada uma com sua
 * implementação, que são utilizadas na classe de EditPersonDataValidation para
 * validar as entradas dos campos. (OCP), (ISP), (DIP)
 *  
 * Todas as referências ao main foram retiradas. As referencias a lista de pessoas e
 * ao primaryStage foram substituidas pelos seus respectivos singletons
 * 
 * @author rubens
 */
public class PersonFormController implements IController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField streetField;
    @FXML private TextField postalCodeField;
    @FXML private TextField cityField;
    @FXML private TextField birthdayField;
    
    private OpeningMode openingMode;
    private PersonFormControllerActions controllerActions;
    
    private IWindow personFromWindow;
    private boolean okClicked = false;
    
    private Person manipulatedPerson;
    
    @FXML
    private void initialize() {
    
        controllerActions = new PersonFormControllerActions(this);
        
    }
    
    @FXML
    private void handleOk() {
        
        boolean isInputsValid = validateInputs();
        
        if (!isInputsValid) {
            
            return;
            
        }
        
        controllerActions.setInputValuesIntoPerson(manipulatedPerson);
            
        okClicked = true;
        personFromWindow.close();    
        
    }
    
    @FXML
    private void handleCancel() {
        
        personFromWindow.close();
        
    }
    
    private boolean validateInputs() {
        
        boolean isFirstNameValid = controllerActions.validateFirstNameInput();
        boolean isLastNamevalid = controllerActions.validateLastNameInput();
        boolean isBirthdayValid = controllerActions.validateBirthdayInput();
        boolean isPostalCodeValid = controllerActions.validatePostalCodeInput();
        boolean isStreetValid = controllerActions.validateStreetInput();
        boolean isCityValid = controllerActions.validateCityInput();
        
        String errorMessage = "";
        
        if (!isFirstNameValid) {
            
            errorMessage += "First Name Is Invalid.\n";
                    
        }
        
        if (!isLastNamevalid) {
            
            errorMessage += "Last Name Is Invalid.\n";
            
        }
        
        if (!isBirthdayValid) {
            
            errorMessage += "Birthday Is Invalid. Use the format (dd/MM/yyyy)\n";
                    
        }
        
        if (!isPostalCodeValid) {
            
            errorMessage += "Postal Code Is Invalid.\n";
                    
        }
        
        if (!isStreetValid) {
            
            errorMessage += "Street Is Invalid.\n";
                    
        }
        
        if (!isCityValid) {
            
            errorMessage += "City Is Invalid.\n";
                    
        }
        
        if (errorMessage.length() != 0) {
            
            controllerActions.showErrorMassage(errorMessage);
            return false;
            
        }
        
        return true;
        
    }
    
    public boolean wasOkClicked() { 
        
        return okClicked;
        
    }
    
    public TextField getFirstNameField() {
        
        return firstNameField;
        
    }
    
    public TextField getLastNameField() {
        
        return lastNameField;
        
    }
    
    public TextField getStreetField() {
        
        return streetField;
        
    }
    
    public TextField getPostalCodeField() {
        
        return postalCodeField;
        
    }
    
    public TextField getCityField() {
        
        return cityField;
        
    }
    
    public TextField getBirthdayField() {
        
        return birthdayField;
        
    }
    
    public OpeningMode getOpeningMode() {
        
        return openingMode;
        
    }
    
    public void setDialogStage(IWindow window) { 
        
        this.personFromWindow = window;
        
    }
    
    public void setOpeningMode(OpeningMode openingMode) {
        
        this.openingMode = openingMode;
        
    }
    
    public void setPerson(Person person) {
        
        manipulatedPerson = person;
        
        if (openingMode == OpeningMode.EDIT) {
            
            controllerActions.showEditPersonContent(manipulatedPerson);
            
        }
        
    }
    
}
