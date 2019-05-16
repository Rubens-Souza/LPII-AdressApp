package ch.rubens.address.view;

import ch.rubens.address.MainApp;
import ch.rubens.address.model.concreate.PersonListSingleton;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

/**
 *
 * @author rubens
 */
public class RootLayoutController {

    private MainApp main;
    
    // Criar novo arquivo
    @FXML
    private void handleNew() {
        PersonListSingleton.getInstance().clear();
        main.setPersonFilePath(null);
    }
    
    // Abrir outro arquivo
    @FXML
    private void handleOpen() {
        
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        
        if (file != null) {
            main.loadPersonDataFromFile(file);
        }
    }
    
    // Salvar o arquivo já aberto
    @FXML
    private void handleSave() {
        
        File personFile = main.getPersonFilePath();
        
        if (personFile != null) {
            main.savePersonDataToFile(personFile);
        }
        else {
            handleSaveAs();
        }
        
    }
    
    // salvar como
    @FXML
    private void handleSaveAs() {
        
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        
        if (file != null) {
            if (!file.getPath().endsWith(".xml"))
                file = new File(file.getPath() + ".xml");
            main.savePersonDataToFile(file);
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
        main.showBirthdayStatistics();
    }
    
    public void setMainApp(MainApp main) { this.main = main; }
    
}
