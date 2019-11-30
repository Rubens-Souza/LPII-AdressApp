package ch.rubens.address.Persistence.Exceptions.XML;

/**
 *
 * @author rubens
 */
public class NoneFileOpenedException extends Exception {
    
    public NoneFileOpenedException() {
        
        System.out.println("Attempt to acess a file that was not opened yet\n");
        
    }
    
}
