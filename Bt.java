import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Bt {
    public static void help( ) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Phone Book");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("This phone book have six function: add, delete, search, list,merge, rename.\n" +
                "\n" +
                "Add function: Type the information on the text field, then click Add button, the contact will be " +
                "automatic store into the text file and display in the list screen.\n" +
                "\n" +
                "Delete function: select the row that you want to delete, click the delete button, then the selected " +
                "row will be delete automatic.\n" +
                "\n" +
                "Search function: Type the name that you want to search in the text field. Click search button. " +
                "the screen will be display the contact you search. " +
                "If you want to go back to list of the phone book, click list button.\n" +
                "\n" +
                "Merge function: If there are two entry with the same name and you want to merge that together, " +
                "type the name you want to merge in the text field. " +
                "the phone number in the second entry will be add to the note in the first entry. " +
                "The second entry will be automatic delete. \n" +
                "\n"+
                "Rename function: If your want to rename the text file, you can type the name in the text field," +
                " click the rename button, the text file will be automatic rename.");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(50, 50, 50, 50));

        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
