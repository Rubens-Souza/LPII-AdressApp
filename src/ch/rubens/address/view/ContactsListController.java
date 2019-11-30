package ch.rubens.address.view;

import ch.rubens.address.view.concreate.ContactsListControllerActions;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.model.concreate.PersonPropertyAdapter;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import ch.rubens.address.view.abstracts.IStageController;

/**
 * As implementações dos seus métodos foram divididas em outras classes para que o 
 ContactsListController tenha como única responsabilidade associar quais ações
 devem ser tomadas em determinados eventos. (SRP)
 * 
 * A segregação das funcionalidades em outras classes/interfaces atende o OCP,
 * o ISP e o DIP.
 * 
 * Todas as referências ao main foram retiradas. As referencias a lista de pessoas e
 * ao primaryStage foram substituidas pelos seus respectivos singletons
 * 
 * @author rubens
 */
public class ContactsListController implements IStageController {
    
    @FXML private TableView<PersonPropertyAdapter> personTable;
    @FXML private TableColumn<PersonPropertyAdapter, String> firstNameColumn;
    @FXML private TableColumn<PersonPropertyAdapter, String> lastNameColumn;
    
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label streetLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label cityLabel;
    @FXML private Label birthdayLabel;

    private ContactsListControllerActions controllerActions;
    
    public ContactsListController() {
        
        controllerActions = new ContactsListControllerActions(this);
        
    }
    
    @FXML
    private void initialize() {
        
        firstNameColumn.setCellValueFactory(
                
                cellData -> cellData.getValue().getFirstNameProperty()
                
        );
        
        lastNameColumn.setCellValueFactory(
                
                cellData -> cellData.getValue().getLastNameProperty()
                
        );
        
        controllerActions.clearLabelsContent();
        
        personTable.getSelectionModel().selectedItemProperty().addListener(
                
                (observable, oldValue, newValue) -> showPersonDetails(newValue)
                
        );
        
    }
    
    private void showPersonDetails(PersonPropertyAdapter personPropertySelected) {
        
        if (personPropertySelected != null) {
            
            Person person = personPropertySelected.getPerson();
            controllerActions.showSelectedPersonContent(person);
            
        }
        
    }
    
    @FXML
    private void handelDeletePerson() {
        
        controllerActions.deletePerson();
        
    }
    
    @FXML
    private void handleNewPerson() {
        
        controllerActions.addPerson();
        
    }
    
    @FXML
    private void handleEditPerson() {
        
        controllerActions.editPerson();
        
    }
    
    public TableView<PersonPropertyAdapter> getPersonTable() {
        return personTable;
    }

    public TableColumn<PersonPropertyAdapter, String> getFirstNameColumn() {
        return firstNameColumn;
    }

    public TableColumn<PersonPropertyAdapter, String> getLastNameColumn() {
        return lastNameColumn;
    }

    public Label getFirstNameLabel() {
        return firstNameLabel;
    }

    public Label getLastNameLabel() {
        return lastNameLabel;
    }

    public Label getStreetLabel() {
        return streetLabel;
    }

    public Label getPostalCodeLabel() {
        return postalCodeLabel;
    }

    public Label getCityLabel() {
        return cityLabel;
    }

    public Label getBirthdayLabel() {
        return birthdayLabel;
    }
    
    public void setTableItems() {
        
        PersonListSingleton personsList = PersonListSingleton.getInstance();
        ObservableList<PersonPropertyAdapter> personsObservableList = personsList.getPersonsObservableList();
        
        personTable.setItems(personsObservableList);
        
    }
    
}
