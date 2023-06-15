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

    GraphicsContext gh;
    
    public BackGround(Canvas canvas) {
        this.canvas = canvas;

    }
    
    private List<Building> buildings;
    private Canvas canvas;
    
    
    
        public void drawBG(Canvas canvas1) {
            gh = canvas1.getGraphicsContext2D();
            canvas.setHeight(600);
            canvas.setWidth(1500);
            
            
            createBuildings();
                // Agregar un controlador de eventos de ratón para permitir el zoom

            drawCity(gh);
  
    }
              /**
        Dibuja la ciudad en el contexto gráfico especificado.

        @param gc el contexto gráfico en el que se dibujará la ciudad
        */
     private void createBuildings() {
        buildings = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 14; i++) {
            int x = 25 + i * (75 + 35);  // 75 es el ancho de los edificios originales
            int y = 200;
            int width = 75;
            int height = 150;
            Color color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            buildings.add(new Building(x, y, width, height, color));
        }
    }


    
    
            /**
        Dibuja la ciudad en el contexto gráfico especificado.

        @param gc el contexto gráfico en el que se dibujará la ciudad
        */
        public void drawCity(GraphicsContext gc) {
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

        gc.setFill(Color.rgb(50, 50, 50));
        gc.fillRect(0, canvas.getHeight() / 2 + 100, canvas.getWidth(), canvas.getHeight() / 2 - 100);
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(5);
        gc.strokeRect(0, canvas.getHeight() / 2 + 100, canvas.getWidth(), canvas.getHeight() / 2 - 100);

        // Dibujar las líneas de tráfico
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(10);
        gc.setLineDashes(20);
        gc.setLineDashOffset(10);
        double lineSpacing = 100;
        for (double y = canvas.getHeight() / 2 + 100 + lineSpacing / 2; y < canvas.getHeight(); y += lineSpacing) {
            gc.strokeLine(50, y, canvas.getWidth() - 50, y);
        }

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
                int doorHeight = (int) (building.getHeight() * 0.15);
                gc.fillRect(building.getX() + building.getWidth() / 2 - doorWidth / 2, building.getY() + building.getHeight() - doorHeight, doorWidth, doorHeight);
            }
    }
    
    
        public void Desert(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setHeight(600);
        canvas.setWidth(1500);
        // Fondo desértico
        gc.setFill(Color.SANDYBROWN);
        gc.fillRect(0, 0, 1500, 600);

        
        
        
        // Detalle de relleno (IMPORTANTE)
        Random random = new Random();

        // Dibujar puntos aleatorios
        gc.setFill(Color.PERU);
        for (int i = 0; i < 1500; i++) {
            double x = random.nextDouble() * 1500; // Generar coordenada x aleatoria en el rango [0, 1500)
            double y = random.nextDouble() * 600; // Generar coordenada y aleatoria en el rango [0, 600)
            gc.fillRect(x, y, 4, 4);
        }
        gc.setFill(Color.CHOCOLATE);
        for (int i = 0; i < 1500; i++) {
            double x = random.nextDouble() * 1500; // Generar coordenada x aleatoria en el rango [0, 1500)
            double y = random.nextDouble() * 600; // Generar coordenada y aleatoria en el rango [0, 600)
            gc.fillRect(x, y, 4, 4);
        }
       
        
        
        // Base rieles
        gc.setLineWidth(60); 
        gc.setStroke(Color.DARKGREY);
        gc.strokeLine(0, 300, 1020, 300);

        // Base diagonales 1 y 2
        
        gc.setLineWidth(50); 
        gc.setStroke(Color.DARKGREY);
        gc.strokeLine(1050, 310, 1550, 600);
        gc.strokeLine(1050, 290, 1550, 0);        
        
        // Riel horizontal 1
        gc.setStroke(Color.DIMGRAY);
        
        
        gc.setLineWidth(20);
        gc.strokeLine(0, 320, 1020, 320);
        gc.strokeLine(0, 320, 1020, 320);
        // Riel horizontal paralelo al anterior
        gc.strokeLine(0, 280, 1020, 280);

  

        // Riel diagonal 1 (HACIA ABAJO)
        gc.setStroke(Color.DIMGRAY);
        gc.setLineWidth(20);
        gc.strokeLine(1000, 310, 1500, 600);
        gc.strokeLine(1050, 310, 1550, 600);

        // Riel diagonal 2 (HACIA ARRIBA)
        gc.setStroke(Color.DIMGRAY);
        gc.setLineWidth(20);
        gc.strokeLine(1000, 290, 1500, 0);
        gc.strokeLine(1050, 290, 1550, 0);


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
    }}