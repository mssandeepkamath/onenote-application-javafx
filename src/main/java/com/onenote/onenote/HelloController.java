package com.onenote.onenote;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Objects;

public class HelloController {


    public Button ProceedButtonView;
    public TextField emailTextField;
    public TextField passwordTextField;

    //dfikhsdkfhs

    public void proceedOnClick(ActionEvent actionEvent) throws Exception{

        Window owner=ProceedButtonView.getScene().getWindow();

        if(emailTextField.getText().equals("") && passwordTextField.getText().equals(""))
        {

            Stage window = (Stage) ProceedButtonView.getScene().getWindow();
            Parent pane = FXMLLoader.load(
                    getClass().getResource("chooser-layout.fxml"));
            window.setResizable(false);
            window.getScene().setRoot(pane);
        }
        else
        {
            AlertHelper.showAlert(Alert.AlertType.ERROR,owner,"Attention","Your credentials are wrong");
        }

    }

    private static class AlertHelper {
        public static void showAlert(Alert.AlertType error, Window owner, String attention, String your_credentials_are_wrong) {
        }
    }
}