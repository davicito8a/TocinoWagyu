package SortVisualizerCore;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

    public class NumberDrawer{
        GraphicsContext gc;
        int coordenadaX = 10;
        int coordenadaY = 20;
        int scale = 1; 

    public NumberDrawer(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
    }
    
    public void drawNumber(int cons, int scale) {
        this.scale = scale;
    
        int digito1 = cons / 10;
        int digito2 = cons % 10;
        int coordenadaX2 = coordenadaX + 50;

        if (cons > 9) {
            dibujarDigito(digito1, coordenadaX, coordenadaY);
            dibujarDigito(digito2, coordenadaX2, coordenadaY);
        } else {
            dibujarDigito(cons, coordenadaX, coordenadaY);
        }
    }

    public void dibujarDigito(int number, int x, int y) {
        switch (number) {
            case 0:
                dibujarCero(gc, x, y);
                break;
            case 1:
                dibujarUno(gc, x, y);
                break;
            case 2:
                dibujarDos(gc, x, y);
                break;
            case 3:
                dibujarTres(gc, x, y);
                break;
            case 4:
                dibujarCuatro(gc, x, y);
                break;
            case 5:
                dibujarCinco(gc, x, y);
                break;
            case 6:
                dibujarSeis(gc, x, y);
                break;
            case 7:
                dibujarSiete(gc, x, y);
                break;
            case 8:
                dibujarOcho(gc, x, y);
                break;
            case 9:
                dibujarNueve(gc, x, y);
                break;
            default:
                break;
        }
    }

    public void dibujarCero(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeOval(x/scale, y/scale, 50/scale , 50/scale);
    }

    public void dibujarUno(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  
        gc.setLineWidth(1.5); 
        gc.strokeLine((x + 25)/scale, y/scale, (x + 25)/scale, (y + 50)/scale);
        gc.strokeLine((x + 25)/scale,y/scale, (x + 10)/scale, (y + 17)/scale );
        gc.strokeLine((x + 10)/scale,(y + 50)/scale, (x + 40)/scale, (y+50)/scale);
    }

    public void dibujarDos(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  
        gc.setLineWidth(1.5);      
        gc.strokeLine(x/scale, y/scale, (x + 35)/scale, y/scale); // Horizontal
        gc.strokeLine((x + 35)/scale, y/scale, (x + 35)/scale, (y + 25)/scale);
        gc.strokeLine((x + 35)/scale, (y + 25)/scale, x/scale, (y + 25)/scale); // Horizontal 2 
        gc.strokeLine(x/scale, (y + 25)/scale, x/scale, (y + 50)/scale);
        gc.strokeLine(x/scale, (y + 50)/scale, (x + 35)/scale, (y + 50)/scale); // Horizontal 3
    }

    public void dibujarTres(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE); 
        gc.setLineWidth(1.5); 
        double ancho = 40;
        gc.strokeLine(x/scale, y/scale, (x + ancho)/scale, y/scale);
        gc.strokeLine((x + ancho)/scale, y/scale, (x + ancho)/scale, (y + 25)/scale);
        gc.strokeLine((x + ancho)/scale, (y + 25)/scale, x/scale, (y + 25)/scale);
        gc.strokeLine((x + ancho)/scale, (y + 25)/scale, (x + ancho)/scale, (y + 50)/scale);
        gc.strokeLine(x/scale, (y + 50)/scale, (x + ancho)/scale, (y + 50)/scale);
    }

    public void dibujarCuatro(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeLine(x/scale, y/scale, x/scale, (y + 25)/scale);
        gc.strokeLine(x/scale, (y + 25)/scale, (x + 30)/scale, (y + 25)/scale);
        gc.strokeLine((x + 25)/scale, y/scale, (x + 25)/scale, (y + 50)/scale);
        
    }
    public void dibujarCinco(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeLine(x/scale, y/scale, (x + 35)/scale, y/scale);
        gc.strokeLine(x/scale, y/scale, x/scale , (y + 25)/scale);
        gc.strokeLine((x + 35)/scale, (y + 25)/scale, x/scale, (y + 25)/scale);
        gc.strokeLine((x + 35)/scale, (y + 25)/scale, (x + 35)/scale, (y + 50)/scale);
        gc.strokeLine(x/scale, (y + 50)/scale, (x + 35)/scale, (y + 50)/scale);
    }

    public void dibujarSeis(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeOval(x/scale, (y + 18)/scale, 35/scale, 35/scale);
        gc.strokeLine((x + 16)/scale  , y/scale , (x+1)/scale, (y + 30)/scale);
    }

    public void dibujarSiete(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeLine(x/scale, y/scale, (x + 50)/scale, y/scale);
        gc.strokeLine((x + 50)/scale, y/scale, x/scale, (y + 50)/scale);
    }

    public void dibujarOcho(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeOval(x/scale, (y + 20)/scale, 35/scale, 35/scale);
        gc.strokeOval((x + 6)/scale, (y - 0.5)/scale, 20/scale, 20/scale); // circulo más pequeño
    }

    public void dibujarNueve(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeOval(x/scale, y/scale, 35/scale, 35/scale);
        gc.strokeLine((x + 35)/scale, (y + 55)/scale, (x + 35)/scale, (y + 20)/scale);
    }
}