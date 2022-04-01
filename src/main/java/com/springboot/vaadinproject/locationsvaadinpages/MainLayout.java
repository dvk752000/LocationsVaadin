package com.springboot.vaadinproject.locationsvaadinpages;

import com.springboot.vaadinproject.SampleListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout(){
        createheader();
        createDrawer();
    }

    private void createDrawer() {
        RouterLink listView = new RouterLink("List", LocationsMainPage.class);
        RouterLink simpleListView = new RouterLink("SampleList", SampleListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(
            new VerticalLayout(
                listView,
                simpleListView
            )
        );
    }

    private void createheader() {
        H1 logo = new H1("Vaadin Implementation");
        logo.setClassName("text-l");
        logo.addClassName("m-m");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setClassName("py-0");
        header.addClassName("py-m");
        addToNavbar(header);
    }
}
