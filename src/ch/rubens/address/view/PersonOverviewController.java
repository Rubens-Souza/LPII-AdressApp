package ch.rubens.address.view;

import ch.rubens.address.MainApp;
import ch.rubens.address.model.abstracts.Person;
import ch.rubens.address.model.abstracts.PersonProperty;
import ch.rubens.address.model.concreate.ConcreatePersonProperty;
import ch.rubens.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author rubens
 */
public class PersonOverviewController {
    
    @FXML private TableView<Person> personTable;
    @FXML private TableColumn<PersonProperty, String> firstNameColumn;
    @FXML private TableColumn<PersonProperty, String> lastNameColumn;
    
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label streetLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label cityLabel;;
    @FXML private Label birthdayLabel;
    
    private MainApp mainApp;
    
    public PersonOverviewController() {}
    
    @FXML
    private void initialize() {
        
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getLastNameProperty());
        
        showPersonDetails(null);
        
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    
    /**
     * Preenche todos os campos de texto para mostrar detalhes sobre a pessoa.
     * Se a pessoa especificada for null, todos os campos de texto são limpos.
     * 
     * @param person a pessoa ou null
     */
    private void showPersonDetails(Person person) {
        
        if (person != null) {
            
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            
        }
        else {
            
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
            
        }
        
    }
    
    @FXML
    private void handelDeletePerson() {
        
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        }
        else {
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nenhuma seleção!");
            alert.setHeaderText("Nenhuma pessoa foi selecionada");
            alert.setContentText("por favor, selecione uma pessoa na tabela.");
            alert.showAndWait();
            
        }
        
                
    }
    
    @FXML
    private void handleNewPerson() {
        
        Person tempPerson = new ConcreatePersonProperty();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonsData().add(tempPerson);
        }
        
    }
    
    @FXML
    private void handleEditPerson() {
        
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }
        }
        
    }
    
    public void setMainApp(MainApp main) {
        
        this.mainApp = main;
        
        personTable.setItems(mainApp.getPersonsData());
        
    }
}
