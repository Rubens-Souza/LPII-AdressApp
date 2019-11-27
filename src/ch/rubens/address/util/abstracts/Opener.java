package ch.rubens.address.util.abstracts;

import java.io.File;

/**
 *
 * @author Rubens
 */
public interface Opener {
    
    public File openFile();
    
    public File openFile(String path);
    
}
