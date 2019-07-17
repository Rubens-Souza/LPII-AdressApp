package ch.rubens.address.windows;

import ch.rubens.address.MainApp;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.view.PersonEditDialogController;
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
public class EditPersonStage {

    private PersonEditDialogController controller;
    private Stage layout;
    
    private boolean opened;
    
    private FXMLLoader loader;

    public EditPersonStage(IPerson person) {
        
        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        
        initLayout();

        initController(person);
        
        setOpened(false);
        
    }
    
    private void initLayout() {
        
        AnchorPane page = null;
        try {
            
            page = (AnchorPane) loader.load();
            
        } catch (IOException ex) {
            
            System.out.println("Erro ao carrgar o layout da tela EditPersonStage. Erro: " + ex);
            
        }
            
        layout = new Stage();
        layout.setTitle("Editar Pessoa");
        layout.getIcons().add(new Image("file:resoucers/imagens/icone_app.png"));
        layout.initModality(Modality.WINDOW_MODAL);
        try {
            
            layout.initOwner(PrimaryStageSingleton.getInstance());
            
        } catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
            System.out.println("Falha ao definir o PrimaryStage para a janela de EditPersonStage. Erro: " + ex);
            
        }
        
        Scene scene = new Scene(page);
        layout.setScene(scene);
        
    }
    
    private void initController(IPerson person) {

        controller = loader.getController();
        controller.setDialogStage(layout);
        controller.setPerson(person);
        
    }
    
    public void open() {
        
        layout.showAndWait();
        setOpened(true);
        
    }
    
    public void close() {
        
        layout.close();
        setOpened(false);
        
    }
    
    public boolean isOpen() {
        
        return opened;
        
    }
    
    public Stage getLayout() {
        
        return layout;
        
    }
    
    public PersonEditDialogController getController() {
        
        return controller;
        
    }
    
    private void setOpened(boolean isOpen) {
        
        this.opened = isOpen;
        
    }
    
}
