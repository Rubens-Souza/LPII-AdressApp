package ch.rubens.address.view;

import ch.rubens.address.model.abstracts.Person;
import ch.rubens.address.util.DateUtil;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
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
    private Person person;
    private boolean okClicked = false;
    
    @FXML
    private void initialize() {
        
    }
    
    public void setDialogStage(Stage dialogStage) { this.dialogStage = dialogStage; }
    public void setPerson(Person person) {
        
        this.person = person;
        
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd/mm/yyyy");
        
    }
    
    public boolean isOkClicked() { return okClicked; }
    
    @FXML
    private void handleOk() {
        
        if (isInputValid()) {
            
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
            
            okClicked = true;
            dialogStage.close();
            
        }
        
    }
    
    private boolean isInputValid() {
        
        String errorMessage = "";
        
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Primeiro nome inválido\n";
        }
        
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Ultimo nome inválido\n";
        }
        
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "Rua inválida\n";
        }
        
        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "Código postal inválido\n";
        } else {
            
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Código postal inválido (dever ser um inteiro)\n";
            }
            
        }
        
        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "Cidade inválida!\n"; 
        }
        
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Aniversário inválido!\n";
        } 
        else {
            if (!DateUtil.validDate(birthdayField.getText()))
                errorMessage += "Aniversário inválido. Use o formato dd/mm/yyyy!\n";
        }

        if (errorMessage.length() == 0){
            return true;
        }
        else {
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Campos Inválidos");
            alert.setHeaderText("Por favor, corrija os compos inválidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
            
        }
        
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
}
