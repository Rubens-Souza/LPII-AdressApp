package ch.rubens.address.util.UserInput;

import ch.rubens.address.windows.concreate.PrimaryStageInstanceException;
import ch.rubens.address.windows.concreate.PrimaryStageSingleton;
import ch.rubens.address.windows.concreate.PrimaryStageSingletonInstanceException;
import java.io.File;
import javafx.stage.FileChooser;
import ch.rubens.address.util.UserInput.Chooser;

/**
 *
 * @author Rubens
 */
public class XMLOpener extends Chooser{

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
