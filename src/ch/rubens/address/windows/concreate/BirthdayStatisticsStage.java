package ch.rubens.address.windows.concreate;

import ch.rubens.address.windows.abstracts.IWindow;
import ch.rubens.address.MainApp;
import ch.rubens.address.model.concreate.PersonListSingleton;
import ch.rubens.address.model.concreate.PersonPropertyAdapter;
import ch.rubens.address.view.BirthdayStatisticsController;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * Esta é uma classe que busca retirar a resposabilidade que a main possuia de 
 * criar e configurar a janela de BirthdayStatistics
 * 
 * @author rubens
 */
public class BirthdayStatisticsStage extends IWindow {

    public BirthdayStatisticsStage() {
        
        setName("Birthday Statistics");
        
        setLoader(new FXMLLoader());
        getLoader().setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        
        initLayout();
        
        initController();
            
        setOpened(false);
        
    }
    
    @Override
    protected void initLayout() {
        
        AnchorPane page = null;
        try {
            
            page = (AnchorPane) getLoader().load();
            
        } catch (IOException ex) {
            
            System.out.println("Erro ao carrgar o layout da tela BirthdayStatisticsStage. Erro: " + ex);
            
        }
          
        setLayout(new Stage());
        getLayout().setTitle("Estatísticas");
        getLayout().initModality(Modality.WINDOW_MODAL);
        try {
            
            getLayout().initOwner(PrimaryStageSingleton.getInstance());
            
        } catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
        System.out.println("Erro ao abrir a . Erro: " + ex);
           
        }
            
        Scene scene = new Scene(page);
        getLayout().setScene(scene);
        
    }
    
    @Override
    protected void initController() {
        
        setController(getLoader().getController());
        
        BirthdayStatisticsController controller = (BirthdayStatisticsController) getController();
        
        PersonListSingleton personList = PersonListSingleton.getInstance();
        controller.setPersonData(personList.getPersonsList());
        
    }
    
}
