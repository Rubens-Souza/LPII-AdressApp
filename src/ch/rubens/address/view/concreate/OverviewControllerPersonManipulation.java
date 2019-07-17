package ch.rubens.address.view.concreate;

import ch.rubens.address.model.concreate.PersonProperty;
import ch.rubens.address.util.abstracts.IFastAlert;
import ch.rubens.address.util.concreate.FastAlertWarning;
import ch.rubens.address.view.PersonOverviewController;
import ch.rubens.address.view.abstracts.IPersonManipulation;
import ch.rubens.address.view.abstracts.IShowPersonInfo;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.concreate.PersonListSingleton;

/**
 * Esta classe é a implementação da interface IPersonManipulation
 * 
 * @author rubens
 */
public class OverviewControllerPersonManipulation implements IPersonManipulation {

    private PersonOverviewController controller;
    
    public OverviewControllerPersonManipulation(PersonOverviewController controller){
        
        setController(controller);
        
    }
    
    @Override
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

    @Override
    public void newPerson() {
        
        IPerson tempPerson = new PersonProperty();
        boolean okClicked = controller.getMainApp().showPersonEditDialog(tempPerson);
        
        if (okClicked) {
            PersonListSingleton.getInstance().addPerson(tempPerson);
        }
        
    }

    @Override
    public void editPerson() {
        
       IPerson selectedPerson = controller.getPersonTable().getSelectionModel().getSelectedItem();
       
       if (selectedPerson != null) {
            boolean okClicked = controller.getMainApp().showPersonEditDialog(selectedPerson);
            
            if (okClicked) {
                
                IShowPersonInfo infoExhibitor = new ShowOverviewInfo(controller);
                infoExhibitor.loadInfo(selectedPerson);
                
            }
        }
        
    }
    
    public PersonOverviewController getController(){
        return controller;
    }
    
    private void setController(PersonOverviewController controller){
        this.controller = controller;
    }
}
