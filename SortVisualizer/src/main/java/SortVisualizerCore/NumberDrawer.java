package SortVisualizerCore;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

    public class NumberDrawer{
        GraphicsContext gc;
        int coordinateX = 20;
        int coordinateY = 20;
        int scale;

    public NumberDrawer(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
    }
    
    public void drawNumber(int number, int scale) {
        this.scale = scale;
    
        int digit1 = number / 10;
        int digit2 = number % 10;
        int space = 50;

        if (number > 9) {
            drawDigit(digit1, coordinateX, coordinateY);
            drawDigit(digit2, coordinateX + space, coordinateY);
        } else {
            drawDigit(number, coordinateX, coordinateY);
        }
    }

    public void drawDigit(int number, int x, int y) {
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
        gc.setStroke(Color.BLACK); 
        gc.setLineWidth(1.5); 
        gc.strokeOval(x/scale, y/scale, 50/scale , 50/scale);
    }

    public void drawOne(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK);  
        gc.setLineWidth(1.5); 
        gc.strokeLine((x + 25)/scale, y/scale, (x + 25)/scale, (y + 50)/scale);
        gc.strokeLine((x + 25)/scale,y/scale, (x + 10)/scale, (y + 17)/scale );
        gc.strokeLine((x + 10)/scale,(y + 50)/scale, (x + 40)/scale, (y+50)/scale);
    }

    public void drawTwo(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK);  
        gc.setLineWidth(1.5);      
        gc.strokeLine(x/scale, y/scale, (x + 35)/scale, y/scale); 
        gc.strokeLine((x + 35)/scale, y/scale, (x + 35)/scale, (y + 25)/scale);
        gc.strokeLine((x + 35)/scale, (y + 25)/scale, x/scale, (y + 25)/scale); 
        gc.strokeLine(x/scale, (y + 25)/scale, x/scale, (y + 50)/scale);
        gc.strokeLine(x/scale, (y + 50)/scale, (x + 35)/scale, (y + 50)/scale); 
    }

    public void drawThree(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK); 
        gc.setLineWidth(1.5); 
        double ancho = 40;
        gc.strokeLine(x/scale, y/scale, (x + ancho)/scale, y/scale);
        gc.strokeLine((x + ancho)/scale, y/scale, (x + ancho)/scale, (y + 25)/scale);
        gc.strokeLine((x + ancho)/scale, (y + 25)/scale, x/scale, (y + 25)/scale);
        gc.strokeLine((x + ancho)/scale, (y + 25)/scale, (x + ancho)/scale, (y + 50)/scale);
        gc.strokeLine(x/scale, (y + 50)/scale, (x + ancho)/scale, (y + 50)/scale);
    }

    public void drawFour(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK);  
        gc.setLineWidth(1.5); 
        gc.strokeLine(x/scale, y/scale, x/scale, (y + 25)/scale);
        gc.strokeLine(x/scale, (y + 25)/scale, (x + 30)/scale, (y + 25)/scale);
        gc.strokeLine((x + 25)/scale, y/scale, (x + 25)/scale, (y + 50)/scale);
        
    }
    public void drawFive(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK); 
        gc.setLineWidth(1.5);  
        gc.strokeLine(x/scale, y/scale, (x + 35)/scale, y/scale);
        gc.strokeLine(x/scale, y/scale, x/scale , (y + 25)/scale);
        gc.strokeLine((x + 35)/scale, (y + 25)/scale, x/scale, (y + 25)/scale);
        gc.strokeLine((x + 35)/scale, (y + 25)/scale, (x + 35)/scale, (y + 50)/scale);
        gc.strokeLine(x/scale, (y + 50)/scale, (x + 35)/scale, (y + 50)/scale);
    }

    public void drawSix(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK); 
        gc.setLineWidth(1.5);  
        gc.strokeOval(x/scale, (y + 18)/scale, 35/scale, 35/scale);
        gc.strokeLine((x + 16)/scale  , y/scale , (x+1)/scale, (y + 30)/scale);
    }

    public void drawSeven(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK); 
        gc.setLineWidth(1.5);  
        gc.strokeLine(x/scale, y/scale, (x + 50)/scale, y/scale);
        gc.strokeLine((x + 50)/scale, y/scale, x/scale, (y + 50)/scale);
    }

    public void drawEight(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK);  
        gc.setLineWidth(1.5);  
        gc.strokeOval(x/scale, (y + 20)/scale, 35/scale, 35/scale);
        gc.strokeOval((x + 6)/scale, (y - 0.5)/scale, 20/scale, 20/scale); 
    }

    public void drawNine(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK); 
        gc.setLineWidth(1.5); 
        gc.strokeOval(x/scale, y/scale, 35/scale, 35/scale);
        gc.strokeLine((x + 35)/scale, (y + 55)/scale, (x + 35)/scale, (y + 20)/scale);
    }
}
