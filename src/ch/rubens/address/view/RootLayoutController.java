package ch.rubens.address.view;

import ch.rubens.address.model.concreate.Person;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.model.concreate.PersonListWrapper;
import ch.rubens.address.util.UserInput.Chooser;
import ch.rubens.address.util.UserInput.FolderChooser;
import ch.rubens.address.util.abstracts.PersistenceService;
import ch.rubens.address.util.concreate.PersistenceXML;
import ch.rubens.address.windows.concreate.BirthdayStatisticsStage;
import ch.rubens.address.windows.concreate.PrimaryStageInstanceException;
import ch.rubens.address.windows.concreate.PrimaryStageSingleton;
import ch.rubens.address.windows.concreate.PrimaryStageSingletonInstanceException;
import ch.rubens.address.windows.abstracts.IWindow;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import ch.rubens.address.view.abstracts.IController;
import ch.rubens.persistence.BO.abstracts.IPersonBO;
import ch.rubens.persistence.BO.concreate.PersonXMLBO;
import java.util.ArrayList;

/**
 *
 * Todas as referências ao main foram retiradas. As referencias a lista de pessoas e
 * ao primaryStage foram substituidas pelos seus respectivos singletons
 * 
 * @author rubens
 */
public class RootLayoutController implements IController {

    private PersistenceService persistence;
    
    public RootLayoutController() {
        
        persistence = new PersistenceXML(PersonListWrapper.class, new PersonListWrapper());
        
    }
    
    // Criar novo arquivo
    @FXML
    private void handleNew() {
        
        PersistenceXML persistenceXML = (PersistenceXML) persistence;
        
        PersonListSingleton.getInstance().clear();
        persistenceXML.resetFilePath();
        
    }
    
    // Abrir outro arquivo
    @FXML
    private void handleOpen() {
        
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = null;
        
        try {
            
            file = fileChooser.showOpenDialog(PrimaryStageSingleton.getInstance());
            
        } catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
            System.out.println("Erro ao abrir o arquivo: " + ex);
            
        }
        
        if (file != null)
            persistence.load(file);
            
    }
    
    // Salvar o arquivo já aberto
    @FXML
    private void handleSave() {
        
        PersistenceXML persistenceXML = (PersistenceXML) persistence;
        
        File personFile = persistenceXML.getFilePath();
        
        if (personFile != null) {
            persistence.save(personFile);
        }
        else {
            handleSaveAs();
        }
        
    }
    
    @FXML
    private void handleExport() {
        
        Chooser chooser = new FolderChooser();
        
        chooser.setPanelName("Choose Folder to Exeport Your Contacts");
        File selectedFolder = chooser.open();
        
        if (selectedFolder != null) {
            
            
            /*ArrayList<Person> personList = PersonListSingleton.getInstance().getObservableList();
            IPersonBO personBO = new PersonXMLBO();
            
            for (Person person : personList) {
                
                
                
            }*/
            
        }
        else {
            
            return;
            
        }
        
    }
    
    // salvar como
    @FXML
    private void handleSaveAs() {
        
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = null;
        
        try {
            file = fileChooser.showSaveDialog(PrimaryStageSingleton.getInstance());
        } catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
            System.out.println("Erro ao salvar o arquivo: " + ex);
            
        }
        
        if (file != null) {
            if (!file.getPath().endsWith(".xml"))
                file = new File(file.getPath() + ".xml");
            persistence.save(file);
        }
        
    }
    
    @FXML
    private void handleAbout() {

    }
    
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
    private void handleShowBirthdayStatistics() {
       
        IWindow statsiticsWindow = new BirthdayStatisticsStage();
        statsiticsWindow.open();
        
    }
    
}
