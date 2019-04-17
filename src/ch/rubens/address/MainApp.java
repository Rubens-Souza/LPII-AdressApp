/*
Separar as funções do main (show, carregar, setPreferences)
criar uma mascara para fazer o loader
*/

package ch.rubens.address;

import ch.rubens.address.model.abstracts.Person;
import ch.rubens.address.model.concreate.PersonListWrapper;
import ch.rubens.address.model.abstracts.ListWrapper;
import ch.rubens.address.model.abstracts.PersonProperty;
import ch.rubens.address.model.concreate.ConcreatePersonProperty;
import ch.rubens.address.view.BirthdayStatisticsController;
import ch.rubens.address.view.PersonEditDialogController;
import ch.rubens.address.view.PersonOverviewController;
import ch.rubens.address.view.RootLayoutController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 *
 * @author rubens
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personsData = FXCollections.observableArrayList();
    
    public MainApp() {
        personsData.add(new ConcreatePersonProperty("Hans", "Muster"));
        personsData.add(new ConcreatePersonProperty("Ruth", "Mueller"));
        personsData.add(new ConcreatePersonProperty("Heinz", "Kurz"));
        personsData.add(new ConcreatePersonProperty("Cornelia", "Meier"));
        personsData.add(new ConcreatePersonProperty("Werner", "Meyer"));
        personsData.add(new ConcreatePersonProperty("Lydia", "Kunz"));
        personsData.add(new ConcreatePersonProperty("Anna", "Best"));
        personsData.add(new ConcreatePersonProperty("Stefan", "Meier"));
        personsData.add(new ConcreatePersonProperty("Martin", "Mueller"));
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
        
        /*File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }*/
        
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
    public boolean showPersonEditDialog(Person person) {
        
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
            controller.setPersonData(personsData);
            
            dialogStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    // metodo para carregar o arquivo XML
    public void loadPersonDataFromFile(File file) {
        
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            
            ListWrapper<PersonProperty> wrapper = (PersonListWrapper) um.unmarshal(file);
            personsData.clear();
            personsData.addAll(wrapper.getList());
            
            setPersonFilePath(file);
        }
        catch (Exception e) {
            System.out.println("Não foi possível carregar dados do arquivo\n"
                               + file.getPath());
        }
        
    }
    
    // Salva os dados de personsData em um XML
    public void savePersonDataToFile(File file) {
        
        try {
            
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            // "Encapsula" a personPropertyList
            ListWrapper<PersonProperty> wrapper = new PersonListWrapper();
            ArrayList<PersonProperty> personPropertyList = new ArrayList();
            
            for(Person p : personsData){
                personPropertyList.add((PersonProperty) p);
            }
            
            wrapper.setList(personPropertyList);
            
            // Salva os dados no XML
            m.marshal(wrapper, file);
            
            // Salva o caminho do arquivo no registro
            setPersonFilePath(file);
            
        }
        catch (Exception e) {
            System.out.println("Não foi possível salvar dados do arquivo:\n" 
                               + file.getPath());
        }
        
    }
    
    // salva o caminho do ultimo arquivo aberto no registro
    public void setPersonFilePath(File file) {
        
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("App de Endereços - " + file.getName());
        }
        else {
            prefs.remove("filePath");
            primaryStage.setTitle("App de Endereços");
        }
        
    }
    
    // carrega o caminho do ultimo arquivo aberto no registro
    public File getPersonFilePath() {
        
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        
        if (filePath != null) {
            return new File(filePath);
        }
        else {
            return null;
        }
        
    }
    
    public Stage getPrimaryStage() { return primaryStage; }
    public ObservableList<Person> getPersonsData() { return personsData; }
    
}
