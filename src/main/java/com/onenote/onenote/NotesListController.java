package com.onenote.onenote;


import com.onenote.onenote.Data.NotesData;
import com.onenote.onenote.Data.TodoData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class NotesListController implements Initializable {


    @FXML
    private TextField Description;

    @FXML
    private TextArea DetailsArea;

    @FXML
    private ComboBox Categories;

    @FXML
    private ImageView Exit;

    @FXML
    private ImageView Maximize;

    @FXML
    private ImageView Minimize;


    @FXML
    private GridPane notesGrid;

    Stage stage = null;

    @FXML
    void Addnew(ActionEvent event) {

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
                setNotesText(notesData);
                cleartext();

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                Alert dialog = new Alert(Alert.AlertType.ERROR, "There was an error your submission. Please retry", ButtonType.OK);
                dialog.show();
            }
        }


    }

    int column=0;

    int row=1;

    public void setNotesText(NotesData notesData){


        try {
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("NotesItem.fxml"));
            VBox vBox=fxmlLoader.load();
            NotesItemController notesItemController=fxmlLoader.getController();
            notesItemController.setData(notesData);

            if(column==4)
            {
                column=0;
                ++row;
            }
            notesGrid.add(vBox,column++,row);
            GridPane.setMargin(vBox,new Insets(10));

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Categories.getItems().setAll("Work", "Personal", "Study");

        Exit.setOnMouseClicked(e -> {
            try{
                Stage window = (Stage) notesGrid.getScene().getWindow();
                Parent pane = FXMLLoader.load(getClass().getResource("chooser-layout.fxml"));
                window.setMaximized(false);
                window.setResizable(false);
                window.getScene().setRoot(pane);
            }catch (IOException exception){
                System.out.println(exception.getMessage());
            }

        });
        Minimize.setOnMouseClicked(e -> {
            stage = (Stage) notesGrid.getScene().getWindow();
            stage.setIconified(true);
        });
        Maximize.setOnMouseClicked(e -> {
            stage = (Stage) notesGrid.getScene().getWindow();
            if(stage.isMaximized())
                stage.setMaximized(false);
            else
                stage.setMaximized(true);
        });



    }

    public void cleartext() {
        Description.clear();
        DetailsArea.clear();
    }


}
