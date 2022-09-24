package com.onenote.onenote.Todo;

import com.onenote.onenote.Data.OtherData;
import com.onenote.onenote.Data.OtherItem;
import com.onenote.onenote.Data.TodoData;
import com.onenote.onenote.Data.TodoItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Addnew implements Initializable {
    @FXML
    private ImageView Exit;

    @FXML
    private BorderPane window;

    @FXML
    private TextField Description;

    @FXML
    private TextArea DetailsArea;

    @FXML
    private DatePicker Deadline;

    @FXML
    private ComboBox Categories;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Exit.setOnMouseClicked(e -> {
            Stage stage = (Stage) window.getScene().getWindow();
            stage.close();
        });



        Deadline.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(LocalDate.now())) {
                Categories.getItems().setAll("In Progress","Someday","Important");
            }else if(newValue.isBefore(LocalDate.now().plusDays(1))){
                Categories.getItems().setAll("Approved","Someday","Important");
            }else if(newValue.equals(LocalDate.now().plusDays(1))){
                Categories.getItems().setAll("Waiting","Someday","Important");
            }else if(newValue.equals(LocalDate.now().plusDays(2))){
                Categories.getItems().setAll("Waiting","Someday","Important");
            }else if(newValue.equals(LocalDate.now().plusDays(3))) {
                Categories.getItems().setAll("Waiting","Someday","Important");
            }else if(newValue.equals(LocalDate.now().plusDays(4))){
                Categories.getItems().setAll("Waiting","Someday","Important");
            }else if(newValue.equals(LocalDate.now().plusDays(5))){
                Categories.getItems().setAll("Waiting","Someday","Important");
            }else if(newValue.equals(LocalDate.now().plusDays(6))){
                Categories.getItems().setAll("Waiting","Someday","Important");
            }else if(newValue.equals(LocalDate.now().plusDays(7))){
                Categories.getItems().setAll("Waiting","Someday","Important");
            }else if(newValue.isAfter(LocalDate.now().plusWeeks(1))) {
                Categories.getItems().setAll("Waiting","Someday","Important");
            }else {
                Categories.getItems().setAll("");
            }
        });


    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) window.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ok(ActionEvent event) {
        Error();
        try {
            String Category1 = Categories.getValue().toString();

            if(Category1.equals("Waiting")){
                String shortDescription = Description.getText();
                String Details = DetailsArea.getText();
                String Category = Categories.getValue().toString();
                LocalDate deadValue = Deadline.getValue();


                TodoData.getInstance().addTodoItem(new TodoItem(shortDescription, Details, Category, deadValue));

            }else if(Category1.equals("In Progress")){
                String shortDescription = Description.getText();
                String Details = DetailsArea.getText();
                String Category = Categories.getValue().toString();
                LocalDate deadValue = Deadline.getValue();


                TodoData.getInstance().addTodoItem(new TodoItem(shortDescription, Details, Category, deadValue));

            }else if(Category1.equals("Important")){
                String shortDescription = Description.getText();
                String Details = DetailsArea.getText();
                String Category = Categories.getValue().toString();
                LocalDate deadValue = Deadline.getValue();

                OtherData.getInstance().addOtherItem(new OtherItem(shortDescription, Details, Category, deadValue));


            }else if(Category1.equals("Someday")){
                String shortDescription = Description.getText();
                String Details = DetailsArea.getText();
                String Category = Categories.getValue().toString();
                LocalDate deadValue = Deadline.getValue();

                OtherData.getInstance().addOtherItem(new OtherItem(shortDescription, Details, Category, deadValue));


            }

            cleartext();

        }catch (Exception exception){
                Alert dialog = new Alert(Alert.AlertType.ERROR,"There was an error your submission. Please retry",ButtonType.OK);
                dialog.show();

        }


    }


    public void cleartext(){
        Description.clear();
        DetailsArea.clear();
        Deadline.getEditor().clear();
        Categories.getItems().clear();


    }
    public void Error(){
        if(Deadline.getValue()==null || Description.getText().isEmpty() || DetailsArea.getText().isEmpty() || Categories.getValue() == null){ }
    }



}
