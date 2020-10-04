/*
 * Utilities for the JavaFX GUI
*/
package com.willie.babysitterproject;

import javafx.scene.control.Alert;

/**
 *
 * @author Andy
 */
public class GuiUtils {
    
    public static void informationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        showAlert(alert, message);
    }
    
    public static void confirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        showAlert(alert, message);
    }
    
    public static void warningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        showAlert(alert, message);
    }
    
    public static void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        showAlert(alert, message);
    }
    
    private static void showAlert(Alert alert, String message) {
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
