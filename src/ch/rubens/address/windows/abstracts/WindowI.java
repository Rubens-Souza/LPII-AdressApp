package ch.rubens.address.windows.abstracts;

import ch.rubens.address.view.abstracts.ControllerI;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 *
 * Implementar o FactoryMethod para dar as implementações a responsabilidade
 * de saber qual controller instanciar.
 * 
 * Servir de interface para uso de outras classes
 * 
 * @author rubens
 */
public abstract class WindowI {
    
    private String name;
    
    private Stage layout;
    private FXMLLoader loader;
    private ControllerI controller;
    
    private boolean opened;
    
    protected abstract void initLayout();
    
    protected abstract void initController(); // factoryMethod
    
    public void open() {
        
        layout.showAndWait();
        setOpened(true);
        
    }
    
    public void close() {
        
        layout.close();
        setOpened(false);
        
    }
    
    public boolean isOpen() {
    
        return opened;
        
    }
    
    public Stage getLayout() {
        
        return layout;
        
    }
    
    public ControllerI getController() {
        
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
    
    protected void setController(ControllerI controller) {
        
        this.controller = controller;
        
    }
    
    protected void setOpened(boolean isOpen) {
        
        this.opened = isOpen;
        
    }
    
    protected void setName(String name) {
        
        this.name = name;
        
    }
    
}
