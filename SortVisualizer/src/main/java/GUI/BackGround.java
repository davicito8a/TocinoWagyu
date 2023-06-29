package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.effect.DropShadow;
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
     * Dibuja la ciudad en el contexto gráfico especificado.
     *
     * @param gc el contexto gráfico en el que se dibujará la ciudad
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
     * Dibuja la ciudad en el contexto gráfico especificado.
     *
     * @param gc el contexto gráfico en el que se dibujará la ciudad
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

    public void Desert(Canvas canvas) {
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

        gc.setFill(Color.DARKOLIVEGREEN);
        for (int i = 0; i < 1500; i++) {
            double x = random.nextDouble() * 900; // Generar coordenada x aleatoria en el rango [0, 1500)
            double y = random.nextDouble() * (300 - 250) + 300; // Generar coordenada y aleatoria en el rango [0, 600)
            gc.fillRect(x, y, 4, 4);
        }

        gc.setFill(Color.DARKOLIVEGREEN);
        for (int i = 0; i < 1500; i++) {
            double x = random.nextDouble() * 900; // Generar coordenada x aleatoria en el rango [0, 1500)
            double y = random.nextDouble() * (300 - 250) + 250; // Generar coordenada y aleatoria en el rango [0, 600)
            gc.fillRect(x, y, 4, 4);
        }

        Stickman(canvas, 5500, 1300);

        // Base rieles
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setColor(Color.BLACK);
        gc.setEffect(shadow);
        gc.setLineWidth(80);
        gc.setStroke(Color.DARKGREY);
        gc.strokeLine(0, 300, 895, 300);

        // Base diagonales 1 y 2
        gc.setStroke(Color.DARKGREY);
        gc.strokeLine(895, 310, 1550, 600);
        gc.strokeLine(895, 290, 1550, 0);

        // Riel (madera) vertical
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(13);
        double railX = 0;

        for (int i = 0; i < 45; i++) { // Dibujar líneas verticales
            gc.strokeLine(railX, 285, railX, 315);
            railX += 20; // Espaciado entre las líneas verticales
        }

        gc.setStroke(Color.BROWN);
        double railXUP1 = 906;
        double railXUP2 = 920;
        double railYUP1 = 274;
        double railYUP2 = 304;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 45; j++) {
                gc.strokeLine(railXUP1, railYUP1, railXUP2, railYUP2);

                railXUP1 += 20;
                railXUP2 += 20;

                if (i == 0) {
                    railYUP1 -= 9;
                    railYUP2 -= 9;
                } else {
                    railYUP1 += 9;
                    railYUP2 += 9;
                }
            }

            // Restaurar los valores iniciales después de completar el primer conjunto de líneas
            railXUP1 = 919;
            railXUP2 = 905;
            railYUP1 = 300;
            railYUP2 = 330;
        }

        // Riel horizontal 
        gc.setStroke(Color.DIMGRAY);
        gc.setLineWidth(9);
        gc.strokeLine(0, 313, 890, 313);
        gc.strokeLine(0, 287, 890, 287);

        // Riel diagonal 1 (HACIA ABAJO)
        gc.setStroke(Color.DIMGRAY);
        gc.strokeLine(895, 317, 1500, 591);
        gc.strokeLine(911, 300, 1550, 590);

        // Riel diagonal 2 (HACIA ARRIBA)
        gc.setStroke(Color.DIMGRAY);
        gc.strokeLine(895, 285, 1500, 11);
        gc.strokeLine(912, 300, 1550, 15);
    }

    public void Stickman(Canvas canvas, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double EscaladoS = .2;

        // Dibujar el cuerpo del stickman
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        gc.strokeOval(x * EscaladoS, y * EscaladoS, 100 * EscaladoS, 100 * EscaladoS); // Cabeza
        gc.strokeLine((x + 50) * EscaladoS, (y + 100) * EscaladoS, (x + 50) * EscaladoS, (y + 200) * EscaladoS); // Cuerpo
        gc.strokeLine((x + 50) * EscaladoS, (y + 200) * EscaladoS, (x - 50) * EscaladoS, (y + 250) * EscaladoS); // Pierna izquierda
        gc.strokeLine((x + 50) * EscaladoS, (y + 200) * EscaladoS, (x + 150) * EscaladoS, (y + 250) * EscaladoS); // Pierna derecha
        gc.strokeLine((x + 50) * EscaladoS, (y + 120) * EscaladoS, (x - 50) * EscaladoS, (y + 170) * EscaladoS); // Brazo izquierdo
        gc.strokeLine((x + 50) * EscaladoS, (y + 120) * EscaladoS, (x + 150) * EscaladoS, (y + 170) * EscaladoS); // Brazo derecho

        // Dibujar el sombrero
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

        gc.fillRect((x + 15) * EscaladoS, (y - 5) * EscaladoS, 70 * EscaladoS, 25 * EscaladoS); // Parte superior del sombrero
        gc.fillRect((x) * EscaladoS, (y + 10) * EscaladoS, 100 * EscaladoS, 20 * EscaladoS); // Borde del sombrero
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
