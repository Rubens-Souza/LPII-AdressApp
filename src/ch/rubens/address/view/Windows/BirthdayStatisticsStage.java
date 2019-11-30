package ch.rubens.address.view.Windows;

import ch.rubens.address.MainApp;
import ch.rubens.address.model.RuntimeData.PersonsListsSingleton;
import ch.rubens.address.Control.StageControllers.BirthdayStatisticsController;
import ch.rubens.address.view.Windows.PrimaryStageExceptions.PrimaryStageInstanceException;
import ch.rubens.address.view.Windows.PrimaryStageExceptions.PrimaryStageSingletonInstanceException;
import java.io.IOException;
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
            
            System.out.println("Erro while opnening the StatisticsStage. Erro: " + ex);
            
        }
          
        setLayout(new Stage());
        getLayout().setTitle("Statistics");
        getLayout().initModality(Modality.WINDOW_MODAL);
        try {
            
            getLayout().initOwner(PrimaryStageSingleton.getInstance());
            
        } catch (PrimaryStageInstanceException | PrimaryStageSingletonInstanceException ex) {
            
            System.out.println("Erro while opnening the StatisticsStage. Erro: " + ex);
           
        }
            
        Scene scene = new Scene(page);
        getLayout().setScene(scene);
        
    }
    
    @Override
    protected void initController() {
        
        setController(getLoader().getController());
        
        BirthdayStatisticsController controller = (BirthdayStatisticsController) getController();
        
        PersonsListsSingleton personList = PersonsListsSingleton.getInstance();
        controller.setPersonData(personList.getPersonsList());
        
    }
    
}
