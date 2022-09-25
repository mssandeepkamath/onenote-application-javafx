package com.onenote.onenote;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChooserController {
    @FXML public Button notesListButton;
    @FXML public Button whiteBoardButton;
    @FXML public Button todoListButton;
    @FXML
    public Button logout;








    @FXML public void whiteBoardAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) whiteBoardButton.getScene().getWindow();
        Parent pane = FXMLLoader.load(
                getClass().getResource("white-board.fxml"));
        window.setMaximized(true);
        window.setResizable(true);
        window.getScene().setRoot(pane);




    }

    @FXML public void notesListAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) todoListButton.getScene().getWindow();
        Parent pane = FXMLLoader.load(
                getClass().getResource("todo-list.fxml"));
        window.setMaximized(true);
        window.getScene().setRoot(pane);
    }

    @FXML public void todoListAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) todoListButton.getScene().getWindow();
        Parent pane = FXMLLoader.load(
                getClass().getResource("todo.fxml"));
        window.setMaximized(true);
        window.getScene().setRoot(pane);



    }


    public void logoutAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) todoListButton.getScene().getWindow();
        Parent pane = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        window.getScene().setRoot(pane);
    }
}
