package ch.rubens.address.view;

import ch.rubens.address.util.abstracts.IParser;
import ch.rubens.address.util.concreate.StringToLocalDateParse;
import ch.rubens.address.view.abstracts.IShowPersonInfo;
import ch.rubens.address.view.concreate.EditPersonDataValidation;
import ch.rubens.address.view.concreate.ShowEditDialogInfo;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.rubens.address.model.abstracts.IPerson;

/**
 * O método isInputValid() foi separado em diversas interfaces, cada uma com sua
 * implementação, que são utilizadas na classe de EditPersonDataValidation para
 * validar as entradas dos campos. (OCP), (ISP), (DIP)
 * 
 * @author rubens
 */
public class PersonEditDialogController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField streetField;
    @FXML private TextField postalCodeField;
    @FXML private TextField cityField;
    @FXML private TextField birthdayField;
    
    private Stage dialogStage;
    private IPerson person;
    private IShowPersonInfo infoExhibitor;
    private EditPersonDataValidation editDialogInputsValidator;
    private boolean okClicked = false;
    
    @FXML
    private void initialize() {}
    
    public PersonEditDialogController() {
        infoExhibitor = new ShowEditDialogInfo(this);
        editDialogInputsValidator = new EditPersonDataValidation(this);
    }
    
    public boolean isOkClicked() { return okClicked; }
    
    @FXML
    private void handleOk() {
        
        if (isInputValid()) {
            
            IParser dateParser = new StringToLocalDateParse("dd/MM/yyyy");
            
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday((LocalDate) dateParser.parse(birthdayField.getText()));
            
            okClicked = true;
            dialogStage.close();
            
        }
        
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    private boolean isInputValid() {
        return editDialogInputsValidator.validateInputEntries();
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
    
    public void setDialogStage(Stage dialogStage) { 
        this.dialogStage = dialogStage;
    }
    
    public void setPerson(IPerson person) {
        
        this.person = person;
        infoExhibitor.loadInfo(person);
        
    }
    
}
