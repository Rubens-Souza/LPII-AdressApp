package ch.rubens.address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.rubens.address.model.abstracts.IPerson;

/**
 *
 * @author rubens
 */
public class BirthdayStatisticsController {
    
    @FXML private BarChart<String, Integer> barChart;
    @FXML private CategoryAxis xAxis;
    
    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    
    @FXML private void initialize() {
        
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        
        monthNames.addAll(Arrays.asList(months));
        
        xAxis.setCategories(monthNames);
        
    }
    
    public void setPersonData(List<IPerson> persons) {
        
        int[] monthCounter = new int[12];
        int i = 0;
        
        for (IPerson p: persons) {
            
            int month = p.getBirthday().getMonthValue();
            month--;
            monthCounter[month]++;
            
        }
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        /**
         * Alterei o tipo de for para ser um for iterativo e seguir o padrão de
         * projeto Iterator
         */
        for (Integer monthNumber : monthCounter) {
            
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthNumber));
            i++;
            
        }
        
        barChart.getData().add(series);
    }
    
}
