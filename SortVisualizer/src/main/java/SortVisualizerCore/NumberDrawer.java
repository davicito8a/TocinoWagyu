package SortVisualizerCore;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NumberDrawer {
    GraphicsContext gc;
    double coorX;
    double coorY;
    double scale;
    Color numbersColor = Color.BLACK;

    public NumberDrawer(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
    }

    public void drawNumber(int number, double scale, double scale2) {
        this.scale = scale;
        coorX = 0.4 * Main.squareDimension * scale2;
        coorY = Main.squareDimension / 1.4 * 0.8 * scale2;
        gc.setLineWidth(2);

        int digit1 = number / 10;
        int digit2 = number % 10;

        if (number > 9) {
            drawDigit(digit1, coorX, coorY);
            coorX = 0.7 * Main.squareDimension * scale2;
            drawDigit(digit2, coorX, coorY);
        } else {
            coorX = 0.5 * Main.squareDimension * scale2;
            drawDigit(number, coorX, coorY);
        }
    }

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

    public void drawZero(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX - 50 * scale, coorY, coorX - 50 * scale, coorY - 200 * scale); // left vertical line
        gc.strokeLine(coorX + 50 * scale, coorY, coorX + 50 * scale, coorY - 200 * scale); // right vertical line
        gc.strokeLine(coorX - 50 * scale, coorY, coorX + 50 * scale, coorY); // top horizontal line
        gc.strokeLine(coorX - 50 * scale, coorY - 200 * scale, coorX + 50 * scale, coorY - 200 * scale); // bottom horizontal line
    }

    public void drawOne(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX, coorY, coorX, coorY - 200 * scale);
        gc.strokeLine(coorX, coorY - 200 * scale, coorX - 50 * scale, coorY - 200 * scale + 50 * scale); //diagonal
    }

    public void drawTwo(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX, coorY, coorX - 100 * scale, coorY);
        gc.strokeLine(coorX, coorY - 200 * scale, coorX - 100 * scale, coorY - 200 * scale);
        gc.strokeLine(coorX, coorY - 100 * scale, coorX, coorY - 200 * scale);
        gc.strokeLine(coorX - 100 * scale, coorY, coorX - 100 * scale, coorY - 100 * scale);
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX, coorY - 100 * scale);

    }

    public void drawThree(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX, coorY, coorX - 100 * scale, coorY); // horizontal
        gc.strokeLine(coorX, coorY, coorX, coorY - 100 * scale); // vertical
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX, coorY - 100 * scale); // horizontal
        gc.strokeLine(coorX, coorY - 100 * scale, coorX, coorY - 200 * scale); //vertical
        gc.strokeLine(coorX - 100 * scale, coorY - 200 * scale, coorX, coorY - 200 * scale); // horizontal
    }

    public void drawFour(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX, coorY, coorX, coorY - 100 * scale); // vertical
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX, coorY - 100 * scale); // horizontal
        gc.strokeLine(coorX, coorY - 100 * scale, coorX, coorY - 200 * scale); // vertical
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX - 100 * scale, coorY - 200 * scale); // vertical
    }

    public void drawFive(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX, coorY, coorX - 100 * scale, coorY);//horizontal
        gc.strokeLine(coorX, coorY, coorX, coorY - 100 * scale); // vertical 
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX, coorY - 100 * scale); // horizontal 
        gc.strokeLine(coorX - 100 * scale, coorY - 100 * scale, coorX - 100 * scale, coorY - 200 * scale);
        gc.strokeLine(coorX, coorY - 200 * scale, coorX - 100 * scale, coorY - 200 * scale);
    }

    public void drawSix(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX - 100 * scale, coorY, coorX - 100 * scale, coorY - 200 * scale); // vertical line MAINONE
        gc.strokeLine(coorX, coorY - 200 * scale, coorX - 100 * scale, coorY - 200 * scale); // horizontal line
        gc.strokeLine(coorX - 100 * scale, coorY, coorX, coorY); // horizontal line
        gc.strokeLine(coorX, coorY, coorX, coorY - 100 * scale); // vertical line        
        gc.strokeLine(coorX, coorY - 100 * scale, coorX - 100 * scale, coorY - 100 * scale); // horizontal line      
        gc.strokeLine(coorX - 100 * scale, coorY - 300 * scale, coorX, coorY - 300 * scale); // horizontal line
    }

    public void drawSeven(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX - 100 * scale, coorY, coorX, coorY - 200 * scale); // diagonal line
        gc.strokeLine(coorX, coorY - 200 * scale, coorX - 80 * scale, coorY - 200 * scale); // horizontal line
    }

    public void drawEight(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX - 50 * scale, coorY, coorX - 50 * scale, coorY - 200 * scale); // left vertical line
        gc.strokeLine(coorX + 50 * scale, coorY, coorX + 50 * scale, coorY - 200 * scale); // right vertical line
        gc.strokeLine(coorX - 50 * scale, coorY, coorX + 50 * scale, coorY); // top horizontal line
        gc.strokeLine(coorX - 50 * scale, coorY / 1.5, coorX + 50 * scale, coorY / 1.5); // middle horizontal line
        gc.strokeLine(coorX - 50 * scale, coorY - 200 * scale, coorX + 50 * scale, coorY - 200 * scale); // bottom horizontal line
    }

    public void drawNine(GraphicsContext gc, double x, double y) {
        gc.setFill(numbersColor);

        gc.strokeLine(coorX + 50 * scale, coorY, coorX + 50 * scale, coorY - 200 * scale); // right vertical line
        gc.strokeLine(coorX - 10 * scale, coorY - 200 * scale, coorX + 50 * scale, coorY - 200 * scale); // top horizontal line
        gc.strokeLine(coorX - 10 * scale, coorY - 100 * scale, coorX + 50 * scale, coorY - 100 * scale); // middle horizontal line
        gc.strokeLine(coorX - 10 * scale, coorY - 200 * scale, coorX - 10 * scale, coorY - 100 * scale); // left vertical line
    }

}
