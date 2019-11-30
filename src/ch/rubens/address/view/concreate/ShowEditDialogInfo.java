package ch.rubens.address.view.concreate;

import ch.rubens.address.util.abstracts.IFormater;
import ch.rubens.address.util.concreate.LocalDateFormater;
import ch.rubens.address.view.Controllers.PersonFormController;
import ch.rubens.address.view.abstracts.IShowPersonInfo;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.concreate.Address;
import ch.rubens.address.model.concreate.Person;

/**
 * Esta classe é a implementação de IShowPersonInfo de acordo com a forma que deve
 * ser exibida pela tabela a direita na página inicial do APP.
 * 
 * @author rubens
 */
public class ShowEditDialogInfo implements IShowPersonInfo {

    private PersonFormController controller;
    
    public ShowEditDialogInfo(PersonFormController controller) {
        setController(controller);
    }
    
    @Override
    public void loadInfo(Person person) {
        
        IFormater dateFormater = new LocalDateFormater("yyyy-MM-dd");
        
        controller.getFirstNameField().setText(person.getFirstName());
        controller.getLastNameField().setText(person.getLastName());
        controller.getBirthdayField().setText(dateFormater.format(person.getBirthday()));
        controller.getBirthdayField().setPromptText("yyyy-MM-dd");
        
        Address personAddress = person.getAddress(0);
        if (personAddress == null) {
            
            personAddress = new Address();
            
        }
        else {
                        
            controller.getPostalCodeField().setText(Integer.toString(personAddress.getPostalCode()));
            controller.getStreetField().setText(personAddress.getStreet());
            controller.getCityField().setText(personAddress.getCity());
            
        }
        
    }

    @Override
    public void hideInfo() {
        
        controller.getFirstNameField().setText("");
        controller.getLastNameField().setText("");
        controller.getStreetField().setText("");
        controller.getPostalCodeField().setText("");
        controller.getCityField().setText("");
        controller.getBirthdayField().setText("");
        
    }
    
    public PersonFormController getController(){
        return controller;
    }
    
    private void setController(PersonFormController controller) {
        this.controller = controller;
    }
    
}
