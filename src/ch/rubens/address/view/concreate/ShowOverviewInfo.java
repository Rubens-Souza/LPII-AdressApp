package ch.rubens.address.view.concreate;

import ch.rubens.address.util.abstracts.IFormater;
import ch.rubens.address.util.concreate.LocalDateFormater;
import ch.rubens.address.view.ContactsListController;
import ch.rubens.address.view.abstracts.IShowPersonInfo;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;

/**
 * Esta classe é a implementação de IShowPersonInfo de acordo com a forma que deve
 * ser exibida pela modal aberta para editar alguma pessoa cadastrada.
 * 
 * @author rubens
 */
public class ShowOverviewInfo implements IShowPersonInfo {
    
    private ContactsListController controller;
    
    public ShowOverviewInfo(ContactsListController controller) {
        
        setController(controller);
        
    }
    
    @Override
    public void loadInfo(Person person) {
        
        if (person != null) {
            
            IFormater dateFormater = new LocalDateFormater("yyyy-MM-dd");
            
            controller.getFirstNameLabel().setText(person.getFirstName());
            controller.getLastNameLabel().setText(person.getLastName());
            controller.getBirthdayLabel().setText(dateFormater.format(person.getBirthday()));
            
            Address personAddress = person.getAddress(0);
            
            controller.getPostalCodeLabel().setText(Integer.toString(personAddress.getPostalCode()));
            controller.getStreetLabel().setText(personAddress.getStreet());
            controller.getCityLabel().setText(personAddress.getCity());
            
        }
        else {
            
            hideInfo();
            
        }
        
    }    
    
    @Override
    public void hideInfo() {
        
        controller.getFirstNameLabel().setText("");
        controller.getLastNameLabel().setText("");
        controller.getStreetLabel().setText("");
        controller.getPostalCodeLabel().setText("");
        controller.getCityLabel().setText("");
        controller.getBirthdayLabel().setText("");
        
    }
    
    public ContactsListController getController() {
        return controller;
    }
    
    private void setController(ContactsListController controller) {
        this.controller = controller;
    }
    
}
