package com.springboot.vaadinproject;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@PageTitle("List")
@Route(value = "Basic List")
public class SampleListView extends VerticalLayout {
    public SampleListView(){
        Button button = new Button("Click ME");
        TextField name = new TextField("Name");
        add(new H1("Hello World"));

        HorizontalLayout horizontalLayout = new HorizontalLayout(name, button);
        horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        add(horizontalLayout);

        button.addClickListener(click -> Notification.show("Hello, " + name.getValue()) );
        
    }
}
