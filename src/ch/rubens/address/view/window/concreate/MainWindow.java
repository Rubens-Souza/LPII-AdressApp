package ch.rubens.address.view.window.concreate;

import ch.rubens.address.MainApp;
import ch.rubens.address.view.window.abstracts.IMainWindow;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author rubens
 */
public class MainWindow implements IMainWindow {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    public MainWindow(Stage primaryStage) throws IOException {
        
        setPrimaryStage(primaryStage);
        this.primaryStage.setTitle("App de Endereços");
        this.primaryStage.getIcons().add(new Image("file:resoucers/imagens/icone_app.png"));
        
        rootLayout = loadRootLayout();
        
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        
    }
    
    private BorderPane loadRootLayout() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
        return (BorderPane) loader.load();
    }
    
    @Override
    public void openMainWindow() {
        primaryStage.show();
    }
    
    @Override
    public Stage getprimaryStage() {
        return primaryStage;
    }

    @Override
    public BorderPane getRootLayout() {
        return rootLayout;
    }
    
    private void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
}
