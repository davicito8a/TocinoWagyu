package SortVisualizer;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

    public class NumberDrawer{
        /*
        Constructor que recibe un objeto Canvas que se utiliza 
        para dibujar los números.*/
        
        int CT = 1; // CT = Constante de Tamaño(números)
        GraphicsContext gc;
        int coordenadaX = 10;
        int coordenadaY = 20;
        
        
     public NumberDrawer(Canvas reciboRetangul){
         
         gc = reciboRetangul.getGraphicsContext2D();
         
   
    }
public void drawNumber(int cons, int quantity) {
    /*
     dibuja el dígito dado en las coordenadas x e y que se le pasan como argumentos. 
    Usa un switch para llamar a los métodos */
    CT = quantity;
    
    

    int digito1 = cons / 10; // primer digito
    int digito2 = cons % 10; // segundo digito
    int coordenadaX2 = coordenadaX + 50; // coordenada para el segundo dígito

    if (cons > 9) {
        dibujarDigito(digito1, coordenadaX, coordenadaY);
        dibujarDigito(digito2, coordenadaX2, coordenadaY);
    } else {
        dibujarDigito(cons, coordenadaX, coordenadaY);
    }
}

public void dibujarDigito(int num, int x, int y) {
    switch (num) {
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
            System.out.println("falla papasito");
            break;
    }
}


    // Números
    public void dibujarCero(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeOval(x/CT, y/CT, 50/CT , 50/CT);
    }

    public void dibujarUno(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeLine((x + 25)/CT, y/CT, (x + 25)/CT, (y + 50)/CT);
        gc.strokeLine((x + 25)/CT,y/CT, (x + 10)/CT, (y + 17)/CT );
        gc.strokeLine((x + 10)/CT,(y + 50)/CT, (x + 40)/CT, (y+50)/CT);
    }

    public void dibujarDos(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5       
        gc.strokeLine(x/CT, y/CT, (x + 35)/CT, y/CT); // Horizontal
        gc.strokeLine((x + 35)/CT, y/CT, (x + 35)/CT, (y + 25)/CT);
        gc.strokeLine((x + 35)/CT, (y + 25)/CT, x/CT, (y + 25)/CT); // Horizontal 2 
        gc.strokeLine(x/CT, (y + 25)/CT, x/CT, (y + 50)/CT);
        gc.strokeLine(x/CT, (y + 50)/CT, (x + 35)/CT, (y + 50)/CT); // Horizontal 3
    }

public void dibujarTres(GraphicsContext gc, double x, double y) {
    gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
    gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
    double ancho = 40;
    gc.strokeLine(x/CT, y/CT, (x + ancho)/CT, y/CT);
    gc.strokeLine((x + ancho)/CT, y/CT, (x + ancho)/CT, (y + 25)/CT);
    gc.strokeLine((x + ancho)/CT, (y + 25)/CT, x/CT, (y + 25)/CT);
    gc.strokeLine((x + ancho)/CT, (y + 25)/CT, (x + ancho)/CT, (y + 50)/CT);
    gc.strokeLine(x/CT, (y + 50)/CT, (x + ancho)/CT, (y + 50)/CT);
}


    public void dibujarCuatro(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeLine(x/CT, y/CT, x/CT, (y + 25)/CT);
        gc.strokeLine(x/CT, (y + 25)/CT, (x + 30)/CT, (y + 25)/CT);
        gc.strokeLine((x + 25)/CT, y/CT, (x + 25)/CT, (y + 50)/CT);
        
    }
    public void dibujarCinco(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeLine(x/CT, y/CT, (x + 35)/CT, y/CT);
        gc.strokeLine(x/CT, y/CT, x/CT , (y + 25)/CT);
        gc.strokeLine((x + 35)/CT, (y + 25)/CT, x/CT, (y + 25)/CT);
        gc.strokeLine((x + 35)/CT, (y + 25)/CT, (x + 35)/CT, (y + 50)/CT);
        gc.strokeLine(x/CT, (y + 50)/CT, (x + 35)/CT, (y + 50)/CT);
    }

    public void dibujarSeis(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeOval(x/CT, (y + 18)/CT, 35/CT, 35/CT);
        gc.strokeLine((x + 16)/CT  , y/CT , (x+1)/CT, (y + 30)/CT);
    }

    public void dibujarSiete(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeLine(x/CT, y/CT, (x + 50)/CT, y/CT);
        gc.strokeLine((x + 50)/CT, y/CT, x/CT, (y + 50)/CT);
    }

    public void dibujarOcho(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeOval(x/CT, (y + 20)/CT, 35/CT, 35/CT);
        gc.strokeOval((x + 6)/CT, (y - 0.5)/CT, 20/CT, 20/CT); // circulo más pequeño
    }

    public void dibujarNueve(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.WHITE);  // Establecer el color de trazo en blanco
        gc.setLineWidth(1.5);  // Establecer el grosor de línea en 1.5
        gc.strokeOval(x/CT, y/CT, 35/CT, 35/CT);
        gc.strokeLine((x + 35)/CT, (y + 55)/CT, (x + 35)/CT, (y + 20)/CT);
    }
    }