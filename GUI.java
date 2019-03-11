import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class GUI extends Application {
    //Written by Yaofeng Lu

    Stage  window;
    static TableView<PhoneBook> table;
    File file = new File("PhoneBook.txt");
    PhoneBook phone = new PhoneBook();
    static ArrayList<PhoneBook> entryList = new ArrayList();
    static TextField firstNameInput, lastNameInput, numberInput, noteInput,searchFirstName,searchLastName;
    static TextField firstNameMerge, lastNameMerge, reNameInput;
    static ObservableList<PhoneBook> phones = FXCollections.observableArrayList(entryList);
    Button addButton, deleteButton,searchButton,listButton,helpButton,mergeButton,renameButton,exitButton;
    HBox   hBox1,hBox2,hBox3,hBox4,hBox5,hBox6;
    VBox   vBox;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Phone Book");

        //First name column
        TableColumn<PhoneBook, String> firstColumn = new TableColumn<>("First Name");
        firstColumn.setMinWidth(200);
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //Last name column
        TableColumn<PhoneBook, String> lastColumn = new TableColumn<>("Last Name");
        lastColumn.setMinWidth(200);
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //Phone number column
        TableColumn<PhoneBook, String> numberColumn = new TableColumn<>("Phone Number");
        numberColumn.setMinWidth(200);
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        //Note column
        TableColumn<PhoneBook, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setMinWidth(200);
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        table = new TableView<>();
        table.setItems(read());
        table.getColumns().addAll(firstColumn, lastColumn, numberColumn, noteColumn);

        //Name input
        firstNameInput = new TextField();
        firstNameInput.setPromptText("First Name");
        firstNameInput.setMinWidth(100);

        lastNameInput = new TextField();
        lastNameInput.setPromptText("Last Name");

        //Number input
        numberInput = new TextField();
        numberInput.setPromptText("Phone Number");

        //Notes input
        noteInput = new TextField();
        noteInput.setPromptText("Note");

        //Search input
        searchFirstName = new TextField();
        searchFirstName.setPromptText("First Name");
        searchLastName  = new TextField();
        searchLastName.setPromptText("Last Name");

        //Merge input
        firstNameMerge = new TextField();
        firstNameMerge.setPromptText("First Name");
        lastNameMerge  = new TextField();
        lastNameMerge.setPromptText("Last Name");

        //Rename input
        reNameInput = new TextField();
        reNameInput.setPromptText("Text file Name");

        //Button
        helpButton   = new Button("Help");
        helpButton.setOnAction(event -> Bt.help());
        renameButton = new Button("Rename");
        renameButton.setOnAction(event -> renameButtonClicked());
        exitButton   = new Button("Exit");
        exitButton.setOnAction(event -> System.exit(0));
        addButton    = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            try {
                searchButtonClicked(searchFirstName.getText(),searchLastName.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mergeButton = new Button("Merge");
        mergeButton.setOnAction(event -> {
            try {
                mergeButtonClicked(firstNameMerge.getText(),lastNameMerge.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        listButton = new Button("List");
        listButton.setOnAction(event -> {
            try {
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        hBox1 = new HBox();
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(helpButton);

        hBox2 = new HBox();
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(firstNameInput, lastNameInput, numberInput, noteInput, addButton, deleteButton);

        hBox3 = new HBox();
        hBox3.setPadding(new Insets(10, 10, 10, 10));
        hBox3.setSpacing(10);
        hBox3.getChildren().addAll(searchFirstName,searchLastName,searchButton,listButton);

        hBox4 = new HBox();
        hBox4.setPadding(new Insets(10, 10, 10, 10));
        hBox4.setSpacing(10);
        hBox4.getChildren().addAll(firstNameMerge,lastNameMerge,mergeButton);

        hBox5 = new HBox();
        hBox5.setPadding(new Insets(10, 10, 10, 10));
        hBox5.setSpacing(10);
        hBox5.getChildren().addAll(reNameInput,renameButton);

        hBox6 = new HBox();
        hBox6.setPadding(new Insets(10, 10, 10, 10));
        hBox6.setSpacing(10);
        hBox6.getChildren().addAll(exitButton);


        vBox = new VBox();
        vBox.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5,hBox6);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(table);
        borderPane.setBottom(vBox);

        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
    }

    //Rename button clicked
    public void renameButtonClicked(){
        String rename    = reNameInput.getText();
        File file        = new File("C:\\Users\\Administrator\\IdeaProjects\\project81\\Book.txt");
        File renamedFile = new File("C:\\Users\\Administrator\\IdeaProjects\\project81\\"+rename+".txt");

        if (file.renameTo(renamedFile)){
            System.out.println("Rename successful");
        }else {
            System.out.println("Rename not successful");
        }
    }

    //Add button clicked
    public void addButtonClicked() {

        String first, last, firstName, lastName;
        firstName = firstNameInput.getText();
        lastName  = lastNameInput.getText();
        //check if the user tries to add another entry with the same name.
        for (int i=0; i < entryList.size(); i++){
            first = entryList.get(i).getFirstName();
            last  = entryList.get(i).getLastName();
            if (first.equals(firstName) && last.equals(lastName)) {
                AlertBox.display("Alert: Your are try to add another entry with the same name!");
            }
        }

        //Checks to see if the code is of the specified length
        if (firstName.length() > 8 || lastName.length() > 8) {
            AlertBox.display("Error: Keep the limit of 8 characters for first name and last name!");
        }else if (numberInput.getText().length()>10){
            AlertBox.display("Error: Invalid phone number! ");
        }
        else {
            try {
                addData();
                refreshTable();
            }catch(Exception err){ }

            table.getItems().add(phone);
            firstNameInput.clear();
            lastNameInput.clear();
            numberInput.clear();
            noteInput.clear();
        }
    }

    //Delete button clicked
    public void deleteButtonClicked() {
        ObservableList<PhoneBook> itemsSelected, all;
        int items;
        all           = table.getItems();
        itemsSelected = table.getSelectionModel().getSelectedItems();
        items         = table.getSelectionModel().getSelectedIndex();
        System.out.println(items);
        entryList.remove(items);
        try {
            storePhoneBok();
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemsSelected.forEach(all::remove);
        System.out.println(entryList.size());
    }

    //Merge button clicked
    public void mergeButtonClicked(String firstName, String lastName) throws Exception {

        String first, last;
        int n =0;
        int max[] = new int[2];

             for (int i=0; i < entryList.size(); i++){
                 first = entryList.get(i).getFirstName();
                 last  = entryList.get(i).getLastName();
                 if (first.equals(firstName) && last.equals(lastName)) {
                     max[n]=i;
                     n++;
                 }
             }
        System.out.println(max[0]+"   "+max[1]);
         entryList.get(max[0]).setNote((entryList.get(max[1]).getNumber()));
         entryList.remove(max[1]);
         storePhoneBok();
         refreshTable();
         firstNameMerge.clear();
         lastNameMerge.clear();
    }

    public  void searchButtonClicked(String message1,String message2) throws Exception {
        Scanner reader = new Scanner(file);
        entryList.clear();

        while (reader.hasNext()) {
            String[] parts = reader.nextLine().split("\t");
            if (parts.length == 4){
                PhoneBook newPhone = new PhoneBook(parts[0],parts[1],parts[2],parts[3]);
                if (parts[0].equals(message1 )&& parts[1].equals(message2)){
                entryList.add(newPhone);
            }else   table.getItems().clear();
            }
            table.getItems().clear();
            phones = FXCollections.observableArrayList(entryList);
            table.setItems(phones);
            searchFirstName.clear();
            searchLastName.clear();
        }
    }


    public ObservableList<PhoneBook> read() throws Exception {

        FileReader fileReader = new FileReader(file);
        Scanner reader = new Scanner(file);

        while (reader.hasNext()) {
            String[] parts = reader.nextLine().split("\t");
            if (parts.length == 4){
                PhoneBook newPhone = new PhoneBook(parts[0],parts[1],parts[2],parts[3]);
                entryList.add(newPhone);
            }
        }
        fileReader.close();
        reader.close();

        phones = FXCollections.observableArrayList(entryList);
        return phones;
    }


    public void  addData() throws Exception {

        if (noteInput.getText().equals("")){
            entryList.add(new PhoneBook(firstNameInput.getText(),lastNameInput.getText(),
                    numberInput.getText()," "));

        }else{
            entryList.add(new PhoneBook(firstNameInput.getText(),lastNameInput.getText(),
                numberInput.getText(),noteInput.getText()));
        }
            storePhoneBok();
    }

    public void refreshTable() throws Exception {
        table.getItems().clear();
        entryList.clear();
        table.setItems(read());
    }

    public  void storePhoneBok() throws Exception{
        FileOutputStream out = new FileOutputStream("PhoneBook.txt");
        PrintStream printStream = new PrintStream(out);

        for (int i=0; i < entryList.size(); i++) {
            printStream.println(entryList.get(i).getFirstName()+"\t"+ entryList.get(i).getLastName()+"\t"+
                                entryList.get(i).getNumber()+"\t"+entryList.get(i).getNote());

            System.out.println("PhoneBook store"+entryList.size());
         }
         printStream.close();
    }
}


