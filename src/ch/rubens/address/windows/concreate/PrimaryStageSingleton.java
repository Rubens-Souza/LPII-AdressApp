package ch.rubens.address.windows.concreate;

import ch.rubens.address.MainApp;
import ch.rubens.address.model.concreate.PersonListWrapper;
import ch.rubens.address.view.ContactsListController;
import ch.rubens.address.view.RootLayoutController;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * Esta é uma classe que busca retirar a resposabilidade que a main possuia de 
 * criar e configurar a janela principal do aplicativo
 * 
 * @author rubens
 */
public class PrimaryStageSingleton {
    
    private static Stage stageInstance = null;
    private static PrimaryStageSingleton classInstance = null;
    
    private BorderPane rootLayout;
    private RootLayoutController rootController;
    private ContactsListController overviewController;
    
    
    private PrimaryStageSingleton() {
        
        stageInstance.setTitle("App de Endereços");
        stageInstance.getIcons().add(new Image("file:resoucers/imagens/icone_app.png"));
        
        initRootLayout();
        
        initContentLayout();
        
    }
    
    public static Stage getInstance(Stage primaryStage) {
                
        if (stageInstance == null && classInstance == null) {
            
            stageInstance = primaryStage;
            
            classInstance = new PrimaryStageSingleton();
            
        }
        
        return stageInstance;
        
    }
    
    public static Stage getInstance() throws PrimaryStageInstanceException, PrimaryStageSingletonInstanceException {
        
        if (stageInstance == null) {
            throw new PrimaryStageInstanceException();
        }
        
        if (classInstance == null) {
            throw new PrimaryStageSingletonInstanceException();
        }
        
        return stageInstance;
        
    }
    
    private void initRootLayout() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            stageInstance.setScene(scene);
            
            stageInstance.show();
            
            rootController = loader.getController();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void initContentLayout() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ContactsList.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(personOverview);
            
            overviewController = loader.getController();
            overviewController.setTableItems();
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
