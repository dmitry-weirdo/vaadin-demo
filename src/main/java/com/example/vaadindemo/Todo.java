package com.example.vaadindemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.AUTO fails with primary key duplication, if pre-inserted records exist
    private Long id;

    private String text;

    private boolean done;

    public Todo() {
    }
    public Todo(final String text, final boolean done) {
        this.text = text;
        this.done = done;
    }

    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(final String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }
    public void setDone(final boolean done) {
        this.done = done;
    }
}