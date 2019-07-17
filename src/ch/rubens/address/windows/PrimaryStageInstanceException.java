package ch.rubens.address.windows;

/**
 *
 * Esta classe representa um possível erro que pode ocorrer ao criar uma instancia
 * do PrimaryStageSingleton sem que o stageInstance da classe tenha sido defindo
 * 
 * @author rubens
 */
public class PrimaryStageInstanceException extends Exception {

    public PrimaryStageInstanceException() {
        
        System.out.println("O PrimaryStageSingleton não recebeu a instância da Main");
        
    }
    
}
