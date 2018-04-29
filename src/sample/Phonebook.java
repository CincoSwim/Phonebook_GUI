package sample;

import java.io.*;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;


public class Phonebook extends Application {
    Stage window;
    TableView<Entry> table;
    static int index;
    static Entry EntryList[] = new Entry[200];

    public static void main(String[] args) throws FileNotFoundException {

        ReadsPhoneBook();
        getsEntries(EntryList, index);
        System.out.println(index);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final ImageView logo = new ImageView();
        Image logopng = new Image("https://i.imgur.com/K3IXCuP.png", 150, 150, false, true);
        logo.setImage(logopng);

        window = primaryStage;
        window.setTitle("WinPhonebook '95");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });


        TextField name = new TextField();
        TextField notes = new TextField();
        TextField number = new TextField();
        name.setPromptText("Name");
        number.setPromptText("Number");
        notes.setPromptText("Notes");


        Button addButton = new Button("Add");
        addButton.setMinWidth(70);
        addButton.setOnAction(e -> {
            EntryList[index] = new Entry();
            EntryList[index].setName(name.getText());
            EntryList[index].setNumber(
                    "(" + number.getText().substring(0, 3) +
                            ")-" + number.getText().substring(3, 6) + "-" +
                            number.getText().substring(6, 10)
            );
            EntryList[index].setNotes(notes.getText());
            table.getItems().add(EntryList[index]);
            System.out.println(EntryList[index].name);
            index++;

        });

        Button findButton = new Button("Find");
        findButton.setMinWidth(70);
        findButton.setOnAction(e ->
                findButtonClicked(name.getText())

        );

        Button mergeButton = new Button("Merge");
        mergeButton.setMinWidth(150);

        Button delButton = new Button("Delete");
        delButton.setMinWidth(150);
        delButton.setOnAction(e -> delButtonClicked());

        HBox addfind = new HBox(10);
        addfind.getChildren().addAll(addButton, findButton);


        VBox controls = new VBox(10);
        controls.setPadding(new Insets(10, 10, 10, 10));
        controls.getChildren().addAll(addfind, name, number, notes, mergeButton, delButton, logo);

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
        notesColumn.setMinWidth(200);
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));


        table = new TableView<>();

        table.setItems(getsEntries(EntryList, index));
        table.getColumns().addAll(nameColumn, numColumn, notesColumn);


        HBox BookView = new HBox();
        BookView.getChildren().addAll(controls, table);


        Scene scene = new Scene(BookView);


        window.setScene(scene);
        window.show();


    }

    public static int ReadsPhoneBook() throws FileNotFoundException {
        //Static Method that reads entries from included phonebook.txt.
        //As each entry is read, a new Entry object is created.

        File phonebook = new File("phonebook.txt");
        Scanner read = new Scanner(phonebook);
        String name, notes, number;

        index = 0;

        try {
            for (int i = 0; i <=200; i++){
                name = read.nextLine();
                number = read.nextLine();
                notes = read.nextLine();
                if (!name.equals("")) {
                    EntryList[index] = new Entry(name, number, notes);
                    index++;
                }
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

    public void delButtonClicked() {
        ObservableList<Entry> entriesSelected, allEntries;
        String selectedName, selectedNumber;
        int indexSelected;
        allEntries = table.getItems();
        entriesSelected = table.getSelectionModel().getSelectedItems();
        selectedName = table.getSelectionModel().getSelectedItem().name;

        indexSelected = findIndex(selectedName);
        System.out.println(indexSelected);
        System.out.println(selectedName);

        EntryList[indexSelected].name = "";
        EntryList[indexSelected].number = "";
        EntryList[indexSelected].notes = "";
        entriesSelected.forEach(allEntries::remove);
    }
    public static void findButtonClicked(String query){
        String name, number, notes;
        int index;
        index = findIndex(query);
        name = EntryList[index].name;
        number = EntryList[index].number;
        notes = EntryList[index].notes;
        AlertBox.display(name, number, notes);
    }



    //Creates and Returns an observable list made of Entry objects.
    public static ObservableList<Entry> getsEntries(Entry[] entryArray, int index) {

        ObservableList<Entry> entries = FXCollections.observableArrayList();

        for (int i = 0; i < index; i++)//make sure index is static through the class. Really bad if it goes out of bounds.
            entries.add(entryArray[i]);

        return entries;
    }

    public static int findIndex(String query) {
        String nameTest;
        int closestIndex = 200;
        for (int i = 0; i <= index - 1; i++) {
            nameTest = EntryList[i].name;
            if (query.equals(nameTest)) {
                closestIndex = i;
            }
        }
        return closestIndex;
    }

    private void closeProgram() {
        Boolean answer = Confirmbox.display("title", "Sure you want to exit?");
        if (answer) {
            try {
                WritesPhoneBook();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                        query = name.getText();
                        //qSuccess = findsEntry(query);
                        //if (!qSuccess)
                        //    System.out.println("** No entry found for " + query);
                        break;



}





*/
