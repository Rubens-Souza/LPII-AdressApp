/*
Separar as funções do main (show)
criar uma mascara para fazer o loader
*/

package ch.rubens.address;

import ch.rubens.address.model.concreate.PersonListWrapper;
import ch.rubens.address.view.BirthdayStatisticsController;
import ch.rubens.address.view.PersonEditDialogController;
import ch.rubens.address.view.PersonOverviewController;
import ch.rubens.address.view.RootLayoutController;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.abstracts.IPersonListSingleton;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.util.abstracts.PersistenceService;
import ch.rubens.address.util.concreate.PersistenceXML;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author rubens
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private IPersonListSingleton personsList;
    private PersistenceService persistence;
    
    public MainApp() {
        
        personsList = PersonListSingleton.getInstance();
        
        persistence = new PersistenceXML(PersonListWrapper.class, new PersonListWrapper(), this);

    }
    
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("App de Endereços");
        this.primaryStage.getIcons().add(new Image("file:resoucers/imagens/icone_app.png"));
        
        initRootLayout();
        
        showPersonOverview();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void initRootLayout() {
        
        PersistenceXML persistenceXML = (PersistenceXML) persistence;
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File file = persistenceXML.getFilePath();
        if (file != null) {
            persistence.load(file);
        }
        
    }
    
    public void showPersonOverview() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(personOverview);
            
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    // mostra a tela de editar e criar
    public boolean showPersonEditDialog(IPerson person) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Pessoa");
            dialogStage.getIcons().add(new Image("file:resoucers/imagens/icone_app.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            
            dialogStage.showAndWait();
            
            return true;
            
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public void showBirthdayStatistics() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Estatísticas");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personsList.getObservableList());
            
            dialogStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public Stage getPrimaryStage() { return primaryStage; }
    
}
