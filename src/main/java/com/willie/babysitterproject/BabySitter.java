/**
 * Screen for BabySitter app.
 */
package com.willie.babysitterproject;

import com.willie.babysitter.constants.Constants;
import com.willie.babysitter.delegate.PayDelegate;
import com.willie.babysitter.delegate.PayRequest;
import com.willie.babysitter.delegate.PayResponse;
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
        ObservableList<String> startTimeOptions = FXCollections.observableArrayList(Times.getHoursList(Constants.MINIMUM_START_HOUR, Constants.MAXIMUM_START_HOUR));
        final ChoiceBox startTimeChoice = new ChoiceBox(startTimeOptions);
        ObservableList<String> bedTimeOptions = FXCollections.observableArrayList(Times.getHoursList(Constants.MINIMUM_BEDTIME_HOUR, Constants.MAXIMUM_BEDTIME_HOUR));
        final ChoiceBox bedTimeChoice = new ChoiceBox(bedTimeOptions);
        ObservableList<String> endTimeOptions = FXCollections.observableArrayList(Times.getHoursList(Constants.MINIMUM_END_HOUR, Constants.MAXIMUM_END_HOUR));
        final ChoiceBox endTimeChoice = new ChoiceBox(endTimeOptions);

        // StartTime
        Text startLabel = new Text("Start time:");
        root.add(startLabel, 0, 0);
        root.add(startTimeChoice, 1, 0);
        
        //TODO An enhancement that may be nice to have is to set the select options for BedTime when StartTime is chosen (and for EndTime when BedTime is chosen).
        // This would prevent the user from selecting invalid times, like bedtime before start time.
        // To do this we'd not only need to dynamically modify the select options, but potentially un-select times that may be invalid to force the user to re-select.
        // This is outside the scope at this time.
        
        // BedTime
        Text bedtimeLabel = new Text("Bed time:");
        root.add(bedtimeLabel, 0, 1);
        root.add(bedTimeChoice, 1, 1);

        // EndTime
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

                // Calculate Pay
                PayDelegate payDelegate = new PayDelegate();
                PayRequest request = new PayRequest(start, bed, end);
                PayResponse response = payDelegate.calculatePay(request);
                if (response.getPayStatus() != PayResponse.OK) {
                    GuiUtils.errorAlert(response.getPayMessage());
                } else {
                    GuiUtils.informationAlert("Amount Earned= $" + response.getPay());
                }
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