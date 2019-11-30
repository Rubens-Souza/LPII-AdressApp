package ch.rubens.address.util.Alerts;

import javafx.scene.control.Alert;

/**
 * Impelementa a interface IFastAlert. (DIP)
 * 
 * @author rubens
 */
public class FastAlertWarning implements IFastAlert {
 
    private Alert alert;
    
    public FastAlertWarning(String title, String header, String content) {
        
            setAlert(new Alert(Alert.AlertType.WARNING));
            setTitle(title);
            setHeader(header);
            setContentText(content);
        
    }
    
    @Override
    public void openAlert() {
        alert.showAndWait();
    }

    @Override
    public Alert getAlert() {
        return alert;
    }

    @Override
    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    @Override
    public void setHeader(String header) {
        alert.setHeaderText(header);
    }

    @Override
    public void setTitle(String title) {
        alert.setTitle(title);
    }

    @Override
    public void setContentText(String text) {
        alert.setContentText(text);
    }
    
}
