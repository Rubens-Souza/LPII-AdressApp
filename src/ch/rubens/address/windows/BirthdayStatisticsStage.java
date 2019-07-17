package ch.rubens.address.windows;

import ch.rubens.address.MainApp;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.view.BirthdayStatisticsController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author rubens
 */
public class BirthdayStatisticsStage {
    
    private BirthdayStatisticsController controller;
    private FXMLLoader loader;
    private Stage layout;
    
    private boolean opened;

    public BirthdayStatisticsStage() {
        

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        
        initLayout();
        
        initController();
            
        setOpened(false);
        
    }
    
    private void initLayout() {
        
        AnchorPane page = null;
        try {
            
            page = (AnchorPane) loader.load();
            
        } catch (IOException ex) {
            
            System.out.println("Erro ao carrgar o layout da tela BirthdayStatisticsStage. Erro: " + ex);
            
        }
          
        layout = new Stage();
        layout.setTitle("Estatísticas");
        layout.initModality(Modality.WINDOW_MODAL);
        try {
            
            layout.initOwner(PrimaryStageSingleton.getInstance());
            
        } catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
        System.out.println("Erro ao abrir a . Erro: " + ex);
           
        }
            
        Scene scene = new Scene(page);
        layout.setScene(scene);
        
    }
    
    private void initController() {
        
        controller = loader.getController();
        controller.setPersonData(PersonListSingleton.getInstance().getObservableList());
        
    }
    
    public void open() {
        
        layout.show();
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
    
    public BirthdayStatisticsController getController() {
        
        return controller;
        
    }
    
    private void setOpened(boolean isOpen) {
        
        this.opened = isOpen;
        
    }
    
}
