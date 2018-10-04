package com.example.vaadindemo;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.marcus.shortcut.Shortcut;

import javax.annotation.PostConstruct;

@Route(value = "", absolute = true)
@Theme(Lumo.class)
public class TodoDiv extends Div {
    private VerticalLayout verticalLayout;

    @Autowired
    private TodoLayout todoLayout;


    public TodoDiv() {
    }

    @PostConstruct
    private void postConstruct() {
        initMainComponents();
    }

    private void initMainComponents() {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addDeleteButton();
    }

    private void setupLayout() {
        verticalLayout = new VerticalLayout();
        verticalLayout.getStyle().set("border", "1px solid #9E9E9E");
        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(verticalLayout); // add layout to current div
    }

    private void addHeader() {
        final Label header = new Label("TODOs");
//        header.addClassName(ValoTheme.LABEL_H1); // todo: ValoTheme is not accessible in vaadin 10, style manually

        verticalLayout.add(header);
    }

    private void addForm() {
        final HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        final TextField task = new TextField();
        task.focus(); // focus field at the beginning


//        final Button addButton = new Button("Add");
        final Button addButton = new Button("");
//        add.addClassName(ValoTheme.BUTTON_PRIMARY); // todo: ValoTheme is not accessible in vaadin 10, style manually
        addButton.setIcon( new Icon(VaadinIcon.PLUS) );

        addButton.addClickListener(click -> {
           todoLayout.addTodo( new Todo(task.getValue(), false) );
           task.clear();
           task.focus();
        });

        formLayout.add(task, addButton);

        verticalLayout.add(formLayout);


        Shortcut.add(verticalLayout, Key.ENTER, addButton::click); // using org.vaadin.marcus.shortcut plugin
    }

    private void addTodoList() {
        todoLayout.setWidth("80%");

        verticalLayout.add(todoLayout);
    }

    private void addDeleteButton() {
        final Button deleteCompletedButton = new Button("Delete completed");
        deleteCompletedButton.addClickListener(click -> {
           todoLayout.deleteCompleted();
        });

        verticalLayout.add(deleteCompletedButton);
    }
}