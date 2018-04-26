package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;


public class Phonebook extends Application {
    Stage window;
    TableView<Entry> table;
    static int index;
    static Entry EntryList[] = new Entry[200];
    static File phonebook;
    public static void main(String[] args) throws FileNotFoundException {

        //These lines just set some dummy entries.
        //This'll get replaced with a method to read/write from a text document, just like before.
        EntryList[0] = new Entry ("Chris Pucko", "513-683-7895", "Cool dudee");
        EntryList[1] = new Entry ("Jonathan DOKOKOKOKOKO", "phonenumberhereplacehlder", "Meta, bro.");
        index = 3;
        //End test entries
        ReadsPhoneBook();
        getsEntries(EntryList, index);
        System.out.println(index);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("thenewboston");

        //Name Column
        TableColumn<Entry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Number Column
        TableColumn<Entry, String> numColumn = new TableColumn<>("Number");
        numColumn.setMinWidth(200);
        numColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        //Notes Column
        TableColumn<Entry, String> notesColumn = new TableColumn<>("Notes");
        notesColumn.setMinWidth(300);
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        table = new TableView<>();
        table.setItems(getsEntries(EntryList, index));
        table.getColumns().addAll(nameColumn, numColumn, notesColumn);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);


        window.setScene(scene);
        window.show();


    }
    public static int ReadsPhoneBook() throws FileNotFoundException {
        //Static Method that reads entries from included phonebook.txt.
        //As each entry is read, a new Entry object is created.

        try {
            phonebook = new File("phonebook.txt");
            if (!phonebook.exists())
                phonebook.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
        }
        Scanner read = new Scanner(phonebook);
        String name, notes, number;


        index = 0;

        try {


            for (int i = 0; i < 200; i++) {
                name = read.nextLine();
                number = read.nextLine();
                notes = read.nextLine();
                EntryList[i] = new Entry(name, number, notes);
                index++;

            }
        } catch (Exception NoSuchElementException) {
            System.out.println("Now Loaded!");
            return index;
        }
        return index;
    }
    public static void WritesPhoneBook() throws FileNotFoundException {
        //Method that writes each objects name, number and notes for easy storage.
        //Formatted so that it can be read back by ReadsPhoneBook().
        PrintStream P = new PrintStream("phonebook.txt");
        for (int i = 0; i < index; i++) {
            P.println(EntryList[i].name);
            P.println(EntryList[i].number);
            P.println(EntryList[i].notes);
        }
        P.close();
        System.out.println("Phonebook Saved");
    }

    //Creates and Returns an observable list made of Entry objects.
    public static ObservableList<Entry> getsEntries(Entry[] entryArray, int index){

        ObservableList<Entry> entries = FXCollections.observableArrayList();

        for (int i=0; i < index; i++)//make sure index is static through the class. Really bad if it goes out of bounds.
            entries.add(entryArray[i]);

        return entries;
    }

    private void closeProgram() {
        Boolean answer = Confirmbox.display("title", "Sure you want to exit?");
        if (answer) {
            System.out.println("File is saved");
            window.close();
        }
    }
}


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



}





*/
