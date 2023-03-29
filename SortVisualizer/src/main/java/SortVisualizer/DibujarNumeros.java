/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SortVisualizer;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author david
 */
public class DibujarNumeros {


    

    public void dibujarCero(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y, 50, 50);
    }

    public void dibujarUno(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x + 25, y, x + 25, y + 50);
        gc.strokeLine(x, y + 25, x + 50, y + 25);
    }

    public void dibujarDos(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x + 50, y, x + 50, y + 25);
        gc.strokeLine(x + 50, y + 25, x, y + 25);
        gc.strokeLine(x, y + 25, x, y + 50);
        gc.strokeLine(x, y + 50, x + 50, y + 50);
    }

    public void dibujarTres(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x + 50, y, x + 50, y + 25);
        gc.strokeLine(x + 50, y + 25, x, y + 25);
        gc.strokeLine(x + 50, y + 25, x + 50, y + 50);
        gc.strokeLine(x, y + 50, x + 50, y + 50);
    }

}
