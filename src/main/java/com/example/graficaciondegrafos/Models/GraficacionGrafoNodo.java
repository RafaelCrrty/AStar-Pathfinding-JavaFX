package com.example.graficaciondegrafos.Models;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class GraficacionGrafoNodo extends StackPane {
    private StackPane stackPane;
    private Circle circle;
    private double x = 0,y = 0;
    private Label name ;
    //private int numMax = 26;
    public GraficacionGrafoNodo(Label name, double x,double y) {
        this.name = name;
        this.x = x;
        this.y = y;
        nodo();
    }
    public void nodo(){
        circle = new Circle(25);
        stackPane = new StackPane();
        double radius = circle.getRadius();
        setY(y-radius);
        setX(x-radius);
        stackPane.setLayoutX(getX());
        stackPane.setLayoutY(getX());
        stackPane.setLayoutX(getX());
        stackPane.setLayoutY(getY());
        stackPane.getChildren().add(circle);
        stackPane.getChildren().add(name);
        circle.setFill(Color.rgb(255,48,68,0.7));

        // Agregar un evento de clic al StackPane
        stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click(event));
    }

    public void click(MouseEvent event) {
        Mediator mediator = Mediator.getInstance();
        Component component1 = new Component(mediator, "Component1");
        mediator.add(component1);
        Medio datos = new Medio(getX(),getY(),getName().getText());
        component1.notify(datos);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }
}
