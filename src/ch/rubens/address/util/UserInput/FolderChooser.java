package ch.rubens.address.util.UserInput;

import ch.rubens.address.view.Windows.PrimaryStageExceptions.PrimaryStageInstanceException;
import ch.rubens.address.view.Windows.PrimaryStageSingleton;
import ch.rubens.address.view.Windows.PrimaryStageExceptions.PrimaryStageSingletonInstanceException;
import java.io.File;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author rubens
 */
public class FolderChooser extends Chooser{

    @Override
    public File open() {
        
        return openChooser("c:");
        
    }

    @Override
    public File open(String path) {
        
        return openChooser(path);
        
    }
    
    private File openChooser(String initialPath) {
        
        DirectoryChooser chooser = new DirectoryChooser();
        
        chooser.setTitle(getPanelName());
        chooser.setInitialDirectory(new File(initialPath));
        
        File selectedFolder = null;
        
        try {
            
            selectedFolder = chooser.showDialog(PrimaryStageSingleton.getInstance());
            
        } 
        catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
            handleOpenDialogError(ex);
            
        }
        
        if (selectedFolder != null) {
            
            return selectedFolder;
            
        }
        else {
            
            return null;
            
        }
        
    }

    private void handleOpenDialogError(Exception ex) {
        
        System.out.println("Error while opening the Folder Selection dialog. " + ex);
        
    }
    
}
