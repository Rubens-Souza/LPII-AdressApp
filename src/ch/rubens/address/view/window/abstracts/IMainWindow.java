/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rubens.address.view.window.abstracts;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author rubens
 */
public interface IMainWindow {
    
    public void openMainWindow();
    
    public Stage getprimaryStage();
    public BorderPane getRootLayout();
    
}
