package com.example.vaadindemo;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
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

    // todo: @Autowired cause app to crash with "java.lang.IllegalStateException: Can't move a node from one state tree to another" on reload
    @Autowired
    private TodoLayout todoLayout;


    public TodoDiv() {
        setText("Hello world!");

//        initMainComponents();
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

    @Override
    protected void onAttach(final AttachEvent attachEvent) {
//        initMainComponents();
        System.out.println("I am in onAttach!!! 777!!!");
    }
    @Override
    protected void onDetach(final DetachEvent detachEvent) {
        System.out.println("I am in onDetach!!! 666!!!");
    }



    private void setupLayout() {
        verticalLayout = new VerticalLayout();
        verticalLayout.getStyle().set("border", "1px solid #9E9E9E");
        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(verticalLayout); // add layout to current div
    }

    private void addHeader() {
        final Label header = new Label("TODOs");
//        header.addClassName(ValoTheme.LABEL_H1); // todo: ValoTheme.LABEL_H1 is not accessible in vaadin 10

        verticalLayout.add(header);
    }

    private void addForm() {
        final HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        final TextField task = new TextField();
        task.focus(); // focus field at the beginning


//        final Button addButton = new Button("Add");
        final Button addButton = new Button("");
//        add.addClassName(ValoTheme.BUTTON_PRIMARY);
        addButton.setIcon( new Icon(VaadinIcon.PLUS) );

        addButton.addClickListener(click -> {
           todoLayout.addTodo( new Todo(task.getValue(), false) );
           task.clear();
           task.focus();
        });
//        ComponentUtil.addListener(addButton, KeyDownEvent.class, (t) -> addButton.click());


        formLayout.add(task, addButton);

        verticalLayout.add(formLayout);


        Shortcut.add(verticalLayout, Key.ENTER, addButton::click); // using org.vaadin.marcus.shortcut plugin
//        Shortcut.add(verticalLayout, Key.ENTER, addButton::click, Key.SHIFT);
    }

    private void addTodoList() {

/*
        // todo: remove this explicit initialization and use @Autowired injection instead
        todoLayout = new TodoLayout();
        todoLayout.init();
*/


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