package com.acme.application.views.context;

import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.acme.application.services.ContextService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Context")
@Route("contextview")
@Menu(order = 1, icon = LineAwesomeIconUrl.GLOBE_SOLID)
@Uses(Icon.class)
public class ContextView extends HorizontalLayout{

    private final ContextService contextService;

    public ContextView(ContextService contextService){

        this.contextService = contextService;

        showSpringBeans();

    }

    private void showSpringBeans(){

        Details beanDetails = new Details("Beans loaded");

    	VerticalLayout verticalLayout = new VerticalLayout();
    	verticalLayout.setSpacing(false);
    	verticalLayout.setPadding(false);

        List<String> beanList = this.contextService.getBeansLoaded();
    	
    	if (beanList != null) {
        	
        	try {
    	    	for (String value: beanList) {
    				verticalLayout.add(new Span(value));
    			}
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}

            beanDetails.setSummaryText("Beans loaded: " + beanList.size());
    	}
    	
    	beanDetails.add(verticalLayout);
        
        add(beanDetails);

    }

}
