package com.springboot.vaadinproject.locationsvaadinpages;

import com.springboot.vaadinproject.location.LocationService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard")
public class DashboardView extends VerticalLayout{
    // private LocationService service;
    public DashboardView(LocationService service){
        // this.service = service;
        addClassName("dashbaord-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        //add(getContactStats(), getCompaniesChart());
    }
    // private Object getCompaniesChart() {
    //     Span stats = new Span(service.countLocations() + " location");
    //     stats.addClassNames("text-xl", "mt-m");
    //     return stats;
    // }
    // private Component getContactStats() {
    //     Chart chart = new Chart(ChartType.PIE);
    // }
}
