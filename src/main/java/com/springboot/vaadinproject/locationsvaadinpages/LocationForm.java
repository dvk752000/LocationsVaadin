package com.springboot.vaadinproject.locationsvaadinpages;

import java.util.List;

import com.springboot.vaadinproject.location.Location;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class LocationForm extends FormLayout {

    Binder<Location> binder = new BeanValidationBinder<>(Location.class);

    TextField id = new TextField("Location ID");
    TextField name = new TextField("Location Name");

    ComboBox<Location> loc = new ComboBox<>("Location");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private Location location;

    public LocationForm(List<Location> location){
        addClassName("location-form");

        binder.bindInstanceFields(this);
        loc.setItems(location);
        loc.setItemLabelGenerator(Location::getName);
        loc.setItemLabelGenerator(Location::getId);

        add(
            id,
            name,
            createButtonLayout()
            );
    }

    public void setLocation(Location location){
        this.location = location;
        binder.readBean(location);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, location)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, delete, cancel);
    }

    private Object validateAndSave() {
        try{
            binder.writeBean(location);
            fireEvent(new SaveEvent(this, location));
        }catch(ValidationException e){
            e.printStackTrace();
        }
        return null;
    }

// Events
public static abstract class LocationFormEvent extends ComponentEvent<LocationForm> {
    private Location location;
  
    protected LocationFormEvent(LocationForm source, Location location) { 
      super(source, false);
      this.location = location;
    }
  
    public Location getLocation() {
      return location;
    }
  }
  
  public static class SaveEvent extends LocationFormEvent {
    SaveEvent(LocationForm source, Location location) {
      super(source, location);
    }
  }
  
  public static class DeleteEvent extends LocationFormEvent {
    DeleteEvent(LocationForm source, Location location) {
      super(source, location);
    }
  
  }
  
  public static class CloseEvent extends LocationFormEvent {
    CloseEvent(LocationForm source) {
      super(source, null);
    }
  }
  
  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
      ComponentEventListener<T> listener) { 
    return getEventBus().addListener(eventType, listener);
  }

public void addListener(Class<SaveEvent> class1, Object listener) {
}

}
