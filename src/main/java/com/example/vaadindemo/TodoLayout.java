package com.example.vaadindemo;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent // Vaadin analogue of Spring's @Component, to be picked up by @Autowired
@UIScope // in Singleton scope, the reload of the form will fail with "java.lang.IllegalStateException: Can't move a node from one state tree to another"
public class TodoLayout extends VerticalLayout implements TodoChangeListener {

    @Autowired
    private TodoRepository repository;

    @PostConstruct
    public void init() {
        update();
    }
    private void update() {
        setTodos( repository.findAll() );
    }

    private void setTodos(final List<Todo> todos) {
        removeAll(); // was removeAllComponents in Vaadin 8

        todos.forEach( todo -> add(new TodoItemLayout(todo, this)) );
    }

    public void addTodo(final Todo todo) {
        repository.save(todo);
        update();
    }

    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }

    // TodoChangeListener implementation
    @Override
    public void todoChanged(final Todo todo) {
        addTodo(todo);
    }
}