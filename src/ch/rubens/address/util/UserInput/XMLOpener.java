package ch.rubens.address.util.concreate;

import ch.rubens.address.util.abstracts.Opener;
import ch.rubens.address.windows.concreate.PrimaryStageInstanceException;
import ch.rubens.address.windows.concreate.PrimaryStageSingleton;
import ch.rubens.address.windows.concreate.PrimaryStageSingletonInstanceException;
import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author Rubens
 */
public class XMLOpener implements Opener{

    @Override
    public File openFile() {
        
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
    public File openFile(String path) {
        
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
