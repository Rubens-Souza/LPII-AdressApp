package ch.rubens.address.windows;

/**
 *
 * @author rubens
 */
public class PrimaryStageSingletonInstanceException extends Exception {

    public PrimaryStageSingletonInstanceException() {
        
        System.out.println("O primary stage foi definido, mas não atribuido a um singleton");
        
    }
    
}
