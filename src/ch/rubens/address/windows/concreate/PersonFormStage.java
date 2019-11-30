package ch.rubens.address.windows.concreate;

import ch.rubens.address.windows.abstracts.IWindow;
import ch.rubens.address.MainApp;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.view.Controllers.PersonFormController;
import ch.rubens.address.view.abstracts.IController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Esta é uma classe que busca retirar a resposabilidade que a main possuia de 
 * criar e configurar a janela de Edição e Novo
 * 
 * @author rubens
 */
public class PersonFormStage extends IWindow {
    
    public enum OpeningMode {
    
        EDIT,
        NEW
        
    }
    
    private OpeningMode openingMode;
    
    public PersonFormStage(Person person, OpeningMode openingMode) {
        
        setOpeningMode(openingMode);
        
        if (getOpeningMode() == OpeningMode.EDIT) {
            
            setName("Edit Person");
            
        }
        else {
            
            setName("Add Person");
            
        }
        
        setLoader(new FXMLLoader());
        getLoader().setLocation(MainApp.class.getResource("view/PersonForm.fxml"));
        
        initLayout();

        initController();
        
        PersonFormController controller = (PersonFormController) getController();
        controller.setPerson(person);
        
        setOpened(false);
        
    }
    
    @Override
    protected void initLayout() {
        
        AnchorPane page = null;
        try {
            
            page = (AnchorPane) getLoader().load();
            
        } catch (IOException ex) {
            
            System.out.println("Erro ao carrgar o layout da tela EditPersonStage. Erro: " + ex);
            
        }
            
        setLayout(new Stage());
        getLayout().setTitle(getName());
        getLayout().getIcons().add(new Image("file:resoucers/imagens/icone_app.png"));
        getLayout().initModality(Modality.WINDOW_MODAL);
        try {
            
            getLayout().initOwner(PrimaryStageSingleton.getInstance());
            
        } catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
            System.out.println("Falha ao definir o PrimaryStage para a janela de PersonFormStage. Erro: " + ex);
            
        }
        
        Scene scene = new Scene(page);
        getLayout().setScene(scene);
        
    }

    @Override
    protected void initController() {
        
        setController(getLoader().getController());
        
        PersonFormController controller = (PersonFormController) getController();
        controller.setDialogStage(this);
        controller.setOpeningMode(openingMode);
        
    }
    
    public void setOpeningMode(OpeningMode openingMode) {
        
        this.openingMode = openingMode;
        
    }
    
    public OpeningMode getOpeningMode() {
        
        return openingMode;
        
    }
    
}
