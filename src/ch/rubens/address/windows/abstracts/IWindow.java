package ch.rubens.address.windows.abstracts;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ch.rubens.address.view.abstracts.IController;

/**
 *
 * Implementar o FactoryMethod para dar as implementações a responsabilidade
 * de saber qual controller instanciar.
 * 
 * Servir de interface para uso de outras classes
 * 
 * @author rubens
 */
public abstract class IWindow {
    
    private String name;
    
    private Stage layout;
    private FXMLLoader loader;
    private IController controller;
    
    private boolean opened;
    private boolean hasClosed;
    
    protected abstract void initLayout();
    
    protected abstract void initController(); // factoryMethod
    
    public void open() {
        
        setHasClosed(false);
        setOpened(true);
        layout.showAndWait();
        
    }
    
    public void close() {
        
        setHasClosed(true);
        setOpened(false);
        layout.close();
        
    }
    
    public boolean isOpen() {
    
        return opened;
        
    }
    
    public boolean hasClosed() {
        
        return hasClosed;
        
    }
    
    public Stage getLayout() {
        
        return layout;
        
    }
    
    public IController getController() {
        
        return controller;
        
    }
    
    public FXMLLoader getLoader() {
        
        return loader;
        
    }
    
    public String getName() {
    
        return name;
        
    }
    
    protected void setLoader(FXMLLoader loader) {
        
        this.loader = loader;
        
    }
    
    protected void setLayout(Stage layout) {
        
        this.layout = layout;
        
    }
    
    protected void setController(IController controller) {
        
        this.controller = controller;
        
    }
    
    protected void setOpened(boolean isOpen) {
        
        this.opened = isOpen;
        
    }
    
    protected void setHasClosed(boolean hasClosed) {
        
        this.hasClosed = hasClosed;
        
    }
    
    protected void setName(String name) {
        
        this.name = name;
        
    }
    
}
