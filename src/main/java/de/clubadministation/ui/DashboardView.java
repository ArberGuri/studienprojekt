package de.clubadministation.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.clubadministation.backend.service.GroupService;
import de.clubadministation.backend.service.MemberService;

import javax.annotation.security.RolesAllowed;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Statistiken")
@AnonymousAllowed
public class DashboardView extends VerticalLayout {

    private MemberService memberService;
    private GroupService groupService;

    public DashboardView(GroupService groupService, MemberService memberService){
        this.memberService = memberService;
        this.groupService = groupService;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getMemberStats(),getGroupStats(), getGroupChart());
    }

    private Component getMemberStats() {
       Span stats = new Span(memberService.countMembers() + " Mitglieder");
       stats.addClassNames("text-xl", "mt-m");
       return stats;
    }

    private Component getGroupStats() {
        Span stats = new Span(groupService.countGroups() + " Gruppen");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Component getGroupChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        groupService.findAll().forEach(group -> dataSeries.add(new DataSeriesItem(group.getGroupName(), group.getMemberCount())));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }


}
