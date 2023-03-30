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

    public class DibujarNumeros{
   public void DibujarNumeros(GraphicsContext gc) {
        //dibujarCero(gc, 50, 100);
        //dibujarUno(gc, 200, 100);
        //dibujarDos(gc, 200, 100);
        //dibujarTres(gc, 250, 100);
        //dibujarCuatro(gc, 300, 100);
        //dibujarCinco(gc, 100, 100);
        //dibujarSeis(gc, 150, 100);
        //mejorar dibujarSiete(gc, 200, 100);
        //dibujarOcho(gc, 250, 100);
        //dibujarNueve(gc, 300, 100);
}

    public void dibujarCero(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y, 50, 50);
    }

    public void dibujarUno(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x + 25, y, x + 25, y + 50);
        gc.strokeLine(x + 25,y, x + 10, y + 17 );
        gc.strokeLine(x + 10,y + 50, x + 40, y+50);
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

    public void dibujarCuatro(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x, y + 25);
        gc.strokeLine(x, y + 25, x + 50, y + 25);
        gc.strokeLine(x + 25, y, x + 25, y + 50);
    }
        public void dibujarCinco(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x , y, x , y + 25);
        gc.strokeLine(x + 50, y + 25, x, y + 25);
        gc.strokeLine(x + 50, y + 25, x + 50, y + 50);
        gc.strokeLine(x, y + 50, x + 50, y + 50);
    }

    public void dibujarSeis(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y + 65, 35, 35);
        gc.strokeLine(x + 15  , y + 30, x, y + 80);
    }

    public void dibujarSiete(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x + 50, y, x, y + 50);
    }

    public void dibujarOcho(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y, 50, 50);
        gc.strokeOval(x + 12.5, y - 25, 25, 25);
    }

    public void dibujarNueve(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y, 35, 35);
        gc.strokeLine(x + 35, y + 55, x + 35, y + 20);
    }
    
    
    
    }