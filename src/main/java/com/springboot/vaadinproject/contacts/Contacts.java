package com.springboot.vaadinproject.contacts;

import com.springboot.vaadinproject.location.Location;
import com.springboot.vaadinproject.location.LocationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Contacts | Vaadin")
@Route(value = "")
public class Contacts extends VerticalLayout {

    Grid<Location> grid = new Grid<>(Location.class);
    TextField filteText = new TextField();
    LocationForm form;
    private LocationService service;


    public Contacts(LocationService service){
        this.service = service;
        addClassName("list-view");//for any CSS
        setSizeFull(); //set size to entire browser window

        configureGrid();
        configureForm();
        
        add(
            getToolbar(),
            getContent()
        );

        updateList();
    }

    private void updateList() {
        grid.setItems(service.findAllLocations(filteText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new LocationForm(service.findAllLocations(""));
        form.setWidth("25em");

    }

    private Component getToolbar() {
        filteText.setPlaceholder("Filter by location name...");
        filteText.setClearButtonVisible(true);
        filteText.setValueChangeMode(ValueChangeMode.LAZY);

        filteText.addValueChangeListener(e -> updateList());

        Button addLocationButton = new Button("Add Location");

        HorizontalLayout toolBar = new HorizontalLayout(filteText, addLocationButton);
        toolBar.addClassName("toolBar");
        return toolBar;
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();

        grid.setColumns("id", "name");
        //for custom objects in the class
        //grid.addColumns(location -> location.getPlace().getName()).setHeader("Place") - It will had a column and set header
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
