package ch.rubens.address.view.abstracts;

/**
 *
 * @author rubens
 */
public abstract class IWindow {
    
    private static String idName;
    
    public abstract void open();
    
    public static String getIdName() {
        
        return idName;
        
    }
    
    protected static void setIdName(String windowName) {
        
        idName = windowName;
        
    }
    
}
