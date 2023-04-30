package GUI;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class BackGround {

    public BackGround(List<Building> buildings, Canvas canvas) {
        this.buildings = buildings;
        this.canvas = canvas;
    }
    
    private List<Building> buildings;
    private Canvas canvas;
    
    
    
    
              /**
        Dibuja la ciudad en el contexto gráfico especificado.

        @param gc el contexto gráfico en el que se dibujará la ciudad
        */
    private void drawCity(GraphicsContext gc) {
        // Definir los colores para el cielo
        Color topColor = Color.rgb(135, 206, 235);
        Color bottomColor = Color.rgb(255, 165, 0);

        // Dibujar el cielo con un gradiente de color
        gc.setFill(new LinearGradient(0, 0, 0, canvas.getHeight() / 2, false, CycleMethod.NO_CYCLE, new Stop(0, topColor), new Stop(1, bottomColor)));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight() / 2);

        // Dibujar nubes en el cielo
        gc.setFill(Color.WHITE);
        gc.fillOval(50, 50, 100, 50);
        gc.fillOval(150, 80, 80, 40);
        gc.fillOval(250, 60, 120, 60);

        // Dibujar las aceras
        gc.setFill(Color.rgb(200, 200, 200));
        gc.fillRect(0, canvas.getHeight() / 2, canvas.getWidth(), 50);
        gc.fillRect(0, canvas.getHeight() / 2 + 100, canvas.getWidth(), 50);

        // Dibujar la carretera
        gc.setFill(Color.rgb(100, 100, 100));
        gc.fillRect(0, canvas.getHeight() / 2 + 50, canvas.getWidth(), 50);

        // Dibujar el suelo de la ciudad
        gc.setFill(Color.rgb(50, 50, 50));
        gc.fillRect(0, canvas.getHeight() / 2 + 100, canvas.getWidth(), canvas.getHeight() / 2 - 100);

            // Dibujar los edificios
            for (Building building : buildings) {
                gc.setFill(building.getColor());

                // Dibujar el cuerpo del edificio
                gc.fillRect(building.getX(), building.getY(), building.getWidth(), building.getHeight());

                // Dibujar ventanas en el edificio
                gc.setFill(Color.rgb(255, 255, 224));
                int windowWidth = (int) (building.getWidth() * 0.1);
                int windowHeight = (int) (building.getHeight() * 0.1);

                for (int x = building.getX() + windowWidth; x < building.getX() + building.getWidth() - windowWidth; x += windowWidth * 2) {
                    for (int y = building.getY() + windowHeight; y < building.getY() + building.getHeight() - windowHeight; y += windowHeight * 2) {
                        gc.fillRect(x, y, windowWidth, windowHeight);
                    }
                }

                // Dibujar la puerta del edificio
                gc.setFill(Color.rgb(150, 75, 0));
                int doorWidth = (int) (building.getWidth() * 0.2);
                int doorHeight = (int) (building.getHeight() * 0.4);
                gc.fillRect(building.getX() + building.getWidth() / 2 - doorWidth / 2, building.getY() + building.getHeight() - doorHeight, doorWidth, doorHeight);
            }
    }



    
    private class Building {
        private int x;
        private int y;
        private int width;
        private int height;
        private Color color;
        
        public Building(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
        
        public int getWidth() {
            return width;
        }
        
        public int getHeight() {
            return height;
        }
        
        public Color getColor() {
            return color;
        }
    }
}