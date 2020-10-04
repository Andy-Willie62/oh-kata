/**
 * Screen for BabySitter app.
 */
package com.willie.babysitterproject;

import com.willie.babysitter.times.Times;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
        
        // StartTime is between 5PM and midnight
        Text startLabel = new Text("Start time:");
        root.add(startLabel, 0, 0);
        final ObservableList<String> startTimeOptions = FXCollections.observableArrayList("5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM", "12AM");
        final ChoiceBox startTimeChoice = new ChoiceBox(startTimeOptions);
        root.add(startTimeChoice, 1, 0);
        
        startTimeChoice.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        System.out.println("StartTime=" + startTimeOptions.get(new_value.intValue()));
                    }
                });
        
        // BedTime is between StartTime and Midnight
        Text bedtimeLabel = new Text("Bed time:");
        root.add(bedtimeLabel, 0, 1);
        TextField bedtimeField = new TextField();
        root.add(bedtimeField, 1, 1);
        
        // EndTime is between StartTime and 4AM
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