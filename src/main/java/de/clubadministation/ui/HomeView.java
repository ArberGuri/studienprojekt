package de.clubadministation.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "home", layout = MainLayout.class)
@AnonymousAllowed
public class HomeView extends VerticalLayout {
    public HomeView(){
        add( new H1("Home View"));
    }
}
