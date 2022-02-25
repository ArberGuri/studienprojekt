package de.clubadministation.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.clubadministation.backend.entity.Group;
import de.clubadministation.backend.entity.Location;
import de.clubadministation.backend.entity.Member;
import de.clubadministation.backend.service.GroupService;
import de.clubadministation.backend.service.LocationService;
import de.clubadministation.backend.service.MemberService;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import javax.annotation.security.RolesAllowed;

@Route(value = "member", layout = MainLayout.class)
@PageTitle("Mitglieder")
@RolesAllowed("ADMIN")
public class MemberView extends VerticalLayout {

    public MemberView(MemberService memberService, GroupService groupService, LocationService locationService) {
        GridCrud<Member> crud = new GridCrud<>(Member.class);

        TextField filter = new TextField();
        filter.setPlaceholder("Suche nach Namen ...");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);

        crud.getGrid().setColumns("firstName", "lastName", "birthDate", "email", "phone", "street");
        crud.getGrid().addColumn(member -> member.getLocation().getCity()).setHeader("Stadt");
        crud.getGrid().addColumn(member -> member.getGroup().getGroupName()).setHeader("Gruppe");
        crud.getGrid().setColumnReorderingAllowed(true);

        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "firstName", "lastName","group", "birthDate", "email", "phone", "street", "location");
        crud.getCrudFormFactory().setVisibleProperties("firstName", "lastName","group", "birthDate", "email", "phone", "street", "location");
        crud.getCrudFormFactory().setFieldProvider("location", new ComboBoxProvider<>("Stadt", locationService.findAll(), new TextRenderer<>(Location::getCity), Location::getCity));
        crud.getCrudFormFactory().setFieldProvider("group", new ComboBoxProvider<>("Gruppe", groupService.findAll(), new TextRenderer<>(Group::getGroupName), Group::getGroupName));
        crud.getCrudFormFactory().setFieldCaptions("Vorname", "Nachname", "Gruppe", "Geburtstag", "Email", "Telefon", "Straße", "Standort");

        setSizeFull();
        add(crud);


        crud.setOperations(
                () -> memberService.findByName(filter.getValue()),
                memberService::add,
                memberService::add,
                memberService::delete
        );

        filter.addValueChangeListener(e -> crud.refreshGrid());

    }
}
