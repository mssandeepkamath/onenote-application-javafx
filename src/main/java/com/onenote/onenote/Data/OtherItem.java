package com.onenote.onenote.Data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.time.LocalDate;

public class OtherItem {
    private String shortDescription;
    private String details;
    private String category;

    private LocalDate deadline;
    private BooleanProperty selected = new SimpleBooleanProperty(false);

    public OtherItem(String shortDescription, String details, String category,  LocalDate deadline) {
        this.shortDescription = shortDescription;
        this.details = details;
        this.category = category;

        this.deadline = deadline;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String Category) {
        this.category = Category;
    }



    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }
    public boolean isSelected() {
        return selected.get();
    }
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
