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

        button.setOnAction(e -> {
            System.out.println("Hey now brown cow");
            System.out.println("Does it work with multiple lines?");
        });




        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();




    }







        static int index;
        static Entry[] entryList;

        public static void main(String[] args) throws FileNotFoundException {

            String nameEntry, notesEntry, commandEntry, numberTemp, query;
            char quitCondition;
            long numberEntry;
            boolean qSuccess;

            launch(args);
            entryList = new Entry[200];

            Scanner input = new Scanner(System.in);

            System.out.println("Loading...");

            System.out.println();



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






