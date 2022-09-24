package com.onenote.onenote.Todo;

import com.onenote.onenote.Data.OtherItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import com.onenote.onenote.Data.*;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Someday {
    private List<OtherItem> OtherItems;
    private Predicate<OtherItem> wantAllItems;
    private FilteredList<OtherItem> filteredList;
    private Predicate<OtherItem> wantTodaysItems;

    @FXML
    private ListView<OtherItem> todoSDesView;

    @FXML
    private ListView<OtherItem> todoActivity;

    @FXML
    private Label deadlineLabel;

    @FXML
    private ContextMenu listContexMenu;

    public void initialize() {
        listContexMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OtherItem item = (OtherItem) todoSDesView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });
        listContexMenu.getItems().addAll(deleteMenuItem);
        wantAllItems = new Predicate<OtherItem>() {
            @Override
            public boolean test(OtherItem OtherItem) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<OtherItem>() {
            @Override
            public boolean test(OtherItem OtherItem) {
                return (OtherItem.getCategory().equals("Someday"));
            }
        };

        filteredList = new FilteredList<OtherItem>(OtherData.getInstance().getOtherItems(), wantAllItems);

        SortedList<OtherItem> sortedList = new SortedList<OtherItem>(filteredList,
                new Comparator<OtherItem>() {
                    @Override
                    public int compare(OtherItem o1, OtherItem o2) {
                        return o1.getCategory().compareTo(o2.getCategory());
                    }
                });

        todoSDesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OtherItem>() {
            @Override
            public void changed(ObservableValue<? extends OtherItem> observable, OtherItem oldValue, OtherItem newValue) {
                if(newValue != null) {
                    OtherItem item = todoSDesView.getSelectionModel().getSelectedItem();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy"); // "d M yy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });
        todoSDesView.setItems(sortedList);
        todoSDesView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        todoSDesView.setCellFactory(new Callback<ListView<OtherItem>, ListCell<OtherItem>>() {
            @Override
            public ListCell<OtherItem> call(ListView<OtherItem> param) {
                ListCell<OtherItem> cell = new ListCell<OtherItem>() {

                    @Override
                    protected void updateItem(OtherItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setText(null);
                        } else {
                            OtherItem selectedItem = (OtherItem) todoSDesView.getSelectionModel().getSelectedItem();

                            filteredList.setPredicate(wantTodaysItems);
                            if(filteredList.contains(selectedItem)){
                                todoSDesView.getSelectionModel().select(selectedItem);
                            }else{
                                todoSDesView.getSelectionModel().selectFirst();
                            }
                            if(item.getCategory().equals("Someday")){
                                setText(item.getShortDescription());
                            }
                        }
                    }
                };
                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if(isNowEmpty){
                                cell.setContextMenu(null);
                            }else {
                                cell.setContextMenu(listContexMenu);
                            }
                        });

                return cell;
            }
        });

        todoActivity.setItems(sortedList);
        todoActivity.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoActivity.setCellFactory(new Callback<ListView<OtherItem>, ListCell<OtherItem>>() {
            @Override
            public ListCell<OtherItem> call(ListView<OtherItem> param) {
                ListCell<OtherItem> cell = new ListCell<OtherItem>() {

                    @Override
                    protected void updateItem(OtherItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setText(null);
                        } else {

                            if(item.getCategory().equals("Someday")){
                                setText(item.getCategory());
                            }
                        }
                    }
                };

                return cell;
            }
        });
    }

    @FXML
    public void handleClickListView() {
        OtherItem item = todoSDesView.getSelectionModel().getSelectedItem();
        if (filteredList.isEmpty()){
            deadlineLabel.setText("");
        }
        else {
            deadlineLabel.setText(item.getDeadline().toString());
        }



    }
    public void deleteItem(OtherItem item) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete todo item");
        alert.setHeaderText("Delete item: "+ item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)){
            OtherData.getInstance().deleteOtherItem(item);
        }

    }
}
