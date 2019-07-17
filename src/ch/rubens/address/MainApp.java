/*
Separar as funções do main (show, carregar, setPreferences)
criar uma mascara para fazer o loader
*/

package ch.rubens.address;

import ch.rubens.address.model.concreate.PersonListWrapper;
import ch.rubens.address.model.concreate.PersonProperty;
import ch.rubens.address.view.BirthdayStatisticsController;
import ch.rubens.address.view.PersonEditDialogController;
import ch.rubens.address.view.PersonOverviewController;
import ch.rubens.address.view.RootLayoutController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import ch.rubens.address.model.abstracts.IPerson;
import ch.rubens.address.model.abstracts.IListWrapper;
import ch.rubens.address.model.abstracts.IPersonListSingleton;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.util.abstracts.IPersistenceFormat;
import ch.rubens.address.util.concreate.PersistDataXML;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author rubens
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private IPersonListSingleton personsList;
    private IPersistenceFormat format;
    
    public MainApp() {
        
        personsList = PersonListSingleton.getInstance();
        
        try {
            format = new PersistDataXML(PersonListWrapper.class, new PersonListWrapper(), this);
        } catch (JAXBException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
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
    
    // metodo para carregar o arquivo XML
    public void loadPersonDataFromFile(File file) {
        
        personsList.clear();
        personsList.addAll(format.load(file));
        
        
    }
    
    // Salva os dados de personsData em um XML
    public void savePersonDataToFile(File file) {
        
        format.save(file);
        
    }
    
    // salva o caminho do ultimo arquivo aberto no registro
    public void setPersonFilePath(File file) {
        
        PersistDataXML a = (PersistDataXML) format;
        a.setFilePath(file);
        
    }
    
    // carrega o caminho do ultimo arquivo aberto no registro
    public File getPersonFilePath() {
        
        PersistDataXML a = (PersistDataXML) format;
        return a.getFilePath();
        
    }
    
    public Stage getPrimaryStage() { return primaryStage; }
    
}
