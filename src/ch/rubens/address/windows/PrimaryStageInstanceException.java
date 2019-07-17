package ch.rubens.address.windows;

/**
 *
 * @author rubens
 */
public class PrimaryStageInstanceException extends Exception {

    public PrimaryStageInstanceException() {
        
        System.out.println("O PrimaryStageSingleto não recebeu a instância da Main");
        
    }
    
}
