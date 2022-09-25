package com.onenote.onenote.Data;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class TodoData {
    private static TodoData instance = new TodoData();
    private static String filename = "TodoListItems.txt";

    private ObservableList<TodoItem> todoItems;


    private DateTimeFormatter formatter;

    public static TodoData getInstance() {
        return instance;
    }


    public TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        todoItems=FXCollections.observableArrayList();
    }

    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void addTodoItem(TodoItem item) {
        todoItems.add(item);
    }

//    public void setTodoItems(List<TodoItem> todoItems) {
//        this.todoItems = todoItems;
//    }

    public void loadTodoItems() throws IOException {

        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String category = itemPieces[2];
                String dateString = itemPieces[3];

                LocalDate date = LocalDate.parse(dateString, formatter);

                do{
                    if(date.equals(LocalDate.now())){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "In Progress", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.equals(LocalDate.now().plusDays(1))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Waiting", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isAfter(LocalDate.now().plusDays(2))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Waiting", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isAfter(LocalDate.now().plusDays(3))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Waiting", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isAfter(LocalDate.now().plusDays(4))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Waiting", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isAfter(LocalDate.now().plusDays(5))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Waiting", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isAfter(LocalDate.now().plusDays(6))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Waiting", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isAfter(LocalDate.now().plusDays(7))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Waiting", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isAfter(LocalDate.now().plusWeeks(1))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Approved", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isBefore(LocalDate.now().plusDays(1))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Approved", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isBefore(LocalDate.now().plusDays(2))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Approved", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isBefore(LocalDate.now().plusDays(3))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Approved", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isBefore(LocalDate.now().plusDays(4))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Approved", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isBefore(LocalDate.now().plusDays(5))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Approved", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isBefore(LocalDate.now().plusDays(6))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Approved", date);
                        todoItems.add(todoItem);
                        break;
                    }else if(date.isBefore(LocalDate.now().plusDays(7))){
                        TodoItem todoItem = new TodoItem(shortDescription, details, "Approved", date);
                        todoItems.add(todoItem);
                        break;
                    }
                }while (dateString.equals(null));

            }

        } finally {
            if(br != null) {
                br.close();
            }
        }
    }



    public void storeTodoItems() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<TodoItem> iter = todoItems.iterator();
            while (iter.hasNext()) {
                TodoItem item = iter.next();
                bw.write(String.format("%s\t%S\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getCategory(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }

        } finally {
            if(bw != null) {
                bw.close();
            }
        }


    }

    public void deleteTodoItem(TodoItem item) {
        todoItems.remove(item);
    }

    public void autoChange() throws IOException{

    }
}
