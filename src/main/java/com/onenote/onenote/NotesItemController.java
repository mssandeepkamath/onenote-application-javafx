package com.onenote.onenote;

import com.onenote.onenote.Data.NotesData;
import com.onenote.onenote.Data.TodoData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NotesItemController {


    @FXML
    public Label shortDescription;
    @FXML
    public Label Details;
    @FXML
    public VBox vbox;


    public void setData(NotesData data)
    {

        shortDescription.setText(data.getShortDescription());
        Details.setText(data.getDetails());
        if(data.getCategory().equals("Personal"))
        {
            vbox.setStyle(" -fx-background-color:  #cafaf4;-fx-background-radius: 30px;");


        }
        else if(data.getCategory().equals("Work"))
        {
           vbox.setStyle(" -fx-background-color: #FFADC9FF;-fx-background-radius: 30px;");

        }
        else
        {
            vbox.setStyle(" -fx-background-color:  #e5f7c3;-fx-background-radius: 30px;");

        }


    }

}
