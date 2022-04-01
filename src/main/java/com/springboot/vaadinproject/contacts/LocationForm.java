package com.springboot.vaadinproject.contacts;

import java.util.List;

import com.springboot.vaadinproject.location.Location;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class LocationForm extends FormLayout {
    TextField id = new TextField("Location ID");
    TextField name = new TextField("Location Name");

    ComboBox<Location> loc = new ComboBox<>("Location");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public LocationForm(List<Location> location){
        addClassName("location-form");

        loc.setItems(location);
        loc.setItemLabelGenerator(Location::getName);
        loc.setItemLabelGenerator(Location::getId);

        add(
            id,
            name,
            createButtonLayout()
            );
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, delete, cancel);
    }

}
