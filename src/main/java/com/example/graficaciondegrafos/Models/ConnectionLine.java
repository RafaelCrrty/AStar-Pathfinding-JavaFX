package com.example.graficaciondegrafos.Models;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class ConnectionLine extends Group {
    private Line line;
    private Label label;
    public ConnectionLine(double startX, double startY, double endX, double endY, String labelText) {
        // Crear una línea
        line = new Line(startX, startY, endX, endY);
        // Crear un Label para representar la conexión
        label = new Label(labelText);
        // Calcular las coordenadas para el Label
        double labelX = (line.getStartX() + line.getEndX()) / 2 - label.getWidth() / 2;
        double labelY = (line.getStartY() + line.getEndY()) / 2 - label.getHeight() / 2;
        // Establecer las coordenadas del Label
        label.setLayoutX(labelX);
        label.setLayoutY(labelY);
        this.getChildren().addAll(line,label);
    }
}
