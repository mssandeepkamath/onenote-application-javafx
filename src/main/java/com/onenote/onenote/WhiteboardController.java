package com.onenote.onenote;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CancellationException;
public class WhiteboardController implements Initializable {

    @FXML
    private TextField brushSize;
    @FXML
    private Button brush;

    @FXML
    private Canvas canva;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    boolean toolSelected = false;

    @FXML
    GraphicsContext brushTool;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        brushTool=canva.getGraphicsContext2D();

      canva.setOnMouseDragged(e->
      {
          double size=Double.parseDouble(brushSize.getText());
         double x=e.getX() - size /2;
         double y=e.getY() - size /2;

          if (toolSelected && !(brushSize.getText().isEmpty()))
          {
              brushTool.setFill(colorPicker.getValue());
             brushTool.fillRoundRect(x,y,size,size,size,size);

          }
      });
    }
    public void brushSubmit(ActionEvent actionEvent) {
        toolSelected=true;
    }
}
