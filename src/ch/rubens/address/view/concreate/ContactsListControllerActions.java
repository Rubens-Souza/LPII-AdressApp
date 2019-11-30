package ch.rubens.address.view.concreate;

import ch.rubens.address.model.concreate.PersonPropertyAdapter;
import ch.rubens.address.util.abstracts.IFastAlert;
import ch.rubens.address.util.concreate.FastAlertWarning;
import ch.rubens.address.view.ContactsListController;
import ch.rubens.address.view.abstracts.IPersonManipulation;
import ch.rubens.address.view.abstracts.IShowPersonInfo;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.windows.concreate.PersonFormStage;
import ch.rubens.address.windows.abstracts.IWindow;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * Esta classe é a implementação da interface IPersonManipulation
 
 A referência ao Main foi substituida pela instanciação da classe PersonFormStage
 para abrir a janela
 * 
 * @author rubens
 */
public class ContactsListControllerActions {

    private ContactsListController controller;
    
    public ContactsListControllerActions(ContactsListController controller){
        
        setController(controller);
        
    }
    
    public void deletePerson() {
        
        TableView<PersonPropertyAdapter> personsTable = controller.getPersonTable();
        int selectedPersonIndex = personsTable.getSelectionModel().getSelectedIndex();
        
        if (selectedPersonIndex >= 0) {
            
            PersonPropertyAdapter personAdapter = personsTable.getItems().get(selectedPersonIndex);
            Person personToRemove = personAdapter.getPerson();
            
            PersonListSingleton personsList = PersonListSingleton.getInstance();
            personsList.removePerson(personToRemove);
            
            clearLabelsContent();
            
        }
        else {
            
            IFastAlert alert = new FastAlertWarning("No Person Selected", 
                    "None person selected to be removed", 
                    "Please, select a person to remove from the contacts"); 
            alert.openAlert();
            
        }
        
    }

    public void addPerson() {
        
        Person addedPerson = new Person();
        Address addedPersonAddress = new Address();
        addedPerson.addAddress(addedPersonAddress);
        
        IWindow personFormWindow = new PersonFormStage(addedPerson, PersonFormStage.OpeningMode.NEW);
        personFormWindow.open();
        
        if (personFormWindow.hasClosed()) {
            
            PersonListSingleton.getInstance().addPerson(addedPerson);
            
        }
        
    }

    public void editPerson() {
        
       TableView<PersonPropertyAdapter> personsTable = controller.getPersonTable();
       PersonPropertyAdapter selectedPerson = personsTable.getSelectionModel().getSelectedItem();
       
       if (selectedPerson != null) {
           
           Person person = selectedPerson.getPerson();
           
           IWindow personFormWindow = new PersonFormStage(person, PersonFormStage.OpeningMode.EDIT);
           personFormWindow.open();

           selectedPerson.setPerson(person);
           
           if (personFormWindow.hasClosed()) {
               
               showSelectedPersonContent(person);
               personsTable.refresh();
               
           }
                
        }
        
    }
    
    public void showSelectedPersonContent(Person selectedPerson) {
        
        Label firstNameLabel = controller.getFirstNameLabel();
        Label lastNameLabel = controller.getLastNameLabel();
        Label birthdayLabel = controller.getBirthdayLabel();
        Label postalCodeLabel = controller.getPostalCodeLabel();
        Label streetLabel = controller.getStreetLabel();
        Label cityLabel = controller.getCityLabel();
        
        firstNameLabel.setText(selectedPerson.getFirstName());
        lastNameLabel.setText(selectedPerson.getLastName());
        birthdayLabel.setText(selectedPerson.getBirthday().toString());
        
        Address selectedPersonAddress = selectedPerson.getAddress(0);
        
        postalCodeLabel.setText(selectedPersonAddress.getPostalCode().toString());
        streetLabel.setText(selectedPersonAddress.getStreet());
        cityLabel.setText(selectedPersonAddress.getCity());
        
    }
    
    public void clearLabelsContent() {
        
        Label firstNameLabel = controller.getFirstNameLabel();
        Label lastNameLabel = controller.getLastNameLabel();
        Label birthdayLabel = controller.getBirthdayLabel();
        Label postalCodeLabel = controller.getPostalCodeLabel();
        Label streetLabel = controller.getStreetLabel();
        Label cityLabel = controller.getCityLabel();
        
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        birthdayLabel.setText("");
        postalCodeLabel.setText("");
        streetLabel.setText("");
        cityLabel.setText("");
        
    }
    
    public ContactsListController getController(){
        
        return controller;
        
    }
    
    private void setController(ContactsListController controller){
        
        this.controller = controller;
        
    }
}
