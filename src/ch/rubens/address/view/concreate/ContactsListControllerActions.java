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
import ch.rubens.address.windows.concreate.EditPersonStage;
import ch.rubens.address.windows.abstracts.IWindow;
import javafx.scene.control.Label;

/**
 * Esta classe é a implementação da interface IPersonManipulation
 * 
 * A referência ao Main foi substituida pela instanciação da classe EditPersonStage
 * para abrir a janela
 * 
 * @author rubens
 */
public class ContactsListControllerActions {

    private ContactsListController controller;
    
    public ContactsListControllerActions(ContactsListController controller){
        
        setController(controller);
        
    }
    
    public void deletePerson() {

        int selectedIndex = controller.getPersonTable().getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
            controller.getPersonTable().getItems().remove(selectedIndex);
        }
        else {
            IFastAlert alert = new FastAlertWarning("Nenhuma seleção!", 
                    "Nenhuma pessoa foi selecionada", 
                    "por favor, selecione uma pessoa na tabela."); 
            alert.openAlert();
        }
        
    }

    public void addPerson() {
        
        Person addedPerson = new Person();
        IWindow editWindow = new EditPersonStage(addedPerson);
        
        editWindow.open();
        
        if (editWindow.isOpen()) {
            
            PersonListSingleton.getInstance().addPerson(addedPerson);
            
        }
        
    }

    public void editPerson() {
        
       PersonPropertyAdapter selectedPerson = controller.getPersonTable().getSelectionModel().getSelectedItem();
       Person person = selectedPerson.getPerson();
       
       if (selectedPerson != null) {
           
           IWindow editWindow = new EditPersonStage(person);
           editWindow.open();

           if (editWindow.isOpen()) {
               
               IShowPersonInfo infoExhibitor = new ShowOverviewInfo(controller);
               infoExhibitor.loadInfo(person);
               
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
