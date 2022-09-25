package com.onenote.onenote;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import com.onenote.onenote.Data.*;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Trash {
    private List<TodoItem> todoItems;
    private Predicate<TodoItem> wantAllItems;
    private FilteredList<TodoItem> filteredList;
    private Predicate<TodoItem> wantTodaysItems;

    @FXML
    private ListView<TodoItem> TrashView;

    @FXML
    private ListView<TodoItem> activityView;

    @FXML
    private Label deadlineLabel;

    public void initialize() {
        wantAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return (todoItem.getCategory().equals("Approved"));
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

        TrashView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = TrashView.getSelectionModel().getSelectedItem();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy"); // "d M yy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });
        TrashView.setItems(sortedList);
        TrashView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TrashView.getSelectionModel().selectFirst();
        TrashView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> param) {
                ListCell<TodoItem> cell = new ListCell<TodoItem>() {

                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setText(null);
                        } else {
                            TodoItem selectedItem = (TodoItem) TrashView.getSelectionModel().getSelectedItem();

                            filteredList.setPredicate(wantTodaysItems);
                            if(filteredList.contains(selectedItem)){
                                TrashView.getSelectionModel().select(selectedItem);
                            }else{
                                TrashView.getSelectionModel().selectFirst();
                            }
                            if(item.getCategory().equals("Approved")){
                                setText(item.getShortDescription());
                            }
                        }
                    }
                };

                return cell;
            }
        });

        activityView.setItems(sortedList);
        activityView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        activityView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> param) {
                ListCell<TodoItem> cell = new ListCell<TodoItem>() {

                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setText(null);
                        } else {

                            if(item.getCategory().equals("Approved")){
                                setText(item.getCategory());
                                setTextFill(Color.RED);
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
        TodoItem item = TrashView.getSelectionModel().getSelectedItem();

        deadlineLabel.setText(item.getDeadline().toString());


    }

}
