/**
 * Screen for BabySitter app.
 */
package com.willie.babysitterproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BabySitter extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(6);
        
        Text startLabel = new Text("Start time:");
        root.add(startLabel, 0, 0);
        TextField startField = new TextField();
        root.add(startField, 1, 0);
        
        Text bedtimeLabel = new Text("Bed time:");
        root.add(bedtimeLabel, 0, 1);
        TextField bedtimeField = new TextField();
        root.add(bedtimeField, 1, 1);
        
        Text endLabel = new Text("End time:");
        root.add(endLabel, 0, 2);
        TextField endField = new TextField();
        root.add(endField, 1, 2);
        
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Calc clicked!"); // TODO Replace
            }
        });
        
        StackPane centerBtn = new StackPane();
        centerBtn.getChildren().add(calculateButton);
        root.add(centerBtn, 0, 3, 2, 1);

        Scene scene = new Scene(root, 480, 768);

        primaryStage.setTitle("Baby Sitter Pay");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}