package de.clubadministation.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.clubadministation.backend.entity.Group;
import de.clubadministation.backend.entity.Location;
import de.clubadministation.backend.service.GroupService;
import de.clubadministation.backend.service.LocationService;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import javax.annotation.security.RolesAllowed;

@Route(value = "group", layout = MainLayout.class)
@PageTitle("Gruppen")
@RolesAllowed("ADMIN")
public class GroupView extends VerticalLayout {

    public GroupView(GroupService groupService, LocationService locationService){
        GridCrud<Group> crud = new GridCrud<>(Group.class);

        TextField filter = new TextField();
        filter.setPlaceholder("Suche nach Namen ...");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);

        crud.getGrid().setColumns("groupName", "type", "street");
        crud.getGrid().addColumn(group -> group.getLocation().getCity()).setHeader("Stadt");
        crud.getGrid().setColumnReorderingAllowed(true);

        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "groupName", "type", "location", "street");
        crud.getCrudFormFactory().setVisibleProperties("groupName", "type", "location", "street");
        crud.getCrudFormFactory().setFieldProvider("location", new ComboBoxProvider<>("Stadt", locationService.findAll(), new TextRenderer<>(Location::getCity), Location::getCity));
        crud.getCrudFormFactory().setFieldCaptions("Gruppen Name", "Art", "Standort", "Straße");


        setSizeFull();
        add(crud);

        crud.setOperations(
                () -> groupService.findByGroupName(filter.getValue()),
                groupService::add,
                groupService::add,
                groupService::delete
        );

        filter.addValueChangeListener(e -> crud.refreshGrid());
    }
}
