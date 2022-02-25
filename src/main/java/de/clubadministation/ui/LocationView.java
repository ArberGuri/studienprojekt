package de.clubadministation.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.clubadministation.backend.entity.Location;
import de.clubadministation.backend.service.LocationService;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.RolesAllowed;

@Route(value = "location", layout = MainLayout.class)
@PageTitle("Standorte")
@RolesAllowed("ADMIN")
public class LocationView extends VerticalLayout {

    public LocationView(LocationService locationService){
        var crud = new GridCrud<>(Location.class, locationService);

        TextField filter = new TextField();
        filter.setPlaceholder("Suche nach Namen ...");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);

        crud.getGrid().setColumns("postcode", "city");
        crud.getCrudFormFactory().setVisibleProperties("postcode", "city");
        crud.getCrudFormFactory().setFieldCaptions("Postleizahl", "Stadt");

        add(crud);
        crud.setOperations(
                () -> locationService.findByCityName(filter.getValue()),
                locationService::add,
                locationService::add,
                locationService::delete
        );

        filter.addValueChangeListener(e -> crud.refreshGrid());
    }
}
