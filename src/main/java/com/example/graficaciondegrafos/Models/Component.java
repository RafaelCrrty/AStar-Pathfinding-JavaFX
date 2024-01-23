package com.example.graficaciondegrafos.Models;

public class Component extends IComponent{
    private Mediator mediator;
    private String name;
    private Object message;

    public Component(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
        this.message = null;
    }

    @Override
    void notify(Object message) {
        mediator.notify(message, this);
    }

    @Override
    void receive(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }

}
