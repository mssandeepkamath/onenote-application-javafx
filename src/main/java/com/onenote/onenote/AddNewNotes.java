package com.onenote.onenote;

import com.onenote.onenote.Data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewNotes implements Initializable {

    @FXML
    private ImageView Exit;

    @FXML
    private BorderPane window;

    @FXML
    private TextField Description;

    @FXML
    private TextArea DetailsArea;

    @FXML
    private ComboBox Categories;

    public void cancel(ActionEvent actionEvent) {

        try {
            final Node source = (Node) actionEvent.getSource();

            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
//            Stage stage = (Stage) window.getScene().getWindow();
//            stage.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public NotesData ok(ActionEvent actionEvent) {

        Window owner = Description.getScene().getWindow();
        NotesData notesData = new NotesData();
        if (Description.getText().isEmpty() || DetailsArea.getText().isEmpty() || Categories.getValue() == null) {

            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Attention", "Fill all the fields!");
        } else {
            try {

                String shortDescription = Description.getText();
                String Details = DetailsArea.getText();
                String Category = Categories.getValue().toString();
                notesData.setShortDescription(shortDescription);
                notesData.setDetails(Details);
                notesData.setCategory(Category);
                cleartext();
               Stage window=(Stage) Description.getScene().getWindow();
               window.close();


            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                Alert dialog = new Alert(Alert.AlertType.ERROR, "There was an error your submission. Please retry", ButtonType.OK);
                dialog.show();
            }
        }

return notesData;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Exit.setOnMouseClicked(e -> {
            Stage stage = (Stage) Description.getScene().getWindow();
            stage.close();
        });

        Categories.getItems().setAll("Work", "Personal", "Study");


    }

    public void cleartext() {
        Description.clear();
        DetailsArea.clear();
        Categories.getItems().clear();
    }
}
