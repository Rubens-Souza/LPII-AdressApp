package ch.rubens.address.view.concreate;

import ch.rubens.address.MainApp;
import ch.rubens.address.view.PersonOverviewController;
import ch.rubens.address.view.RootLayoutController;
import ch.rubens.address.view.abstracts.IWindow;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author rubens
 */
public class PrimaryStageSingleton extends IWindow {

    private static PrimaryStageSingleton instance = null;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane overviewLayout;
    private RootLayoutController rootController;
    private PersonOverviewController overviewController;
    
    private PrimaryStageSingleton(Stage startPrimaryStage) {

        primaryStage = startPrimaryStage;
        primaryStage.setTitle("App de Endereços");
        primaryStage.getIcons().add(new Image("file:resoucers/imagens/icone_app.png"));
        
        try {
            loadRootLayout();
        } catch (IOException ex) {
            Logger.getLogger(PrimaryStageSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene rootScene = new Scene(rootLayout);
        primaryStage.setScene(rootScene);
        
        loadRootController();
        
        try {
            loadPersonOverviewLayout();
        } catch (IOException ex) {
            Logger.getLogger(PrimaryStageSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        rootLayout.setCenter(overviewLayout);
        
        loadOverviewController();
        
    }
    
    private void loadRootLayout() throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(PrimaryStageSingleton.class.getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        
    }
    
    private void loadRootController() {
        
        FXMLLoader loader = new FXMLLoader();
        
        rootController = loader.getController();
        rootController.setMainApp(this);
        
    }
    
    private void loadPersonOverviewLayout() throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
        overviewLayout = (AnchorPane) loader.load();
        
    }
    
    private void loadOverviewController() {
        
        FXMLLoader loader = new FXMLLoader();
        
        overviewController = loader.getController();
        overviewController.setMainApp(this);
        
    }
    
    public static PrimaryStageSingleton initSingleStageInstance(Stage startPrimaryStage) {
        
        if (instance == null)
            instance = new PrimaryStageSingleton(startPrimaryStage);
        
        return instance;
        
    }
    
    public static PrimaryStageSingleton getInstance() {
        
        return instance;
        
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    @Override
    public void open() {
        primaryStage.show();
    }
    
}
