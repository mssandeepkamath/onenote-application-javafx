package com.onenote.onenote.Data;


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

public class OtherData {
    private static OtherData instance = new OtherData();
    private static String filename = "TodoListOtherItems.txt";

    private ObservableList<OtherItem> OtherItems;
    private DateTimeFormatter formatter;

    public static OtherData getInstance() {
        return instance;
    }

    private OtherData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public ObservableList<OtherItem> getOtherItems() {
        return OtherItems;
    }

    public void addOtherItem(OtherItem item) {
        OtherItems.add(item);
    }

//    public void setOtherItems(List<OtherItem> OtherItems) {
//        this.OtherItems = OtherItems;
//    }

    public void loadOtherItems() throws IOException {

        OtherItems = FXCollections.observableArrayList();
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
                OtherItem OtherItem = new OtherItem(shortDescription, details, category, date);
                OtherItems.add(OtherItem);

            }


        } finally {
            if(br != null) {
                br.close();
            }
        }
    }

    private void OtherItem(OtherItem in_progress) {
        return;
    }

    public void storeOtherItems() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<OtherItem> iter = OtherItems.iterator();
            while (iter.hasNext()) {
                OtherItem item = iter.next();
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

    public void deleteOtherItem(OtherItem item) {
        OtherItems.remove(item);
    }

    public void autoChange() throws IOException{

    }
}
