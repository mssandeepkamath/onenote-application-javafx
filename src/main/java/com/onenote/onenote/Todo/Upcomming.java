package com.onenote.onenote.Todo;

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

public class Upcomming  {

    private List<TodoItem> todoItems;
    private Predicate<TodoItem> wantAllItems;
    private FilteredList<TodoItem> filteredList;
    private Predicate<TodoItem> wantTodaysItems;

    @FXML
    private ListView<TodoItem> todoSDesView;

    @FXML
    private ListView<TodoItem> todoActivity;

    @FXML
    private Label deadlineLabel;

    @FXML
    private ContextMenu listContexMenu;

    public void initialize() {
        listContexMenu = new ContextMenu();
        MenuItem  deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TodoItem item = (TodoItem) todoSDesView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });
        listContexMenu.getItems().addAll(deleteMenuItem);
        wantAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return (todoItem.getCategory().equals("Waiting"));
            }
        };

        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), wantAllItems);

        SortedList<TodoItem> sortedList = new SortedList<TodoItem>(filteredList,
                new Comparator<TodoItem>() {
                    @Override
                    public int compare(TodoItem o1, TodoItem o2) {
                        return o1.getCategory().compareTo(o2.getCategory());
                    }
                });

        todoSDesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = todoSDesView.getSelectionModel().getSelectedItem();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy"); // "d M yy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });
        todoSDesView.setItems(sortedList);
        todoSDesView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        todoSDesView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> param) {
                ListCell<TodoItem> cell = new ListCell<TodoItem>() {

                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setText(null);
                        } else {
                            TodoItem selectedItem = (TodoItem) todoSDesView.getSelectionModel().getSelectedItem();

                            filteredList.setPredicate(wantTodaysItems);
                            if(filteredList.contains(selectedItem)){
                                todoSDesView.getSelectionModel().select(selectedItem);
                            }else{
                                todoSDesView.getSelectionModel().selectFirst();
                            }
                            if(item.getCategory().equals("Waiting")){
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
        todoActivity.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> param) {
                ListCell<TodoItem> cell = new ListCell<TodoItem>() {

                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setText(null);
                        } else {

                            if(item.getCategory().equals("Waiting")){
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
        TodoItem item = todoSDesView.getSelectionModel().getSelectedItem();
        if (filteredList.isEmpty()){
            deadlineLabel.setText("");
        }
        else {
            deadlineLabel.setText(item.getDeadline().toString());
        }



    }
    public void deleteItem(TodoItem item) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete todo item");
        alert.setHeaderText("Delete item: "+ item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)){
            TodoData.getInstance().deleteTodoItem(item);
        }

    }
}

