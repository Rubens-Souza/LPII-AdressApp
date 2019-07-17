package ch.rubens.address;

import ch.rubens.address.model.abstracts.IPersonListSingleton;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.windows.PrimaryStageSingleton;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * A main tem a unica responsabilidade de criar o primaryStage e entrega-lo ao
 * PrimaryStageSingleton
 * 
 * @author rubens
 */
public class MainApp extends Application {
    
    private IPersonListSingleton personsList;
    
    public MainApp() {
        
        personsList = PersonListSingleton.getInstance();

    }
    
    @Override
    public void start(Stage primaryStage) {

        PrimaryStageSingleton.getInstance(primaryStage);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
