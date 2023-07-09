package SortVisualizerCore;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class NumberDrawer {

    GraphicsContext gc;
    double coorX;
    double coorY;
    double scale;

    public NumberDrawer(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1.5);
    }

    /**
     * Dibuja un número en el lienzo del canvas en una determinada escala.
     *
     * @param number   El número a dibujar.
     * @param scale    La escala del dibujo.
     * @param scale2   La escala adicional para ajustar la posición.
     */
    public void drawNumber(int number, double scale, double scale2) {
        coorX = 0.35 * Main.squareDimension * scale2 + 50 * scale;
        coorY = Main.squareDimension / 1.4 * 0.775 * scale2;
        this.scale = scale;

        int digit1 = number / 10;
        int digit2 = number % 10;

        if (number > 9) {
            drawDigit(digit1, coorX, coorY);
            coorX = 0.65 * Main.squareDimension * scale2 + 50 * scale;
            drawDigit(digit2, coorX, coorY);
        } else {
            coorX = 0.5 * Main.squareDimension * scale2 + 50 * scale;
            drawDigit(number, coorX, coorY);
        }
    }

    /**
     * Dibuja un dígito en el lienzo del canvas en una posición determinada.
     *
     * @param number   El dígito a dibujar.
     * @param x        La coordenada X de la posición del dibujo.
     * @param y        La coordenada Y de la posición del dibujo.
     */
    public void drawDigit(int number, double x, double y) {
        switch (number) {
            case 0:
                drawZero(gc, x, y);
                break;
            case 1:
                drawOne(gc, x, y);
                break;
            case 2:
                drawTwo(gc, x, y);
                break;
            case 3:
                drawThree(gc, x, y);
                break;
            case 4:
                drawFour(gc, x, y);
                break;
            case 5:
                drawFive(gc, x, y);
                break;
            case 6:
                drawSix(gc, x, y);
                break;
            case 7:
                drawSeven(gc, x, y);
                break;
            case 8:
                drawEight(gc, x, y);
                break;
            case 9:
                drawNine(gc, x, y);
                break;
            default:
                break;
        }
    }

    /**
     * Dibuja el número cero en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawZero(GraphicsContext gc, double x, double y) {
        gc.strokeOval(coorX - 100 * scale, coorY - 200 * scale, 100 * scale, 200 * scale);
    }

    /**
     * Dibuja el número uno en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawOne(GraphicsContext gc, double x, double y) {
        gc.strokeLine(coorX, coorY, coorX, coorY - 200 * scale);
        gc.strokeLine(coorX, coorY - 200 * scale, coorX - 100 * scale, coorY - 150 * scale);
    }

    /**
     * Dibuja el número dos en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawTwo(GraphicsContext gc, double x, double y) {
        gc.strokeLine(coorX, coorY, coorX - 100 * scale, coorY);
        gc.strokeLine(coorX - 6.7 * scale, coorY - 125 * scale, coorX - 100 * scale, coorY);
        gc.strokeArc(coorX - 100 * scale, coorY - 200 * scale, 100 * scale, 100 * scale, 180, -210, ArcType.OPEN);
    }

    /**
     * Dibuja el número tres en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawThree(GraphicsContext gc, double x, double y) {
        gc.strokeArc(coorX - 100 * scale, coorY - 200 * scale, 100 * scale, 100 * scale, 270, 270, ArcType.OPEN);
        gc.strokeArc(coorX - 100 * scale, coorY - 100 * scale, 100 * scale, 100 * scale, 180, 270, ArcType.OPEN);
    }

    /**
     * Dibuja el número cuatro en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawFour(GraphicsContext gc, double x, double y) {
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX - 100 * scale, coorY - 200 * scale);
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX, coorY - 100 * scale);
        gc.strokeLine(coorX, coorY, coorX, coorY - 200 * scale);
    }

    /**
     * Dibuja el número cinco en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawFive(GraphicsContext gc, double x, double y) {
        gc.strokeArc(coorX - 100 * scale, coorY - 100 * scale, 100 * scale, 100 * scale, 180, 270, ArcType.OPEN);
        gc.strokeLine(coorX - 50 * scale, coorY - 100 * scale, coorX - 100 * scale, coorY - 100 * scale);
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX - 100 * scale, coorY - 200 * scale);
        gc.strokeLine(coorX - 100 * scale, coorY - 200 * scale, coorX, coorY - 200 * scale);
    }

    /**
     * Dibuja el número seis en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawSix(GraphicsContext gc, double x, double y) {
        gc.strokeLine(coorX - 93 * scale, coorY - 75 * scale, coorX, coorY - 200 * scale);
        gc.strokeOval(coorX - 100 * scale, coorY - 100 * scale, 100 * scale, 100 * scale);
    }

    /**
     * Dibuja el número siete en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawSeven(GraphicsContext gc, double x, double y) {
        gc.strokeLine(coorX, coorY - 200 * scale, coorX - 100 * scale, coorY - 200 * scale);
        gc.strokeLine(coorX, coorY - 200 * scale, coorX - 100 * scale, coorY);
        gc.strokeLine(coorX - 100 * scale, coorY - 200 * scale, coorX - 100 * scale, coorY - 175 * scale);
    }

    /**
     * Dibuja el número ocho en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawEight(GraphicsContext gc, double x, double y) {
        gc.strokeOval(coorX - 100 * scale, coorY - 200 * scale, 100 * scale, 100 * scale);
        gc.strokeOval(coorX - 100 * scale, coorY - 100 * scale, 100 * scale, 100 * scale);
    }

    /**
     * Dibuja el número nueve en el lienzo del canvas en una posición determinada.
     *
     * @param gc   El contexto gráfico del canvas.
     * @param x    La coordenada X de la posición del dibujo.
     * @param y    La coordenada Y de la posición del dibujo.
     */
    public void drawNine(GraphicsContext gc, double x, double y) {
        gc.strokeOval(coorX - 100 * scale, coorY - 200 * scale, 100 * scale, 100 * scale);
        gc.strokeLine(coorX, coorY, coorX, coorY - 150 * scale);
    }

}
