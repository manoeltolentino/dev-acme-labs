package com.acme.fw.vaadin.views.emptyview;

import com.acme.fw.vaadin.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Empty view")
@Route(value = "", layout = MainLayout.class)
@Menu(order = 0, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@PermitAll
public class EmptyviewView extends Composite<VerticalLayout> {

    public EmptyviewView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }
}
