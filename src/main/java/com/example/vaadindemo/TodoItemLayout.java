package com.example.vaadindemo;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TodoItemLayout extends HorizontalLayout {
    private final Checkbox done;
    private final TextField text;

    public TodoItemLayout(final Todo todo, final TodoChangeListener changeListener) {
        done = new Checkbox();
        text = new TextField();

//        final Label textStatusLabel = new Label("Error will be here");
        final Label textStatusLabel = new Label();
//        textStatusLabel.getElement().addEventListener();

//        text.addClassName(ValoTheme.TEXTFIELD_BORDERLESS); // no ValoTheme in Vaadin 10

        add(done, text, textStatusLabel);
//        add(done, text);

        setDefaultVerticalComponentAlignment(Alignment.CENTER);

        final Binder<Todo> binder = new Binder<>(Todo.class);
        binder.setBean(todo);
        binder.addValueChangeListener(event -> changeListener.todoChanged(todo));

//        binder.bindInstanceFields(this);

        // try to add validation
        binder.forField(done).bind(Todo::isDone, Todo::setDone);

        binder
            .forField(text)
            .asRequired("Todo text must be non-empty") // todo: this works and prevent, but textStatusLabel is not shown, maybe because of the layout
            .withStatusLabel(textStatusLabel)
            .bind(Todo::getText, Todo::setText)
        ;
    }
}