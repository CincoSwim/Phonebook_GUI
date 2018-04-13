package sample;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;


public class Phonebook extends Application {
    static Button button;

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane root = new BorderPane();

        button = new Button("yes");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("This should compact things");
            }
        });

        primaryStage.setTitle("PhoneBook 1995");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();




    }






    public static void main(String[] args) {

        launch(args);

    }
}
