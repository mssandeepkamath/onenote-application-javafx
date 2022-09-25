package com.onenote.onenote;

import com.onenote.onenote.Data.OtherData;
import com.onenote.onenote.Data.TodoData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CancellationException;
public class WhiteboardController implements Initializable {
    @FXML
    public ImageView yelloBrush;
    @FXML
    public ImageView blueBrush;
    @FXML
    public ImageView redBrush;
    @FXML
    public ImageView blackBrush;
    @FXML
    public ImageView clear;
    @FXML
    private ImageView Exit;

    @FXML
    private TextField brushSize;

    private double lastX ;
    private double lastY ;

    @FXML
    private Canvas canva;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    boolean toolSelected = true;

    @FXML
    GraphicsContext brushTool;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        brushTool=canva.getGraphicsContext2D();
        brushSize.setText("10");
        colorPicker.setValue(Color.BLACK);

        canva.setOnMousePressed(e -> {
            lastX = e.getX();
            lastY = e.getY();
        });


        canva.setOnMouseDragged(e->{
            double size=Double.parseDouble(brushSize.getText());
       double x=e.getX() - size /2;
       double y=e.getY() - size /2;


          if (toolSelected && !(brushSize.getText().isEmpty()))
          {

              brushTool.setLineWidth(size);
              brushTool.setStroke(colorPicker.getValue());
              brushTool.strokeLine(lastX, lastY, x, y);
              lastX = x ;
              lastY = y ;
          }

        });


    }
    @FXML
    public void brushSubmit(ActionEvent actionEvent) {
        toolSelected=true;
    }
    @FXML
    public void clearCanva(MouseEvent mouseEvent) {
        brushTool.clearRect(0,0,canva.getWidth(),canva.getHeight());
    }
    @FXML
    public void yelloColor(MouseEvent mouseEvent) {
        colorPicker.setValue(Color.YELLOW);
        toolSelected=true;
    }
    @FXML
    public void blueColor(MouseEvent mouseEvent) {
        colorPicker.setValue(Color.DARKBLUE);
        toolSelected=true;
    }
    @FXML
    public void redColor(MouseEvent mouseEvent) {
        colorPicker.setValue(Color.RED);
        toolSelected=true;
    }
@FXML
    public void blackColor(MouseEvent mouseEvent) {
    colorPicker.setValue(Color.BLACK);
    toolSelected=true;
    }

    @FXML
    public void exitAction(MouseEvent mouseEvent) {

        try{
            Stage window = (Stage) brushSize.getScene().getWindow();
            Parent pane = FXMLLoader.load(
                    getClass().getResource("chooser-layout.fxml"));
            window.setMaximized(false);
            window.setResizable(false);
            window.getScene().setRoot(pane);
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }

    }

    public void smallAction(MouseEvent mouseEvent) {


            Stage stage = (Stage) brushSize.getScene().getWindow();
            stage.setIconified(true);

    }

    public void minAction(MouseEvent mouseEvent) {

            Stage stage = (Stage) brushSize.getScene().getWindow();
            if(stage.isMaximized())
                stage.setMaximized(false);
            else
                stage.setMaximized(true);


    }
}
