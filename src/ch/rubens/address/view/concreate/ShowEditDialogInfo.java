package ch.rubens.address.view.concreate;

import ch.rubens.address.model.abstracts.Person;
import ch.rubens.address.util.abstracts.IFormater;
import ch.rubens.address.util.concreate.LocalDateFormater;
import ch.rubens.address.view.PersonEditDialogController;
import ch.rubens.address.view.abstracts.IShowPersonInfo;

/**
 * Esta classe é a implementação de IShowPersonInfo de acordo com a forma que deve
 * ser exibida pela tabela a direita na página inicial do APP.
 * 
 * @author rubens
 */
public class ShowEditDialogInfo implements IShowPersonInfo {

    private PersonEditDialogController controller;
    
    public ShowEditDialogInfo(PersonEditDialogController controller) {
        setController(controller);
    }
    
    @Override
    public void loadInfo(Person person) {
        
        IFormater dateFormater = new LocalDateFormater("dd/MM/yyyy");
        
        controller.getFirstNameField().setText(person.getFirstName());
        controller.getLastNameField().setText(person.getLastName());
        controller.getStreetField().setText(person.getStreet());
        controller.getPostalCodeField().setText(Integer.toString(person.getPostalCode()));
        controller.getCityField().setText(person.getCity());
        controller.getBirthdayField().setText(dateFormater.format(person.getBirthday()));
        controller.getBirthdayField().setPromptText("dd/mm/yyyy");
        
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
    
    public PersonEditDialogController getController(){
        return controller;
    }
    
    private void setController(PersonEditDialogController controller) {
        this.controller = controller;
    }
    
}
