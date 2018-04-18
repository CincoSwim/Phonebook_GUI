package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;


public class Phonebook extends Application {
    Button button;
    Stage window;
    Scene scene1, scene2;


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("thenewboston");

        HBox topmenu = new HBox();
        Button buttonA = new Button("file");
        Button buttonB = new Button("edit");
        Button buttonC = new Button("view");

        topmenu.getChildren().addAll(buttonA, buttonB, buttonC);

        VBox leftmenu = new VBox();
        Button buttonD = new Button("Dee");
        Button buttonE = new Button("Ee");
        Button buttonF = new Button("Eph");

        leftmenu.getChildren().addAll(buttonD, buttonE, buttonF);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topmenu);
        borderPane.setLeft(leftmenu);


        Scene scene = new Scene(borderPane, 300, 200);
        window.setScene(scene);
        window.show();


    }
    private void closeProgram(){
        Boolean answer = Confirmbox.display("title", "Sure you want to exit?");
        if (answer){
            System.out.println("File is saved");
            window.close();}
    }

    public static void main(String[] args) throws FileNotFoundException {


        launch(args);


        //Reimplement these with buttons somehow.
        //need to display entries as well.
                /*
                    case 'e':
                        nameEntry = commandEntry.substring(2);
                        System.out.print("Enter Number: ");
                        numberTemp = input.nextLine();
                        numberEntry = Long.parseLong(numberTemp.replaceAll("[^0-9]", ""));
                        if (numberEntry < 1000000000)
                            numberEntry += 4190000000L;
                        System.out.print("Enter Notes: ");
                        notesEntry = input.nextLine();
                        System.out.println();

                        entryList[index] = new Entry(nameEntry, numberEntry, notesEntry);
                        index++;
                        break;

                    case 'f':
                        query = commandEntry.substring(2);
                        //qSuccess = findsEntry(query);
                        //if (!qSuccess)
                        //    System.out.println("** No entry found for " + query);
                        break;
                    case 'l':
                        System.out.println();
                        //listsEntries();
                        break;

                    case 'q':
                        quitCondition = 'q';
                        System.out.println("Saving and Shutting Down......");
                        //WritesPhoneBook();
                        System.exit(0);
*/
    }

}






