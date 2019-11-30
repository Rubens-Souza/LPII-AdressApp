package ch.rubens.address.util.UserInput;

import ch.rubens.address.view.Windows.PrimaryStageExceptions.PrimaryStageInstanceException;
import ch.rubens.address.view.Windows.PrimaryStageSingleton;
import ch.rubens.address.view.Windows.PrimaryStageExceptions.PrimaryStageSingletonInstanceException;
import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author Rubens
 */
public class XMLChooser extends Chooser{

    @Override
    public File open() {
        
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = null;
        
        try {
            
            file = fileChooser.showOpenDialog(PrimaryStageSingleton.getInstance());
            
        } 
        catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
            handleFileOpenError(ex);
            
        }
            
        return file;
        
    }

    @Override
    public File open(String path) {
        
        File file = null;
        
        if (path.endsWith(".xml")) {
            
            file = new File(path);
            
        }
        
        return file;
        
    }
    
    private void handleFileOpenError(Exception ex) {
        
        System.out.println("Error when opening file: " + ex);
        
    }
    
}
