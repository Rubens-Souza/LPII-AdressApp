package ch.rubens.address.view;

import ch.rubens.address.model.abstracts.Person;
import ch.rubens.address.model.abstracts.PersonProperty;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.view.abstracts.IPersonManipulation;
import ch.rubens.address.view.concreate.OverviewControllerPersonManipulation;
import ch.rubens.address.view.concreate.ShowOverviewInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.rubens.address.view.abstracts.IShowPersonInfo;

/**
 * As implementações dos seus métodos foram divididas em outras classes para que o 
 * PersonOverviewController tenha como única responsabilidade associar quais ações
 * devem ser tomadas em determinados eventos. (SRP)
 * 
 * A segregação das funcionalidades em outras classes/interfaces atende o OCP,
 * o ISP e o DIP.
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
    @FXML private Label cityLabel;
    @FXML private Label birthdayLabel;
    
    private IPersonManipulation personManipulator;
    private IShowPersonInfo infoExhibitor;
    
    public PersonOverviewController() {
        
        personTable.setItems(PersonListSingleton.getPersonListSingleInstace().getList());
        personManipulator = new OverviewControllerPersonManipulation(this);
        infoExhibitor = new ShowOverviewInfo(this);
        
    }
    
    @FXML
    private void initialize() {
        
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getLastNameProperty());
        
        infoExhibitor.hideInfo();
        
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        
    }
    
    private void showPersonDetails(Person person) {
        infoExhibitor.loadInfo(person);
    }
    
    @FXML
    private void handelDeletePerson() {
        personManipulator.deletePerson();
    }
    
    @FXML
    private void handleNewPerson() {
        personManipulator.newPerson();
    }
    
    @FXML
    private void handleEditPerson() {
        personManipulator.editPerson();
    }
    
    public TableView<Person> getPersonTable() {
        return personTable;
    }

    public TableColumn<PersonProperty, String> getFirstNameColumn() {
        return firstNameColumn;
    }

    public TableColumn<PersonProperty, String> getLastNameColumn() {
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
    
}
