/**
 * Screen for BabySitter app.
 */
package com.willie.babysitterproject;

import com.willie.babysitter.times.Times;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

        // Setup the Select Options
        ObservableList<String> startTimeOptions = FXCollections.observableArrayList(Times.getHoursList(17, 0));
        final ChoiceBox startTimeChoice = new ChoiceBox(startTimeOptions);
        ObservableList<String> bedTimeOptions = FXCollections.observableArrayList(Times.getHoursList(17, 0));
        final ChoiceBox bedTimeChoice = new ChoiceBox(bedTimeOptions);
        ObservableList<String> endTimeOptions = FXCollections.observableArrayList(Times.getHoursList(18, 4));
        final ChoiceBox endTimeChoice = new ChoiceBox(endTimeOptions);

        // StartTime is between 5PM and midnight
        Text startLabel = new Text("Start time:");
        root.add(startLabel, 0, 0);
        root.add(startTimeChoice, 1, 0);
        
        // Validate startTime
//        startTimeChoice.getSelectionModel().selectedIndexProperty().addListener(
//                new ChangeListener<Number>() {
//                    public void changed(ObservableValue ov, Number value, Number new_value) {
//                        String startStr = (String) startTimeChoice.getSelectionModel().getSelectedItem();
//                        String bedStr = (String) bedTimeChoice.getSelectionModel().getSelectedItem();
//                        String endStr = (String) endTimeChoice.getSelectionModel().getSelectedItem();
//                        System.out.println("Start: start=" + startStr + ", bed=" + bedStr + ", end=" + endStr); // TODO Replace
//                    }
//                });
        
        // BedTime is between StartTime and Midnight
        Text bedtimeLabel = new Text("Bed time:");
        root.add(bedtimeLabel, 0, 1);
        root.add(bedTimeChoice, 1, 1);

        // Validate bedTime        
//        bedTimeChoice.getSelectionModel().selectedIndexProperty().addListener(
//                new ChangeListener<Number>() {
//                    public void changed(ObservableValue ov, Number value, Number new_value) {
//                        String startStr = (String) startTimeChoice.getSelectionModel().getSelectedItem();
//                        String bedStr = (String) bedTimeChoice.getSelectionModel().getSelectedItem();
//                        String endStr = (String) endTimeChoice.getSelectionModel().getSelectedItem();
//                        System.out.println("Bed: start=" + startStr + ", bed=" + bedStr + ", end=" + endStr); // TODO Replace
//                    }
//                });

        // EndTime is between StartTime+1 and 4AM
        Text endLabel = new Text("End time:");
        root.add(endLabel, 0, 2);
        root.add(endTimeChoice, 1, 2);
        
        
        // Calculate babysitter pay
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the string values
                String startTime = (String) startTimeChoice.getSelectionModel().getSelectedItem();
                String bedTime = (String) bedTimeChoice.getSelectionModel().getSelectedItem();
                String endTime = (String) endTimeChoice.getSelectionModel().getSelectedItem();
                
                // Convert to military time
                int start = Times.getHour(startTime);
                int bed = Times.getHour(bedTime);
                int end = Times.getHour(endTime);
                GuiUtils.errorAlert("Calc: start=" + start + ", bed=" + bed + ", end=" + end); // TODO Remove
            }
        });
        
        // Show the screen
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