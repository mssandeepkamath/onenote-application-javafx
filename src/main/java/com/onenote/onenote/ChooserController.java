package com.onenote.onenote;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooserController {
    @FXML public Button notesListButton;
    @FXML public Button whiteBoardButton;
    @FXML public Button todoListButton;

    @FXML public void whiteBoardAction(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("white-board.fxml"));
        Stage window = (Stage) whiteBoardButton.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
    }

    @FXML public void notesListAction(ActionEvent actionEvent) throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("todo-list.fxml"));
        Stage window = (Stage) todoListButton.getScene().getWindow();
        window.setScene(new Scene(root,1280,500));
    }

    @FXML public void todoListAction(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("todo.fxml"));
        Stage window = (Stage) todoListButton.getScene().getWindow();
        window.setScene(new Scene(root,1280,500));
    }
}
