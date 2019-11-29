package ch.rubens.address.util.UserInput;

import java.io.File;

/**
 *
 * @author Rubens
 */
public abstract class Chooser {
    
    private String panelName;
    
    public abstract File open();
    
    public abstract File open(String path);
    
    public void setPanelName(String panelName) {
        
        this.panelName = panelName;
        
    }
    
    public String getPanelName() {
        
        return panelName;
        
    }
    
}
