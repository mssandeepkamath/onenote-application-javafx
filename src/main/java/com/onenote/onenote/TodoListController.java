package com.onenote.onenote;

import com.onenote.onenote.Util.EventSerializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TodoListController implements Initializable {

    @FXML private Button addEventButton;
    @FXML private DatePicker datePicker;
    @FXML private TextField eventTextField;
    @FXML
    private ListView<LocalEvent> eventListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            var events = EventSerializer.deserialize();
            eventListView.getItems().addAll(events);
        }catch(Exception e)  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TodoFX");
            alert.setHeaderText("Events could not be loaded from file system");
        }

    }

    public Object[] getEvents()  {
        return eventListView.getItems().toArray();
    }

@FXML
    public void addEventAction(ActionEvent actionEvent) {

    var newEvent = new LocalEvent(datePicker.getValue(), eventTextField.getText());

    eventListView.getItems().add(newEvent);

    datePicker.setValue(LocalDate.now());

    eventTextField.setText("");
    }

}
