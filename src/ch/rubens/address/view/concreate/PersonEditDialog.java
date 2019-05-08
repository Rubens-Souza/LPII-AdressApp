package ch.rubens.address.view.concreate;

import ch.rubens.address.view.PersonEditDialogController;
import ch.rubens.address.view.abstracts.IWindow;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author rubens
 */
public class PersonEditDialog extends IWindow {

    private PersonEditDialogController controller;
    private Stage dialogStage;
    private AnchorPane layout;
    
    public PersonEditDialog() throws IOException {
        
        setIdName("PersonEditDialog");
        
        loadLayout();
        loadStage();
        
        Scene scene = new Scene(layout);
        
        dialogStage.setScene(scene);
        
        loadController();
        
    }
    
    private void loadLayout() throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(PersonEditDialog.class.getResource("view/PersonEditDialog.fxml"));
        layout = (AnchorPane) loader.load();
        
    }
    
    private void loadStage() {
        
        dialogStage = new Stage();
        
        dialogStage.setTitle("Editar Pessoa");
        dialogStage.getIcons().add(new Image("file:resoucers/imagens/icone_app.png"));
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(PrimaryStageSingleton.getInstance().getPrimaryStage());
        
    }
    
    private void loadController() {
        
        FXMLLoader loader = new FXMLLoader();
        
        controller = loader.getController();
        controller.setDialogStage(dialogStage);
        //controller.setPerson(person);
        
    }
    
    @Override
    public void open() {
        
        dialogStage.showAndWait();
        
    }
    
}
